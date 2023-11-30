package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import com.qualcomm.robotcore.hardware.Servo;


public class Robot{
    /*
    Variable Declarations (Including Subsystems)
     */
    public final ElapsedTime timer;
    private final LinearOpMode opMode;
    private final HardwareMap hardwareMap;
    private final Telemetry telemetry;

    public SampleMecanumDrive drive;
    public Delivery delivery;
    public Intake intake;
    public Servo drop;
    public DcMotorEx wheels;

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

        delivery = new Delivery(telemetry, hardwareMap, timer, drop);

        intake = new Intake(telemetry, hardwareMap, timer, wheels);

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