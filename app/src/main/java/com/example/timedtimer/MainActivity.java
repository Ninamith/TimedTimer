package com.example.timedtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView mTextField;

    TextView setTotalTimeInput;
    int setTotalTime;

    TextView setTimeInput;
    Button submitTimeButton;
    int setTime;

    int numOfIntervals;

    TextView leftTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leftTextField = (TextView) findViewById(R.id.textView3);

        button= (Button) findViewById(R.id.button);
        mTextField= (TextView) findViewById(R.id.textView);

        setTotalTimeInput = (EditText) findViewById(R.id.editText2);
        setTimeInput = (EditText) findViewById(R.id.editText1);
        submitTimeButton = (Button) findViewById(R.id.button1);
        submitTimeButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               setTotalTime = Integer.valueOf(setTotalTimeInput.getText().toString());
               setTotalTime = setTotalTime * 60 * 1000;
               setTime = Integer.valueOf(setTimeInput.getText().toString());
               setTime = setTime * 1000;
               numOfIntervals = setTotalTime/setTime;
            }

        });

        leftTextField.setText("Enter how long (in mins) you want the total timer to run for, and how long each interval is below!");
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                    new CountDownTimer(setTime, 1000) {
                        int counter = 1;
                        public void onTick(long millisUntilFinished) {
                            mTextField.setText(String.valueOf(millisUntilFinished / 1000));
                        }

                        public void onFinish() {
                            if(counter == numOfIntervals) {
                                leftTextField.setText(String.valueOf(numOfIntervals-counter) + " intervals left!");
                                mTextField.setText("done!");
                            } else {
                                leftTextField.setText(String.valueOf(numOfIntervals-counter) + " intervals left!");
                                counter++;
                                start();
                            }
                        }
                    }.start();
            }
        });
    }
}
