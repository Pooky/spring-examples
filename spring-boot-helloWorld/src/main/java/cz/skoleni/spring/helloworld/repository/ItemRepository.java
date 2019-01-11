package cz.skoleni.spring.helloworld.repository;

import java.util.*;
import cz.skoleni.spring.helloworld.entity.*;

public interface ItemRepository {

    long count();

    Item findById(int id);

    List<Item> findAll();

}
