package com.example.assignment3.Uno;

import static com.example.assignment3.Uno.UnoCard.RED;

import GameFramework.LocalGame;
import GameFramework.actionMessage.GameAction;
import GameFramework.players.GamePlayer;

public class UnoLocalGame extends LocalGame {
    private UnoGameState gameState;
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        if (this.gameState == null){
            this.gameState = new UnoGameState();
        }
    }

    @Override
    protected boolean canMove(int playerIdx) {
        return false;
    }

    @Override
    protected String checkIfGameOver() {
        return null;
    }

    /**
     *
     * @param action - the action received from a player
     *
     * @return if the move was valid
     */
    @Override
    protected boolean makeMove(GameAction action) {
        if(action instanceof UnoPlayCardAction){
            // write code to check
            return true;
        }
        else if (action instanceof UnoDrawCardAction){
            ((UnoGameState)state).drawCard();
            // switch player's turns
            return true;
        }
        return false;
    }
}
