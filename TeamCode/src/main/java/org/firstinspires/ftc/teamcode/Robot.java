package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.motors.RevRoboticsCoreHexMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

import org.firstinspires.ftc.teamcode.util.BNO055IMUUtil;
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.teamcode.util.AxisDirection;
import com.qualcomm.robotcore.hardware.Servo;


import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Robot{
    /*
    Variable Declarations (Including Subsystems)
     */
    public final ElapsedTime timer;
    private final LinearOpMode opMode;
    private final HardwareMap hardwareMap;
    private final Telemetry telemetry;

    public SampleMecanumDrive drive;
    public BNO055IMU imu;


    /*
    From delivery branch
     */
    public Delivery delivery;
    public Intake intake;
    public Servo drop1;
    public Servo drop2;
    public DcMotorEx wheels;
    public DistanceSensor front;
    public DistanceSensor left;
    public DistanceSensor right;
    public DistanceSensor pixel;

    /*
    From linear slide branch
     */
    public LinearSlide linearSlide;
    public DcMotorEx slide;

    public ShoveSystem shoveSystem;




    public DcMotorEx frontLeftM; //Front Left Drive Motor initial declaration
    public DcMotorEx frontRightM; //Front Right Drive Motor initial declaration
    public DcMotorEx backLeftM; //Back Left Drive Motor initial declaration
    public DcMotorEx backRightM; //Back Right Drive Motor initial declaration
    public DcMotor shove;
    public Servo airplane;
    public Servo elevator;


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
        if(isTeleOp) {

            frontRightM = hardwareMap.get(DcMotorEx.class, "frontRight");
            frontLeftM = hardwareMap.get(DcMotorEx.class, "frontLeft");
            backLeftM = hardwareMap.get(DcMotorEx.class, "backLeft");
            backRightM = hardwareMap.get(DcMotorEx.class, "backRight");

            frontLeftM.setDirection(DcMotorSimple.Direction.REVERSE);
            backLeftM.setDirection(DcMotorSimple.Direction.REVERSE);

            frontLeftM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); //Set run mode of front left motor to use power, NOT encoders
            frontRightM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); //Set run mode of front right motor to use power, NOT encoders
            backLeftM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); //Set run mode of back left motor to use power, NOT encoders
            backRightM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); //Set run mode of back right motor to use power, NOT encoders

            frontLeftM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            frontRightM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backLeftM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backRightM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        } else {

        /*drive = new SampleMecanumDrive(hardwareMap);

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);
        BNO055IMUUtil.remapZAxis(imu, AxisDirection.NEG_X);

        drive = new SampleMecanumDrive(hardwareMap);*/

            int TOLERANCE = 15;

            frontRightM = hardwareMap.get(DcMotorEx.class, "frontRight");
            frontLeftM = hardwareMap.get(DcMotorEx.class, "frontLeft");
            backLeftM = hardwareMap.get(DcMotorEx.class, "backLeft");
            backRightM = hardwareMap.get(DcMotorEx.class, "backRight");

            frontLeftM.setDirection(DcMotorSimple.Direction.REVERSE);
            backLeftM.setDirection(DcMotorSimple.Direction.REVERSE);

            frontLeftM.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //Set run mode of front left motor to use power, NOT encoders
            frontRightM.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //Set run mode of front right motor to use power, NOT encoders
            backLeftM.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //Set run mode of back left motor to use power, NOT encoders
            backRightM.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //Set run mode of back right motor to use power, NOT encoders

            frontRightM.setTargetPositionTolerance(TOLERANCE);
            frontLeftM.setTargetPositionTolerance(TOLERANCE);
            backLeftM.setTargetPositionTolerance(TOLERANCE);
            backRightM.setTargetPositionTolerance(TOLERANCE);

            frontLeftM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            frontRightM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backLeftM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backRightM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        }

        slide=hardwareMap.get(DcMotorEx.class,"slide");
        slide.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        linearSlide = new LinearSlide(telemetry, hardwareMap, timer, slide);

        shove=hardwareMap.get(DcMotor.class,"shove");
        shove.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shoveSystem = new ShoveSystem(telemetry, hardwareMap, timer, shove);

        wheels = hardwareMap.get(DcMotorEx.class,"wheels");
        intake = new Intake(telemetry, hardwareMap, timer, wheels, elevator);

        drop1 = hardwareMap.get(Servo.class,"drop1");
        drop2 = hardwareMap.get(Servo.class, "drop2");
        airplane = hardwareMap.get(Servo.class,"airplane");
        elevator = hardwareMap.get(Servo.class, "elevator");
        delivery = new Delivery(telemetry, hardwareMap, timer, drop1, drop2, airplane);



        right = hardwareMap.get(DistanceSensor.class, "right");
        left = hardwareMap.get(DistanceSensor.class, "left");
        front = hardwareMap.get(DistanceSensor.class, "front");
        pixel = hardwareMap.get(DistanceSensor.class, "pixel");




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