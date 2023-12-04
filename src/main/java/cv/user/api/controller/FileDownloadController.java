package cv.user.api.controller;

import cv.user.api.common.ReturnObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@CrossOrigin
@RestController
@RequestMapping("/download")
public class FileDownloadController {
    @GetMapping("/program")
    public Mono<ResponseEntity<?>> downloadProgram(@RequestParam String program) {
        String filePath = "download/" + program; // Path to the jar file on the server
        File file = new File(filePath);
        return Mono.defer(() -> {
            if (!file.exists()) {
                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(ContentDisposition.builder("inline").filename(program).build());
            //headers.setContentDispositionFormData("attachment", "core-account.jar");

            Resource resource = new FileSystemResource(file);

            return Mono.just(new ResponseEntity<>(resource, headers, HttpStatus.OK));
        }).subscribeOn(Schedulers.boundedElastic());
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
