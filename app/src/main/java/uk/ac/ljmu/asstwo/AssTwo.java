package uk.ac.ljmu.asstwo;

//All Android Imports, Covers all basic requirements for the App Conversion
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import java.util.Random;

//The Primary Application Class
public class AssTwo extends AppCompatActivity {

    //Private Variables and objects for the dice images, dice
    //randomiser, player number and text display.
    private static int player = 1;
    DatabaseHelper mDatabaseHelper;
    private ImageView ImageViewDie1, ImageViewDie2;
    private Random rng = new Random();
    private TextView Statement, Player, PlayerOneScore, PlayerTwoScore;
    private int i1, i2 = 0;

    //Object which calls objects on what to do when both
    //the exit and roll dice button is clicked.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asstwo);

        //Pulls the buttons and text from the front app and references them.
        ImageViewDie1 = (ImageView) findViewById(R.id.imageViewDie1);
        ImageViewDie2 = (ImageView) findViewById(R.id.imageViewDie2);
        Statement = (TextView) findViewById(R.id.Statement);
        Player = (TextView) findViewById(R.id.Player);
        final Button rollDice = (Button) findViewById(R.id.rollDice);
        Button exit = (Button) findViewById(R.id.Exit);
        PlayerOneScore = (TextView) findViewById(R.id.PlayerOneScore);
        PlayerTwoScore = (TextView) findViewById(R.id.PlayerTwoScore);
        mDatabaseHelper = new DatabaseHelper(this);

        //This is used for operating the Roll Dice Button.
        //It simply executes the rollDice() Object.
        rollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    rollDice();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //This simply just allows the exit button to shut down the app
        //This is also responsible for updating the SQLite database.
        exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //pulling player name data from First Page.
                Intent I2 = getIntent();

                //Player 1 and player 2's name.
                String PlayerUn = I2.getStringExtra("PlayerUn");
                String PlayerDeux = I2.getStringExtra("PlayerDeux");

                //Establishes the data for and score for both players.
                String newEntry = PlayerUn.toString();
                int newEntry2 = i1;
                String newEntry3 = PlayerDeux.toString();
                int newEntry4 = i2;

                //Warning in case player puts nothing in text field.
                if (PlayerUn.length() != 0) {
                    AddData(newEntry, newEntry2);
                    AddData(newEntry3, newEntry4);
                } else {
                    toastMessage("Put something in the text field!");
                }
                    startActivity(new Intent(AssTwo.this, FirstPage.class));
            }
        });


    }

    //Responsible for adding data to database.
    public void AddData(String Username1, int Score1) {

        boolean insertData = mDatabaseHelper.addData(Username1, Score1);

        //warning message in case data does not go in.
        //Could be removed now that testing is done?
        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something Went Wrong");
        }
    }

    //Executes warning message.
    private void toastMessage(String Message) {
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
    }

    //--------------------------------------------------------------------------
    //This is the Roll Dice Object, the main function for the application.
    public void rollDice() throws InterruptedException {

        final Animation anim1 = AnimationUtils.loadAnimation(AssTwo.this, R.anim.shake);
        //final Animation anim2 = AnimationUtils.loadAnimation(AssTwo.this, R.anim.shake);

        final Animation.AnimationListener animationListener = new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                final Button rollDice = (Button) findViewById(R.id.rollDice);

                rollDice.setEnabled(false);

            }

            @Override
            public void onAnimationEnd(Animation animation) {


                //Dice number randomiser.
                int dice1 = rng.nextInt(6) + 1;
                int dice2 = rng.nextInt(6) + 1;

                //Brings through the player names written out.
                Intent I2 = getIntent();

                String PlayerUn = I2.getStringExtra("PlayerUn");
                String PlayerDeux = I2.getStringExtra("PlayerDeux");

                //displays message for both players scores.
                PlayerOneScore.setText(PlayerUn + ": " + i1);
                PlayerTwoScore.setText(PlayerDeux + ": " + i2);

                //This sets what the Text says, such as which player is rolling.
                switch (player) {
                    case 1: {
                        Player.setText(PlayerUn);
                        Player.setTextColor(0xFF002aff);
                        Statement.setText(" rolls :");
                        break;
                    }
                    case 2: {
                        Player.setText(PlayerDeux);
                        Player.setTextColor(0xFFff0000);
                        Statement.setText(" rolls :");
                        break;
                    }
                }

                //Displays the images for dice 1. Each of the six Cases
                //display each of the six faces of a dice.
                switch (dice1) {
                    case 1: {
                        ImageViewDie1.setImageResource(R.drawable.die1);
                        break;
                    }

                    case 2: {
                        ImageViewDie1.setImageResource(R.drawable.die2);
                        break;
                    }

                    case 3: {
                        ImageViewDie1.setImageResource(R.drawable.die3);
                        break;
                    }

                    case 4: {
                        ImageViewDie1.setImageResource(R.drawable.die4);
                        break;
                    }

                    case 5: {
                        ImageViewDie1.setImageResource(R.drawable.die5);
                        break;
                    }

                    case 6: {
                        ImageViewDie1.setImageResource(R.drawable.die6);
                        break;
                    }
                }

                //Displays the images for dice 1. Each of the six Cases
                //display each of the six faces of a dice.
                switch (dice2) {
                    case 1: {
                        ImageViewDie2.setImageResource(R.drawable.die1);
                        break;
                    }

                    case 2: {
                        ImageViewDie2.setImageResource(R.drawable.die2);
                        break;
                    }

                    case 3: {
                        ImageViewDie2.setImageResource(R.drawable.die3);
                        break;
                    }

                    case 4: {
                        ImageViewDie2.setImageResource(R.drawable.die4);
                        break;
                    }

                    case 5: {
                        ImageViewDie2.setImageResource(R.drawable.die5);
                        break;
                    }

                    case 6: {
                        ImageViewDie2.setImageResource(R.drawable.die6);
                        break;
                    }
                }

                //This contains the statement produced if both the dice equal the same.
                if (dice1 == dice2) {

                    //Statement displayed when winner achieved.
                    switch (player) {
                        case 1: {
                            Player.setText(PlayerUn);
                            Statement.setText(" wins !");
                            PlayerOneScore.setText(PlayerUn + ": " + i1++);
                            break;
                        }
                        case 2: {
                            Player.setText(PlayerDeux);
                            Statement.setText(" wins !");
                            PlayerTwoScore.setText(PlayerDeux + ": " + i2++);
                            break;
                        }
                    }
                }

                //This switches the player after the dice has been rolled.
                if (player == 1) {
                    player = 2;
                } else {
                    player = 1;
                }

                final Button rollDice = (Button) findViewById(R.id.rollDice);

                rollDice.setEnabled(true);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };

        //Returns the image to how it was after the animation.
        anim1.setAnimationListener(animationListener);

        ImageViewDie1.startAnimation(anim1);
        ImageViewDie2.startAnimation(anim1);

        //--------------------------------------------------------------------------

    }
}
