package org.firstinspires.ftc.teamcode.util;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

/*
public class Pipeline extends OpenCvPipeline {
    Mat dst = new Mat();
    Mat hsvMat = new Mat();
    Mat hsvThresh = new Mat();

    private boolean pause = false;

    @Override
    public void onViewportTapped() {

    }

    public Mat processFrame(Mat inputMat) {

        if (!pause) {
            Imgproc.cvtColor(inputMat, hsvMat, Imgproc.COLOR_RGB2HSV);
            //Core.inRange(hsvMat, lowThresh, highThresh, hsvThresh);
        }

    }



}


 */