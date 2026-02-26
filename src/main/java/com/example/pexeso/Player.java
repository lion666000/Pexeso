package com.example.pexeso;

public class Player {
    String name;
    int pulls = 0;
    int body = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPulls() {
        return pulls;
    }

    public int getBody() {
        return body;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPulls(int pulls) {
        this.pulls = pulls;
    }

    public void setBody(int body) {
        this.body = body;
    }

    public int pridatDvojici() {
        pulls++;
        return pulls;
    }

    public int pridatBody(int i) {
        body += i;
        return body;
    }
}
