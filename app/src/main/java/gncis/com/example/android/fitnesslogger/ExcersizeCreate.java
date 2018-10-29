package gncis.com.example.android.fitnesslogger;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ExcersizeCreate extends AppCompatActivity {

    TextInputEditText name,calories,reptime;
    Button cancel, create ;
    RadioGroup measureIn;
    RadioButton repTime;
    int tr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excersize_create);

        cancel = findViewById(R.id.ExcersizeCancel);
        create = findViewById(R.id.ExcersizeCreate);
        name = findViewById(R.id.ExcersizeName);
        calories = findViewById(R.id.ExcersizeCalories);
        reptime = findViewById(R.id.ExcersizeRepTime);
        measureIn = findViewById(R.id.ExcersizeMeasureIn);

        onClickListener();
    }

    public void onClickListener()
    {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder=new AlertDialog.Builder(ExcersizeCreate.this);
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

                AlertDialog.Builder builder=new AlertDialog.Builder(ExcersizeCreate.this);
                builder.setMessage("Are you sure want to create this excersize?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Excersize excersize = new Excersize();

                        repTime = findViewById(measureIn.getCheckedRadioButtonId());
                        if(repTime.getText().toString()=="Repetitions")
                            tr = 0;
                        else
                            tr = 1;

                        excersize.setTr(tr);
                        excersize.setName(name.getText().toString());
                        excersize.setCal(Integer.parseInt(calories.getText().toString()));
                        excersize.setRepTime(Integer.parseInt(reptime.getText().toString()));

                        ExcersizeData excersizeData = new ExcersizeData(getApplicationContext());

                        excersizeData.enterExcersize(excersize);

                        Toast.makeText(ExcersizeCreate.this, "Record Sucessfully Created", Toast.LENGTH_SHORT).show();
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
    }

    @Override
    protected void onPause() {
        super.onPause();
        startActivity(new Intent(ExcersizeCreate.this, ExcersizeActivity.class));
    }
}
