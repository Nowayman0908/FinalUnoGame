package com.example.assignment3.Game;

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

    }
}
