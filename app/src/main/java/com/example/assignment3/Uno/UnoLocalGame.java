package com.example.assignment3.Uno;

import static com.example.assignment3.Uno.UnoCard.RED;

import GameFramework.LocalGame;
import GameFramework.actionMessage.GameAction;
import GameFramework.players.GamePlayer;

public class UnoLocalGame extends LocalGame {
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
    }

    @Override
    protected boolean canMove(int playerIdx) {
        return false;
    }

    @Override
    protected String checkIfGameOver() {
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        UnoCard card = new UnoCard(0);
        if(card.getColor() == 0)
        {
            return true;
        }
        return false;
    }
}
