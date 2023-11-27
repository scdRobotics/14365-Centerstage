package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Sensors extends Subsystem{
    private DistanceSensor front; //Underbelly Dist Sensor #1 initial declaration

    private RevBlinkinLedDriver led;


    private BNO055IMU imu;


    private double IMU_OFFSET = 0;

    private Servo backOdo;
    private Servo leftOdo;
    private Servo rightOdo;


    public enum LED_STATE{
        DEFAULT,
        DESYNCED,
        POLE_GOOD,
        POLE_BAD,
        CONE_DETECTED
    }

    LED_STATE current = LED_STATE.DEFAULT;

//    boolean isBlue = PoseTransfer.isBlue;

    //"Constructor" object for Sensors
    public Sensors(BNO055IMU imu, DistanceSensor front, RevBlinkinLedDriver led, Servo backOdo, Servo leftOdo, Servo rightOdo, Telemetry telemetry, HardwareMap hardwareMap, ElapsedTime timer){
        super(telemetry,hardwareMap,timer); //Map basic, required aspects of robot

        this.imu = imu;

        this.front=front;

        this.led = led;

        this.backOdo = backOdo;
        this.leftOdo = leftOdo;
        this.rightOdo = rightOdo;

//        updateLEDs();
    }
}
