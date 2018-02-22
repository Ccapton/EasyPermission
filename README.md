# EasyPermission
android 6.0+ 动态权限申请封装

### 用法示例
**1.在activity中**
```
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. 默认的无后续处理方式
       /* EasyPermission.request(this,
                Manifest.permission.INTERNET,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_COARSE_LOCATION);*/

        // 2. 带回调的方式
       EasyPermission.request(this, new EasyPermission.OnPermissionListener() {
            @Override
            public void onPermissionDenied(String permissionName) {
                showDialog(permissionName);
            }

            @Override
            public void onPermissionGranted(String permissionName) {
                Toast.makeText(MainActivity.this, permissionName+"申请成功", Toast.LENGTH_SHORT).show();
            }
        },
        Manifest.permission.CAMERA, 
        Manifest.permission.ACCESS_COARSE_LOCATION);

 }
 /* 省略代码 */   
// 重写onRequestPermissionsResult
@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
   super.onRequestPermissionsResult(requestCode, permissions, grantResults);
   EasyPermission.onRequestPermissionsResult(requestCode,permissions,grantResults);
}
    
```
**2.在fragment中**
```
@Override
public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    // 这里调用 与activity的onCreate方法内相同的代码
}
 /* 省略代码 */   
// 重写onRequestPermissionsResult
@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
   super.onRequestPermissionsResult(requestCode, permissions, grantResults);
   EasyPermission.onRequestPermissionsResult(requestCode,permissions,grantResults);
}
```
### gradle引入
**在project的build.gradle中**
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
**在app的build.gradle中**
```
dependencies {
	        compile 'com.github.Ccapton:EasyPermission:1.0'
	}
```
