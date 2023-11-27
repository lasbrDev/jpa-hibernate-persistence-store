package br.com.lasbr.model;

import javax.persistence.*;

    @Entity
    @Table(name = "categories")
    public class Category {

        @EmbeddedId
        private CategoryId id;
        public Category() {
        }

        public Category(String name) {
            this.id = new CategoryId(name, "xpto");
        }

        public String getName() {
            return this.id.getName();
        }
    }
