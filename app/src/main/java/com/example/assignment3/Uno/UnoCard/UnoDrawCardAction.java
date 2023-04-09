package com.example.assignment3.Uno.UnoCard;

import GameFramework.actionMessage.GameAction;
import GameFramework.players.GamePlayer;

public class UnoDrawCardAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public UnoDrawCardAction(GamePlayer player) {
        super(player);
    }
}
