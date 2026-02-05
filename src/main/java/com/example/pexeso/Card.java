package com.example.pexeso;

import javafx.scene.control.Button;

public class Card {
    public int id;
    public Button button;
    public boolean matched = false;
    public boolean flipped = false;

    public Card(int id) {
        this.id = id;
        this.button = new Button("?");
        this.button.setMinSize(60, 60);
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public int getId() {
        return id;
    }

    public Button getButton() {
        return button;
    }

    public boolean getFlipped() {
        return flipped;
    }

    public boolean isMatched() {
        return matched;
    }

    public void flip(){
        button.setText(String.valueOf(id));
        flipped = true;
    }

    public void unflip(){
        button.setText("?");
        flipped = false;
        if (matched) {
            button.setText(String.valueOf(id));
            button.setDisable(true);
        }
    }

    public String toString(){
        return "" + id;
    }

}
