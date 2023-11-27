package br.com.lasbr.model;


import javax.persistence.*;

    @Entity
    @Table(name = "clients")
    public class Client {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Embedded
        private PersonalData personalData;

        public Client(String name, String cpf) {
            this.personalData = new PersonalData(name, cpf);
        }

        public String getName() {
            return this.personalData.getName();
        }

        public String getCpf() {
            return this.personalData.getCpf();
        }

        public Client() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public PersonalData getPersonalData() {
            return personalData;
        }
    }
