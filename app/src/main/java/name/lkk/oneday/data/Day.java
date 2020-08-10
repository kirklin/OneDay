package name.lkk.oneday.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity
public class Day implements Serializable {
    private static final long serialVersionUID = -7990954864728722480L;
    @PrimaryKey(autoGenerate = true)
    private long dayId;
    @ColumnInfo(name = "day_title")
    private String title;
    @ColumnInfo(name = "day_weather")
    private int weather;
    @ColumnInfo(name = "day_mood")
    private int mood;


    public Day(String title) {
        this.title = title;
    }

    public long getDayId() {
        return dayId;
    }

    public void setDayId(long dayId) {
        this.dayId = dayId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWeather() {
        return weather;
    }

    public void setWeather(int weather) {
        this.weather = weather;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }


}
