package name.lkk.oneday.data;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Check.class,Day.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    static synchronized AppDatabase getDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"app_database")
                    .build();
        }
        return INSTANCE;
    }
    public abstract DayDao getDayDao();
    public abstract CheckDao getCheckDao();


}
