package cz.skoleni.spring.entity;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Item {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Name cannot be empty")
    @Size(min = 1)
    private String name;

    @Min(1)
    private double price;

    @OneToMany(mappedBy = "item")
    private List<OrderedItem> orderedItems;

}
