package com.unlimint.parser.Converter;


import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.unlimint.parser.Entity.Order;
import com.unlimint.parser.DTO.OrderDTO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class CsvJsonConverter {
    private static List<OrderDTO> list = new ArrayList<>();
    private static int idCounter = 1;
    private static Object lock = new Object();

    public static List<OrderDTO> getList() {
        return list;
    }

    public static void jsonToOrderDTO(File jsonFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        MappingIterator<Order> orderMappingIterator = objectMapper.readerFor(Order.class).readValues(jsonFile);
        orderIterator(orderMappingIterator, list, jsonFile.getName());
    }

    public static void csvToOrderDTO(File csvFile) throws IOException {
        CsvSchema orderSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        MappingIterator<Order> orderMappingIterator = csvMapper.readerFor(Order.class).with(orderSchema).readValues(csvFile);
        orderIterator(orderMappingIterator, list, csvFile.getName());
    }

    private static void orderIterator(MappingIterator<Order> orderMappingIterator, List<OrderDTO> orderDTOList, String filename) {
        int lineCounter = 1;
        while (orderMappingIterator.hasNext()) {
            synchronized (lock) {
                try {
                    Order o = orderMappingIterator.next();
                    orderDTOList.add(new OrderDTO(idCounter++, o.getAmount(), o.getCurrency(), o.getComment(), filename, lineCounter++, "OK"));
                } catch (RuntimeJsonMappingException e) {
                    OrderDTO exOrder = new OrderDTO(idCounter++, -1, null, null, filename, lineCounter++, e.getMessage());
                    orderDTOList.add(exOrder);
                }
            }
        }
    }
}
