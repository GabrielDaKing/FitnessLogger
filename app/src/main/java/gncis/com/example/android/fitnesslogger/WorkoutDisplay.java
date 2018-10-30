package gncis.com.example.android.fitnesslogger;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WorkoutDisplay extends AppCompatActivity {

    TextInputEditText name,calories,reptime;
    Button delete, modify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_display);

        delete = findViewById(R.id.WorkoutDelete);
        modify = findViewById(R.id.WorkoutModify);
        name = findViewById(R.id.WorkoutName);
        calories = findViewById(R.id.WorkoutCalories);
        reptime = findViewById(R.id.WorkoutRepTime);

        onClickListener();
    }

    public void onClickListener()
    {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WorkoutDisplay.this, "Record sucessfully Deleted", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WorkoutDisplay.this, "Record Sucessfully Created", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
