package org.opencv.samples.captureonly;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2 {

    // Used to load the 'native-lib' library on application startup.
    private CameraBridgeViewBase mOpenCvCameraView;
    private static final String TAG = "MainActivity";
    private Mat mRgba;
    private Mat mGray;
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            super.onManagerConnected(status);
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:

                    break;
                case LoaderCallbackInterface.INIT_FAILED:
                    break;
            }
            Log.i(TAG, "OpenCV loaded successfully");
            System.loadLibrary("native-lib");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        askPermissions();
        JniManager.init();
        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.camera_surface);
        mOpenCvCameraView.setVisibility(CameraBridgeViewBase.VISIBLE);
        mOpenCvCameraView.setMaxFrameSize(640, 480);
        mOpenCvCameraView.setCvCameraViewListener(this);
    }

    @Override
    public void onCameraViewStarted(int i, int i1) {
        mGray = new Mat();
        mRgba = new Mat();
    }

    @Override
    public void onCameraViewStopped() {
        mGray.release();
        mRgba.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRgba = inputFrame.rgba();
        mGray = inputFrame.gray();
        JniManager.process(mRgba.getNativeObjAddr(), mGray.getNativeObjAddr());
        return mRgba;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
        JniManager.start();
        mOpenCvCameraView.enableView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JniManager.stop();
    }
    void askPermissions() {
        final String[] permissions_needed = {Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
                //,Manifest.permission.SYSTEM_ALERT_WINDOW
        };
        final int REQUEST_CODE_ASK_PERMISSIONS = 123;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            ArrayList array_aux = new ArrayList<String>();

            String[] permissions_requested;
            for (String aPermissions_needed : permissions_needed) {
                int hasPermission = ContextCompat.checkSelfPermission(this, aPermissions_needed);
                if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                    array_aux.add(aPermissions_needed);
                    Log.d("Activity", "request permission %s" + aPermissions_needed);
                }
            }
            if (array_aux.size() != 0) {
                //Log.d("Activity", "allow permission");
                permissions_requested = (String[]) array_aux.toArray(new String[array_aux.size()]);


                ActivityCompat.requestPermissions(this, permissions_requested,
                        REQUEST_CODE_ASK_PERMISSIONS);
            }
        } else {
            Log.v(TAG, "No need to ask permissions programatically");
        }

    }
}
