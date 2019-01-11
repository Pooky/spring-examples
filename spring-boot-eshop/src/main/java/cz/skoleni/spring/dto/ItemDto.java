package cz.skoleni.spring.dto;

import java.util.*;
import cz.skoleni.spring.entity.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ItemDto {

    private int id;

    private String name;

    private double price;

    private List<OrderedItemDto> orderedItems;

}
