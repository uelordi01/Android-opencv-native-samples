//
// Created by uelordi on 6/10/17.
//

#ifndef CAPTUREONLY_NATIVE_LIB_CPP_H
#define CAPTUREONLY_NATIVE_LIB_CPP_H
#include <jni.h>
#ifdef __cplusplus
extern "C" {
#endif
JNIEXPORT void JNICALL
Java_org_opencv_samples_captureonly_JniManager_init(JNIEnv *env, jclass type);

JNIEXPORT void JNICALL
Java_org_opencv_samples_captureonly_JniManager_process(JNIEnv *env, jclass type, jlong colorImage,
                                                       jlong greyImage);
JNIEXPORT void JNICALL
Java_org_opencv_samples_captureonly_JniManager_start(JNIEnv *env, jclass type);

JNIEXPORT void JNICALL
Java_org_opencv_samples_captureonly_JniManager_stop(JNIEnv *env, jclass type);

#ifdef __cplusplus
}
#endif
#endif //CAPTUREONLY_NATIVE_LIB_CPP_H
