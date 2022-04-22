package main.java.creational.builder;

public class Card {

    private final String cardType;
    private final String number;
    private final String name;
    private final int expires;
    private final boolean credit;

    public Card(Builder builder){
        this.cardType = builder.cardType;
        this.number = builder.number;
        this.name = builder.name;
        this.expires = builder.expires;
        this.credit = builder.credit;
    }

    public String getCardType() {
        return cardType;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getExpires() {
        return expires;
    }

    public boolean isCredit() {
        return credit;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardType='" + cardType + '\'' +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", expires=" + expires +
                ", credit=" + credit +
                '}';
    }

    /*
    el patron builder permite crear objetos DTO, POJO, etc... de manera más sencilla
    simplemente haciendo uso de una inner class con los mismos atributos de la porpia
    clase para luego devolver una instancia de la clase principal mediante el constructor
    de la misma
     */
    public static class Builder{
        private String cardType;
        private String number;
        private String name;
        private int expires;
        private boolean credit;

        public Builder(String cardType, String number){
            this.cardType = cardType;
            this.number = number;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder expires(int expires){
            this.expires = expires;
            return this;
        }

        public Builder credit(boolean credit){
            this.credit = credit;
            return this;
        }

        public Card buildCard(){
            return new Card(this);
        }
    }

}
