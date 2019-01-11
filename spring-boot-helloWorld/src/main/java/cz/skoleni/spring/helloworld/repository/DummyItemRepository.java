package cz.skoleni.spring.helloworld.repository;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;
import cz.skoleni.spring.helloworld.entity.*;

@Profile("dummy")
@Repository
public class DummyItemRepository implements ItemRepository {

    public long count() {
        return 123;
    }

    @Override
    public Item findById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Item> findAll() {
        throw new UnsupportedOperationException();
    }

}
