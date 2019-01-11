package cz.skoleni.spring.helloworld.service;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;
import cz.skoleni.spring.helloworld.entity.*;
import cz.skoleni.spring.helloworld.repository.*;

@Scope("prototype")
@Service
public class ItemService {

    //@Qualifier("jdbcItemRepository")
    @Autowired
    private ItemRepository itemRepository;

    public ItemService(){
        System.out.println("Creating ItemService " + itemRepository);
    }

    public long count(){
        return itemRepository.count();
    }

    public Item findById(int id){
        return itemRepository.findById(id);
    }

    public List<Item> findAll(){
        return itemRepository.findAll();
    }

}
