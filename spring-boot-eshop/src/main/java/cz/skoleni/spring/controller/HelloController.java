package cz.skoleni.spring.controller;

import java.io.*;
import java.nio.file.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import cz.skoleni.spring.pojo.*;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public Message message(){
        return new Message("Spring Boot works !!!");
    }

    @GetMapping(path = "/dog", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] dog() throws IOException {
        return Files.readAllBytes(Paths.get("images/dog.jpg"));
    }

    @GetMapping(path = "/file")
    public ResponseEntity<Object> getFile(@RequestParam String name) throws IOException {

        MediaType mediaType = MediaType.IMAGE_JPEG;
        if(name.endsWith(".png")){
            mediaType = MediaType.IMAGE_PNG;
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(mediaType)
                .body(Files.readAllBytes(Paths.get("images", name)));

    }

}
