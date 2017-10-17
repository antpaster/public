package com.example.apasternak.mycamera2app;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CameraActivity extends AppCompatActivity {

    private Button mPictureButton;
    private TextureView mTextureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        mTextureView = (TextureView) findViewById(R.id.texture);
        mTextureView.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                CameraManager manager = (CameraManager) getSystemService(CAMERA_SERVICE);

                try {
                    for (String cameraId : manager.getCameraIdList()) {
                        CameraCharacteristics chars = manager.getCameraCharacteristics(cameraId);
                        // Do something with the characteristics
                        Integer facing = chars.get(CameraCharacteristics.LENS_FACING);

                        if (facing != null && facing == CameraCharacteristics.LENS_FACING_FRONT) {
                            // No selfies!
                            Context context = getApplicationContext();
                            CharSequence text = "No selfie for you! Turn the camera around";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();

                            // don’t process anything for the front facing camera
                            continue;

                        } else {
                            // Open the rear facing camera (see github repo below)
                        }
                    }
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        mPictureButton = (Button) findViewById(R.id.picture);
        mPictureButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Pressed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
