package cz.skoleni.spring.helloworld.entity;

import lombok.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@Builder
public class Item {

    private int id;
    private String name;
    private int price;


}
