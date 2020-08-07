package name.lkk.oneday.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Check {
    @PrimaryKey(autoGenerate = true)
    private long checkId;
    //关联Day
    private long dayCreatorId;
      @ColumnInfo(name = "check_title")
    private String title;
    @ColumnInfo(name = "check_contents")
    private String contents;

    public Check(long dayCreatorId, String title, String contents) {
        this.dayCreatorId = dayCreatorId;
        this.title = title;
        this.contents = contents;
    }

    public long getCheckId() {
        return checkId;
    }

    public void setCheckId(long checkId) {
        this.checkId = checkId;
    }

    public long getDayCreatorId() {
        return dayCreatorId;
    }

    public void setDayCreatorId(long dayCreatorId) {
        this.dayCreatorId = dayCreatorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
