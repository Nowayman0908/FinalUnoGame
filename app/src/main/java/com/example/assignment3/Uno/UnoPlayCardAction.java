package com.example.assignment3.Uno;

import GameFramework.actionMessage.GameAction;
import GameFramework.players.GamePlayer;

public class UnoPlayCardAction extends GameAction{
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public UnoPlayCardAction(GamePlayer player) {
        super(player);
    }
}
