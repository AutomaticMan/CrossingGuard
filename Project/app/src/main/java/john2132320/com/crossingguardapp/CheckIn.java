package john2132320.com.crossingguardapp;

import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Created by David on 4/18/2016.
 */
public class CheckIn implements Serializable {
    @com.google.gson.annotations.SerializedName("id")
    private String mId;

    @com.google.gson.annotations.SerializedName("checkInInterval")
    private int mCheckInInterval;

    /*@com.google.gson.annotations.SerializedName("checkInStart")
    private DateTime mCheckInStart;*/

    @com.google.gson.annotations.SerializedName("endDateMillis")
    private long mCheckInEnd;

    @com.google.gson.annotations.SerializedName("contactEmail")
    private String mContactEmail;

    @com.google.gson.annotations.SerializedName("checkInText")
    private String mCheckInText;

    @com.google.gson.annotations.SerializedName("checkedIn")
    private boolean mCheckedIn;

    @com.google.gson.annotations.SerializedName("userID")
    private String mUserID;

    public CheckIn(String contactEmail, String checkInText, int checkInInterval, String userID) {
        //this.setId(id);
        this.setCheckInText(checkInText);
        this.setContactEmail(contactEmail);
        this.setmCheckInInterval(checkInInterval);
        this.mUserID = userID;
        this.mCheckedIn = false;
    }

    public CheckIn() {
    }

    public int getCheckInInterval(){return mCheckInInterval;}

    public long getCheckInEnd(){return mCheckInEnd;}

    public void setmCheckInInterval(int interval){
        mCheckInInterval = interval;
    }

    public void setCheckInText(String text){
        mCheckInText = text;
    }

    public void setContactEmail(String text){
        mContactEmail = text;
    }

    public String getCheckInText(){
        return mCheckInText;
    }

    public final void setId(String id) {
        mId = id;
    }

    public void checkIn(){
        mCheckedIn = true;
    }
}
