//
// Created by uelordi on 2/10/17.
//
#include <jni.h>

#include <opencv2/core.hpp>
#include <opencv2/objdetect.hpp>
#ifndef FACEDETECT_NATIVE_LIB_H
#define FACEDETECT_NATIVE_LIB_H
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_opencv_samples_fd_DetectionBasedTracker
 * Method:    nativeCreateObject
 * Signature: (Ljava/lang/String;F)J
 */
JNIEXPORT jlong JNICALL
Java_org_opencv_samples_facedetect_DetectionBasedTracker_nativeCreateObject
        (JNIEnv *, jclass, jstring, jint);

/*
 * Class:     org_opencv_samples_fd_DetectionBasedTracker
 * Method:    nativeDestroyObject
 * Signature: (J)V
 */
JNIEXPORT void JNICALL
Java_org_opencv_samples_facedetect_DetectionBasedTracker_nativeDestroyObject
        (JNIEnv *, jclass, jlong);

/*
 * Class:     org_opencv_samples_fd_DetectionBasedTracker
 * Method:    nativeStart
 * Signature: (J)V
 */
JNIEXPORT void JNICALL
Java_org_opencv_samples_facedetect_DetectionBasedTracker_nativeStart
        (JNIEnv *, jclass, jlong);

/*
 * Class:     org_opencv_samples_fd_DetectionBasedTracker
 * Method:    nativeStop
 * Signature: (J)V
 */
JNIEXPORT void JNICALL
Java_org_opencv_samples_facedetect_DetectionBasedTracker_nativeStop
        (JNIEnv *, jclass, jlong);

/*
 * Class:     org_opencv_samples_fd_DetectionBasedTracker
 * Method:    nativeSetFaceSize
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL
Java_org_opencv_samples_facedetect_DetectionBasedTracker_nativeSetFaceSize
        (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     org_opencv_samples_fd_DetectionBasedTracker
 * Method:    nativeDetect
 * Signature: (JJJ)V
 */
JNIEXPORT void JNICALL
Java_org_opencv_samples_facedetect_DetectionBasedTracker_nativeDetect
        (JNIEnv *, jclass, jlong, jlong, jlong);
JNIEXPORT void JNICALL
Java_org_opencv_samples_facedetect_FdActivity_rotateFlipImage(JNIEnv *,
                                                            jclass ,
                                                            jint ,
                                                            jint ,
                                                            jlong);

#ifdef __cplusplus
}
#endif
#endif //FACEDETECT_NATIVE_LIB_H

