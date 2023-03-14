package com.example.assignment3;

import GameFramework.GameMainActivity;
import GameFramework.LocalGame;
import GameFramework.gameConfiguration.GameConfig;
import GameFramework.infoMessage.GameState;

public class MainActivity extends GameMainActivity {


    @Override
    public GameConfig createDefaultConfig() {

        GameConfig config = new GameConfig(tableRows, 1,3,"Uno", getConfig().getPortNum());
        return null;
    }

    @Override
    public LocalGame createLocalGame(GameState gameState) {
        return null;
    }
}