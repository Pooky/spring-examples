package cz.skoleni.spring.controller;

import java.util.*;
import javax.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import cz.skoleni.spring.dto.*;
import cz.skoleni.spring.service.*;
import io.swagger.annotations.*;

@Api
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<ItemDto> items(){
        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public ItemDto item(@PathVariable int id){
        return itemService.findById(id);
    }



    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        itemService.deletebyId(id);
    }

    @PostMapping
    public ItemDto insert(@Valid @RequestBody ItemDto item){
        item.setId(0);
        return itemService.save(item);
    }

    @PutMapping("/{id}")
    public ItemDto update(@PathVariable int id, @Valid @RequestBody ItemDto item) {
        item.setId(0);
        return itemService.save(item);
    }

}
