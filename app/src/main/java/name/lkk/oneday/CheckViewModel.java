package name.lkk.oneday;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import name.lkk.oneday.data.Check;
import name.lkk.oneday.data.CheckRepository;

public class CheckViewModel extends AndroidViewModel {
    private CheckRepository checkRepository;

    public CheckViewModel(@NonNull Application application) {
        super(application);
        checkRepository = new CheckRepository(application);
    }
    LiveData<List<Check>> getAllCheckLive(long dayid){
        return checkRepository.getAllCheckWithDay(dayid);}
    public void insertCheck(Check... checks){
        checkRepository.insertCheck(checks);
    }
    public void updateCheck(Check... checks){
        checkRepository.updateCheck(checks);
    }
    public void deleteCheck(Check... checks){
        checkRepository.deleteCheck(checks);
    }
}
