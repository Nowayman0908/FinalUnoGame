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
            Thread.sleep(250);
        }
        catch(Exception e){

        }
        if(gameState.isTurn()){
            //Incomplete, need to determine which random card to play.
            //Same as Dumb AI.
            int randomSelect;
            int colorInPlay = gameState.getColorInPlay();
            int numInPlay = gameState.getNumInPlay();
            if(gameState.getColorsInHand().contains(colorInPlay)){

            }

            UnoPlayCardAction action = new UnoPlayCardAction(this);
            gameState.setCurrentTurn(false);
        }
    }
}
