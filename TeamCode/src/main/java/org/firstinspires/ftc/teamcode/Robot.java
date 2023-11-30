package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.util.BNO055IMUUtil;
import org.firstinspires.ftc.teamcode.util.AxisDirection;







public class Robot{
    /*
    Variable Declarations (Including Subsystems)
     */
    public final ElapsedTime timer;
    private final LinearOpMode opMode;
    private final HardwareMap hardwareMap;
    private final Telemetry telemetry;

    public SampleMecanumDrive drive;
    public Sensors sensors;
    public BNO055IMU imu;
    public DistanceSensor right;
    public DistanceSensor left;
    public RevBlinkinLedDriver led;

    public Servo backOdo;
    public Servo rightOdo;
    public Servo leftOdo;



    /*
    Constructor w/ important data to bring in from operational programs
     */
    public Robot(LinearOpMode opMode, HardwareMap hardwareMap, Telemetry telemetry, ElapsedTime timer, boolean isTeleOp) {
        this.opMode = opMode;
        this.timer = timer;
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        this.init(isTeleOp);
    }

    /*
    Initialize Robot objects
     */
    private void init(boolean isTeleOp){
        /*
        HardwareMap
         */




        /*
        Setup Subsystem Objects
         */

        drive = new SampleMecanumDrive(hardwareMap);

        right = hardwareMap.get(DistanceSensor.class, "right");
        left = hardwareMap.get(DistanceSensor.class, "left");
        /*leftFront = hardwareMap.get(DistanceSensor.class, "leftFront");
        rightFront = hardwareMap.get(DistanceSensor.class, "rightFront");
        leftBack = hardwareMap.get(DistanceSensor.class, "leftBack");
        rightBack = hardwareMap.get(DistanceSensor.class, "rightBack");*/

        led = hardwareMap.get(RevBlinkinLedDriver.class, "led");




        imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);
        BNO055IMUUtil.remapZAxis(imu, AxisDirection.NEG_X);



        backOdo = hardwareMap.get(Servo.class, "backOdo");
        leftOdo = hardwareMap.get(Servo.class, "leftOdo");
        rightOdo = hardwareMap.get(Servo.class, "rightOdo");


        sensors = new Sensors(imu, right, left, led, backOdo, leftOdo, rightOdo, telemetry, hardwareMap, timer);

    }

    /*
    Quick telemetry function. Helps avoid conflicts caused by having telemetry in several threads. Also, can implement a "TelemetryStream" object here, if desired
     */
    public void telemetry(String caption, String value){
        telemetry.addData(caption, value);
        telemetry.update();
    }

    /*
    Simple pause function-- basic, necessary function for all aspects of robot
     */
    public void pause(double secs){
        ElapsedTime mRuntime = new ElapsedTime();
        while(mRuntime.time()< secs && !opMode.isStopRequested()){

        }
    }



}