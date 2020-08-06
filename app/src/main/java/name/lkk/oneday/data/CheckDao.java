package name.lkk.oneday.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CheckDao {
    @Insert
    void insertCheck(Check... checks);

    @Update
    void updateCheck(Check... checks);

    @Delete
    void deleteCheck(Check... checks);


    @Query("SELECT * FROM `check` WHERE dayCreatorId=:dayid ORDER BY checkId")
    LiveData<List<Check>>getAllCheckWithDay(long dayid);

}
