package name.lkk.oneday.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DayRepository {
    private DayDao dayDao;
    private LiveData<List<Day>>allDayLive;

    public DayRepository(Context context) {
        AppDatabase appDatabase = AppDatabase.getDatabase(context.getApplicationContext());
        dayDao = appDatabase.getDayDao();
        allDayLive = dayDao.getAllDayLive();

    }

    public LiveData<List<Day>> getAllDayLive() {
        return allDayLive;
    }



    public LiveData<List<Day>> findDaysWithPattern(String pattern){
        return dayDao.findDaysWithPattern("%"+pattern+"%");
    }
    public void insertDay(Day... Days){
        new InsertAsyncTask(dayDao).execute(Days);
    }
    public void updateDay(Day... Days){
        new UpdateAsyncTask(dayDao).execute(Days);
    }
    public void deleteDay(Day... Days){
        new DeleteAsyncTask(dayDao).execute(Days);
    }
    public void deleteAllDay(){new DeleteAllAsyncTask(dayDao).execute();}


    static class InsertAsyncTask extends AsyncTask<Day, Void, Void> {
        private DayDao dayDao;

        public InsertAsyncTask(DayDao dayDao) {
            this.dayDao = dayDao;
        }
        @Override
        protected Void doInBackground(Day... Days) {
            dayDao.insertDay(Days);
            return null;
        }
    }
    static class UpdateAsyncTask extends AsyncTask<Day, Void, Void> {
        private DayDao dayDao;

        public UpdateAsyncTask(DayDao dayDao) {
            this.dayDao = dayDao;
        }
        @Override
        protected Void doInBackground(Day... Days) {
            dayDao.updateDay(Days);
            return null;
        }
    }
    static class DeleteAsyncTask extends AsyncTask<Day, Void, Void> {
        private DayDao dayDao;

        public DeleteAsyncTask(DayDao dayDao) {
            this.dayDao = dayDao;
        }
        @Override
        protected Void doInBackground(Day... Days) {
            dayDao.deleteDay(Days);
            return null;
        }
    }
    static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private DayDao dayDao;

        public DeleteAllAsyncTask(DayDao dayDao) {
            this.dayDao = dayDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            dayDao.deleteAllDays();
            return null;
        }
    }

}
