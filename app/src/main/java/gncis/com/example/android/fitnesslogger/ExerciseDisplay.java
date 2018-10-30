package gncis.com.example.android.fitnesslogger;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ExerciseDisplay extends AppCompatActivity {

    TextInputEditText name,calories,reptime;
    Button cancel, modify;
    int ID,tr;
    RadioButton repTime;
    RadioGroup measureIn;
    ExerciseData exerciseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_display);

        cancel = findViewById(R.id.ExcersizeCancel);
        modify = findViewById(R.id.ExcersizeModify);
        name = findViewById(R.id.ExcersizeName);
        calories = findViewById(R.id.ExcersizeCalories);
        reptime = findViewById(R.id.ExcersizeRepTime);
        measureIn = findViewById(R.id.ExcersizeMeasureIn);

        ID = getIntent().getIntExtra("exercise_id",-1);

        exerciseData = new ExerciseData(this);

        Exercise exercise ;
        exercise = exerciseData.returnExercise(ID);

        name.setText(exercise.getName());
        calories.setText(String.valueOf(exercise.getCal()));
        reptime.setText(String.valueOf(exercise.getRepTime()));

        onClickListener();
    }

    public void onClickListener()
    {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(ExerciseDisplay.this, "", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().equals("")
                        || calories.getText().toString().equals("")
                        || reptime.getText().toString().equals(""))
                    Toast.makeText(ExerciseDisplay.this,
                            "Fields cannot be empty!", Toast.LENGTH_LONG).show();

                else {

                    Exercise exercise = new Exercise();

                    repTime = findViewById(measureIn.getCheckedRadioButtonId());
                    if (repTime.getText().toString().equals("Repetitions"))
                        tr = 0;
                    else
                        tr = 1;

                    exercise.setId(ID);
                    exercise.setTr(tr);
                    exercise.setName(name.getText().toString());
                    exercise.setCal(Integer.parseInt(calories.getText().toString()));
                    exercise.setRepTime(Integer.parseInt(reptime.getText().toString()));

                    ExerciseData exerciseData = new ExerciseData(getApplicationContext());
                    exerciseData.updateExercise(exercise);

                    Toast.makeText(ExerciseDisplay.this, "Workout Sucessfully Modified", Toast.LENGTH_SHORT).show();
                    finish();
                }


            }
        });
    }
}
