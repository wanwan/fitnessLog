package org.zaregoto.apl.fitnesslogger;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private ArrayList<FitnessLog> mLogs = new ArrayList<>();
    private FitnessLogAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                dispatchTakePictureIntent();
            }
        });

        ListView lv = findViewById(R.id.mainlist);
        mAdapter = new FitnessLogAdapter(this, R.layout.photo_listitem, mLogs);
        if (null != lv) {
            lv.setAdapter(mAdapter);
        }

        String[] requirePermissions = {android.Manifest.permission.CAMERA,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE};
        ArrayList<String> needPermissions = new ArrayList<>();
        for (String permission: requirePermissions) {
            if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
                // 許可されている時の処理
            } else {
                // 拒否されている時の処理
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                    // 明示的に拒否されている場合
                    // TODO: 現状このフローは考えない
                    finish();
                } else {
                    needPermissions.add(permission);
                }
            }
        }
        if (needPermissions.size() > 0) {
            ActivityCompat.requestPermissions(this, needPermissions.toArray(new String[needPermissions.size()]), 0);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            Date today = new Date();
            FitnessLog log = new FitnessLog(today, imageBitmap, null);
            //log.setThumbnail(imageBitmap);
            mLogs.add(log);
            if (null != mAdapter) {
                mAdapter.notifyDataSetChanged();
            }
            //mImageView.setImageBitmap(imageBitmap);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 0: { //ActivityCompat#requestPermissions()の第2引数で指定した値
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //許可された場合の処理
                }else{
                    //拒否された場合の処理
                    // TODO:
                    finish();
                }
                break;
            }
        }

    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}
