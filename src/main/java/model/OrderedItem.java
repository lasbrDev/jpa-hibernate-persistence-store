package model;

import javax.persistence.*;
import java.math.BigDecimal;

    @Entity
    @Table(name = "ordered_items")
    public class OrderedItem {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "unit_price")
        private BigDecimal unitPrice;
        private int quantity;
        @ManyToOne
        private Order order;
        @ManyToOne
        private Product product;

        public OrderedItem() {
        }
        public OrderedItem(int quantity, Order order, Product product) {
            this.quantity = quantity;
            this.order = order;
            this.unitPrice = product.getPrice();
            this.product = product;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public BigDecimal getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public Order getOrder() {
            return order;
        }

        public void setOrder(Order order) {
            this.order = order;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }
    }
