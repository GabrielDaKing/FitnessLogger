package gncis.com.example.android.fitnesslogger;

import android.content.Context;
import android.content.SharedPreferences;

public class SPManager {

    private static final String PREF_NAME = "empty";
    private static SharedPreferences sharedPreferences;
    private static String USER_AGE = "age";
    private static String USER_HEIGHT = "height";
    private static String USER_WEIGHT = "weight";
    private static String USER_CALORIE_INTAKE = "calorie_intake";
    private static String USER_WEIGHT_GOAL = "weight_goal";


    private static SharedPreferences.Editor editor;

    public static SharedPreferences isEmpty() {
        return sharedPreferences;
    }

    public static void DeleteData(Context context) {
        if (sharedPreferences == null)
            init(context);
        editor.clear();
        editor.commit();
    }

    private static void init(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static String getUserAge(Context context) {
        if (sharedPreferences == null)
            init(context);
        return sharedPreferences.getString(USER_AGE, "empty");
    }

    public static void setUserAge(Context context, String userName) {
        if (sharedPreferences == null)
            init(context);
        editor.putString(USER_AGE, userName);
        editor.apply();
    }

    public static String getUserHeight(Context context) {
        if (sharedPreferences == null)
            init(context);
        return sharedPreferences.getString(USER_HEIGHT, "empty");
    }

    public static void setUserHeight(Context context, String userInfo) {
        if (sharedPreferences == null)
            init(context);
        editor.putString(USER_HEIGHT, userInfo);
        editor.apply();
    }

    public static String getUserCalorieIntake(Context context) {
        if (sharedPreferences == null)
            init(context);
        return sharedPreferences.getString(USER_CALORIE_INTAKE, "empty");
    }

    public static void setUserCalorieIntake(Context context, String userImage) {
        if (sharedPreferences == null)
            init(context);
        editor.putString(USER_CALORIE_INTAKE, userImage);
        editor.apply();
    }

    public static String getUserWeight(Context context) {
        if (sharedPreferences == null)
            init(context);
        return sharedPreferences.getString(USER_WEIGHT, "empty");
    }

    public static void setUserWeight(Context context, String userImage) {
        if (sharedPreferences == null)
            init(context);
        editor.putString(USER_WEIGHT, userImage);
        editor.apply();
    }


    public static String getUserWeightGoal(Context context) {
        if (sharedPreferences == null)
            init(context);
        return sharedPreferences.getString(USER_WEIGHT_GOAL, "empty");
    }

    public static void setUserWeightGoal(Context context, String userImage) {
        if (sharedPreferences == null)
            init(context);
        editor.putString(USER_WEIGHT_GOAL, userImage);
        editor.apply();
    }

}