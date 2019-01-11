package cz.skoleni.spring.repository;

import java.util.*;
import cz.skoleni.spring.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.rest.core.annotation.*;

@RepositoryRestResource(collectionResourceRel = "items", path = "items")
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query("select i from Item i left join fetch i.orderedItems where i.id = ?1")
    Optional<Item> findByIdFetchOrderedItems(int id);

    @Query("select i from Item i left join fetch i.orderedItems")
    List<Item> findAllFetchOrderedItems();
}
