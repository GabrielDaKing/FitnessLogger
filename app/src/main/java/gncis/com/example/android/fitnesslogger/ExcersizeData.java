package gncis.com.example.android.fitnesslogger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

public class ExcersizeData extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "excersize";
    public static final String NAME = "name";
    public static final String EXCERSIZE_ID = BaseColumns._ID;
    public static final String CALORIES = "calories";
    public static final String REPTIME = "repTime";
    public static final String TIME_OR_REPS = "tr";

    private static final String DATABASE_NAME = "Fitness2.db";

    private static final int DATABASE_VERSION = 1;

    public ExcersizeData(Context context) {
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
        db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + " ; ");
        onCreate(db);
        db.close();
    }


    public void enterExcersize(Excersize excersize ) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, excersize.getName());
        contentValues.put(CALORIES, excersize.getCal());
        contentValues.put(REPTIME, excersize.getRepTime());
        contentValues.put(TIME_OR_REPS, excersize.getTr());

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    public ArrayList<Excersize> allExcersizes() {

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " ;";
        ArrayList<Excersize> excersizes = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if(cursor!=null) {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Excersize excersize = new Excersize();
                excersize.setId(cursor.getInt(cursor.getColumnIndex(EXCERSIZE_ID)));
                excersize.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                excersize.setCal(cursor.getInt(cursor.getColumnIndex(CALORIES)));
                excersize.setRepTime(cursor.getInt(cursor.getColumnIndex(REPTIME)));
                excersize.setTr(cursor.getInt(cursor.getColumnIndex(TIME_OR_REPS)));

                excersizes.add(excersize);
                cursor.moveToNext();
            }

            cursor.close();
        }
        sqLiteDatabase.close();
        return excersizes;
    }

    public void deleteExcersize(){}

}
