package com.capton.rxpdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.capton.ep.EasyPermission;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  startRequestPermission(Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragmentLayout,new DemoFragment());
        transaction.commitNow();

    }

    private void startRequestPermission(String...permission){
        EasyPermission.request(this, new EasyPermission.OnPermissionListener() {
            @Override
            public void onPermissionDenied(String permissionName) {
                showDialog(permissionName);
            }

            @Override
            public void onPermissionGranted(String permissionName) {
                Toast.makeText(MainActivity.this, permissionName+"申请成功", Toast.LENGTH_SHORT).show();
            }
        },permission);
    }

    private void showDialog(final String permissionName){
        AlertDialog alertDialog = new AlertDialog.Builder(this).setMessage(permissionName+"申请失败，是否重新申请，否则不能继续操作")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                          startRequestPermission(permissionName);
                    }
                }).setNegativeButton("不了",null).create();
        alertDialog.show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermission.onRequestPermissionsResult(requestCode,permissions,grantResults);

    }
}
