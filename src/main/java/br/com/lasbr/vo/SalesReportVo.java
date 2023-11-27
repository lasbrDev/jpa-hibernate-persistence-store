package br.com.lasbr.vo;

import java.time.LocalDate;

    public class SalesReportVo {

        private String productName;
        private Long soldAmount;
        private LocalDate lastSaleDate;

        public SalesReportVo(String productName, Long soldAmount, LocalDate lastSaleDate) {
            this.productName = productName;
            this.soldAmount = soldAmount;
            this.lastSaleDate = lastSaleDate;
        }

        @Override
        public String toString() {
            return "SalesReportVo{" +
                    "productName='" + productName + '\'' +
                    ", soldAmount=" + soldAmount +
                    ", lastSaleDate=" + lastSaleDate +
                    '}';
        }

        public String getProductName() {
            return productName;
        }

        public Long getSoldAmount() {
            return soldAmount;
        }

        public LocalDate getLastSaleDate() {
            return lastSaleDate;
        }
    }
