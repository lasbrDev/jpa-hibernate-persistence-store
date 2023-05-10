package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

    @Entity
    @Table(name = "orders")
    public class Order {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private BigDecimal amount;
        private LocalDate date = LocalDate.now();

        @ManyToOne
        private Client client;

        public Order() {
        }

        public Order(Client client) {
            this.client = client;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public Client getClient() {
            return client;
        }

        public void setClient(Client client) {
            this.client = client;
        }
    }
