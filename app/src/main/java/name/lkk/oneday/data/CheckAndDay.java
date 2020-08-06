package name.lkk.oneday.data;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CheckAndDay {
    @Embedded
    private Day day;
    @Relation(
            parentColumn = "dayId",
            entityColumn = "dayCreatorId"
    )
    private List<Check> checkList;

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public List<Check> getCheckList() {
        return checkList;
    }

    public void setCheckList(List<Check> checkList) {
        this.checkList = checkList;
    }
}
