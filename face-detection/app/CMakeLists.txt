# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)
SET(PROJECT_NAME detection_based_tracker)
SET(OpenCV_FOUND true )
FIND_PACKAGE(OpenCV REQUIRED)
add_library( # Sets the name of the library.
             ${PROJECT_NAME}

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             src/main/cpp/native-lib.h
             src/main/cpp/native-lib.cpp )


target_link_libraries( # Specifies the target library.
                       ${PROJECT_NAME}
                        ${OpenCV_LIBS}
                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )