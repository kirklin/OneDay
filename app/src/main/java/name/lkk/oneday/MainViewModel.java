package name.lkk.oneday;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import name.lkk.oneday.data.Day;
import name.lkk.oneday.data.DayRepository;

public class MainViewModel extends AndroidViewModel {
    private DayRepository dayRepository;
    public MainViewModel(@NonNull Application application) {
        super(application);
        dayRepository = new DayRepository(application);
    }

    LiveData<List<Day>> getAllDayLive(){return dayRepository.getAllDayLive();}
    LiveData<List<Day>> findDaysWithPattern(String pattern){return dayRepository.findDaysWithPattern(pattern);}

    public void insertDay(Day... days){
        dayRepository.insertDay(days);
    }
    public void deleteAllDay(){
        dayRepository.deleteAllDay();
    }
}
