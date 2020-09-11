package com.example.yoga_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity
{

    Button startBtn;
    TextView mTextView;
    private CountDownTimer countDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillIs;
    String buttonValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
         buttonValue= intent.getStringExtra("Value");

         int intValue = Integer.valueOf(buttonValue);




        switch (intValue) {
            case 1:
                setContentView(R.layout.activity_boat);
                break;
            case 2:
                setContentView(R.layout.activity_bow);
                break;
            case 3:
                setContentView(R.layout.activity_bridge);
                break;
            case 4:
                setContentView(R.layout.activity_chair);
                break;
            case 5:
                setContentView(R.layout.activity_child);
                break;
            case 6:
                setContentView(R.layout.activity_cobbler);
                break;
            case 7:
                setContentView(R.layout.activity_cow);
                break;

        }

        startBtn = findViewById(R.id.startButton);
        mTextView = findViewById(R.id.time);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    stopTimer();
                } else {
                    startTimer();
                }
            }
        });

    }

    private void stopTimer()
    {
     countDownTimer.cancel();
     mTimerRunning=false;
     startBtn.setText("Start");
    }

    private void startTimer()
    {
        final CharSequence value1 = mTextView.getText();
        String num1 = value1.toString();
        String num2 = num1.substring(0,2);
        String num3 = num1.substring(3,5);

        final int number = Integer.valueOf(num2)*60+Integer.valueOf(num3);
        mTimeLeftInMillIs = number*1000;

        countDownTimer =new CountDownTimer(mTimeLeftInMillIs,1000) {
            @Override
            public void onTick(long millisUntilFinished)
            {
                mTimeLeftInMillIs = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish()
            {
                int newValue = Integer.valueOf(buttonValue)+1;
                if (newValue<=7)
                {
                    Intent intent = new Intent(ThirdActivity.this,ThirdActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("Value",String.valueOf(newValue));
                    startActivity(intent);

                }
                else
                {
                    newValue = 1;
                    Intent intent = new Intent(ThirdActivity.this,ThirdActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("Value",String.valueOf(newValue));
                    startActivity(intent);
                }

            }
        }.start();
        startBtn.setText("Pause");
        mTimerRunning=true;
    }

    private void updateTimer()
    {
        int minutes = (int) mTimeLeftInMillIs/60000;
        int seconds = (int) mTimeLeftInMillIs%60000 / 1000;

        String timeLeftText = "";
        if (minutes<10)
            timeLeftText ="0";
        timeLeftText = timeLeftText+minutes+":";
        if(seconds<10)
            timeLeftText+="0";
            timeLeftText+=seconds;
        mTextView.setText(timeLeftText);

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        stopTimer();
        finish();
    }
}
