package com.capton.rxpdemo;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.capton.ep.EasyPermission;

/**
 * Created by capton on 2018/2/22.
 */

public class DemoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_demo,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 1. 默认的无后续处理方式
       /* EasyPermission.request(this,
                Manifest.permission.INTERNET,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_COARSE_LOCATION);*/

        // 2. 带回调的方式
        EasyPermission.request(this, new EasyPermission.OnPermissionListener() {
            @Override
            public void onPermissionDenied(String permissionName) {
                Toast.makeText(getContext(), permissionName+"申请失败", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPermissionGranted(String permissionName) {
                Toast.makeText(getContext(), permissionName+"申请成功", Toast.LENGTH_SHORT).show();
            }
        },
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermission.onRequestPermissionsResult(requestCode,permissions,grantResults);

    }
}
