package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String[][] buttons = new String[3][3];
    private final ImageButton[][] imageButtons = new ImageButton[3][3];
    private boolean player1Turn = true;
    private boolean isGameOver = false;
    private int TurnCount ;
    private LinearLayout linearLayout;
    private Button gameResetBtn;
    private ImageView playXO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout =findViewById(R.id.MainLinearBoard_marks);
        playXO = findViewById(R.id.MainActivity_PlayXO);
        gameResetBtn = findViewById(R.id.MainActivity_GameReset);

        gameResetBtn.setVisibility(View.INVISIBLE);
        gameResetBtn.setOnClickListener(v->resetGame());


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "Boardbutton_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                imageButtons[i][j] = findViewById(resID);
                imageButtons[i][j].setOnClickListener(this);
                buttons[i][j] = "empty";

            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                imageButtons[i][j].setImageResource(R.drawable.empty);
                buttons[i][j] = "empty";
            }

        }
        isGameOver = false;
        gameResetBtn.setVisibility(View.INVISIBLE);
        linearLayout.setForeground(getDrawable(R.drawable.empty));
        playXO.setImageResource(R.drawable.xplay);
        TurnCount = 0;
        player1Turn = true;
    }

    @Override
    public void onClick(View v) {
        String vId= v.getResources().getResourceEntryName(v.getId());
        int i = vId.charAt(vId.length()-2)-48;
        int j = vId.charAt(vId.length()-1)-48;
        Log.d("TAG","I " + i);
        Log.d("TAG","j " + j);


        if (isGameOver||!buttons[i][j].equals("empty")){
            return;
        }

        if (player1Turn) {
            ((ImageButton) v).setImageResource(R.drawable.x);
            buttons[i][j] = "x";
        } else {
            ((ImageButton) v).setImageResource(R.drawable.o);
            buttons[i][j] = "o";
        }

        TurnCount++;
        if(checkForWin()) {
            isGameOver = true;
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        }
        else if(TurnCount == 9){
        draw();
        }
        else{
            if(player1Turn)
            {
                playXO.setImageResource(R.drawable.oplay);
            }
            else
            {
                playXO.setImageResource(R.drawable.xplay);
            }
            player1Turn = !player1Turn;
            Log.d("TAG","PLAYER " + player1Turn);
        }



    }
    private void player1Wins() {
        playXO.setImageResource(R.drawable.xwin);
        gameResetBtn.setVisibility(View.VISIBLE);
    }
    private void player2Wins() {
        playXO.setImageResource(R.drawable.owin);
        gameResetBtn.setVisibility(View.VISIBLE);
    }



    private void draw(){
        playXO.setImageResource(R.drawable.nowin);
        gameResetBtn.setVisibility(View.VISIBLE);
    }


    private boolean checkForWin() {
        return checkRows() || checkCols() || checkDiagonals();
    }




    @SuppressLint("UseCompatLoadingForDrawables")
    private boolean checkDiagonals() {
        if (buttons[0][0].equals(buttons[1][1])
                && buttons[0][0].equals(buttons[2][2])
                && !buttons[0][0].equals("empty")) {
            linearLayout.setForeground(getDrawable(R.drawable.mark1));
            return true;
        }
        if(buttons[0][2].equals(buttons[1][1])
                && buttons[0][2].equals(buttons[2][0])
    && !buttons[0][2].equals("empty")){
            linearLayout.setForeground(getDrawable(R.drawable.mark2));
            return true;
        }
        return false;
    }



    @SuppressLint("UseCompatLoadingForDrawables")
    private boolean checkCols() {
    if(buttons[0][0].equals(buttons[1][0])
    && buttons[0][0].equals(buttons[2][0])
    && !buttons[0][0].equals("empty")){
       linearLayout.setForeground(getDrawable(R.drawable.mark3));
       return true;
    }
        if(buttons[0][1].equals(buttons[1][1])
                && buttons[0][1].equals(buttons[2][1])
                && !buttons[0][1].equals("empty")){
            linearLayout.setForeground(getDrawable(R.drawable.mark4));
            return true;
        }
        if(buttons[0][2].equals(buttons[1][2])
                && buttons[0][2].equals(buttons[2][2])
                && !buttons[0][2].equals("empty")){
            linearLayout.setForeground(getDrawable(R.drawable.mark5));
            return true;
        }
    return false;
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private boolean checkRows() {
        if(buttons[0][0].equals(buttons[0][1])
                && buttons[0][0].equals(buttons[0][2])
                && !buttons[0][0].equals("empty")){
            linearLayout.setForeground(getDrawable(R.drawable.mark3r));
            return true;
        }
        if(buttons[1][0].equals(buttons[1][1])
                && buttons[1][0].equals(buttons[1][2])
                && !buttons[1][0].equals("empty")){
            linearLayout.setForeground(getDrawable(R.drawable.mark4r));
            return true;
        }
        if(buttons[2][0].equals(buttons[2][1])
                && buttons[2][0].equals(buttons[2][2])
                && !buttons[2][0].equals("empty")){
            linearLayout.setForeground(getDrawable(R.drawable.mark5r));
            return true;
        }
        return false;
    }



}