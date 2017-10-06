
#include <string>
#include "native-lib.h"
//#include <opencv2/core.hpp>
#include "opencv2/opencv.hpp"

//OpenCV includes:

JNIEXPORT void JNICALL
Java_org_opencv_samples_captureonly_JniManager_init(JNIEnv *env, jclass type) {

    // TODO

}

JNIEXPORT void JNICALL
Java_org_opencv_samples_captureonly_JniManager_process(JNIEnv *env, jclass type, jlong colorImage,
                                                       jlong greyImage) {

    // TODO
    cv::Mat  &inMat =*(cv::Mat *) colorImage;
    cv::putText(inMat,"FPS", cv::Point(15,15),  CV_FONT_HERSHEY_SIMPLEX, 0.5, cv::Scalar(0,255,0));
}

JNIEXPORT void JNICALL
Java_org_opencv_samples_captureonly_JniManager_start(JNIEnv *env, jclass type) {

    // TODO

}

JNIEXPORT void JNICALL
Java_org_opencv_samples_captureonly_JniManager_stop(JNIEnv *env, jclass type) {

    // TODO

}

extern "C"
JNIEXPORT jstring JNICALL
Java_org_opencv_samples_captureonly_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
