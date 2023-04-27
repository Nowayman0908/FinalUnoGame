package com.example.assignment3.Game;

import com.example.assignment3.Uno.UnoCard.UnoDrawCardAction;
import com.example.assignment3.Uno.UnoCard.UnoNumberCard;
import com.example.assignment3.Uno.UnoCard.UnoPlayCardAction;
import com.example.assignment3.Uno.UnoCard.UnoSpecialCard;
import com.example.assignment3.Uno.UnoGameState;
import com.example.assignment3.Uno.UnoCard.UnoCard;
import java.util.ArrayList;
import GameFramework.infoMessage.GameInfo;
import GameFramework.players.GameComputerPlayer;

public class SmartAIPlayer extends GameComputerPlayer {
    UnoGameState gameState;
    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public SmartAIPlayer(String name) {
        super(name);
    }

    //A work in progress but as is, the code works.
    @Override
    protected void receiveInfo(GameInfo info) {
        if (info instanceof UnoGameState) {
            gameState = (UnoGameState) info;
        } else {
            return;
        }

        try {
            Thread.sleep(750);
        }
        catch (Exception ignored) {

        }
            ArrayList<UnoCard> unoCards = gameState.getHandArray().get(this.playerNum);
            ArrayList<UnoCard> playableCards = new ArrayList<>();
            int colorInPlay = gameState.getColorInPlay();
            int numInPlay = gameState.getNumInPlay();
           for(int i = 0; i < unoCards.size(); i++){
             if(unoCards.get(i) instanceof UnoNumberCard){
                 if(((UnoNumberCard) unoCards.get(i)).getNum() == numInPlay){
                     playableCards.add(unoCards.get(i));
                 }
                 else if((unoCards.get(i)).getColor() == colorInPlay){
                     playableCards.add(unoCards.get(i));
                 }
             }
             else if(unoCards.get(i) instanceof UnoSpecialCard){
                 if((unoCards.get(i).getColor() == colorInPlay)){
                     playableCards.add(unoCards.get(i));
                 }
                 else if(((UnoSpecialCard) unoCards.get(i)).getAbility() == UnoSpecialCard.WILD || ((UnoSpecialCard) unoCards.get(i)).getAbility() == UnoSpecialCard.DRAWFOUR){
                     playableCards.add(unoCards.get(i));
                 }
             }
           }
           UnoCard bestCard = bestCardToPlay(playableCards);
        if(bestCard == null){
            if(unoCards.size() != 0){
                UnoDrawCardAction draw = new UnoDrawCardAction(this);
                game.sendAction(draw);
            }
        }
        else {
            for (int p = 0; p < unoCards.size(); p++) {
                if (bestCard == unoCards.get(p)) {
                    UnoPlayCardAction playCardAction = new UnoPlayCardAction(this, p);
                    game.sendAction(playCardAction);
                    break;
                }
            }
        }
        }

    //Started to create the method for ranking the cards.
    private UnoCard bestCardToPlay(ArrayList<UnoCard> canPlay){
        if(canPlay.size() == 0){
            return null;
        }
        else if(canPlay.size() == 1){
            return canPlay.get(0);
        }
        boolean prioDraw = false;
        for(int l = 0; l < gameState.getHandArray().size(); l++){
            if(gameState.getHandArray().get(l).size() <= 3){
                prioDraw = true;
            }
        }
        if(prioDraw){
            UnoCard select;
            for(int u = 0; u < canPlay.size(); u++){
                select = canPlay.get(u);
                if(select instanceof UnoSpecialCard){
                    if(((UnoSpecialCard) select).getAbility() != UnoSpecialCard.WILD){
                        return select;
                    }
                }
                else if(select instanceof UnoNumberCard){
                    if(((UnoNumberCard) select).getNum() == gameState.getNumInPlay() && select.getColor() != gameState.getColorInPlay()){
                        return select;
                    }
                }
            }
        }
        return canPlay.get(0);
    }
}
