package gncis.com.example.android.fitnesslogger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

public class ExerciseData extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "exercise";
    private static final String NAME = "name";
    private static final String EXCERSIZE_ID = BaseColumns._ID;
    private static final String CALORIES = "calories";
    private static final String REPTIME = "repTime";
    private static final String TIME_OR_REPS = "tr";

    private static final String DATABASE_NAME = "Fitness.db";

    private static final int DATABASE_VERSION = 1;

    ExerciseData(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_BUS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
                + NAME + " DATETIME ,"
                + EXCERSIZE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
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
        db.close();
    }


    public void enterExcersize(Exercise exercise) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, exercise.getName());
        contentValues.put(CALORIES, exercise.getCal());
        contentValues.put(REPTIME, exercise.getRepTime());
        contentValues.put(TIME_OR_REPS, exercise.getTr());

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    public ArrayList<Exercise> allExcersizes() {

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " ;";
        ArrayList<Exercise> exercises = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if(cursor!=null) {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Exercise exercise = new Exercise();
                exercise.setId(cursor.getInt(cursor.getColumnIndex(EXCERSIZE_ID)));
                exercise.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                exercise.setCal(cursor.getInt(cursor.getColumnIndex(CALORIES)));
                exercise.setRepTime(cursor.getInt(cursor.getColumnIndex(REPTIME)));
                exercise.setTr(cursor.getInt(cursor.getColumnIndex(TIME_OR_REPS)));

                exercises.add(exercise);
                cursor.moveToNext();
            }

            cursor.close();
        }
        sqLiteDatabase.close();
        return exercises;
    }

    public void deleteExcersize(Exercise exercise){
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                    + EXCERSIZE_ID + " = " + exercise.getId() + " ; ";
            sqLiteDatabase.execSQL(query);
        }

    public Exercise returnExercise(int id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME
                + " WHERE " + EXCERSIZE_ID + " = " + id + " ; ";

        Exercise exercise = new Exercise();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();

            exercise.setId(cursor.getInt(cursor.getColumnIndex(EXCERSIZE_ID)));
            exercise.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            exercise.setCal(cursor.getInt(cursor.getColumnIndex(CALORIES)));
            exercise.setRepTime(cursor.getInt(cursor.getColumnIndex(REPTIME)));
            exercise.setTr(cursor.getInt(cursor.getColumnIndex(TIME_OR_REPS)));
            cursor.close();
        }
        return exercise;
    }

    public void updateExercise(Exercise exercise) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET "
                + NAME + " = '" + exercise.getName() + "', "
                + REPTIME + " = '" + exercise.getRepTime() + "', "
                + CALORIES + " = '" + exercise.getCal() + "', "
                + TIME_OR_REPS + " = '" + exercise.getTr()
                + "' WHERE " + EXCERSIZE_ID + " = " + exercise.getId();
        sqLiteDatabase.execSQL(query);
    }

}
