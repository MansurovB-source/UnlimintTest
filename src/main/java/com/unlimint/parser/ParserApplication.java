package com.unlimint.parser;

import com.unlimint.parser.Converter.CsvJsonConverter;
import com.unlimint.parser.DTO.OrderDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class ParserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParserApplication.class, args);
        String csvFile = args[0].toString();
        String jsonFile = args[1].toString();

        Runnable task_1 = () -> {
            try {
                CsvJsonConverter.jsonToOrderDTO(new File(jsonFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Runnable task_2 = () -> {
            try {
                CsvJsonConverter.csvToOrderDTO(new File(csvFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Thread thread_1 = new Thread(task_1);
        Thread thread_2 = new Thread(task_2);
        thread_1.start();
        thread_2.start();
        try {
            thread_1.join();
            thread_2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (OrderDTO o : CsvJsonConverter.getList()) {
            System.out.println(o.toString());
        }
    }

}
