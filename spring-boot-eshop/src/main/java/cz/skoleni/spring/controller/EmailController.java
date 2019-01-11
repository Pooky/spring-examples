package cz.skoleni.spring.controller;

import java.util.*;
import java.util.concurrent.*;
import java.util.logging.*;
import java.util.stream.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.web.bind.annotation.*;
import lombok.*;
import lombok.extern.slf4j.*;

@Slf4j
@RestController
public class EmailController {

    @Lazy
    @Autowired
    private EmailController self;

    @GetMapping("/emails")
    public String sendEmails(){

        log.info("Starting sending email");

        List<Future<Integer>> list =  IntStream.range(0, 10_000)
                .mapToObj(self::sendEmail)
                .collect(Collectors.toList());

        for (Future<Integer> integerFuture : list) {
            try {
                integerFuture.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }


        return "Done";
    }

    @Async
    @SneakyThrows
    public Future<Integer> sendEmail(int i){
        log.info("sending email ... {}", i);
        Thread.sleep(100);

        return new AsyncResult<>(i);

    }

}
