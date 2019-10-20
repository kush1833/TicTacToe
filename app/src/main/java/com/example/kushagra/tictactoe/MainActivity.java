package com.example.kushagra.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    boolean player1Turn = true;
    int roundCount = 0;
    Button buttons[][] = new Button[3][3];
    Button resetBtn;
    TextView player1TextView, player2TextView;
    int player1Point = 0;
    int player2Point = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        // Set listeners for all buttons
        for(int i = 0; i < 3; i++){
            for(int j = 0;j < 3; j++){
                String s = "tt"+i+""+j+"Btn";
                int btnid = getResources().getIdentifier(s, "id", getPackageName());
                buttons[i][j] = findViewById(btnid);
                buttons[i][j].setOnClickListener(this);
            }
        }


        resetBtn = findViewById(R.id.resetBtn);

        player1TextView = findViewById(R.id.player1TextView);
        player2TextView = findViewById(R.id.player2TextView);

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1Point = 0;
                player2Point = 0;
                resetField();
                updatePoints();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals(""))
            return;
        if(player1Turn){
            ((Button)v).setText("X");
        }
        else{
            ((Button)v).setText("O");
        }
        roundCount++;
        if(checkForWin()){
            if(player1Turn){
                player1Win();
            }
            else{
                player2Win();
            }
        }
        else if(roundCount == 9){
            matchDraw();
        }
        else{
            player1Turn = !player1Turn;
        }
    }
    
    // Check for Win
    private boolean checkForWin(){
        String[][] field= new String[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for(int i = 0; i < 3; i++) {
            if(field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")){
                return true;
            }
            else if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")){
                return true;
            }
        }
        if(field[0][0].equals(field[1][1]) && field[1][1].equals(field[2][2]) && !field[2][2].equals("")){
            return true;
        }
        else if(field[0][2].equals(field[1][1]) && field[1][1].equals(field[2][0]) && !field[2][0].equals("")){
            return true;
        }
        return false;
    }
    
    // If Player 1 wins
    private void player1Win(){
        player1Point++;
        Toast.makeText(this, "Player 1 wins", Toast.LENGTH_SHORT).show();
        updatePoints();
        resetField();
    }
    
    // If Player 2 wins
    private void player2Win(){
        player2Point++;
        Toast.makeText(this, "Player 2 wins", Toast.LENGTH_SHORT).show();
        updatePoints();
        resetField();
    }
    
    // match is draw
    private void matchDraw(){
        Toast.makeText(this, "Match Drawn", Toast.LENGTH_SHORT).show();
        resetField();
    }
    
    
    // Update the points of each player
    private void updatePoints(){
        player1TextView.setText("PLAYER 1:"+player1Point);
        player2TextView.setText("PLAYER 2:"+player2Point);
    }
    
    
    // Reset the game
    private void resetField(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        player1Turn = true;
    }
}
