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
    private DistanceSensor left;
    private DistanceSensor right; //Dist Sensor initial declaration
    private DistanceSensor tbd;

    private RevBlinkinLedDriver led;


    private BNO055IMU imu;


    private double IMU_OFFSET = 0;

    private Servo backOdo;
    private Servo leftOdo;
    private Servo rightOdo;
    boolean isBlue = PoseTransfer.isBlue;

    public enum LED_STATE{
        DEFAULT,
        DESYNCED,
        POLE_GOOD,
        POLE_BAD,
        CONE_DETECTED
    }

    public double getLeftDist(){
        return left.getDistance(DistanceUnit.INCH);
    }

    public double getRightDist(){
        return right.getDistance(DistanceUnit.INCH);
    }

    public double getTBDDist(){
        return tbd.getDistance(DistanceUnit.INCH);
    }

    LED_STATE current = LED_STATE.DEFAULT;

    public double getIMUReadout(){
        return imu.getAngularOrientation().firstAngle + IMU_OFFSET;
    }


    public void setImuOffset(double i){
        IMU_OFFSET = i;
    }

    public void deployOdo(){
        backOdo.setPosition(1);
        rightOdo.setPosition(0);
        leftOdo.setPosition(1);
    }

    public void retractOdo(){
        backOdo.setPosition(0);
        rightOdo.setPosition(0.5);
        leftOdo.setPosition(0);
    }



    public void updateLEDs(){
        switch(current){

            case DEFAULT:
                if(isBlue){
                    led.setPattern(RevBlinkinLedDriver.BlinkinPattern.SKY_BLUE);
                }
                else{
                    led.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);
                }
                break;


            case DESYNCED:
                if(isBlue){
                    led.setPattern(RevBlinkinLedDriver.BlinkinPattern.HEARTBEAT_BLUE);
                }
                else{
                    led.setPattern(RevBlinkinLedDriver.BlinkinPattern.HEARTBEAT_RED);
                }
                break;
        }
    }


    //"Constructor" object for Sensors
    public Sensors(BNO055IMU imu, DistanceSensor right, DistanceSensor left, RevBlinkinLedDriver led, Servo backOdo, Servo leftOdo, Servo rightOdo, Telemetry telemetry, HardwareMap hardwareMap, ElapsedTime timer){
        super(telemetry,hardwareMap,timer); //Map basic, required aspects of robot

        this.imu = imu;

        this.right=right;
        this.left=left;
        //this.tbd=;

        this.led = led;

        this.backOdo = backOdo;
        this.leftOdo = leftOdo;
        this.rightOdo = rightOdo;



//        updateLEDs();
    }
}
