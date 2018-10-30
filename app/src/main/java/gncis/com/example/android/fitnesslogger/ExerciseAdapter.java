package gncis.com.example.android.fitnesslogger;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ExerciseAdapter extends ArrayAdapter<Exercise> {

    TextView Name, Cal, Rep;

    ExerciseAdapter(Context context, int resource, ArrayList<Exercise> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.excersize_tile, parent, false);
        }

        Name = convertView.findViewById(R.id.ExcersizeName);
        Cal = convertView.findViewById(R.id.ExcersizeCalories);
        Rep = convertView.findViewById(R.id.ExcersizeRepTime);
        LinearLayout Tile = convertView.findViewById(R.id.ExcersizeTile);

        Exercise exercise = getItem(position);

        if (exercise != null) {

            Name.setText(exercise.getName());
            String cal = String.valueOf(exercise.getCal());
            Cal.setText(cal + " cal");
            String rep = String.valueOf(exercise.getRepTime());
            Rep.setText(rep);

            if (exercise.getTr() == 0)
                Rep.setText(exercise.getRepTime() + " Reps ");
            else
                Rep.setText(exercise.getRepTime() + "s ");


            Tile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("What do you want to do to this exercise?");
                    builder.setPositiveButton("Modify", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    });

                    builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }
            });

        }

        return convertView;
    }

}


