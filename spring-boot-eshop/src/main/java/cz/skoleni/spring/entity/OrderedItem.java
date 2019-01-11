package cz.skoleni.spring.entity;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "ORDEREDITEM")
public class OrderedItem {

    @Id
    @Column(name = "ORDEREDITEMID")
    private int id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

}
