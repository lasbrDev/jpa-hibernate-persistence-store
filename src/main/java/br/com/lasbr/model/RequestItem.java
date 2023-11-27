package br.com.lasbr.model;

import javax.persistence.*;
import java.math.BigDecimal;

    @Entity
    @Table(name = "requested_items")
    public class RequestItem {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "unit_price")
        private BigDecimal unitPrice;
        private int quantity;
        @ManyToOne(fetch = FetchType.LAZY)
        private Order request;
        @ManyToOne(fetch = FetchType.LAZY)
        private Product product;

        public RequestItem() {
        }
        public RequestItem(int quantity, Order request, Product product) {
            this.quantity = quantity;
            this.request = request;
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
            return request;
        }

        public void setOrder(Order request) {
            this.request = request;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public BigDecimal getValue() {
            return unitPrice.multiply(new BigDecimal(quantity));
        }
    }
