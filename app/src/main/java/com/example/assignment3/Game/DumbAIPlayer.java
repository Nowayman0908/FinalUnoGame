package com.example.assignment3.Game;

import GameFramework.infoMessage.GameInfo;
import GameFramework.players.GameComputerPlayer;

import com.example.assignment3.Uno.UnoCard.UnoDrawCardAction;
import com.example.assignment3.Uno.UnoGameState;

import java.util.Random;

public class DumbAIPlayer extends GameComputerPlayer {

    private Random ran = new Random();

    private UnoGameState gameState;

    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public DumbAIPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if(info instanceof UnoGameState) {
            gameState = (UnoGameState) info;
        }
        else {
            return;
        }
        try {
            Thread.sleep(250);
        } catch (Exception e) {

        }
        UnoDrawCardAction draw = new UnoDrawCardAction(this);
        game.sendAction(draw);

        //Work on making the Dumb AI play.
        if(gameState.isTurn()){
            int colorInPlay = gameState.getColorInPlay();
            int numInPlay = gameState.getNumInPlay();
            int indexOfPlayCard;
            for(int i = 0; i < gameState.getColorsInHand().size(); i++){
                if((int) gameState.getColorsInHand().get(i) == colorInPlay || (int) gameState.getNumInHandCards().get(i) == numInPlay){
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