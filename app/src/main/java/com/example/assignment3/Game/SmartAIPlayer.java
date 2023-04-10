package com.example.assignment3.Game;

import com.example.assignment3.Uno.UnoCard.UnoNumberCard;
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

    @Override
    protected void receiveInfo(GameInfo info) {
        gameState = (UnoGameState) info;
        try {
            //Adds a pause in the game.
            Thread.sleep(250);
        }
        catch(Exception e){

        }
        //Ranking each card, how will one do this?
        if(gameState.isTurn()){
            ArrayList<UnoCard> unoCards = gameState.getHandArray().get(this.playerNum);
            ArrayList<UnoCard> playableCards = new ArrayList<>();
           for(int i = 0; i < unoCards.size(); i++){
             if(unoCards.get(i) instanceof UnoNumberCard){
                 if(((UnoNumberCard) unoCards.get(i)).getNum() == gameState.getNumInPlay()){
                     playableCards.add(unoCards.get(i));
                 }
                 else if((unoCards.get(i)).getColor() == gameState.getColorInPlay()){
                     playableCards.add(unoCards.get(i));
                 }
             }
             else if(unoCards.get(i) instanceof UnoSpecialCard){
                 if((unoCards.get(i).getColor() == gameState.getColorInPlay())){
                     playableCards.add(unoCards.get(i));
                 }
                 else if(((UnoSpecialCard) unoCards.get(i)).getAbility() == UnoSpecialCard.WILD){
                     playableCards.add(unoCards.get(i));
                 }
             }
           }
            //This will occur at the end.
            gameState.setCurrentTurn(false);
        }
    }

    //Started to create the method for ranking the cards.
    private UnoCard bestCardToPlay(ArrayList<UnoCard> canPlay){
        if(canPlay.size() == 0){
            return null;
        }
        if(canPlay.size() == 1){
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
                    else{
                        break;
                    }
                }
                else if(select instanceof UnoNumberCard){
                    if(((UnoNumberCard) select).getNum() == gameState.getNumInPlay() && select.getColor() != gameState.getColorInPlay()){
                        return select;
                    }
                }
            }
            return canPlay.get(0);
        }
        else{
            return canPlay.get(0);
        }
    }
}
