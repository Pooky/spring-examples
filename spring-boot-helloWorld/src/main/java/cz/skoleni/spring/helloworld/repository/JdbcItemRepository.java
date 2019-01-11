package cz.skoleni.spring.helloworld.repository;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;
import cz.skoleni.spring.helloworld.entity.*;

@Profile("jdbc")
@Repository
public class JdbcItemRepository implements ItemRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Item> itemRowMapper = (resultSet, i) -> {
        return Item.builder().id(resultSet.getInt("item_id"))
                .name(resultSet.getString("name"))
                .price(resultSet.getInt("price"))
                .build();
    };

    public long count() {
        return jdbcTemplate.queryForObject("select count(*) from item", Long.class);
    }

    @Override
    public Item findById(int id) {
        return jdbcTemplate.queryForObject("select * from item where item_id = ?", itemRowMapper, id);
    }

    @Override
    public List<Item> findAll() {
        return jdbcTemplate.query("select * from item", itemRowMapper);
    }

}