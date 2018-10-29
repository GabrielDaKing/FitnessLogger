package gncis.com.example.android.fitnesslogger;

public class Workout {

    String name;
    int Cal;
    int repTime;
    int tr;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCal() {
        return Cal;
    }

    public void setCal(int Cal) {
        this.Cal = Cal;
    }

    public int getRepTime() {
        return repTime;
    }

    public void setRepTime(int reps) {
        this.repTime = repTime;
    }

    public int getTr() {
        return tr;
    }

    public void setTr(int tr) {
        this.tr = tr;
    }
}
