package name.lkk.oneday.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CheckRepository {
    private CheckDao checkDao;
    private LiveData<List<Check>>allCheckLive;

    public CheckRepository(Context context) {
        AppDatabase appDatabase = AppDatabase.getDatabase(context.getApplicationContext());
        checkDao = appDatabase.getCheckDao();

    }
    public LiveData<List<Check>> getAllCheckWithDay(long dayid){
        allCheckLive = checkDao.getAllCheckWithDay(dayid);
        return allCheckLive;
    }

    public void insertCheck(Check... checks){
        new InsertAsyncTask(checkDao).execute(checks);
    }
    public void updateCheck(Check... checks){
        new UpdateAsyncTask(checkDao).execute(checks);
    }
    public void deleteCheck(Check... checks){
        new DeleteAsyncTask(checkDao).execute(checks);
    }

    static class InsertAsyncTask extends AsyncTask<Check, Void, Void> {
        private CheckDao checkDao;

        public InsertAsyncTask(CheckDao checkDao) {
            this.checkDao = checkDao;
        }
        @Override
        protected Void doInBackground(Check... checks) {
            checkDao.insertCheck(checks);
            return null;
        }
    }
    static class UpdateAsyncTask extends AsyncTask<Check, Void, Void> {
        private CheckDao checkDao;

        public UpdateAsyncTask(CheckDao checkDao) {
            this.checkDao = checkDao;
        }
        @Override
        protected Void doInBackground(Check... checks) {
            checkDao.updateCheck(checks);
            return null;
        }
    }
    static class DeleteAsyncTask extends AsyncTask<Check, Void, Void> {
        private CheckDao checkDao;

        public DeleteAsyncTask(CheckDao checkDao) {
            this.checkDao = checkDao;
        }
        @Override
        protected Void doInBackground(Check... checks) {
            checkDao.deleteCheck(checks);
            return null;
        }
    }

}
