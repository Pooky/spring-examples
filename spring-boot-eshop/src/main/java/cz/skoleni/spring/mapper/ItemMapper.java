package cz.skoleni.spring.mapper;

import java.util.*;
import org.mapstruct.*;
import org.mapstruct.factory.*;
import org.springframework.stereotype.*;
import cz.skoleni.spring.dto.*;
import cz.skoleni.spring.entity.*;


@Mapper
public interface ItemMapper {

    //ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    ItemDto itemToItemDto(Item item);

    Item itemDtoToItem(ItemDto itemDto);
    List<ItemDto> itemsToItemDtos(List<Item> items);


   /* default ItemDto itemToItemDto2(Item item){

        ItemDto res = itemToItemDto(item);
        res.setName("Ahoj");

        return res;
    }*/
}

