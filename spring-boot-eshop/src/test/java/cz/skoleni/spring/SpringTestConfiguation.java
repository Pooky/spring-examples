package cz.skoleni.spring;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.web.client.*;

@Configuration
public class SpringTestConfiguation {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
