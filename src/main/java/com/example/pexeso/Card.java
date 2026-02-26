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
        //button.setText(String.valueOf(id));
        button.setText("");

        flipped = true;

        String link = "/images/x.jpg";
        switch(id){
            case 0:
                link = "/images/ostrich.jpg";
                break;
            case 1:
                link = "/images/tiger.jpg";
                break;
            case 2:
                link = "/images/digger.jpg";
                break;
            case 3:
                link = "/images/iguana.jpg";
                break;
            case 4:
                link = "/images/plank.jpg";
                break;
            case 5:
                link = "/images/gripen.jpg";
                break;
            case 6:
                link = "/images/redwood.jpg";
                break;
            case 7:
                link = "/images/unnamed.jpg";
                break;
            case 8:
                link = "/images/lightning.jpg";
                break;
            case 9:
                link = "/images/apple.jpg";
                break;
            case 10:
                link = "/images/up.jpg";
                break;
            case 11:
                link = "/images/star.jpg";
                break;
            case 69:
                link = "/images/special.jpg";
                break;
        }


        button.setStyle(
                "-fx-background-image: url('" +
                        getClass().getResource(link).toExternalForm() +
                        "');" +
                        "-fx-background-radius: 8;" +
                        "-fx-background-size: cover;"

        );
    }

    public void unflip(){
        button.setText("?");
        flipped = false;
        if (matched) {
            //button.setText(String.valueOf(id));
            button.setText("");
            button.setDisable(true);
        }
        else {
            button.setStyle("-fx-background-image: none;"+"-fx-background-radius: 8;");
        }
    }

    public String toString(){
        return "" + id;
    }

}
