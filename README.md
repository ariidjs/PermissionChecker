# Permission Checker

Simplify Android permission with Kotlin Supports.

### Feature
- Multiple Permissions supports
- Supported with clean architecture when implementing 'BaseActivity' class.
- Callback with permission state :
    - Permission granted
    - Permission denied
    - Permission permanently denied
- Snackbar for showing denied causes.
- Single Permission (Coming Soon)

### How to use
Add maven `jitpack.io` and `dependencies` in `gradle files (Project)` :
```gradle
// build.gradle project with Android Studio 2021.xx OLDER
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}

// settings.gradle project with Android Studio 2021.xx
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

After that you can easily include the library in your **app** `build.gradle`:

```
dependencies {
	        implementation 'com.github.ariidjs:PermissionChecker:1.0.0'
	}
```

## Usage

 - Just extend your **Activity** to **PermissionChecker** and implement the **PermissionChecker.PermissionCallback**.
```
    class MainActivity : PermissionChecker(), PermissionChecker.PermissionCallback {
          ...
    }
```
 - Call **requestPermission** function inside your **onCreate** function.
```
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CAMERA,
            requestCode = PERMISSION_REQUEST_CODE
        )
    }
```
 - Don't forget to implement callbacks function!
 ```
      override fun onPermissionResult(requestCode: Int, type: Type) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            when (type) {
                Type.GRANTED -> {} //do the action when granted
                Type.DENIED -> {} // do the action when denied :)
            }
        }
    }
 ```
- That's it.


## Contribution
You can sent your constibution to `branch` `open-pull`.

---

```
Copyright 2022 ariidjs
```
