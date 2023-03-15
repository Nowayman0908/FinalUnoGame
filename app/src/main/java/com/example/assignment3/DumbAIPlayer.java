package com.example.assignment3;

import GameFramework.infoMessage.GameInfo;
import GameFramework.players.GameComputerPlayer;

public class DumbAIPlayer extends GameComputerPlayer {
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

    }
}
