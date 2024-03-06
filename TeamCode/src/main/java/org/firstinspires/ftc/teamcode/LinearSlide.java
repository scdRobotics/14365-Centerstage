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
    private final DcMotorEx slide;
    public LinearSlide(Telemetry telemetry, HardwareMap hardwareMap, ElapsedTime timer, DcMotorEx slide) {
        super(telemetry, hardwareMap, timer);
        this.slide=slide;
    }

    public void runSlide(int pos, double power){
        telemetry.addData("SSSS", "");
        telemetry.update();
        slide.setTargetPosition(pos);
        slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slide.setPower(power);
    }

    public void resetEncoders(){
        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

}