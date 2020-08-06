package name.lkk.oneday.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DayDao {
    @Insert
    void insertDay(Day... days);

    @Update
    void updateDay(Day... days);

    @Delete
    void deleteDay(Day... days);

    @Query("DELETE FROM Day")
    void deleteAllDays();

    @Query("SELECT * FROM Day ORDER BY dayId DESC")
    LiveData<List<Day>> getAllDayLive();

    @Query("SELECT dayId FROM Day WHERE dayId=:id")
    long getDayId(long id);


    @Query("SELECT * FROM Day WHERE day_title LIKE :pattern ORDER BY dayId DESC")
    LiveData<List<Day>>findDaysWithPattern(String pattern);
}
