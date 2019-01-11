package cz.skoleni.spring.helloworld;

import javax.sql.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.context.annotation.*;
import org.springframework.context.support.*;
import org.springframework.core.io.*;
import org.springframework.jdbc.core.*;
import com.zaxxer.hikari.*;
import cz.skoleni.spring.helloworld.service.*;

@Configuration
@ComponentScan
public class Main {

    @Bean
    public String string(){
        return "hello";
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource, String param){
        System.out.println(param + " world");
        return new JdbcTemplate(dataSource);
    }


    @Bean
    public DataSource dataSource(
            @Value("${db.url}") String url,
            @Value("${db.user}") String username,
            @Value("${db.password}") String password
    ){
        
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;

    }


    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
        PropertySourcesPlaceholderConfigurer configurer
                = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource("config.properties"));
        
        return configurer;
    }



    public static void main(String[] args) {

 
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.getEnvironment().setActiveProfiles("jdbc");
        applicationContext.register(Main.class);
        applicationContext.refresh();


        ItemService itemService = applicationContext.getBean(ItemService.class);

        System.out.println(itemService.count());
        System.out.println(itemService.findById(1));
        System.out.println("***********************");
        itemService.findAll().stream().forEach(System.out::println);


        applicationContext.close();
    }


}
