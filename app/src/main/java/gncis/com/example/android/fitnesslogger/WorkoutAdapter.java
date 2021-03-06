package gncis.com.example.android.fitnesslogger;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by Samreen on 11-03-2018.
 */

public class WorkoutAdapter extends ArrayAdapter<Workout> {

    WorkoutData workoutData;

    WorkoutAdapter(Context context, int resource, ArrayList<Workout> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.workout_tile, parent, false);
        }

        TextView Name = convertView.findViewById(R.id.WorkoutName);
        TextView Cal = convertView.findViewById(R.id.WorkoutCalories);
        TextView Rep = convertView.findViewById(R.id.WorkoutRepTime);
        LinearLayout Tile = convertView.findViewById(R.id.WorkoutTile);

        final Workout workout = getItem(position);

        Name.setText(workout.getName());
        String cal = String.valueOf(workout.getCal());
        Cal.setText(cal + " cal");
        String rep = String.valueOf(workout.getRepTime());
        Rep.setText(rep);

        if (workout.getTr() == 0)
            Rep.setText(workout.getRepTime()+" Reps ");
        else
            Rep.setText(workout.getRepTime()+"s ");

        assert workout != null;

        Tile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setMessage("What do you want to do to this workout?");
                builder.setPositiveButton("Modify", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Intent intent = new Intent(getContext(),WorkoutDisplay.class);
                        //intent.putExtra("workout_id", workout.getId());
                        //getContext().startActivity(intent);
                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        workoutData = new WorkoutData(getContext());
                        workoutData.deleteWorkout(workout);
                        Toast.makeText(getContext(), "Exercise Deleted", Toast.LENGTH_SHORT).show();
                        getContext().startActivity(new Intent(getContext(),WorkoutActivity.class));
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

        return convertView;
    }

}

