package gncis.com.example.android.fitnesslogger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class WorkoutActivity extends AppCompatActivity {

    WorkoutAdapter workoutAdapter;
    WorkoutData workoutData;

    Button NewWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        workoutData = new WorkoutData(this);

        ListView listView = findViewById(R.id.workoutsView);
        ArrayList<Workout> arrayList = workoutData.allWorkouts();
        workoutAdapter = new WorkoutAdapter(this, R.layout.workout_tile, arrayList);
        listView.setAdapter(workoutAdapter);

        NewWorkout = findViewById(R.id.NewWorkout);

        NewWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WorkoutActivity.this, WorkoutCreate.class));
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
