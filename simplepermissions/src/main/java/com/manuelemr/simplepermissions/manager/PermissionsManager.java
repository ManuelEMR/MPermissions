package com.manuelemr.simplepermissions.manager;


import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;

/**
 * Created by manuelmunoz on 12/8/16.
 */

public class PermissionsManager {

    private static final String FRAGMENT_TAG = "PermissionsFragmentTag";

    public static class Builder {

        String[] mStoredPermissions;
        WeakReference<Activity> mStoredActivity;
        PermissionsFragment mPermissionsFragment;

        public Builder(){}

        private void getPermissionsFragment(Activity activity){

            mPermissionsFragment = (PermissionsFragment)activity.getFragmentManager()
                    .findFragmentByTag(FRAGMENT_TAG);

            if(mPermissionsFragment == null){

                mPermissionsFragment = new PermissionsFragment();
                activity.getFragmentManager()
                        .beginTransaction()
                        .add(mPermissionsFragment, FRAGMENT_TAG)
                        .commit();
                activity.getFragmentManager().executePendingTransactions();
            }
        }

        public Builder in(Activity activity){
            mStoredActivity = new WeakReference<>(activity);
            getPermissionsFragment(activity);
            return this;
        }

        public  Builder forPermissions(String... permission){

            if(permission == null || permission.length == 0){
                throw new IllegalArgumentException("Permissions must no be null or have lenght 0");
            }

            mStoredPermissions = permission;
            return this;
        }

//        public Builder doBeforeRequest(WillRequestListener requestListener){
//            storedRequestListener = requestListener;
//            return this;
//        }


        public void request(@NonNull PermissionsFragment.Listener listener){

            checkFields();

            Activity activity = mStoredActivity.get();

            //Cannot build context has disappeared
            if(activity == null) {
                mPermissionsFragment = null;
                return;
            }

            handleDifferentVersions(listener);
        }

        private void checkFields(){

            if(mStoredActivity == null)
                throw new NullPointerException("Must set context using 'with' function");

            if(mStoredPermissions == null)
                throw new NullPointerException("Must set permission with 'forPermission' function");
        }

        private void handleDifferentVersions(PermissionsFragment.Listener listener){

            if(isHigherThanMarshmellow()){

                mPermissionsFragment.setListener(listener);
                mPermissionsFragment.requestPermissions(mStoredPermissions);
            }
            else{
                Permission[] permissions = new Permission[mStoredPermissions.length];
                for(int i=0; i<mStoredPermissions.length; i++){
                    permissions[i] = new Permission(mStoredPermissions[i], true, false);
                }
                listener.OnResult(permissions);
            }

        }

        private boolean isHigherThanMarshmellow(){
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
        }
    }
}
