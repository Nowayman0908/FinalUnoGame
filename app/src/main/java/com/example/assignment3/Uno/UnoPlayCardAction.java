package com.example.assignment3.Uno;

import GameFramework.actionMessage.GameAction;
import GameFramework.players.GamePlayer;

public class UnoPlayCardAction extends GameAction{
    public int index;
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     * @param index is the index of the card in the player's hand
     */
    public UnoPlayCardAction(GamePlayer player, int index) {
        super(player);
        this.index = index;
    }
}
