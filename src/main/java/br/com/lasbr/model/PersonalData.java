package br.com.lasbr.model;

import javax.persistence.Embeddable;

    @Embeddable
    public class PersonalData {

        private String name;
        private String cpf;

        public PersonalData() {
        }

        public PersonalData(String name, String cpf) {
            this.name = name;
            this.cpf = cpf;
        }

        public String getName() {
            return name;
        }
        public String getCpf() {
            return cpf;
        }
    }
