package gncis.com.example.android.fitnesslogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    Button UserButton, ExcersizeButton, WorkoutButton, IntervalTimerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        UserButton = findViewById(R.id.UserButton);
        ExcersizeButton = findViewById(R.id.ExcersizeButton);
        WorkoutButton = findViewById(R.id.WorkoutButton);
        IntervalTimerButton = findViewById(R.id.IntervalTimer);

        //setTitle("Main Menu");

        onClickListeners();
    }

    public void onClickListeners()
    {
        UserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this,UserActivity.class));
            }
        });

        WorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this,WorkoutActivity.class));
            }
        });

        ExcersizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this,ExcersizeActivity.class));
            }
        });

        IntervalTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this,IntervalTimerActivity.class));
            }
        });
    }
}
