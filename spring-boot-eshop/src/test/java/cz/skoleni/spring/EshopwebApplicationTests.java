package cz.skoleni.spring;

import javax.transaction.*;
import org.hibernate.annotations.common.reflection.java.generics.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.*;
import cz.skoleni.spring.dto.*;
import cz.skoleni.spring.entity.*;
import cz.skoleni.spring.service.*;

import static org.assertj.core.api.Assertions.*;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class EshopwebApplicationTests {

	@Autowired
	private ItemService itemService;
	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void saveItem(){
		ItemDto itemDto = ItemDto.builder()
				.name("name")
				.price(1.0)
				.build();

		ItemDto item = itemService.save(itemDto);
		assertThat(item.getId())
				.isNotZero();

	}


	@Test
	public void contextLoads() {

		assertThat(itemService.findAll()).isNotEmpty();

		assertThat(itemService.findAll())
				.extracting(ItemDto::getId)
				.contains(1, 2, 3);

		ItemDto itemDto = restTemplate.getForObject("http://localhost:9090/item/1", ItemDto.class);
		assertThat(itemDto.getId()).isEqualTo(1);

	}

}
