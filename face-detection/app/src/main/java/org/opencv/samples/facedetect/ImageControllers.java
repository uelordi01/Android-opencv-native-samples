package org.opencv.samples.facedetect;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.util.Log;
import android.view.Surface;
import android.view.WindowManager;

/**
 * Created by uelordi on 3/10/17.
 */

public class ImageControllers {

    public static final int CAMERA_FACE_FRONT = 0;
    public static final int CAMERA_FACE_BACK = 1;

    private static final int ANGLE_0 =  0;
    private static final int ANGLE_90 =  90;
    private static final int ANGLE_180 = 180;
    private static final int ANGLE_270  = 270;
    private static final String LOG_TAG = "ImageControllers";
    private static ImageControllers m_instance;
    int mCameraSensorOrientation;
    int mDevice_orientation;
    int mCameraId;
    int makeTranspose;
    int flipType;

    public static synchronized ImageControllers getInstance() {
        if (m_instance == null) {
            m_instance = new ImageControllers();
        }
        return m_instance;
    }

    public void analizeRotationsForImage(Context context, int cameraId) {
        boolean enableFacing = false;
        boolean allowMirroring = false;
        makeTranspose = 0;
        flipType = -2;
        mCameraId = cameraId;
        mCameraSensorOrientation = getCameraSensorOrientation(context);
        mDevice_orientation = getDisplayRotation(context);
        if (mCameraId == CAMERA_FACE_FRONT) {
            enableFacing = true;
            allowMirroring = true;
        }
        mCameraSensorOrientation = getRelativeImageOrientation(mDevice_orientation,
                mCameraSensorOrientation,
                enableFacing,
                allowMirroring);
        switch (mCameraSensorOrientation) {
            case ANGLE_0:
                makeTranspose = 0;
                if (mCameraId == CAMERA_FACE_FRONT) {
                    flipType = 1;
                }
            case ANGLE_180:
                makeTranspose = 0;
                if (mCameraId == CAMERA_FACE_FRONT) {
                    flipType = 0;
                } else {
                    flipType = -1;
                }
                break;
            case ANGLE_90:
                makeTranspose = 1;
                if (mCameraId == CAMERA_FACE_FRONT) {
                    flipType = -1;
                } else {
                    flipType = 1;
                }
                break;

            case ANGLE_270:
                makeTranspose = 1;
                if (mCameraId == CAMERA_FACE_FRONT) {
                    flipType = 0;
                }
                break;
        }
    }


    private int getRelativeImageOrientation(int displayRotation, int sensorOrientation,
                                            boolean isFrontFacing, boolean compensateForMirroring) {
        int result;
        if (isFrontFacing) {
            result = (sensorOrientation + displayRotation) % 360;
            if (compensateForMirroring) {
                result = (360 - result) % 360;
            }
        } else {
            result = (sensorOrientation - displayRotation + 360) % 360;
        }
        return result;
    }

    private int getCameraSensorOrientation(Context context) {
        CameraCharacteristics characteristics;
        CameraManager manager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        int mSensorOrientation = 0;
        try {
            mSensorOrientation = manager.getCameraCharacteristics(String.valueOf(mCameraId))
                    .get(CameraCharacteristics.SENSOR_ORIENTATION);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        return mSensorOrientation;
    }

    private int getDisplayRotation(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int rotation = windowManager.getDefaultDisplay().getRotation();
        switch (rotation) {
            case Surface.ROTATION_0:
                return 0;
            case Surface.ROTATION_90:
                return 90;
            case Surface.ROTATION_180:
                return 180;
            case Surface.ROTATION_270:
                return 270;
        }
        return 0;
    }
    public int getMakeTranspose() {
        return makeTranspose;
    }

    public void setMakeTranspose(int makeTranspose) {
        this.makeTranspose = makeTranspose;
    }

    public int getFlipType() {
        return flipType;
    }

    public void setFlipType(int flipType) {
        this.flipType = flipType;
    }
}




