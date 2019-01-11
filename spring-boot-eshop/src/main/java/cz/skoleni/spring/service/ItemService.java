package cz.skoleni.spring.service;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import cz.skoleni.spring.dto.*;
import cz.skoleni.spring.entity.*;
import cz.skoleni.spring.exception.*;
import cz.skoleni.spring.mapper.*;
import cz.skoleni.spring.repository.*;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper mapper;

    @Cacheable("items")
    public List<ItemDto> findAll(){
        return mapper.itemsToItemDtos(itemRepository.findAllFetchOrderedItems());
    }
    
    //@Transactional
    public ItemDto findById(int id){

        Item item =  itemRepository.findByIdFetchOrderedItems(id).orElseThrow(() ->new EntityNotFoundException("Item with id "+ id + " není přítomen"));

        return mapper.itemToItemDto(item);
    }

    @CacheEvict(cacheNames = "items", allEntries = true)
    public ItemDto save(ItemDto itemDto){

        Item item = mapper.itemDtoToItem(itemDto);
        Item savedItem = itemRepository.save(item);

        return mapper.itemToItemDto(savedItem);
    }
    
    @CacheEvict(cacheNames = "items", allEntries = true)
    public void deletebyId(int id){
        itemRepository.deleteById(id);
    }

}
