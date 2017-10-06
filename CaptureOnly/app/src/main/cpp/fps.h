//
// Created by uelordi on 6/10/17.
//

#ifndef CAPTUREONLY_FPS_H
#define CAPTUREONLY_FPS_H
#include <ctime>
class Fps {
public:
    Fps();
    ~Fps();
    void start() {
        time_start = std::time(0);
        time_end = std::time(0);
    }
    void float checkFps() {
        time_end = std::time(0);
        float fps = (float)1/(time_end - time_start);
        return fps;
    }
    void end() {

    }
private:
    int frame;
    std::time_t time_start;
    std::time_t time_end;


};


#endif //CAPTUREONLY_FPS_H
