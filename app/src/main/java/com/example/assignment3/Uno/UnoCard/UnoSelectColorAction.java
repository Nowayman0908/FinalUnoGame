package com.example.assignment3.Uno.UnoCard;

import GameFramework.actionMessage.GameAction;
import GameFramework.players.GamePlayer;

public class UnoSelectColorAction extends GameAction {
    public int selectedColor; // depending on the color the player chooses, this number will change (0-3)

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public UnoSelectColorAction(GamePlayer player, int selectedColor) {
        super(player);
        this.selectedColor = selectedColor;
    }
}
