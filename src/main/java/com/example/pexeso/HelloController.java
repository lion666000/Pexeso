package com.example.pexeso;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;

public class HelloController {
    private ArrayList<Card> cardsField = new ArrayList<Card>();
    private ArrayList<Player> players = new ArrayList<Player>();

    Card firstFlipped = null;
    Card secondFlipped = null;

    boolean konec;
    int kolo = 1;
    Player currentPlayer = null;

    int nextPlayer = 0;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label player1NameLabel;
    @FXML
    private Label player2NameLabel;
    @FXML
    private Label player3NameLabel;

    @FXML
    private Label player1ScoreLabel;
    @FXML
    private Label player2ScoreLabel;
    @FXML
    private Label player3ScoreLabel;

    @FXML
    private Label player1PullsLabel;
    @FXML
    private Label player2PullsLabel;
    @FXML
    private Label player3PullsLabel;

    @FXML
    private Button konecButton;

    @FXML
    private Button resetButton;

    @FXML
    private void handleResetButton(){
        System.err.println("Hra se resetuje");

        cardsField = new ArrayList<Card>();
        players = new ArrayList<Player>();

        firstFlipped = null;
        secondFlipped = null;

        konec = false;
        kolo = 1;
        currentPlayer = null;


        initialize();
    };

    @FXML
    private void handleShowKonec() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "" , ButtonType.FINISH);
        alert.setTitle("Finito");
        alert.setHeaderText("Konec hry");
        alert.setContentText(
                players.get(0).getName() + " score: " + players.get(0).getBody() + " tahy: " + players.get(0).getPulls() + "\n" +
                players.get(1).getName() + " score: " + players.get(1).getBody() + " tahy: " + players.get(1).getPulls() + "\n" +
                players.get(2).getName() + " score: " + players.get(2).getBody() + " tahy: " + players.get(2).getPulls()
        );



        alert.showAndWait();
    }

    @FXML
    private void handleKonec(){
        System.out.println("Konec hry");

        for (int i = 0; i < players.size(); i++) {
            System.out.println(players.get(i).getName() + " Body: " + players.get(i).getBody() + " Tahy: " + players.get(i).getPulls());
        }

        handleShowKonec();

        cardsField = new ArrayList<Card>();
        players = new ArrayList<Player>();

        firstFlipped = null;
        secondFlipped = null;

        konec = false;
        kolo = 1;
        currentPlayer = null;


        initialize();
    }


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
            currentPlayer.pridatBody(1);
            currentPlayer.pridatDvojici();

            player1ScoreLabel.setText(players.get(0).getBody() + " B");
            player2ScoreLabel.setText(players.get(1).getBody() + " B");
            player3ScoreLabel.setText(players.get(2).getBody() + " B");

            player1PullsLabel.setText(players.get(0).getPulls() + " Tahů");
            player2PullsLabel.setText(players.get(1).getPulls() + " Tahů");
            player3PullsLabel.setText(players.get(2).getPulls() + " Tahů");




        }
        else if (firstFlipped.getId() != secondFlipped.getId()) {

            if (nextPlayer < 2) {
                nextPlayer++;
            }
            else {
                nextPlayer = 0;
            }

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

        for (Card c : cardsField) {
            if (c.matched) {
                konec = true;
            }
            else {
                konec = false;
                break;
            }
        }

        //---------------------------------------------------------------------------------------------------Konec

        if (konec) {
            konecButton.setDisable(false);
        }
    }

    @FXML
    protected void handleSpecialCard(Card card) {
        currentPlayer.pridatBody(3);
        currentPlayer.pridatDvojici(); // hráč má šanci otočit dě karty takže se to počítá jako tah 🦆🦆🦆🦆🦛🦛🦛🦛
        card.setMatched(true);
        kolo++;

        firstFlipped.unflip();
        secondFlipped.unflip();

        firstFlipped = null;
        secondFlipped = null;

        player1ScoreLabel.setText(players.get(0).getBody() + " B");
        player2ScoreLabel.setText(players.get(1).getBody() + " B");
        player3ScoreLabel.setText(players.get(2).getBody() + " B");

        player1PullsLabel.setText(players.get(0).getPulls() + " Tahů");
        player2PullsLabel.setText(players.get(1).getPulls() + " Tahů");
        player3PullsLabel.setText(players.get(2).getPulls() + " Tahů");
    }

    @FXML
    protected void handleCardClick(Card card){

        currentPlayer = players.get(nextPlayer);

        if (nextPlayer == 0) {
            player1NameLabel.setUnderline(true);
            player2NameLabel.setUnderline(false);
            player3NameLabel.setUnderline(false);
        }
        else if (nextPlayer == 1) {
            player1NameLabel.setUnderline(false);
            player2NameLabel.setUnderline(true);
            player3NameLabel.setUnderline(false);
        }
        else if (nextPlayer == 2) {
            player1NameLabel.setUnderline(false);
            player2NameLabel.setUnderline(false);
            player3NameLabel.setUnderline(true);
        }

        //------------------------------------------------------------------------

        if (firstFlipped == null) {
            card.flip();
            firstFlipped = card;

            System.out.println(firstFlipped.getId());

            if (firstFlipped.getId() == 69) {
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(event -> handleSpecialCard(firstFlipped));
                pause.play();
            }
        }
        else if (firstFlipped != null && secondFlipped == null && firstFlipped != card) {
            card.flip();
            secondFlipped = card;

            System.out.println(secondFlipped.getId());

            if  (secondFlipped.getId() == 69) {
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(event -> handleSpecialCard(secondFlipped));
                pause.play();
            }
            else {
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(event -> checkMatch(card));
                pause.play();
            }



        }
        else {
            System.out.println("Karta uz se rovna jine");
        }

    }

    @FXML
    protected void generateButtons(){
        for (int i = 0; i < 12; i++){
            cardsField.add(new Card(i));
            cardsField.add(new Card(i));


        }
        cardsField.add(new Card(69));

        Collections.shuffle(cardsField);
    }

    @FXML
    protected void generatePlayers(){
        players.add(new Player("skibidi 1"));
        players.add(new Player("skibidi 2"));
        players.add(new Player("skibidi 3"));

        player1NameLabel.setText(players.get(0).getName());
        player2NameLabel.setText(players.get(1).getName());
        player3NameLabel.setText(players.get(2).getName());
    }

    @FXML
    protected void displayButtons(){
        int collumn = 0;
        int row = 0;

        for  (Card card : cardsField){
            gridPane.add(card.getButton(), collumn, row);
            card.getButton().setOnAction(e -> handleCardClick(card));


            collumn++;

            if (collumn == 5){
                row++;
                collumn = 0;
            }
        }
    }



    @FXML
    protected void initialize() {
        konecButton.setDisable(true);

        player1ScoreLabel.setText("0 B");
        player2ScoreLabel.setText("0 B");

        generateButtons();
        displayButtons();
        generatePlayers();


        //handleShowKonec();
    }
}
