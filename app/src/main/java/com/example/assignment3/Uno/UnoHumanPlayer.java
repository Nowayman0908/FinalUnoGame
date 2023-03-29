package com.example.assignment3.Uno;

import android.view.View;
import android.view.View.OnClickListener;

import com.example.assignment3.Uno.UnoGameState;

import GameFramework.GameMainActivity;
import GameFramework.infoMessage.GameInfo;
import GameFramework.players.GameHumanPlayer;

public class UnoHumanPlayer extends GameHumanPlayer implements OnClickListener  {
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
        if(info instanceof UnoGameState){
        }
    }

    @Override
    public void setAsGui(GameMainActivity activity) {

    }

    @Override
    public void onClick(View view) {

    }
}
