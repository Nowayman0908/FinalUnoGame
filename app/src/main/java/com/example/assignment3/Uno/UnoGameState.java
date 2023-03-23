package com.example.assignment3.Uno;

import java.util.ArrayList;
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
            else{
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
    public boolean setCurrentTurn(boolean turnSet){ isTurn = turnSet; return true; }
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
}
