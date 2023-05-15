package model;

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
        private BigDecimal amount;
        private LocalDate date = LocalDate.now();

        @ManyToOne
        private Client client;

        @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
        private List<OrderedItem> items = new ArrayList<>();

        public Order() {
        }

        public Order(Client client) {
            this.client = client;
        }

        public void addItem(OrderedItem item) {
            item.setOrder(this);
            this.items.add(item);
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
