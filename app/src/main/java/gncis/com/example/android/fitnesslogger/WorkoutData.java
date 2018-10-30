package gncis.com.example.android.fitnesslogger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

public class WorkoutData extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "workout";
    private static final String NAME = "name";
    private static final String WORKOUT_ID = BaseColumns._ID;
    private static final String CALORIES = "calories";
    private static final String REPTIME = "repTime";
    private static final String TIME_OR_REPS = "tr";

    private static final String DATABASE_NAME = "Fitness2.db";

    private static final int DATABASE_VERSION = 1;

    WorkoutData(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_BUS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
                + NAME + " DATETIME ,"
                + WORKOUT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + REPTIME + " INTEGER, "
                + CALORIES + " INTEGER, "
                + TIME_OR_REPS + " INTEGER ); ";

        //Log.v(TAG, "blahCREATES BUS TABLE");
        db.execSQL(SQL_CREATE_BUS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + " ; ");
        onCreate(db);
    }


    public void enterWorkout(Workout workout) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, workout.getName());
        contentValues.put(CALORIES, workout.getCal());
        contentValues.put(REPTIME, workout.getRepTime());
        contentValues.put(TIME_OR_REPS, workout.getTr());

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    public ArrayList<Workout> allWorkouts() {

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " ;";
        ArrayList<Workout> workouts = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Workout workout = new Workout();
                workout.setId(cursor.getInt(cursor.getColumnIndex(WORKOUT_ID)));
                workout.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                workout.setCal(cursor.getInt(cursor.getColumnIndex(CALORIES)));
                workout.setRepTime(cursor.getInt(cursor.getColumnIndex(REPTIME)));
                workout.setTr(cursor.getInt(cursor.getColumnIndex(TIME_OR_REPS)));

                workouts.add(workout);
                cursor.moveToNext();
            }
            cursor.close();
        }
        sqLiteDatabase.close();
        return workouts;
    }

    public void deleteWorkout(Workout workout){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + WORKOUT_ID + " = " + workout.getId() + " ; ";
        sqLiteDatabase.execSQL(query);
    }

    public Workout returnWorkout(int id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME
                + " WHERE " + WORKOUT_ID + " = " + id;

        Workout workout = new Workout();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();

            workout.setId(cursor.getInt(cursor.getColumnIndex(WORKOUT_ID)));
            workout.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            workout.setCal(cursor.getInt(cursor.getColumnIndex(CALORIES)));
            workout.setRepTime(cursor.getInt(cursor.getColumnIndex(REPTIME)));
            workout.setTr(cursor.getInt(cursor.getColumnIndex(TIME_OR_REPS)));
            cursor.close();
        }
        return workout;
    }

    public void updateWorkout(Workout workout) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET "
                + NAME + " = '" + workout.getName() + "', "
                + REPTIME + " = '" + workout.getRepTime() + "', "
                + CALORIES + " = '" + workout.getCal() + "', "
                + TIME_OR_REPS + " = '" + workout.getTr()
                + " WHERE " + WORKOUT_ID + " = " + workout.getId();
        sqLiteDatabase.execSQL(query);
    }

}
