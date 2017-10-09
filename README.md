# Android-opencv-native-samples
OpenCV repository samples with cmake and lldb  integration for latest android studio versions.  
## prequisites
* [Android studio 2.3.3.](https://developer.android.com/studio/index.html)
* Cmake support for android studio
* LLDB  support for android studio
* C++  support for android studio
* [Opencv 3.3 Android sdk version](https://sourceforge.net/projects/opencvlibrary/files/opencv-android/3.3.0/opencv-3.3.0-android-sdk.zip/download)

## How to install:
* clone the repository
```
git clone https://github.com/uelordi01/Android-opencv-native-samples.git
```
* *file->open->project* in android studio
* Find local.properties file in the project and add the opencv sdk dir as this snippet (looks like this).
```javascript
ndk.dir = /home/your_user/android-sdk/ndk-bundle
sdk.dir = /home/user/android-sdk
```
So add this line in local.properties:

```javascript
opencv.dir = you_opencv_sdk_dir
```
* Click on gradle sync button.
## Features:
* Opencv gradle compliant
* CMake integration.
* c++ support integration
* lldb easy c++ debugging.

## Gradle Configuration
* buildToolsVersion 26.0.0
* compileSdkVersion 25
* gradle version 3.3

## Prerequisites
* Cmake support for android studio
* LLDB  support for android studio
* C++  support for android studio

## Samples:
* FaceDetection: Opencv face detection sample comparing two methods java + c++ and only java.

## FAQ:
#### Why my camera image is upside down or mirrored?
This code is not prepared to take into account the smartphone camera position, I mean, there are some mobile phones that they have the camera on the left or on the right. Depending on that you image could be rotated or flipped.
#### Why the camera shows me rotated image when I put in portrait mode?
Yeah, the code is not still prepared to this this kind of rotations, so for now we will work in portrait mode.
#### Why gradle says that there is an error with the app compat library?
This is a gradle compatibility issue, that could be figured out putting the latest sdk in the compiledVersion
```javascript
compile 'com.android.support:appcompat-v7:25.3.1'
```
the 25.3.1 number has to coincide with your sdk compile version.
```javascript
android {
    compileSdkVersion 25
    ......
  }
```
## TIPS:
#### Would you like to change the camera from the front to back?
Just go to the res/layout folder and find the layout which corresponds to the activity. Later find this layout element:
```javascript
    <org.opencv.android.JavaCameraView
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        app:camera_id="front"
        app:show_fps="true"
        android:id="@+id/fd_activity_surface_view" />
```        
and change the **app:camera_id:**
```javascript
    app:camera_id="back"
```    
#### Would you like to show the fps in the camera layout?
Just go to the res/layout folder and find the layout which corresponds to the activity. Later find this layout element:
```javascript
    <org.opencv.android.JavaCameraView
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        app:camera_id="front"
        app:show_fps="false"
        android:id="@+id/fd_activity_surface_view" />
```        
and change the **app:show_fps**
```javascript
    app:show_fps="true"
```    
