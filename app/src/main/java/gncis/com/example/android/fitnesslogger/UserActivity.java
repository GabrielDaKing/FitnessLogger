package gncis.com.example.android.fitnesslogger;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    TextInputEditText height, weight, caloriIn, weightGoal, age;
    Button Confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        age = findViewById(R.id.userAge);
        height = findViewById(R.id.userHeight);
        weight = findViewById(R.id.userWeight);
        weightGoal = findViewById(R.id.userWeightGoal);
        caloriIn = findViewById(R.id.userCalorieIn);
        Confirm = findViewById(R.id.userConfirm);

        initialize();
        onClickListener();

    }

    public void initialize() {
        try {


            if (!SPManager.getUserAge(this).equals("empty"))
                age.setText(SPManager.getUserAge(this));
            if (!SPManager.getUserHeight(this).equals("empty"))
                height.setText(SPManager.getUserHeight(this));
            if (!SPManager.getUserWeight(this).equals("empty"))
                weight.setText(SPManager.getUserWeight(this));
            if (!SPManager.getUserCalorieIntake(this).equals("empty"))
                caloriIn.setText(SPManager.getUserCalorieIntake(this));
            if (!SPManager.getUserWeightGoal(this).equals("empty"))
                weightGoal.setText(SPManager.getUserWeightGoal(this));
        } catch (Exception e) {
        }
    }

    public void onClickListener() {
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SPManager.setUserAge(getApplicationContext(), age.getText().toString());
                    SPManager.setUserHeight(getApplicationContext(), height.getText().toString());
                    SPManager.setUserWeight(getApplicationContext(), weight.getText().toString());
                    SPManager.setUserCalorieIntake(getApplicationContext(), caloriIn.getText().toString());
                    SPManager.setUserWeightGoal(getApplicationContext(), weightGoal.getText().toString());
                } catch (Exception e) {
                }

                startActivity(new Intent(UserActivity.this, MainMenu.class));
                Toast.makeText(UserActivity.this, "Details Set", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
