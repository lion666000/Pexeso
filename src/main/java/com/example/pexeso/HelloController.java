package com.example.pexeso;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.ArrayList;

public class HelloController {
    private ArrayList<Card> cardsField = new ArrayList<Card>();
    private ArrayList<Player> players = new ArrayList<Player>();

    Card firstFlipped = null;
    Card secondFlipped = null;

    boolean konec;
    int kolo = 1;
    Player currentPlayer = null;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label player1NameLabel;

    @FXML
    private Label player2NameLabel;

    @FXML
    private Label player1ScoreLabel;
    @FXML
    private Label player2ScoreLabel;

    @FXML
    protected void checkMatch(Card card){
        if (firstFlipped.getId() == secondFlipped.getId()) {
            System.out.println("Matched");

            for (Card c : cardsField) {
                if (c.getId() == card.getId()) {
                    c.setMatched(true);
                    c.unflip();
                }
            }

            firstFlipped = null;
            secondFlipped = null;
            kolo++;
            currentPlayer.pridatBody();

            player1ScoreLabel.setText(players.get(0).getBody() + " B");
            player2ScoreLabel.setText(players.get(1).getBody() + " B");

            for (Card c : cardsField) {
                if (c.matched) {
                    konec = true;
                }
                else {
                    konec = false;
                }
            }

            if (konec) {
                System.out.println("Konec hry");
                System.out.println(players.get(0).getName() + " : " + players.get(0).getPocetDvojic());
                System.out.println(players.get(1).getName() + " : " + players.get(1).getPocetDvojic());
            }
        }
        else if (firstFlipped.getId() != secondFlipped.getId()) {

            for (Card c : cardsField) {
                if (c == firstFlipped ||  c == secondFlipped) {
                    c.unflip();
                    System.out.println(c.getId() + " Unflipped");
                }
            }
            firstFlipped = null;
            secondFlipped = null;
            kolo++;
            System.out.println("not matched");
        }
        else {
            System.out.println("Vyber dalsi");
        }
    }

    @FXML
    protected void handleCardClick(Card card){

        player1NameLabel.setText(players.get(0).getName());
        player2NameLabel.setText(players.get(1).getName());

        if (kolo % 2 == 0){
            currentPlayer = players.get(1); // player 2

            player1NameLabel.setUnderline(false);
            player2NameLabel.setUnderline(true);
        }
        else{
            currentPlayer = players.get(0); // player 1

            player1NameLabel.setUnderline(true);
            player2NameLabel.setUnderline(false);
        }

        //------------------------------------------------------------------------

        if (firstFlipped == null) {
            card.flip();
            firstFlipped = card;

            System.out.println(firstFlipped.getId());
        }
        else if (firstFlipped != null && secondFlipped == null && firstFlipped != card) {
            card.flip();
            secondFlipped = card;

            System.out.println(secondFlipped.getId());

            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> checkMatch(card));
            pause.play();

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


    }

    @FXML
    protected void generatePlayers(){
        players.add(new Player("skibidi 1"));
        players.add(new Player("skibidi 2"));
    }

    @FXML
    protected void displayButtons(){
        int collumn = 0;
        int row = 0;

        for  (Card card : cardsField){
            gridPane.add(card.getButton(), collumn, row);
            card.getButton().setOnAction(e -> handleCardClick(card));

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
        generatePlayers();
    }
}
