package com.example.assignment3.Uno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import GameFramework.infoMessage.GameState;

/**
 * @authors Starr Nakamitsu, Eduardo Gonon, Ayden Semerak.
 */
public class UnoGameState extends GameState {
    //The current status of the game.

    // %10 |Within the same package as the MainActivity class, create a new game
    //state class for your game. (Eventually this class will be a subclass of the
    //GameState class in the game framework but it doesn’t have to be for this
    //assignment.) Your game state class’ instance variables should encompass
    //all the information you will need about the current state of the game in
    //order to display it properly for a human user or allow a computer player
    //to make decisions.
    private int handSize; //The handsize for the current state of the game.
    private int numInPlay; //The current number on the played area.
    private ArrayList<Integer> numInHandCards = new ArrayList<>(); //The array of all the numbers in the hand.
    private int playerNum; //Number of players in the game.
    private int playerID; //ID of the player.
    private int colorInPlay; //The current number on the played area.
    private ArrayList <Integer> colorInHandCards = new ArrayList<>(); //The array of all the colors in the hand represented as integers.
    //Red is 0, Green is 1, Blue is 2, and Yellow is 3.
    private boolean isTurn; //Whether the current player can play.
    //Used to determine what card will be shown in the selected area.
    private ArrayList <Boolean> isCardSelected;
    private Random ran = new Random(); //The random object used the generate the numbers for colorSelect.
    private int colorSelect; //Variable used to assign cards a Color int from 0 - 3.
    private int numSelect; //Variable used to assign cards a number to represent cards from 0 - 14;

    private ArrayList <UnoCard> deck = new ArrayList<>(); // An array of the deck of cards

    // %5 |Implement a constructor for your class that initializes all the variables to
    //reflect the start of the game before any actions have been taken.

    //Default constructor.
    public UnoGameState(){
        handSize = 7;
        numInPlay = 7;
        playerNum = 2;
        colorInPlay = 1;
        isTurn = false;
        playerID = 1;
        int wildCheck;

        for(int i = 0; i < handSize; i++){
            colorSelect = ran.nextInt(4);
            //Convert to switch statement.
            if(colorSelect == 0){
                //Represents Red.
                colorInHandCards.add(colorSelect);
            }
            else if(colorSelect == 1){
                //Represents Green.
                colorInHandCards.add(colorSelect);
            }
            else if(colorSelect == 2){
                //Represents Blue.
                colorInHandCards.add(colorSelect);
            }
            else {
                //Represents Yellow.
                colorInHandCards.add(colorSelect);
            }
            //Note add a way to make the card distrubution the same as Uno.
            numSelect = ran.nextInt(15);
            if(numSelect == 0 || numSelect == 13 || numSelect == 14) {
                //To make the wild card and +4 as likely as a normal game of Uno.
                wildCheck = ran.nextInt(14);
                if(wildCheck == 13){
                    numInHandCards.add(numSelect);
                }
            }
            numInHandCards.add(numSelect);

        }

        /**
         * the outer for-loop iterates through the all of the number cards except 0 (1-9)
         * the inner loop creates 2 copies of the cards (doubles them)
         * this loops creates a total of 72 cards
         * at the end, the cards are added to the deck
         */

        for(int i = 1; i <= 9; i++){
            for (int j = 0; j < 2; j++){
                UnoNumberCard redCard = new UnoNumberCard(UnoCard.RED, i);
                UnoNumberCard greenCard = new UnoNumberCard(UnoCard.GREEN, i);
                UnoNumberCard blueCard = new UnoNumberCard(UnoCard.BLUE, i);
                UnoNumberCard yellowCard = new UnoNumberCard(UnoCard.YELLOW, i);

                deck.add(redCard);
                deck.add(greenCard);
                deck.add(blueCard);
                deck.add(yellowCard);
            }
        }
        // these are the 0 cards for each color
        UnoNumberCard redCard = new UnoNumberCard(UnoCard.RED, 0);
        UnoNumberCard greenCard = new UnoNumberCard(UnoCard.GREEN, 0);
        UnoNumberCard blueCard = new UnoNumberCard(UnoCard.BLUE, 0);
        UnoNumberCard yellowCard = new UnoNumberCard(UnoCard.YELLOW, 0);

        // adding the 0 cards to the deck
        deck.add(redCard);
        deck.add(greenCard);
        deck.add(blueCard);
        deck.add(yellowCard);

        /**
         * the outer for-loop increments through the different special cards except for wild cards (SKIP to REVERSE)
         * the inner for-loop creates 2 copies of the cards (doubles them)
         * this loop creates a total of 24 cards
         * and adds them to the deck
         */
        for(int i = UnoSpecialCard.SKIP; i <= UnoSpecialCard.REVERSE; i++){
            for(int j = 0; j < 2; j++){
                UnoSpecialCard specialRedCard = new UnoSpecialCard(UnoCard.RED, i);
                UnoSpecialCard specialGreenCard = new UnoSpecialCard(UnoCard.GREEN, i);
                UnoSpecialCard specialBlueCard = new UnoSpecialCard(UnoCard.BLUE, i);
                UnoSpecialCard specialYellowCard = new UnoSpecialCard(UnoCard.YELLOW, i);

                deck.add(specialRedCard);
                deck.add(specialGreenCard);
                deck.add(specialBlueCard);
                deck.add(specialYellowCard);
            }
        }

        // this for-loop runs 4 times to create and add the wild cards
        for (int i = 0; i < 4; i++){
            UnoSpecialCard wildCard = new UnoSpecialCard(UnoCard.COLORLESS, UnoSpecialCard.WILD);

            deck.add(wildCard);
        }

        // prints the deck before and after it's shuffled
        printDeck();
        System.out.println("Shuffling Deck");
        Collections.shuffle(deck); // this shuffles all of the cards in the deck
        printDeck();
    }
    public UnoGameState(int initHandSize,int initNumInPlay, int setPlayerNum , int initColorInPlay, int setPlayerId){
        handSize = initHandSize;
        numInPlay = initNumInPlay;
        playerNum = setPlayerNum;
        playerID = setPlayerId;
        colorInPlay = initColorInPlay;
        //Setting this one is odd, may as well initially set it to false.
        isTurn = false;


        for(int i = 0; i < handSize; i++){
            colorSelect = ran.nextInt(4);
            if(colorSelect == 0){
                //Represents Red.
                colorInHandCards.add(colorSelect);
            }
            else if(colorSelect == 1){
                //Represents Green.
                colorInHandCards.add(colorSelect);
            }
            else if(colorSelect == 2){
                //Represents Blue.
                colorInHandCards.add(colorSelect);
            }
            else{
                //Represents Yellow.
                colorInHandCards.add(colorSelect);
            }
        }
    }

    // %15 |Implement a copy constructor that makes a deep copy of a given instance
    //of your class and adjusts it so it shows only the data visible to a specific
    //player.
    //• This must be a deep copy. If your game state contains any variables that
    //are of a class type then the objects they refer to must also be copied. If
    //any of these objects class-type variables then those must be copied too,
    //and so on. If you don’t understand this, please ask. Shallow copies will
    //be penalized.

    public UnoGameState(UnoGameState game){
        this(game.getHandSize(),game.getNumInPlay(), game.getPlayerNum(),game.getColorInPlay(), game.getPlayerID());
    }

    // %5 |Add a toString() method to the game state class the describes the state of
    //the game as a string. This method should print the values of all the variables in your game state. If the variable is an array (or similar aggregate
    //type) all its values must be printed. Your string should have sufficient
    //formatting so its reasonable for you to tell values go with which variables.
    //Be sure to put the @Override tag on your toString() method to verify it
    //has the proper signature.
    @Override
    public String toString(){
        String stateOfGame;
        stateOfGame = "Number of Players in the game: " + playerNum + " Player ID: " + playerID + " HandSize: " + handSize + " Number in Play: " +
                numInPlay + " Color in play: " + colorInPlay +"\n";
        return stateOfGame;
    }

    // %15 |Add a method for each action that players may take in your game. Each
    //method should have a boolean return value. When called, each method
    //should:
    //– verify the move is a legal move for the current game state. If it’s not,
    //return false.
    //– modify the game state to reflect that a given player has taken that
    //action. Then, return true. Each method should require that the
    //player id of the player who is taking that action be passed in as
    //the first parameter. Other parameters will likely be needed for some
    //actions.

    //Get method Row.
    public int getHandSize() { return handSize; }
    public int getPlayerNum() { return playerNum; }
    public int getPlayerID() { return playerID; }
    public int getNumInPlay() { return numInPlay; }
    public int getColorInPlay(){ return colorInPlay; }
    public boolean isTurn() { return isTurn; }
    public ArrayList getColorsInHand(){ return colorInHandCards; }
    public ArrayList getNumInHandCards() { return numInHandCards; }

    //Set method Row.
    //This method checks if either the played number or color match and then changes them in either or
    //both cases.
    public boolean setCardInPlay(int usedPlayerID, int usedNum, int usedColor){
        if(this.playerID == usedPlayerID && this.numInPlay == usedNum || this.colorInPlay == usedColor) {
            numInPlay = usedNum;
            colorInPlay = usedColor;
            return true;
        }
        else{
            return false;
        }
    }
    public boolean setCurrentTurn(boolean turnSet){
        isTurn = turnSet;
        return true;
    }

    //A method to increase handsize or decrease handsize by one whether the boolean is set to
    //true or false.
    public boolean incremHandSize(int usedPlayerID, boolean incrHandSize){
        if(this.playerID == usedPlayerID) {
            if (incrHandSize == true) {
                handSize++;
                return true;
            } else {
                if (handSize >= 1) {
                    handSize--;
                    return true;
                } else {
                    return false;
                }
            }
        }
        else{
            return false;
        }
    }

    // This method is just a way for us to check the deck
    public void printDeck(){
        // for each card (c) in the deck
        for(UnoCard c : deck) {
            String cardStr = ""; // building a string to print

            // these if-statements check the color
            if(c.getColor() == UnoCard.RED) {
                cardStr = cardStr + "Red ";
            }
            else if(c.getColor() == UnoCard.GREEN) {
                cardStr = cardStr + "Green ";
            }
            else if(c.getColor() == UnoCard.BLUE) {
                cardStr = cardStr + "Blue ";
            }
            else if(c.getColor() == UnoCard.YELLOW) {
                cardStr = cardStr + "Yellow ";
            }

            // the outermost if-statement checks if the card is a special card
            // if it is, it checks what kind of special card it is
            if(c instanceof UnoSpecialCard) {
                if(((UnoSpecialCard) c).getAbility() == UnoSpecialCard.SKIP){
                    cardStr = cardStr + "Skip";
                }
                else if(((UnoSpecialCard) c).getAbility() == UnoSpecialCard.DRAWTWO){
                    cardStr = cardStr + "Draw Two";
                }
                else if(((UnoSpecialCard) c).getAbility() == UnoSpecialCard.REVERSE){
                    cardStr = cardStr + "Reverse";
                }
                else if(((UnoSpecialCard) c).getAbility() == UnoSpecialCard.WILD){
                    cardStr = cardStr + "Wild";
                }
            }
            // if else (not a special card), it gets the number of the card
            else {
                cardStr = cardStr + ((UnoNumberCard) c).getNum();
            }
            System.out.println(cardStr); // prints the built string
        }
    }

}
