package com.manuelemr.simplepermissions.manager;

/**
 * Created by manuelmunoz on 1/8/17.
 */

public class Permission {

    String mName;
    boolean mIsGranted;
    boolean mShowRequestRationale;

    public Permission(String name, boolean isGranted, boolean showRequestRationale){
        mIsGranted = isGranted;
        mName = name;
        mShowRequestRationale = showRequestRationale;
    }

    public String getName() {
        return mName;
    }

    public boolean isGranted() {
        return mIsGranted;
    }

    public boolean shouldShowRequestRationale() {
        return mShowRequestRationale;
    }
}
