package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name="LM_TeleOp", group="TeleOp")
public class LM_TeleOp extends LinearOpMode {

    /*
       Setting constants
     */

    final double SLIDE_POW = 0.75; //was 0.25

    final int MAX_SLIDE_POS = 1000; //Placeholder; find real value with LM_Slide_Pos_TeleOp

    final double INTAKE_SPEED = 0.8;


    final double ELEVATOR_PIXEL = 0;

    boolean plane = false;


    /*
        Declaring the enum for the statemachine
     */

    enum STATE {
        normal,
        shoving,
        retracting,
        retracting2;
    }

    //Setting the initial state

    STATE state = STATE.normal;

    enum PIXEL_STATE {
        initial,
        lifting,
        lowering
    }

    PIXEL_STATE pixel_state = PIXEL_STATE.initial;


    @Override
    public void runOpMode(){

        /*
             Setup and declarations
        */


        ElapsedTime timer = new ElapsedTime();
        Robot robot = new Robot(this,hardwareMap,telemetry,timer,true);

        double slow = 1;
        double slidePos = 0;
        double targetSlidePos = 0;



        double y = 0;
        double x = 0;
        double rx = 0;

        double mag = 0;

        double denominator = 0;
        double frontLeftPower = 0;
        double backLeftPower = 0;
        double frontRightPower = 0;
        double backRightPower = 0;


        Delivery delivery = robot.delivery;
        Intake intake = robot.intake;
        Servo drop1 = robot.drop1;
        Servo drop2 = robot.drop2;
        CRServo hang = robot.hang;
        DcMotorEx wheels = robot.wheels;
        DistanceSensor front = robot.front;
        DistanceSensor left = robot.left;
        DistanceSensor right = robot.right;
        DistanceSensor pixel = robot.pixel;



        LinearSlide linearSlide = robot.linearSlide;
        DcMotorEx slide = robot.slide;



        DcMotorEx frontLeftM = robot.frontLeftM; //Front Left Drive Motor initial declaration
        DcMotorEx frontRightM = robot.frontRightM; //Front Right Drive Motor initial declaration
        DcMotorEx backLeftM = robot.backLeftM; //Back Left Drive Motor initial declaration
        DcMotorEx backRightM = robot.backRightM; //Back Right Drive Motor initial declaration

        Servo airplane = robot.airplane;

        robot.linearSlide.resetEncoders();


        waitForStart();

        while(!isStopRequested()){

            /*

            DRIVE CODE

             */

            y = gamepad1.left_stick_y;
            x = gamepad1.left_stick_x * 1.1;

            /* Cardinal direction code
            mag = Math.sqrt(Math.pow(x,2)+Math.pow(y,2));

            if(Math.abs(x) >= Math.abs(y)){
                y = 0;
                x = Math.signum(x) * mag;
            }else {
                x = 0;
                y = Math.signum(y) * mag;
            }

             */

            rx = gamepad1.right_stick_x;



            if(gamepad1.left_bumper && (rx>0.1 || rx<-0.1)){
                slow=3;
            }
            else if(gamepad1.left_bumper && (y>0.1 || x>0.1 || y<-0.1 || x<-0.1)){
                slow=2;
            }
            else{
                slow=1;
            }

            denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            frontLeftPower = (-y + x + rx) / denominator;
            backLeftPower = (-y - x + rx) / denominator;
            frontRightPower = (-y - x - rx) / denominator;
            backRightPower = (-y + x - rx) / denominator;

            /*
            Recall that for a right turn:
                FRONT LEFT Power Should Be POSITIVE
                FRONT RIGHT Power Should Be NEGATIVE
                BACK LEFT Power Should Be POSITIVE
                FRONT LEFT Power Should Be NEGATIVE
                ^^ ALL WITHIN EQUAL MAGNITUDES

            TESTING PROCEDURE:
                1. Hold joystick to the left while robot is on the ground. Observe values
                    -If all value magnitudes are equal and positive/negative is as expected, AND magnitudes are sufficiently high, likely a build issue (shot motors unable to withstand pressure? idk im not a build guy but smth like that)
                    -Else, continue
                2. CONTINUE HOLDING joystick to the left while lifting robot into air. MOTOR POWER VALUES SHOULD NOT VARY. If motors change behavior and spin as expected, it's a build problem.

                IF VALUES at ANY POINT are NOT as expected, there's likely a program OR gamepad issue in play. Observe values, follow the formulas here, and see what the problem may be. Good luck !

                (Also, make sure "slow" is at 1, that could just be causing issues)
            */


            telemetry.addData("Front Left Power: ", frontLeftPower);
            telemetry.addData("Back Left Power: ", backLeftPower);
            telemetry.addData("Front Right Power: ", frontRightPower);
            telemetry.addData("Back Right Power: ", backRightPower);
            telemetry.addData("Curr Slow Val: ", slow);
            telemetry.addData("Plane", airplane.getPosition());
            telemetry.addData("Slide pos: ", slidePos);
            telemetry.addData("State: ", state);
            telemetry.addData("Left Y: ", gamepad2.left_stick_y);
            telemetry.update();

            /*
                Making the robot move with the joystick
             */

            if(Math.abs(gamepad1.left_stick_y)>0.075 || Math.abs(gamepad1.right_stick_y)>0.075 || Math.abs(gamepad1.right_stick_x) > 0.075 || Math.abs(gamepad1.left_stick_x) > 0.075){ //Account for potential joystick drift
                frontLeftM.setPower(frontLeftPower/slow);
                backLeftM.setPower(backLeftPower/slow);
                frontRightM.setPower(frontRightPower/slow);
                backRightM.setPower(backRightPower/slow);
            }
            else{
                frontLeftM.setPower(0);
                frontRightM.setPower(0);
                backLeftM.setPower(0);
                backRightM.setPower(0);
            }



            /*

            SLIDE & SERVO CODE

             */

            //NOTE: Currently manual only. Look at last year's "slidePosIdx" for an example implementation


            //////SLIDE CODE

            /*
                State machine
             */

            switch(state) {







                case normal: //driver controlled state
                                 if(gamepad2.left_stick_y>0.1 || gamepad2.left_stick_y<-0.1) {
                                     slidePos += -gamepad2.left_stick_y * 5;
                                     slidePos = (int) slidePos;
                                  }


                                if(gamepad2.y) { //Reset button
                                    slidePos = 974;
                                }

                                linearSlide.runSlide((int) slidePos, SLIDE_POW);
                                break;
                default:
                    // code block
            }

            //State Machine ends







            //state machine reset
            if(gamepad2.left_stick_y>0.1 || gamepad2.left_stick_y<-0.1 || gamepad2.right_stick_y>0.1 || gamepad2.right_stick_y<-0.1) {
                state = STATE.normal; // resetting the state during retracting
            }

            //end of reset


            //Setting target position for the slide
            if(gamepad2.dpad_up) {
                targetSlidePos = slide.getCurrentPosition();
                state = STATE.normal; // resetting the state during retracting
            }

            //Moving the slide to the target position
            if(gamepad2.dpad_down) {
                slidePos = targetSlidePos;
            }



            linearSlide.runSlide((int) slidePos, SLIDE_POW);



//            if(gamepad2.a){
//                delivery.dropPixel();
//            }
//            else{
//                delivery.closeDrop();
//            }








            /*

            INTAKE CODE

             */
            //Forwards
            if(gamepad2.left_trigger>0.1){
                intake.runIntake(INTAKE_SPEED);
            }
            //Backwards
            else if(gamepad2.right_trigger>0.1){
                intake.runIntake(-INTAKE_SPEED);
            }
            else{
                intake.runIntake(0);
            }



            /*

            AIRPLANE LAUNCHER CODE

             */

            if(gamepad1.left_bumper){ //Rudimentary implementation, may need to hold down bumper until airplane is expelled
                delivery.useAirplane();
            } else {
                delivery.resetPlane();
            }
            //NOTE: No return servo pos, because once airplane is launched, no reason to reset servo


            if(gamepad2.b) {
                robot.delivery.openDropAuto();
            } else
            {
                robot.delivery.closeDropAuto();
            }

            if(gamepad2.a) {
                robot.hang.setPower(1);
            }
            else if(gamepad2.x) {
                robot.hang.setPower(-1);
            }
            else {
                robot.hang.setPower(0);
            }



        }
    }
}
