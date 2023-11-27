package br.com.lasbr.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

    @Entity
    @Table(name = "orders")
    public class Order {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "amount")
        private BigDecimal amount = BigDecimal.ZERO;
        private LocalDate date = LocalDate.now();

        @ManyToOne(fetch = FetchType.LAZY)
        private Client client;

        @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
        private List<OrderItem> items = new ArrayList<>();

        public Order() {
        }

        public Order(Client client) {
            this.client = client;
        }

        public void addItem(OrderItem item) {
            item.setOrder(this);
            this.items.add(item);
            this.amount = this.amount.add(item.getValue());
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
