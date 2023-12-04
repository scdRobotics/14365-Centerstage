package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class Vision extends Subsystem{
    public Vision(Telemetry telemetry, HardwareMap hardwareMap, ElapsedTime timer) {
        //declare camera
        public OpenCvCamera webcam1;

        Pipeline pipeline = new Pipeline();

        /*public void activateYellowPieplineGamera1(){
            //webcam1.setPipeline

             public void onOpened(){
                 webcam1.startStreaming(1280,720);
                 telemetry.addData("Camera Opened! ", "");
                 telemetry.update();
            }

        }*/

        super(telemetry, hardwareMap, timer);
    }
}
