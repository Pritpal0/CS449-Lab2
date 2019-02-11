package com.example.umpirebuddy;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private int ballCounter = 0;
    private int strikeCounter = 0;
    private  int outcounter = 0;
    TextView Strike;
    TextView Ball;
    TextView outs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Strike = (TextView) findViewById(R.id.StrikeViewCounter);
        Ball = (TextView) findViewById(R.id.BallViewCounter);
        outs = (TextView) findViewById(R.id.totalouts);

        SharedPreferences settings = getSharedPreferences("Preferred", 0);
        outcounter = settings.getInt("Total Outs",outcounter);
        totaloutcounter();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_option_reset:
                setContentView(R.layout.activity_main);

                outcounter = 0;
                ballCounter = 0;
                strikeCounter = 0;
                Ball.setText("Ball: " + ballCounter);
                Strike.setText("Strike: " + strikeCounter);
                outs.setText("Total Outs: " + outcounter);
                return true;

            case R.id.menu_option_about:
                setContentView(R.layout.aboutpage);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void strikeButtonOnClick(View V){

        if (strikeCounter >=2 ){
            strikemessage();
            strikeCounter = 0;
            ballCounter = 0;
            outcounter++;
            Ball.setText("Ball: " + ballCounter);
            Strike.setText("Strike: " + strikeCounter);
            outs.setText("Total Outs: "+ outcounter);

            SharedPreferences settings = getSharedPreferences("Preferred", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("Total Outs", outcounter);
            editor.commit();

        }
        else {

            strikeCounter++;
            Strike.setText("Strike: " + strikeCounter);
        }
    }

    public void BallButtonOnClick(View V){
         TextView Strike = (TextView) findViewById(R.id.StrikeViewCounter);
         TextView Ball = (TextView) findViewById(R.id.BallViewCounter);
        if (ballCounter >= 3){
            ballmessage();
            ballCounter =0;
            strikeCounter =0;
            Ball.setText("Ball: " + ballCounter);
            Strike.setText("Strike: " + strikeCounter);
        }
        else {
            ballCounter++;
            Ball.setText("Ball: " + ballCounter);
        }

    }


    public void ballmessage(){
        AlertDialog.Builder ballAlert = new AlertDialog.Builder(MainActivity.this);
        ballAlert.setTitle("Walk!");
        ballAlert.setMessage("Player Walk's!");
        ballAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        ballAlert.show();
    }
    public void strikemessage(){
        AlertDialog.Builder strikeAlert = new AlertDialog.Builder(MainActivity.this);
        strikeAlert.setTitle("Strike!");
        strikeAlert.setMessage("Player is Out!");
        strikeAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        strikeAlert.show();
    }
    public void backtoapp(View vw){
        setContentView(R.layout.activity_main);
    }

    public void totaloutcounter(){
        TextView r = (TextView)findViewById(R.id.totalouts);
        r.setText("Total Outs: " + Integer.toString(outcounter));
    }

}

