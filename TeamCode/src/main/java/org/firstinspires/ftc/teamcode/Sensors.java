package org.firstinspires.ftc.teamcode;
//Package is a VERY important step! Required to do basically anything with the robot

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
//Most imports are automatically handled by Android Studio as you program

public class Sensors extends Subsystem {

    private ColorSensor pixelSense;


    //"Constructor" object for Sensors
    public Sensors(ColorSensor pixelSense, Telemetry telemetry, HardwareMap hardwareMap, ElapsedTime timer){
        super(telemetry,hardwareMap,timer); //Map basic, required aspects of robot

        this.pixelSense = pixelSense;
    }

    public double returnARGBReadout(){
        return pixelSense.argb();
    }

    public double returnAlphaReadout(){
        return pixelSense.alpha();
    }

    public double returnRedReadout(){
        return pixelSense.red();
    }

    public double returnGreenReadout(){
        return pixelSense.green();
    }

    public double returnBlueReadout(){
        return pixelSense.blue();
    }

    public double returnPurpleReadout(){
        return pixelSense.blue() * pixelSense.red();
    }




}