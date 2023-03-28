package com.example.assignment3.Game;

import com.example.assignment3.Uno.UnoGameState;
import com.example.assignment3.Uno.UnoPlayCardAction;

import GameFramework.infoMessage.GameInfo;
import GameFramework.players.GameComputerPlayer;

public class SmartAIPlayer extends GameComputerPlayer {
    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public SmartAIPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        UnoGameState gameState = (UnoGameState) info;
        try {
            //Adds a pause in the game.
            Thread.sleep(250);
        }
        catch(Exception e){

        }
        //Ranking each card, how will one do this?
        if(gameState.isTurn()){
            int colorInPlay = gameState.getColorInPlay();
            int numInPlay = gameState.getNumInPlay();
            int indexOfPlayCard;
            for(int i = 0; i < gameState.getColorsInHand().size(); i++){
                if((int) gameState.getColorsInHand().get(i) == colorInPlay && (int) gameState.getNumInHandCards().get(i) == numInPlay){
                    indexOfPlayCard = i;
                    gameState.setCardInPlay(this.playerNum,(int) gameState.getNumInHandCards().get(i),(int) gameState.getColorsInHand().get(i));
                    gameState.getColorsInHand().remove(i);
                    gameState.getNumInHandCards().remove(i);
                    gameState.incremHandSize(this.playerNum, false);
                    break;
                }
            }
            gameState.setCurrentTurn(false);
        }
    }
}
