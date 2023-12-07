package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class Vision extends Subsystem{
    //declare camera
    public OpenCvCamera webcam1;

    public Vision(Telemetry telemetry, HardwareMap hardwareMap, ElapsedTime timer) {


        Pipeline pipeline = new Pipeline(1, 578.272, 578.272, 402.145, 221.506);//since we have new cameras, the parameters are subject to change

        public Vision(OpenCvCamera webcam1, Telemetry telemetry, HardwareMap hardwareMap, ElapsedTime timer){
                    //super(telemetry, hardwareMap, timer);
                    this.webcam1 = webcam1;

        }


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
