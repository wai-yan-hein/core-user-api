package cv.user.api.controller;

import cv.user.api.common.ReturnObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/download")
public class FileDownloadController {
    @GetMapping("/program")
    public Mono<ResponseEntity<Resource>> downloadProgram(@RequestParam String program) {
        String filePath = "download/"+program; // Path to the jar file on the server
        File file = new File(filePath);
        if (!file.exists()) {
            return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "core-account.jar");

        Resource resource = new FileSystemResource(file);

        return Mono.just(new ResponseEntity<>(resource, headers, HttpStatus.OK));
    }
    @GetMapping("/getUpdatedProgramDate")
    public Mono<?> getUpdatedProgramDate(@RequestParam String program) {
        String filePath = "download/";
        if (program.equals("core-account.jar")) {
            String path = filePath + program;
            Path file = Paths.get(path);
            try {
                if (Files.exists(file)) {
                    long lastModifiedTimestamp = Files.getLastModifiedTime(file).toMillis();
                    var ro = ReturnObject.builder().timestampUtc(lastModifiedTimestamp).build();
                    return Mono.just(ro);
                }
            } catch (IOException e) {
                // Handle IO exception if needed
            }
        }
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
