package gncis.com.example.android.fitnesslogger;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ExerciseCreate extends AppCompatActivity {

    TextInputEditText name, calories, reptime;
    Button cancel, create;
    RadioGroup measureIn;
    RadioButton repTime;
    int tr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_create);

        cancel = findViewById(R.id.ExcersizeCancel);
        create = findViewById(R.id.ExcersizeCreate);
        name = findViewById(R.id.ExcersizeName);
        calories = findViewById(R.id.ExcersizeCalories);
        reptime = findViewById(R.id.ExcersizeRepTime);
        measureIn = findViewById(R.id.ExcersizeMeasureIn);

        onClickListener();
    }

    public void onClickListener() {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ExerciseCreate.this);
                builder.setMessage("Are you sure want to cancel creating this excersize?");
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

                if(name.getText().toString().equals("")
                        || calories.getText().toString().equals("")
                        || reptime.getText().toString().equals(""))
                    Toast.makeText(ExerciseCreate.this,
                            "Fields cannot be empty!", Toast.LENGTH_LONG).show();

                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ExerciseCreate.this);
                    builder.setMessage("Are you sure want to create this exercise?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Exercise exercise = new Exercise();

                            repTime = findViewById(measureIn.getCheckedRadioButtonId());
                            if (repTime.getText().toString().equals("Repetitions"))
                                tr = 0;
                            else
                                tr = 1;

                            exercise.setTr(tr);
                            exercise.setName(name.getText().toString());
                            exercise.setCal(Integer.parseInt(calories.getText().toString()));
                            exercise.setRepTime(Integer.parseInt(reptime.getText().toString()));

                            ExerciseData exerciseData = new ExerciseData(getApplicationContext());

                            exerciseData.enterExcersize(exercise);

                            Toast.makeText(ExerciseCreate.this, "Exercise Sucessfully Created", Toast.LENGTH_SHORT).show();
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
        startActivity(new Intent(ExerciseCreate.this, ExerciseActivity.class));
    }
}
