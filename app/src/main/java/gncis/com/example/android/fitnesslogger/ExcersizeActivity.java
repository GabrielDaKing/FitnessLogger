package gncis.com.example.android.fitnesslogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ExcersizeActivity extends AppCompatActivity {

    ExcersizeAdapter excersizeAdapter;
    ExcersizeData excersizeData;

    Button NewExcersize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excersize);

        excersizeData = new ExcersizeData(this);

        ListView listView = findViewById(R.id.excersizesView);
        ArrayList<Excersize> arrayList =  excersizeData.allExcersizes();
        excersizeAdapter = new ExcersizeAdapter(this, R.layout.excersize_tile, arrayList);
        listView.setAdapter(excersizeAdapter);

        NewExcersize = findViewById(R.id.NewExcersize);

        NewExcersize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ExcersizeActivity.this,ExcersizeCreate.class));
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
