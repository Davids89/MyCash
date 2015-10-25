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

    public Cash(Integer value, String currency, String category, String userID){
        //mDate = date;
        mValue = value;
        mCategory = category;
        mCurrency = currency;
        mUserID = userID;
    }

    public Integer getValue(){
        return mValue;
    }

    public String getCategory(){
        return mCategory;
    }

    public Date getDate(){
        return mDate;
    }

    public String getCurrency(){
        return mCurrency;
    }

    public String getUser(){
        return mUserID;
    }
}
