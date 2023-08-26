package cv.user.api.controller;

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
import java.text.SimpleDateFormat;
import java.util.Date;

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
        String filePath ="download/";
        if (program.equals("core-account.jar")) {
            String path = filePath+program;
            File file = new File(path);
            if (file.exists()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String lastModified = dateFormat.format(new Date(file.lastModified()));
                return Mono.just(lastModified);
            }
        }
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
