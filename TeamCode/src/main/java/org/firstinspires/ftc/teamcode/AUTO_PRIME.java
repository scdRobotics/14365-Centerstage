package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "AUTO_PRIME", group = "Autonomous")
@Disabled
public class  AUTO_PRIME extends LinearOpMode {

    public Robot robot;

    /*
    Put Autonomous value constants
     */

    /*
    FULL FORMULA FOR ENCODER IS:

    Distance to Travel / Wheel Diameter * PI * Encoder Counts Per Rotation = Encoder Counts Per Distance

    You can solve this manually and put them in FIRST_ENCODER_VAL, SECOND_ENCODER_VAL, THIRD_ENCODER_VAL, and any others you make for simple encoder auto movement

    Or, you can empirically tune. Sometimes, good enough is good enough :)
     */

    public final int FIRST_ENCODER_VAL = 0;
    public final int SECOND_ENCODER_VAL = 0;

    public final double MOTOR_POWER = 0.3;

    int START_X = 0;
    double START_Y = 0;
    double START_ANG = 0;

    //Use robot.pause(TIME_WAIT) at beginning of program after wait for start, to not interfere with alliance partner :D
    public final double TIME_WAIT = 17;



    /*
    Put any computationally-heavy assignments or processing work that's SHARED ACROSS ALL AUTO PROGRAMS here
     */
    void initAuto(){
        ElapsedTime timer = new ElapsedTime();
        this.robot = new Robot(this, hardwareMap, telemetry, timer, false);
    }

    @Override
    public void runOpMode() throws InterruptedException{

    }


    public void encoderForward(double pow, int encVal){
        pow = Math.abs(pow);
        robot.frontLeftM.setTargetPosition(encVal);
        robot.frontRightM.setTargetPosition(encVal);
        robot.backLeftM.setTargetPosition(encVal);
        robot.backRightM.setTargetPosition(encVal);

        robot.frontLeftM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontRightM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeftM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRightM.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.frontLeftM.setPower(pow);
        robot.frontRightM.setPower(pow);
        robot.backLeftM.setPower(pow);
        robot.backRightM.setPower(pow);

        //IF THIS LOOP GOES ON TOO LONG, adjust tolerance in robot.java in autonomous robot motor declaration file
        while(!isStopRequested()){
            telemetry.addData("Front Left Encoder: ", robot.frontLeftM.getCurrentPosition());
            telemetry.addData("Front Right Encoder: ", robot.frontRightM.getCurrentPosition());
            telemetry.addData("Back Left Encoder: ", robot.backLeftM.getCurrentPosition());
            telemetry.addData("Back Right Encoder: ", robot.backRightM.getCurrentPosition());
            telemetry.update();
        }

        robot.frontLeftM.setPower(0);
        robot.frontRightM.setPower(0);
        robot.backLeftM.setPower(0);
        robot.backRightM.setPower(0);

        robot.frontLeftM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRightM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeftM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRightM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void encoderReverse(double pow, int encVal){
        pow = Math.abs(pow);
        robot.frontLeftM.setTargetPosition(-encVal);
        robot.frontRightM.setTargetPosition(-encVal);
        robot.backLeftM.setTargetPosition(-encVal);
        robot.backRightM.setTargetPosition(-encVal);

        robot.frontLeftM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontRightM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeftM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRightM.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.frontLeftM.setPower(pow);
        robot.frontRightM.setPower(pow);
        robot.backLeftM.setPower(pow);
        robot.backRightM.setPower(pow);

        //IF THIS LOOP GOES ON TOO LONG, adjust tolerance in robot.java in autonomous robot motor declaration file
        while(!isStopRequested()){
            telemetry.addData("Front Left Encoder: ", robot.frontLeftM.getCurrentPosition());
            telemetry.addData("Front Right Encoder: ", robot.frontRightM.getCurrentPosition());
            telemetry.addData("Back Left Encoder: ", robot.backLeftM.getCurrentPosition());
            telemetry.addData("Back Right Encoder: ", robot.backRightM.getCurrentPosition());
            telemetry.update();
        }

        robot.frontLeftM.setPower(0);
        robot.frontRightM.setPower(0);
        robot.backLeftM.setPower(0);
        robot.backRightM.setPower(0);

        robot.frontLeftM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRightM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeftM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRightM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void encoderStrafeRight(double pow, int encVal){
        pow = Math.abs(pow);
        robot.frontLeftM.setTargetPosition(encVal);
        robot.frontRightM.setTargetPosition(-encVal);
        robot.backLeftM.setTargetPosition(-encVal);
        robot.backRightM.setTargetPosition(encVal);

        robot.frontLeftM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontRightM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeftM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRightM.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.frontLeftM.setPower(pow);
        robot.frontRightM.setPower(pow);
        robot.backLeftM.setPower(pow);
        robot.backRightM.setPower(pow);

        //IF THIS LOOP GOES ON TOO LONG, adjust tolerance in robot.java in autonomous robot motor declaration file
        while(!isStopRequested()){
            telemetry.addData("Front Left Encoder: ", robot.frontLeftM.getCurrentPosition());
            telemetry.addData("Front Right Encoder: ", robot.frontRightM.getCurrentPosition());
            telemetry.addData("Back Left Encoder: ", robot.backLeftM.getCurrentPosition());
            telemetry.addData("Back Right Encoder: ", robot.backRightM.getCurrentPosition());
            telemetry.update();
        }

        robot.frontLeftM.setPower(0);
        robot.frontRightM.setPower(0);
        robot.backLeftM.setPower(0);
        robot.backRightM.setPower(0);

        robot.frontLeftM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRightM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeftM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRightM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void encoderStrafeLeft(double pow, int encVal){
        pow = Math.abs(pow);
        robot.frontLeftM.setTargetPosition(encVal);
        robot.frontRightM.setTargetPosition(encVal);
        robot.backLeftM.setTargetPosition(encVal);
        robot.backRightM.setTargetPosition(-encVal);

        robot.frontLeftM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontRightM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeftM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRightM.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.frontLeftM.setPower(pow);
        robot.frontRightM.setPower(pow);
        robot.backLeftM.setPower(pow);
        robot.backRightM.setPower(pow);

        //IF THIS LOOP GOES ON TOO LONG, adjust tolerance in robot.java in autonomous robot motor declaration file
        while(!isStopRequested()){
            telemetry.addData("Front Left Encoder: ", robot.frontLeftM.getCurrentPosition());
            telemetry.addData("Front Right Encoder: ", robot.frontRightM.getCurrentPosition());
            telemetry.addData("Back Left Encoder: ", robot.backLeftM.getCurrentPosition());
            telemetry.addData("Back Right Encoder: ", robot.backRightM.getCurrentPosition());
            telemetry.update();
        }

        robot.frontLeftM.setPower(0);
        robot.frontRightM.setPower(0);
        robot.backLeftM.setPower(0);
        robot.backRightM.setPower(0);

        robot.frontLeftM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRightM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeftM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRightM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void encoderTurnLeft(double pow, int encVal){
        pow = Math.abs(pow);
        robot.frontLeftM.setTargetPosition(-encVal);
        robot.frontRightM.setTargetPosition(encVal);
        robot.backLeftM.setTargetPosition(-encVal);
        robot.backRightM.setTargetPosition(encVal);

        robot.frontLeftM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontRightM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeftM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRightM.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.frontLeftM.setPower(pow);
        robot.frontRightM.setPower(pow);
        robot.backLeftM.setPower(pow);
        robot.backRightM.setPower(pow);

        //IF THIS LOOP GOES ON TOO LONG, adjust tolerance in robot.java in autonomous robot motor declaration file
        while(!isStopRequested()){
            telemetry.addData("Front Left Encoder: ", robot.frontLeftM.getCurrentPosition());
            telemetry.addData("Front Right Encoder: ", robot.frontRightM.getCurrentPosition());
            telemetry.addData("Back Left Encoder: ", robot.backLeftM.getCurrentPosition());
            telemetry.addData("Back Right Encoder: ", robot.backRightM.getCurrentPosition());
            telemetry.update();
        }

        robot.frontLeftM.setPower(0);
        robot.frontRightM.setPower(0);
        robot.backLeftM.setPower(0);
        robot.backRightM.setPower(0);

        robot.frontLeftM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRightM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeftM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRightM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void encoderTurnRight(double pow, int encVal){
        pow = Math.abs(pow);
        robot.frontLeftM.setTargetPosition(encVal);
        robot.frontRightM.setTargetPosition(-encVal);
        robot.backLeftM.setTargetPosition(encVal);
        robot.backRightM.setTargetPosition(-encVal);

        robot.frontLeftM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontRightM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeftM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRightM.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.frontLeftM.setPower(pow);
        robot.frontRightM.setPower(pow);
        robot.backLeftM.setPower(pow);
        robot.backRightM.setPower(pow);

        //IF THIS LOOP GOES ON TOO LONG, adjust tolerance in robot.java in autonomous robot motor declaration file
        while(!isStopRequested()){
            telemetry.addData("Front Left Encoder: ", robot.frontLeftM.getCurrentPosition());
            telemetry.addData("Front Right Encoder: ", robot.frontRightM.getCurrentPosition());
            telemetry.addData("Back Left Encoder: ", robot.backLeftM.getCurrentPosition());
            telemetry.addData("Back Right Encoder: ", robot.backRightM.getCurrentPosition());
            telemetry.update();
        }

        robot.frontLeftM.setPower(0);
        robot.frontRightM.setPower(0);
        robot.backLeftM.setPower(0);
        robot.backRightM.setPower(0);

        robot.frontLeftM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRightM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeftM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRightM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }


}