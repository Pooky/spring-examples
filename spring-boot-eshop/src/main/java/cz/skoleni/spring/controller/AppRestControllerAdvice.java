package cz.skoleni.spring.controller;

import org.mapstruct.factory.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import cz.skoleni.spring.exception.*;
import cz.skoleni.spring.mapper.*;
import cz.skoleni.spring.pojo.*;


@RestControllerAdvice
public class AppRestControllerAdvice {

    @Bean
    public ItemMapper itemMapper(){
        return Mappers.getMapper(ItemMapper.class);
    }


    @ExceptionHandler
    public ResponseEntity handleENF(EntityNotFoundException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new Message(e.getMessage()));
    }

}
