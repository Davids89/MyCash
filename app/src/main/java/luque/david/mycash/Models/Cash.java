package luque.david.mycash.Models;

import java.util.Date;

/**
 * Created by David on 24/10/15.
 */
public class Cash {
    private Date mDate;
    private Integer mValue;
    private String mCurrency;
    private String mSubject;
    private String mCategory;
    private String mUserID;

    public Cash(){

    }

    public Cash(Integer value, String currency, String category, String userID, String subject){
        //mDate = date;
        mValue = value;
        mCategory = category;
        mCurrency = currency;
        mUserID = userID;
        mSubject = subject;
    }

    public Integer getmValue() {
        return mValue;
    }

    public void setmValue(Integer mValue) {
        this.mValue = mValue;
    }

    public String getmCurrency() {
        return mCurrency;
    }

    public void setmCurrency(String mCurrency) {
        this.mCurrency = mCurrency;
    }

    public String getmSubject() {
        return mSubject;
    }

    public void setmSubject(String mSubject) {
        this.mSubject = mSubject;
    }

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getmUserID() {
        return mUserID;
    }

    public void setmUserID(String mUserID) {
        this.mUserID = mUserID;
    }
}
