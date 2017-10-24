package com.example.apasternak.videotoserver;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoToServerActivity extends AppCompatActivity implements
    EasyPermissions.PermissionCallbacks {

    private static final String TAG = VideoToServerActivity.class.getSimpleName();
    private static final int REQUEST_VIDEO_CAPTURE = 300;
    private static final int READ_REQUEST_CODE = 200;
    private static final String SERVER_PATH = "";
    private Uri uri;
    private String pathToStoredVideo;
    private VideoView displayRecordedVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_to_server);

        displayRecordedVideo = (VideoView) findViewById(R.id.video_display);

        Button captureVideoButton = (Button) findViewById(R.id.capture_video);

        captureVideoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent videoCaptureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (videoCaptureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(videoCaptureIntent, REQUEST_VIDEO_CAPTURE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_VIDEO_CAPTURE) {
            uri = data.getData();
            
            if (EasyPermissions.hasPermissions(VideoToServerActivity.this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                displayRecordedVideo.setVideoURI(uri);
                displayRecordedVideo.start();

                pathToStoredVideo = getRealPathFromURIPath(uri, VideoToServerActivity.this);
                Log.d(TAG, "Recorded Video Path " + pathToStoredVideo);

                /// Store the video to your server
                uploadVideoToServer(pathToStoredVideo);
            } else {
                EasyPermissions.requestPermissions(VideoToServerActivity.this,
                    getString(R.string.read_file), READ_REQUEST_CODE,
                    Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }
    }
}
