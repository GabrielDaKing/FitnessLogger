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

public class WorkoutDisplay extends AppCompatActivity {

    TextInputEditText name,calories,reptime;
    Button cancel, modify;
    int ID,tr;
    WorkoutData workoutData;
    RadioButton repTime;
    RadioGroup measureIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_display);

        cancel = findViewById(R.id.WorkoutCancel);
        modify = findViewById(R.id.WorkoutModify);
        name = findViewById(R.id.WorkoutName);
        calories = findViewById(R.id.WorkoutCalories);
        reptime = findViewById(R.id.WorkoutRepTime);
        measureIn = findViewById(R.id.WorkoutMeasureIn);

        ID = getIntent().getIntExtra("workout_id",-1);

        workoutData = new WorkoutData(this);

        Workout workout = workoutData.returnWorkout(ID);

        name.setText(workout.getName());
        calories.setText(workout.getCal());
        reptime.setText(workout.getRepTime());

        onClickListener();
    }

    public void onClickListener()
    {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(WorkoutDisplay.this, "Record sucessfully Deleted", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().equals("")
                        || calories.getText().toString().equals("")
                        || reptime.getText().toString().equals(""))
                    Toast.makeText(WorkoutDisplay.this,
                            "Fields cannot be empty!", Toast.LENGTH_LONG).show();

                else {

                            Workout workout = new Workout();

                            repTime = findViewById(measureIn.getCheckedRadioButtonId());
                            if (repTime.getText().toString().equals("Repetitions"))
                                tr = 0;
                            else
                                tr = 1;

                            workout.setId(ID);

                            workout.setTr(tr);
                            workout.setName(name.getText().toString());
                            workout.setCal(Integer.parseInt(calories.getText().toString()));
                            workout.setRepTime(Integer.parseInt(reptime.getText().toString()));

                            WorkoutData workoutData = new WorkoutData(getApplicationContext());
                            workoutData.updateWorkout(workout);

                            Toast.makeText(WorkoutDisplay.this, "Workout Sucessfully Modified", Toast.LENGTH_SHORT).show();
                            finish();
                        }


            }
        });
    }
}
