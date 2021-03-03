package com.unlimint.parser.DTO;

import com.unlimint.parser.Entity.Order;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class OrderDTO extends Order {
    private String filename;
    private int line;
    private String result;

    public OrderDTO(int id, int amount, String currency, String comment, String filename, int line, String result) {
        super(id, amount, currency, comment);
        this.filename = filename;
        this.line = line;
        this.result = result;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + getId() +
                ", amount=" + getAmount() +
                ", currency='" + getCurrency() + '\'' +
                ", comment='" + getComment() + '\'' +
                "filename='" + filename + '\'' +
                ", line=" + line +
                ", result='" + result.trim() + '\'' +
                '}';
    }
}
