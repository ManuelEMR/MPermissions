package com.manuelemr.simplepermissions.manager;


import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manuelemr.simplepermissions.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PermissionsFragment extends Fragment {

    private static final int PERMISSIONS_REQUEST_CODE = 101;

    public interface Listener{
        void OnResult(Permission[] permissions);
    }

    private Listener mListener;

    public PermissionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissions(String[] permissions){
        requestPermissions(permissions, PERMISSIONS_REQUEST_CODE);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode != PERMISSIONS_REQUEST_CODE) return;

        Permission[] resultPermissions = new Permission[permissions.length];

        for(int i=0; i<resultPermissions.length; i++){
            boolean shouldShowRequestPermissionsRationale = shouldShowRequestPermissionRationale(permissions[i]);
            resultPermissions[i] = new Permission(permissions[i],
                    grantResults[i] == PackageManager.PERMISSION_GRANTED,
                    shouldShowRequestPermissionsRationale);
        }

        mListener.OnResult(resultPermissions);
    }
}
