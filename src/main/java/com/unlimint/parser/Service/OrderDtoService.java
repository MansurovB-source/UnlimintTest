package com.unlimint.parser.Service;

import com.unlimint.parser.Converter.CsvJsonConverter;
import com.unlimint.parser.DTO.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */

@Component
public class OrderDtoService {
    private CsvJsonConverter converter;

    @Value("${fileCsv}")
    String fileCsv;

    @Value("${fileJson}")
    String fileJson;

    @Autowired
    public OrderDtoService(CsvJsonConverter converter) {
        this.converter = converter;
    }

    public void convert() {
        try {
            converter.jsonToOrderDTO(new File(fileJson));
            converter.csvToOrderDTO(new File(fileCsv));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void show() {
        for (OrderDTO o : converter.getList()) {
            System.out.println(o);
        }
    }

    //other methods
}
