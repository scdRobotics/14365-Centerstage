package org.firstinspires.ftc.teamcode;
//Package is a VERY important step! Required to do basically anything with the robot

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;

//Most imports are automatically handled by Android Studio as you program

public class plane extends Subsystem {
    private final Servo airplane;

    //"Constructor" object for Delivery
    public plane(Servo airplane, Telemetry telemetry, HardwareMap hardwareMap, ElapsedTime timer){
        super(telemetry,hardwareMap,timer);
        this.airplane=airplane;
    }


    public void runPlane(double pos){
        airplane.setPosition(pos);
    }
}