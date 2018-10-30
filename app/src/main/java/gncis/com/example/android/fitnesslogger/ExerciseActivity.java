package gncis.com.example.android.fitnesslogger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity {

    ExerciseAdapter exerciseAdapter;
    ExerciseData exerciseData;

    Button NewExcersize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        exerciseData = new ExerciseData(this);

        ListView listView = findViewById(R.id.excersizesView);
        ArrayList<Exercise> arrayList = exerciseData.allExcersizes();
        exerciseAdapter = new ExerciseAdapter(this, R.layout.excersize_tile, arrayList);
        listView.setAdapter(exerciseAdapter);

        NewExcersize = findViewById(R.id.NewExcersize);

        NewExcersize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ExerciseActivity.this, ExerciseCreate.class));
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
