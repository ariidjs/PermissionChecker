# Permission Checker

Simplify Android permission with Kotlin Supportss.

[![](https://jitpack.io/v/ariidjs/PermissionChecker.svg)](https://jitpack.io/#ariidjs/PermissionChecker)

### Feature
- Multiple Permissions supports
- Supported with clean architecture when implementing 'BaseActivity' class.
- Callback with permission state :
    - Permission granted
    - Permission denied
    - Permission permanently denied
- Snackbar for showing denied causes.
- Single Permission

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

```gradle
	dependencies {
	        implementation 'com.github.ariidjs:PermissionChecker:1.0.1'
	}
```

## Usage

 - Just extend your **Activity** to **PermissionChecker** and implement the **PermissionChecker.PermissionCallback**.
```kotlin
    class MainActivity : PermissionChecker(), PermissionChecker.PermissionCallback {
          ...
    }
```
### Multiple Permission
 - Call **requestPermission** function inside your **onCreate** function.
```kotlin
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
### Single Permission
```kotlin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestSinglePermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            requestCode = PERMISSION_REQUEST_CODE
        )
    }
```
 - Don't forget to implement callbacks function!
 ```kotlin
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
Copyright 2023 ariidjs
```
