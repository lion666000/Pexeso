package com.example.pexeso;

public class Player {
    String name;
    int pocetDvojic = 0;
    int body = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPocetDvojic() {
        return pocetDvojic;
    }

    public int getBody() {
        return body;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPocetDvojic(int pocetDvojic) {
        this.pocetDvojic = pocetDvojic;
    }

    public void setBody(int body) {
        this.body = body;
    }

    public int pridatDvojici() {
        pocetDvojic++;
        return pocetDvojic;
    }

    public int pridatBody() {
        body ++;
        return body;
    }
}
