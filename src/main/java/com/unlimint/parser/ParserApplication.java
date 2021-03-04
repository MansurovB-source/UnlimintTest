package com.unlimint.parser;

import com.unlimint.parser.Service.OrderDtoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class ParserApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(ParserApplication.class, args);
        OrderDtoService dto = applicationContext.getBean(OrderDtoService.class);
        dto.convert();
        dto.show();
    }

}
