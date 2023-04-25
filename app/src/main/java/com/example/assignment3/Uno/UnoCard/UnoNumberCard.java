package com.example.assignment3.Uno.UnoCard;

import androidx.annotation.NonNull;

public class UnoNumberCard extends UnoCard {

    //instance variables
    protected int num;

    public UnoNumberCard(int initColor, int initNum) {
        super(initColor);
        this.num = initNum;
    }

    //getter method: this method gets the number of the card
    public int getNum(){
        return this.num;
    }

    //copy constructor
    public UnoNumberCard(UnoNumberCard numCard) {
        this(numCard.getColor(), numCard.getNum());
    }

    @NonNull
    @Override
    public String toString() {
        String color;
        String card;
        color = super.toString();

        card = color + " " + getNum();

        return card;

    }
}

