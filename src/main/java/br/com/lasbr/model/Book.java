package br.com.lasbr.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book extends Product {

    private String author;
    private Integer numbeOfPages;

    public Book() {
    }
    public Book(String author, Integer numbeOfPages) {
        this.author = author;
        this.numbeOfPages = numbeOfPages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getNumbeOfPages() {
        return numbeOfPages;
    }

    public void setNumbeOfPages(Integer numbeOfPages) {
        this.numbeOfPages = numbeOfPages;
    }
}
