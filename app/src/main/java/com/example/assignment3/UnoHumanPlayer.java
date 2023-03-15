package com.example.assignment3;

import android.view.View;

import GameFramework.GameMainActivity;
import GameFramework.infoMessage.GameInfo;
import GameFramework.players.GameHumanPlayer;

public class UnoHumanPlayer extends GameHumanPlayer {
    /**
     * constructor
     *
     * @param name the name of the player
     */
    public UnoHumanPlayer(String name) {
        super(name);
    }

    @Override
    public View getTopView() {
        return null;
    }

    @Override
    public void receiveInfo(GameInfo info) {

    }

    @Override
    public void setAsGui(GameMainActivity activity) {

    }
}
