package com.example.assignment3;

import android.graphics.Color;

public class UnoGameState {
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
    private int[] numInHandCards; //The array of all the numbers in the hand.
    private int playerNum;

    private Color colorInPlay; //The current number on the played area.
    private Color[] colorInHandCards; //The array of all the colors in the hand.

    private boolean isTurn; //Whether the current player can play.
    private boolean[] isCardSelected; //Used to determine what card will be shown in the selected area.

    // %5 |Implement a constructor for your class that initializes all the variables to
    //reflect the start of the game before any actions have been taken.
    public UnoGameState(int initHandSize, int initNumInPlay, int setPlayerNum , Color initColorInPlay){
        handSize = initHandSize;
        numInPlay = initNumInPlay;
        playerNum = setPlayerNum;
        colorInPlay = initColorInPlay;
        //Setting this one is odd, may as well initially set it to false.
        isTurn = false;
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
        this(game.getHandSize(),game.getNumInPlay(), game.getPlayerNum(),game.getColorInPlay());
    }

    // %5 |Add a toString() method to the game state class the describes the state of
    //the game as a string. This method should print the values of all the variables in your game state. If the variable is an array (or similar aggregate
    //type) all its values must be printed. Your string should have sufficient
    //formatting so its reasonable for you to tell values go with which variables.
    //Be sure to put the @Override tag on your toString() method to verify it
    //has the proper signature.
    @Override
    public String toString(){
        return null;
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
    public int getNumInPlay() { return numInPlay; }
    public Color getColorInPlay(){ return colorInPlay; }

}
