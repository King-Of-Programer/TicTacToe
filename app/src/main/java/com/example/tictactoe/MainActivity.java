package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {
    private boolean checkWin(boolean[] isButtonX, boolean[] isButtonClicked) {
        int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
        for (int[] winningPosition : winningPositions) {
            if (isButtonClicked[winningPosition[0]] && isButtonClicked[winningPosition[1]] && isButtonClicked[winningPosition[2]]
                    && isButtonX[winningPosition[0]] == isButtonX[winningPosition[1]]
                    && isButtonX[winningPosition[1]] == isButtonX[winningPosition[2]]) {
                return true;
            }
        }
        return false;
    }
    private void resetGame(Button[] b, boolean[] isButtonClicked, boolean[] isButtonX, boolean[] player) {
        for (int i = 0; i < 9; i++) {
            b[i].setText("");
            isButtonClicked[i] = false;
            isButtonX[i] = false;
        }
        player[0] = true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout layout = findViewById(R.id.layout);
        layout.setColumnCount(3);

        Button[] b = new Button[9];
        boolean[] isButtonClicked = new boolean[9];
        boolean[] isButtonX = new boolean[9];
        final boolean[] player = {true};

        for (int i = 0; i < 9; i++) {
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.setMargins(10, 10, 10, 10);
            layoutParams.width = 200;
            layoutParams.height = 200;

            b[i] = new Button(this);
            b[i].setLayoutParams(layoutParams);
            b[i].setText("");
            layout.addView(b[i]);

            final int index = i;
            b[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isButtonClicked[index]) {
                        if (player[0]) {
                            b[index].setText("X");
                            b[index].setTextColor(Color.RED);
                            isButtonX[index] = true;
                        } else {
                            b[index].setText("O");
                            b[index].setTextColor(Color.BLUE);
                            isButtonX[index] = false;
                        }
                        isButtonClicked[index] = true;
                        player[0] = !player[0];

                        if (checkWin(isButtonX, isButtonClicked)) {
                            Toast.makeText(MainActivity.this, "Игра окончена! Победитель: " + (player[0] ? "O" : "X"), Toast.LENGTH_LONG).show();
                            resetGame(b, isButtonClicked, isButtonX, player);
                        }
                    }
                }
            });
        }
    }

}
