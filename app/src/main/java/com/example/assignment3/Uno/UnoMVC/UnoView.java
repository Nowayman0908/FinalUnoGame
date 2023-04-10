package com.example.assignment3.Uno.UnoMVC;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

/**
 * @author Ayden Semerak.
 */
public class UnoView extends SurfaceView {

    private UnoModel UM;
    private Paint black = new Paint(Color.BLACK);
    private Paint red = new Paint(Color.RED);
    private Paint blue = new Paint(Color.BLUE);
    private Paint yellow = new Paint(Color.YELLOW);
    private Paint green = new Paint(Color.GREEN);
    private Paint white = new Paint(Color.WHITE);
    private Paint grey = new Paint(Color.GRAY);

    public UnoView(Context context) {
        super(context);
        UM = new UnoModel();
        setWillNotDraw(false);
    }

    public void onDraw (Canvas canvas){
        //Fill in later.
    }

    public void drawNumberCard (Canvas canvas,int x,int y, int colorChoice, int cardNumber){
        //Creates a Graphic of a Uno card with a White Border, Color inside, and the type of card.
        String cardText = cardNumber + "";
        canvas.drawRect(x,y,x+50,y+50,white);
        switch(colorChoice){
            case 0:
                canvas.drawRect(x+ 10,y + 10,x+40,y+40,red);
                canvas.drawText(cardText,x+25,y+25,red);
                break;
            case 1:
                canvas.drawRect(x+10,y+10,x+40,y+40,green);
                canvas.drawText(cardText,x+25,y+25,green);
                break;
            case 2:
                canvas.drawRect(x+10,y+10,x+40,y+40,blue);
                canvas.drawText(cardText,x+25,y+25,blue);
                break;
            case 3:
                canvas.drawRect(x+10,y+10,x+40,y+40,yellow);
                canvas.drawText(cardText,x+25,y+25,yellow);
                break;
        }
    }

    public void drawSpecialCard(Canvas canvas, int x, int y, int colorChoice){
        canvas.drawRect(x,y,x+36,y+50,white);
        switch(colorChoice){
            case 0:
                canvas.drawRect(x+ 10,y + 10,x+26,y+40,red);
                canvas.drawText("0",x+25,y+25,black);
                break;
            case 1:
                canvas.drawRect(x+10,y+10,x+26,y+40,green);
                canvas.drawText("0",x+25,y+25,black);
                break;
            case 2:
                canvas.drawRect(x+10,y+10,x+26,y+40,blue);
                canvas.drawText("0",x+25,y+25,black);
                break;
            case 3:
                canvas.drawRect(x+10,y+10,x+26,y+40,yellow);
                canvas.drawText("0",x+25,y+25,black);
                break;
        }
    }

    public void drawPlayerCards(Canvas canvas, int playerNum){
        //I need a way to access the game state to know the handsize of the CPU/Human players.
        switch (playerNum){
            case 2:
               // String handSize = ;
                canvas.drawRect(500,20,526,70,grey);
                //canvas.drawText();
                break;
            case 3:
                //canvas.drawRect();
                //canvas.drawText();
                break;
            case 4:
                //canvas.drawRect();
                //canvas.drawText();
                break;
            default:
                //canvas.drawRect();
                //canvas.drawText();
                break;

        }
    }
    public UnoModel getUM(){
        return UM;
    }
}
