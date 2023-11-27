package br.com.lasbr.model;

import br.com.lasbr.model.Category;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

    @Entity
    @Table(name = "products")
    @NamedQuery(name = "Product.productsByCategory", query = "SELECT p FROM Product p WHERE p.category.name = :name")
    public class Product {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String descrition;
        private BigDecimal price;
        private LocalDate dateRegister = LocalDate.now();

        @ManyToOne(fetch = FetchType.LAZY)
        private Category category;

        public Product() {
        }

        public Product(String name, String descrition, BigDecimal price, Category category) {
            this.name = name;
            this.descrition = descrition;
            this.price = price;
            this.category = category;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescrition() {
            return descrition;
        }

        public void setDescrition(String descrition) {
            this.descrition = descrition;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public LocalDate getDateRegister() {
            return dateRegister;
        }

        public void setDateRegister(LocalDate dateRegister) {
            this.dateRegister = dateRegister;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }
    }
