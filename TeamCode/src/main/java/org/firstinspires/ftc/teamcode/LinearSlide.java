package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LinearSlide extends Subsystem{
    private final DcMotorEx slide1;
    private final DcMotorEx slide2;
    public LinearSlide(Telemetry telemetry, HardwareMap hardwareMap, ElapsedTime timer, DcMotorEx slide1, DcMotorEx slide2) {
        super(telemetry, hardwareMap, timer);
        this.slide1=slide1;
        this.slide2=slide2;
    }

    public void runSlide(int pos, double power){
        slide1.setTargetPosition(pos);
        slide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slide1.setPower(power);
    }

    public int slideIdxToEncoderVal(int idx){

        if(idx==0){
            return 0;
        }
        else if(idx==1){
            return 1776;
        }
        else if(idx==2){
            return 2900;
        }
        else if(idx==3){
            return 4000;
        }

        return 0;
    }
}
