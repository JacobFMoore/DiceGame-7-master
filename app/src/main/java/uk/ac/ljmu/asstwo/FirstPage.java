package uk.ac.ljmu.asstwo;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class FirstPage extends AppCompatActivity {

    //declarations go here.
    Button begin, exitB, highScore;
    EditText playerOne, playerTwo;
    TextView versus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        //Declartions of items.
        begin = (Button) findViewById(R.id.Begin);
        playerOne = (EditText) findViewById(R.id.PlayerOne);
        playerTwo = (EditText) findViewById(R.id.PlayerTwo);
        versus = (TextView) findViewById(R.id.Versus);
        exitB = (Button) findViewById(R.id.ExitB);
        highScore = (Button) findViewById(R.id.HighScore);

        //Goes to the High Scores page, nothing else needed.
        highScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstPage.this, HighScores.class));
            }
        });

        //Exits the system, shuts down completely.
        exitB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                moveTaskToBack(true);
                System.exit(0);
            }
        });

        //executes the object which begins game saves player name data.
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Begin();
            }
        });

    }

    public void Begin() {
        startActivity(new Intent(FirstPage.this, AssTwo.class));

        //Saves the two entered player names to String to be saved in internal storage.
        String P1ToSave = playerOne.getText().toString();
        String P2ToSave = playerTwo.getText().toString();

        try {
            //Saves player ones name inside of internal storage to be called later.
            FileOutputStream fileOutputStream1 = openFileOutput("Player One Save File.txt", MODE_PRIVATE);
            fileOutputStream1.write(P1ToSave.getBytes());
            fileOutputStream1.close();

            //Saves player ones name inside of internal storage to be called later.
            FileOutputStream fileOutputStream2 = openFileOutput("Player Two Save File.txt", MODE_PRIVATE);
            fileOutputStream2.write(P2ToSave.getBytes());
            fileOutputStream2.close();

            //Sends message to confirm that names have been saved.
            //Toast.makeText(getApplicationContext(), "Player Saved", Toast.LENGTH_SHORT).show();

            //Text is then reset to nothing to be updated if players want another game.
            playerOne.setText("");
            playerTwo.setText("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            //begins the reading of the text fiel containing the player ones name.
            FileInputStream fileInputStream1 = openFileInput("Player One Save File.txt");
            InputStreamReader inputStreamReader1 = new InputStreamReader(fileInputStream1);

            //begins the reading of the text fiel containing the player ones name.
            FileInputStream fileInputStream2 = openFileInput("Player Two Save File.txt");
            InputStreamReader inputStreamReader2 = new InputStreamReader(fileInputStream2);

            //Selects both player one and player twos name to be read in AssTwo Class.
            BufferedReader bufferedReader1 = new BufferedReader(inputStreamReader1);
            BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader2);
            StringBuffer stringBuffer1 = new StringBuffer();
            StringBuffer stringBuffer2 = new StringBuffer();

            //String used to help read lines of both of the
            String lines1;
            while ((lines1 = bufferedReader1.readLine()) != null) {
                stringBuffer1.append(lines1 + "\n");
            }
            String lines2;
            while ((lines2 = bufferedReader2.readLine()) != null) {
                stringBuffer2.append(lines2 + "\n");
            }

            //Sends of the data for the first player and second player to the AssTwo page to be
            //displayed in the texviews.
            Intent I = new Intent(FirstPage.this, AssTwo.class);

            //Pushes the player names to be receieved on the AssTwo pages/
            I.putExtra("PlayerUn", stringBuffer1.toString());
            I.putExtra("PlayerDeux", stringBuffer2.toString());

            //Starts intent activity.
            startActivity(I);

            //displayVersus.setText(stringBuffer2.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
