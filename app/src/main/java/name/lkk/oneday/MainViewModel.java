package name.lkk.oneday;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import name.lkk.oneday.data.Day;
import name.lkk.oneday.data.DayRepository;

public class MainViewModel extends AndroidViewModel {
    private DayRepository dayRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        dayRepository = new DayRepository(application);
    }

    LiveData<List<Day>> getAllDayLive() {
        return dayRepository.getAllDayLive();
    }

    LiveData<List<Day>> findDaysWithPattern(String pattern) {
        return dayRepository.findDaysWithPattern(pattern);
    }

    public void insertDay(Day... days) {
        dayRepository.insertDay(days);
    }

    public void deleteDay(Day... days) {
        dayRepository.deleteDay(days);
    }

    public void deleteAllDay() {
        dayRepository.deleteAllDay();
    }

    public void saveData(final Context context) {
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard.getAbsolutePath() +
                "/OneDay");
        boolean mkdirs = directory.mkdirs();
        File file = new File(directory, "OneDayBackup.json");
        Gson gson = new Gson();
        List<Day> dayList= getAllDayLive().getValue();
        String data = gson.toJson(dayList);
        FileOutputStream fos = null;
        BufferedWriter writer = null;
        try {
            fos = new FileOutputStream(file);
            writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write(data);
            Toast.makeText(context, "导出成功", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void readData(final Context context) {
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard.getAbsolutePath() +
                "/OneDay");
        boolean mkdirs = directory.mkdirs();
        File file = new File(directory, "OneDayBackup.json");
        if (!file.exists()){

            Toast.makeText(context, "导入失败，找不到文件", Toast.LENGTH_SHORT).show();
            return;
        }
        FileInputStream fis = null;
        InputStreamReader isr = null;
        StringBuilder s = null;
        try {
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            char[] inputBuffer = new char[1024];
            s = new StringBuilder();
            int charRead;
            while ((charRead = isr.read(inputBuffer)) > 0) {
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                s.append(readString);
                inputBuffer = new char[1024];
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (isr != null)
                    isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Gson gson = new Gson();
        List<Day> dayreadList2 = new ArrayList<Day>();

        String jsonDays = s != null ? s.toString() : "";
        Type typeDays = new TypeToken<List<Day>>() {
        }.getType();
        dayreadList2 = gson.fromJson(jsonDays, typeDays);
        for (Day day : dayreadList2) {
            deleteDay(day);
            insertDay(day);
        }
        Toast.makeText(context, "导入成功", Toast.LENGTH_SHORT).show();
    }


}
