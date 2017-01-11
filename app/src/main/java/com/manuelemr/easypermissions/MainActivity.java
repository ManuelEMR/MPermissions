package com.manuelemr.easypermissions;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.manuelemr.simplepermissions.manager.Permission;
import com.manuelemr.simplepermissions.manager.PermissionsFragment;
import com.manuelemr.simplepermissions.manager.PermissionsManager;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button requestButton = (Button)findViewById(R.id.request_button);
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PermissionsManager
                        .Builder()
                        .in(MainActivity.this)
                        .forPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_CONTACTS)
                        .request(new PermissionsFragment.Listener() {
                            @Override
                            public void OnResult(Permission[] permissions) {

                                for(int i=0; i<permissions.length; i++){
                                    Permission permission = permissions[i];

                                    if (permission.getName().equals(Manifest.permission.CAMERA)) {

                                        if (permission.isGranted()) {
                                            //User granted permission continue and use camera
                                            Log.d("Permission", Manifest.permission.CAMERA + " Granted");
                                        } else if (permission.shouldShowRequestRationale()) {
                                            //User has denied permission once and have not selected 'dont ask again'
                                            Log.d("Permission", Manifest.permission.CAMERA + " Show Rationale");
                                        } else {
                                            //User has denied permission with 'Dont ask again' selected
                                            Log.d("Permission", Manifest.permission.CAMERA + " Dont ask again");
                                        }
                                    }
                                    else if(permission.getName().equals(Manifest.permission.READ_CONTACTS)) {

                                        if (permission.isGranted()) {
                                            //User granted permission continue and use phone contacts
                                            Log.d("Permission", Manifest.permission.READ_CONTACTS + " Granted");
                                        } else if (permission.shouldShowRequestRationale()) {
                                            //User has denied permission once and have not selected 'dont ask again'
                                            Log.d("Permission", Manifest.permission.READ_CONTACTS + " Show Rationale");
                                        } else {
                                            //User has denied permission with 'Dont ask again' selected
                                            Log.d("Permission", Manifest.permission.READ_CONTACTS + " Dont ask again");
                                        }
                                    }
                                }
                            }
                        });
            }
        });


    }
}
