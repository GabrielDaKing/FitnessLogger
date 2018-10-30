package gncis.com.example.android.fitnesslogger;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class IntervalTimerActivity extends AppCompatActivity {

    static CountDownTimer countDownTimer1, countDownTimer2;
    TextView RestWork, Seconds;
    Button Start, Stop, Set;
    EditText WorkTime, RestTime, Sets;
    int restSec, workSec, set;
    int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval_timer);

        RestWork = findViewById(R.id.RestWork);
        Seconds = findViewById(R.id.Seconds);
        Start = findViewById(R.id.Start);
        Stop = findViewById(R.id.Stop);
        WorkTime = findViewById(R.id.WorkTime);
        RestTime = findViewById(R.id.RestTime);
        Sets = findViewById(R.id.Sets);

        onClickListener();

    }

    public void onClickListener() {

        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                restSec = -1;
                workSec = -1;
                set = -1;

                if (Sets.getText().toString().equals("")
                        || RestTime.getText().toString().equals("")
                        || WorkTime.getText().toString().equals(""))
                    Toast.makeText(IntervalTimerActivity.this,
                            "Values cannot be empty!", Toast.LENGTH_LONG).show();
                else {
                    set = Integer.parseInt(Sets.getText().toString());
                    restSec = Integer.parseInt(RestTime.getText().toString());
                    workSec = Integer.parseInt(WorkTime.getText().toString());

                    a = 0;

                    if (!(set <= 0 || restSec < 0 || workSec <= 0)) {

                        //Toast.makeText(IntervalTimerActivity.this, i, Toast.LENGTH_SHORT).show();

                        final NumberFormat f = new DecimalFormat("00");

                        countDownTimer1 = new CountDownTimer(workSec * 1000, 1000) {

                            public void onTick(long millisUntilFinished) {
                                RestWork.setText("Work");
                                if (a == 1) {
                                    cancel();
                                    Seconds.setText("00:00:00");
                                }
                                long hour = (millisUntilFinished / 3600000) % 24;
                                long min = (millisUntilFinished / 60000) % 60;
                                long sec = (millisUntilFinished / 1000) % 60;

                                Seconds.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));

                            }

                            public void onFinish() {
                                countDownTimer1.cancel();
                                Seconds.setBackgroundColor(Color.parseColor("#1976d2"));
                                Seconds.setBackgroundColor(Color.parseColor("#ff6659"));

                                countDownTimer2 = new CountDownTimer(restSec * 1000, 1000) {

                                    public void onTick(long millisUntilFinished) {
                                        RestWork.setText("Rest");
                                        if (a == 1) {
                                            cancel();
                                            Seconds.setText("00:00:00");
                                        }

                                        long hour = (millisUntilFinished / 3600000) % 24;
                                        long min = (millisUntilFinished / 60000) % 60;
                                        long sec = (millisUntilFinished / 1000) % 60;

                                        Seconds.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
                                    }

                                    public void onFinish() {
                                        Seconds.setBackgroundColor(Color.parseColor("#1976d2"));
                                        Seconds.setBackgroundColor(Color.parseColor("#ff6659"));
                                        countDownTimer2.cancel();
                                        if (set-- > 0)
                                            countDownTimer1.start();
                                    }

                                }.start();
                            }

                        }.start();
                    } else
                        Toast.makeText(IntervalTimerActivity.this, "Please enter valid Values", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = 1;
            }
        });

    }
}
