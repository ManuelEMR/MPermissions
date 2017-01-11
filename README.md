# MPermissions
Simplify to a maximum Android M permission request

# Setup #

To use this library your ```minSdkVersion``` must be >= 9.

Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Then add the dependency:
```
dependencies {
    compile 'com.github.ManuelEMR:MPermissions:v0.2-beta'
}
```

# Usage #

Create a new PermissionsManager.Builder instance, and start filling in parameters:

```
new PermissionsManager
            .Builder()
            .in(MainActivity.this)
            .forPermissions(Manifest.permission.CAMERA)
            .request(new PermissionsFragment.Listener() {
                @Override
                public void OnResult(Permission[] permissions) {

                    if(permissions[0].isGranted()){
                        //User granted permission continue and use camera
                    }
                    else if(permissions[0].shouldShowRequestRationale()){
                        //User has denied permission once and have not selected 'dont ask again'
                    }
                    else {
                        //User has denied permission with 'Dont ask again' selected
                    }
                }
            });
```

For multiple permissions at once:

```
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
```
