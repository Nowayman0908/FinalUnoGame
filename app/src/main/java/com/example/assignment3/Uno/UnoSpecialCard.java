package com.example.assignment3.Uno;

public class UnoSpecialCard extends UnoCard{

    // These special cards are constants that can be referenced anywhere
    public static final int SKIP = 0;
    public static final int DRAWTWO = 1;
    public static final int REVERSE = 2;
    public static final int WILD = 3;
    public static final int DRAWFOUR = 4;


    // instance variable
    protected int ability;

    // constructor
    public UnoSpecialCard(int initColor, int initAbility) {
        super(initColor);
        this.ability = initAbility;
    }

    // getter method: this method gets the ability of the card
    public int getAbility(){
        return this.ability;
    }

    // copy constructor
    public UnoSpecialCard(UnoSpecialCard spcCard) {
        this(spcCard.getColor(), spcCard.getAbility());
    }

    @Override
    public String toString() {
        String color;
        String ability;
        String card;
        color = super.toString();

        if (this.ability == SKIP){
            card = color + " Skip";
        }
        else if (this.ability == DRAWTWO){
            card = color + " Draw Two";
        }
        else if (this.ability == REVERSE){
            card = color + " Reverse";
        }
        else if (this.ability == WILD){
            card = color + " Wild";
        }
        else{
            card = color + " Draw Four";
        }

        return card;
    }
}
