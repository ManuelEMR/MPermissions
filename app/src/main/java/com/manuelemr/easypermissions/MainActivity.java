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

                                    switch (permission.getName()){

                                        case Manifest.permission.CAMERA:
                                            if(permission.isGranted()){
                                                //User granted permission continue and use camera
                                            }
                                            else if(permission.shouldShowRequestRationale()){
                                                //User has denied permission once and have not selected 'dont ask again'
                                            }
                                            else {
                                                //User has denied permission with 'Dont ask again' selected
                                            }
                                            break;
                                        case Manifest.permission.READ_CONTACTS:
                                            if(permission.isGranted()){
                                                //User granted permission continue and use phone contacts
                                            }
                                            else if(permission.shouldShowRequestRationale()){
                                                //User has denied permission once and have not selected 'dont ask again'
                                            }
                                            else {
                                                //User has denied permission with 'Dont ask again' selected
                                            }
                                            break;
                                    }

                                }
                            }
                        });
            }
        });


    }
}
