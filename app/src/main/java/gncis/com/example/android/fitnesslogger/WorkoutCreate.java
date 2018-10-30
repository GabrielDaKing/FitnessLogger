package gncis.com.example.android.fitnesslogger;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class WorkoutCreate extends AppCompatActivity {

    TextInputEditText name, calories, reptime;
    Button cancel, create;
    RadioGroup measureIn;
    RadioButton repTime;
    int tr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_create);

        cancel = findViewById(R.id.WorkoutCancel);
        create = findViewById(R.id.WorkoutCreate);
        name = findViewById(R.id.WorkoutName);
        calories = findViewById(R.id.WorkoutCalories);
        reptime = findViewById(R.id.WorkoutRepTime);
        measureIn = findViewById(R.id.WorkoutMeasureIn);

        onClickListener();
    }

    public void onClickListener() {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(WorkoutCreate.this);
                builder.setMessage("Are you sure want to cancel creating this workout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();

            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name.getText().toString().equals("")
                        || calories.getText().toString().equals("")
                        || reptime.getText().toString().equals(""))
                    Toast.makeText(WorkoutCreate.this,
                            "Fields cannot be empty!", Toast.LENGTH_LONG).show();

                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(WorkoutCreate.this);
                    builder.setMessage("Are you sure want to create this workout?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Workout workout = new Workout();

                            repTime = findViewById(measureIn.getCheckedRadioButtonId());
                            if (repTime.getText().toString().equals("Repetitions"))
                                tr = 0;
                            else
                                tr = 1;

                            workout.setTr(tr);
                            workout.setName(name.getText().toString());
                            workout.setCal(Integer.parseInt(calories.getText().toString()));
                            workout.setRepTime(Integer.parseInt(reptime.getText().toString()));

                            WorkoutData workoutData = new WorkoutData(getApplicationContext());
                            workoutData.enterWorkout(workout);

                            Toast.makeText(WorkoutCreate.this, "Workout Sucessfully Created", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        startActivity(new Intent(WorkoutCreate.this, WorkoutActivity.class));
    }
}
