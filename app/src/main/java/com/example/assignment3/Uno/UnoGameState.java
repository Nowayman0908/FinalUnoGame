package com.example.assignment3.Uno;

import com.example.assignment3.Uno.UnoCard.UnoCard;
import com.example.assignment3.Uno.UnoCard.UnoNumberCard;
import com.example.assignment3.Uno.UnoCard.UnoSelectColorAction;
import com.example.assignment3.Uno.UnoCard.UnoSpecialCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import GameFramework.infoMessage.GameState;

/**
 * @authors Starr Nakamitsu, Eduardo Gonon, Ayden Semerak.
 */
public class UnoGameState extends GameState {
    //The current status of the game.

    private int handSize; //The handsize for the current state of the game.
    private int numInPlay; //The current number on the played area. (-1 if not a number)

    private int spcInPlay; // the current special number in the played area. (-1 if not special)
    private int playerNum; //Number of players in the game.
    private int playerID; //ID of the player.
    private int colorInPlay; //The current number on the played area.
    private Random ran = new Random(); //The random object used the generate the numbers for colorSelect.
    private int colorSelect; //Variable used to assign cards a Color int from 0 - 3.
    private int numSelect; //Variable used to assign cards a number to represent cards from 0 - 14;
    private ArrayList <UnoCard> deck = new ArrayList<>(); // An array list of the deck of cards
    private ArrayList <UnoCard> discardPile = new ArrayList<>(); // An array list of the played cards (discard pile)
    private ArrayList<ArrayList<UnoCard>> handArray = new ArrayList<>(); // An array list of player's hands (which are also array lists)
    private int order = 1; // this is the order of the player's turn (1 is clockwise, -1 is counterclockwise)


    // %5 |Implement a constructor for your class that initializes all the variables to
    //reflect the start of the game before any actions have been taken.

    //Default constructor.
    public UnoGameState(){
        handSize = 7;
        numInPlay = 7;
        playerNum = 2;
        colorInPlay = 1;
        playerID = 0;



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

        // this for-loop runs 4 times to create and add the wild cards and draw 4 cards
        for (int i = 0; i < 4; i++){
            UnoSpecialCard wildCard = new UnoSpecialCard(UnoCard.COLORLESS, UnoSpecialCard.WILD);
            UnoSpecialCard drawFour = new UnoSpecialCard(UnoCard.COLORLESS, UnoSpecialCard.DRAWFOUR);
            deck.add(wildCard);
            deck.add(drawFour);
        }

        // prints the deck before and after it's shuffled
        printDeck();
        System.out.println("Shuffling Deck");
        Collections.shuffle(deck); // this shuffles all of the cards in the deck
        printDeck();

        // this puts the first card of the deck into the discard/played cards pile
        discardPile.add(deck.remove(0));
        colorInPlay = discardPile.get(0).getColor();
        if(discardPile.get(0).isNumber()){
            numInPlay = ((UnoNumberCard) discardPile.get(0)).getNum();
            spcInPlay = -1;
        }
        else {
            spcInPlay = ((UnoSpecialCard) discardPile.get(0)).getAbility();
            numInPlay = -1;
        }

        // this adds a player's hand (an array list) into the handArray array list
        for (int i = 0; i < playerNum; i++){
            handArray.add(new ArrayList<UnoCard> (handSize));
            // this adds 7 cards from the deck into the player's hands
            for(int j = 0; j < 7; j++) {
                handArray.get(i).add(deck.remove(0));
            }
        }

    }
    public UnoGameState(int initHandSize,int initNumInPlay, int setPlayerNum , int initColorInPlay, int setPlayerId){
        handSize = initHandSize;
        numInPlay = initNumInPlay;
        playerNum = setPlayerNum;
        playerID = setPlayerId;
        colorInPlay = initColorInPlay;
    }

    public UnoGameState(UnoGameState game){
        this(game.getHandSize(),game.getNumInPlay(), game.getPlayerNum(),game.getColorInPlay(), game.getPlayerID());

        ArrayList<UnoCard> copyDeck = new ArrayList<>();
        ArrayList<UnoCard> copyDiscardPile = new ArrayList<>();
        ArrayList<ArrayList<UnoCard>> copyHands = new ArrayList<>();

        // goes thru the deck and copies all of the cards
        for(UnoCard card : game.deck)
        {
            if(card instanceof UnoNumberCard){
                copyDeck.add(new UnoNumberCard((UnoNumberCard) card));
            }
            else if(card instanceof  UnoSpecialCard){
                copyDeck.add(new UnoSpecialCard((UnoSpecialCard) card));
            }

        }

        // goes thru the discard pile and copies all of the cards
        for(UnoCard card : game.discardPile){

            if(card instanceof UnoNumberCard){
                copyDiscardPile.add(new UnoNumberCard((UnoNumberCard) card));
            }
            else if(card instanceof  UnoSpecialCard){
                copyDiscardPile.add(new UnoSpecialCard((UnoSpecialCard) card));
            }

        }

        // goes thru each player's hands and copies the cards in their hand
        for(ArrayList<UnoCard> hand : game.handArray){
            ArrayList<UnoCard> playerHand = new ArrayList<>();

            for(UnoCard card : hand){
                if(card instanceof UnoNumberCard){
                    playerHand.add(new UnoNumberCard((UnoNumberCard) card));
                }
                else if(card instanceof  UnoSpecialCard){
                    playerHand.add(new UnoSpecialCard((UnoSpecialCard) card));
                }
            }

            copyHands.add(playerHand);
        }

        deck = copyDeck;
        discardPile = copyDiscardPile;
        handArray = copyHands;
    }

    @Override
    public String toString(){
        String stateOfGame;
        stateOfGame = "Number of Players in the game: " + playerNum + " Player ID: " + playerID + " HandSize: " + handSize + " Number in Play: " +
                numInPlay + " Color in play: " + colorInPlay +"\n";
        return stateOfGame;
    }

    //Get method Row.
    public int getHandSize() { return handSize; }
    public int getPlayerNum() { return playerNum; }
    public int getPlayerID() { return playerID; }
    public int getNumInPlay() { return numInPlay; }
    public int getColorInPlay(){ return colorInPlay; }
    public int getSpcInPlay(){return spcInPlay; };
    public ArrayList<ArrayList<UnoCard>> getHandArray(){
        return handArray;
    }
    public ArrayList getDiscardPile(){
        return discardPile;
    }

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

    // this method is called on when a player draws a card from the deck.
    // the card in the deck will be moved to the player's hand
    public boolean drawCard(){
        handArray.get(playerID).add(deck.remove(0));
        return true;
    }

    // this method removes the card from the player's hand
    // and moves it to the discard pile
    public boolean playCard(int index){
        // if it's a wild, it will automatically be red
        if(handArray.get(playerID).get(index).getColor() == UnoCard.COLORLESS) {
            discardPile.add(handArray.get(playerID).remove(index));
            colorInPlay = UnoCard.RED;
            numInPlay = -1;
            spcInPlay = ((UnoSpecialCard)discardPile.get(discardPile.size() - 1)).getAbility();
        }
        else if(colorInPlay == handArray.get(playerID).get(index).getColor()) {
            discardPile.add(handArray.get(playerID).remove(index));
            if(discardPile.get(discardPile.size() - 1).isNumber()){
                numInPlay = ((UnoNumberCard) discardPile.get(discardPile.size() - 1)).getNum();
                spcInPlay = -1;
            }
            else {
                spcInPlay = ((UnoSpecialCard) discardPile.get(discardPile.size() - 1)).getAbility();
                numInPlay = -1;
            }
            endTurn();
            return true;
        }
        else if(handArray.get(playerID).get(index).isSpecial() && ((UnoSpecialCard) handArray.get(playerID).get(index)).getAbility() == spcInPlay) {
            discardPile.add(handArray.get(playerID).remove(index));
            colorInPlay = discardPile.get(discardPile.size() - 1).getColor();
            endTurn();
            return true;

        }
        else if(handArray.get(playerID).get(index).isNumber() && ((UnoNumberCard) handArray.get(playerID).get(index)).getNum() == numInPlay) {
            discardPile.add(handArray.get(playerID).remove(index));
            colorInPlay = discardPile.get(discardPile.size() - 1).getColor();
            endTurn();
            return true;
        }
        return false;
    }

    // this method is for the skip ability
    // if there are two players, it will be the player's
    // turn who played the card
    public boolean skip(){
        playerID = (playerID - order*2) % playerNum;
        if(playerID < 0){
            playerID = playerNum + playerID;
        }
        return true;
    }

    // this method is for the reverse ability
    // if there are two players, it will be the player's
    // turn who played the card

    public boolean reverse(){
        if (order == 1){
            order = -1;
        }
        else{
            order = 1;
        }

        return true;
    }

    // this method is for the draw 2 ability
    // when the player draws 2 cards, their turn is skipped
    public boolean drawTwo(){
        for (int i = 0; i < 2; i++){
            drawCard();
        }
        endTurn();

        return true;
    }

    // this method is for the wild ability
    public boolean wild(){
        return true;
    }

    // this method is for the draw four ability
    // after the next player draws four cards, their turn
    // will end
    public boolean drawFour(){
        // write code to allow the player to select a color
        for (int i = 0; i < 4; i++){
            drawCard();
        }
        endTurn();
        return true;
    }

    // this method is for when a player selects a color
    public boolean selectColor(int colorSelected){
        colorInPlay = colorSelected;
        return true;

    }

    // based on the order (clockwise or counterclockwise), the playerID
    // will change accordingly
    public boolean endTurn(){
            playerID = (playerID + order) % playerNum;
            if (playerID < 0){
                playerID = playerNum + playerID;
            }
        return true;
    }

    public void setPlayerHand (int playerID){
        UnoSpecialCard wild = new UnoSpecialCard(UnoCard.COLORLESS,UnoSpecialCard.WILD);
        UnoSpecialCard drawTwo = new UnoSpecialCard(UnoCard.RED,UnoSpecialCard.DRAWTWO);
        UnoSpecialCard drawFour = new UnoSpecialCard(UnoCard.COLORLESS,UnoSpecialCard.DRAWFOUR);
        UnoSpecialCard reverse = new UnoSpecialCard(UnoCard.RED,UnoSpecialCard.REVERSE);
        UnoSpecialCard skip = new UnoSpecialCard(UnoCard.RED,UnoSpecialCard.SKIP);
        handArray.get(playerID).clear();
        handArray.get(playerID).add(wild);
        handArray.get(playerID).add(drawTwo);
        handArray.get(playerID).add(drawFour);
        handArray.get(playerID).add(reverse);
        handArray.get(playerID).add(skip);
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
