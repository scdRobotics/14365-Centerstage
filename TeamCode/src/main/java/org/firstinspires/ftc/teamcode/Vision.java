package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
/*
public class Vision extends Subsystem{
    //declare camera
    public OpenCvCamera webcam;

    public Vision(Telemetry telemetry, HardwareMap hardwareMap, ElapsedTime timer) {


        Pipeline pipeline = new Pipeline(1, 578.272, 578.272, 402.145, 221.506);//since we have new cameras, the parameters are subject to change

        //Constructor (Done)
        public Vision(OpenCvCamera webcam, Telemetry telemetry, HardwareMap hardwareMap, ElapsedTime timer){
                super(telemetry, hardwareMap, timer);
                this.webcam = webcam;
        }


        /*public void activateYellowPipelineCamera(){
            //webcam1.setPipeline

             public void onOpened(){
                 webcam.startStreaming(1280,720);
                 telemetry.addData("Camera Opened! ", "");
                 telemetry.update();
            }

        }*/
/*
        public void activateYellowPipelineCamera(){
            webcam.setPipeline(Pipeline);
            webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
            {
                @Override
                public void onOpened()
                {
                    webcam.startStreaming(1280,720, OpenCvCameraRotation.UPRIGHT);
                    telemetry.addData("Camera Opened! ", "");
                    telemetry.update();
                }

                @Override
                public void onError(int errorCode)
                {

                }
            });
        }


        super(telemetry, hardwareMap, timer);
    }
}*/
