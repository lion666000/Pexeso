package com.example.pexeso;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class HelloController {
    private ArrayList<Card> cardsField = new ArrayList<Card>();

    int firstFlipped = -1;
    int secondFlipped = -1;

    @FXML
    private GridPane gridPane;

    @FXML
    protected void handleCardClick(Card card){

        if (!card.isMatched()) {
            if (firstFlipped == -1) {
                card.flip();
                firstFlipped = card.getId();
            }
            else if (firstFlipped != -1 && secondFlipped == -1) {
                card.flip();
                secondFlipped = card.getId();
            }


            if (firstFlipped == secondFlipped) {

                card.setMatched(true);
                firstFlipped = 0;
                secondFlipped = 0;

                for (Card c : cardsField) {
                    if (c.getId() == card.getId()) {
                        c.setMatched(true);
                        c.unflip();
                    }
                }
            }
            else if (firstFlipped != secondFlipped && firstFlipped != -1 && secondFlipped != -1) {
                firstFlipped = 0;
                secondFlipped = 0;
                for (Card c : cardsField) {
                    if (c.getId() == card.getId()) {
                        c.unflip();
                    }
                }
            }
            else {
                System.out.println("Vyber dalsi");
            }
        }
        else {
            System.out.println("Karta uz se rovna jine");
        }

    }

    @FXML
    protected void generateButtons(){
        for (int i = 0; i < 8; i++){
            cardsField.add(new Card(i));
            cardsField.add(new Card(i));
        }

        for (Card card : cardsField){
            System.out.println(card);
        }
    }

    @FXML
    protected void displayButtons(){
        int collumn = 0;
        int row = 0;

        for  (Card card : cardsField){
            gridPane.add(card.getButton(), collumn, row);
            card.getButton().setOnAction(event -> handleCardClick(card));

            collumn++;

            if (collumn == 4){
                row++;
                collumn = 0;
            }
        }
    }



    @FXML
    protected void initialize() {
        generateButtons();
        displayButtons();
    }
}
