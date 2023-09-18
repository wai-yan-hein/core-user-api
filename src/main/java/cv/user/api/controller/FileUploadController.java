package cv.user.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.nio.file.Paths;
@CrossOrigin
@RestController
@RequestMapping("/upload")
public class FileUploadController {
    private static final String DOWNLOAD_DIR = "./download/";
    @PostMapping("/program")
    public Mono<String> uploadFile(@RequestPart("file") Mono<FilePart> filePartMono) {
        return filePartMono
                .flatMap(this::saveFile)
                .switchIfEmpty(Mono.error(new FileUploadException("File upload failed.")))
                .map(filename -> "File uploaded successfully: " + filename);
    }

    private Mono<String> saveFile(FilePart filePart) {
        String fileName = filePart.filename();
        if (fileName.equals("core-account.jar") || fileName.equals("core-account.apk")) {
            Path path = Paths.get(DOWNLOAD_DIR + filePart.filename());
            return filePart.transferTo(path).then(Mono.just(filePart.filename()));
        } else {
            return Mono.error(new FileUploadException("Invalid file name: " + fileName));
        }
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "File upload failed")
    public static class FileUploadException extends RuntimeException {
        public FileUploadException(String message) {
            super(message);
        }
    }

}
