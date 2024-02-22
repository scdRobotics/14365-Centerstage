package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name="LM_TeleOp", group="TeleOp")
public class LM_TeleOp extends LinearOpMode {

    final double SLIDE_POW = 0.75; //was 0.25

    final int MAX_SLIDE_POS = 1000; //Placeholder; find real value with LM_Slide_Pos_TeleOp

    final double INTAKE_SPEED = -0.8;

    //shove constants
    final int SHOVE_CLOSE = 0;
    final int SHOVE_OPEN = 78;

    final double ELEVATOR_PIXEL = 0;

    boolean plane = false;



    //boolean reset = false;

    enum STATE {
        normal,
        shoving,
        retracting,
        retracting2;
    }
    STATE state = STATE.normal;
   //state currentState;

    /* Proly not gonna need this
    public void switchState(STATE state){

    }
    */



    @Override
    public void runOpMode(){
        ElapsedTime timer = new ElapsedTime();
        Robot robot = new Robot(this,hardwareMap,telemetry,timer,true);

        double slow = 1;
        double slidePos = 0;
        double targetSlidePos = 0;

        double tarShovePos = 0;

        double y = 0;
        double x = 0;
        double rx = 0;

        double denominator = 0;
        double frontLeftPower = 0;
        double backLeftPower = 0;
        double frontRightPower = 0;
        double backRightPower = 0;

        //making copy of robot.xxx

        Delivery delivery = robot.delivery;
        Intake intake = robot.intake;
        Servo drop1 = robot.drop1;
        Servo drop2 = robot.drop2;
        DcMotorEx wheels = robot.wheels;
        DistanceSensor front = robot.front;
        DistanceSensor left = robot.left;
        DistanceSensor right = robot.right;
        DistanceSensor pixel = robot.pixel;
        DistanceSensor elevatorDist = robot.elevatorDist;


        LinearSlide linearSlide = robot.linearSlide;
        DcMotorEx slide = robot.slide;

        ShoveSystem shoveSystem = robot.shoveSystem;

        DcMotorEx frontLeftM = robot.frontLeftM; //Front Left Drive Motor initial declaration
        DcMotorEx frontRightM = robot.frontRightM; //Front Right Drive Motor initial declaration
        DcMotorEx backLeftM = robot.backLeftM; //Back Left Drive Motor initial declaration
        DcMotorEx backRightM = robot.backRightM; //Back Right Drive Motor initial declaration
        DcMotor shove = robot.shove;
        Servo airplane = robot.airplane;

        waitForStart();

        while(!isStopRequested()){

            /*

            DRIVE CODE

             */

            y = gamepad1.left_stick_y;
            x = gamepad1.left_stick_x * 1.1;
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
            backLeftPower = (y - x + rx) / denominator;
            frontRightPower = (-y - x - rx) / denominator;
            backRightPower = (y + x - rx) / denominator;

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
            telemetry.addData("Target Shove Pos: ", tarShovePos);
            telemetry.addData("Shove Pos: ", shove.getCurrentPosition());
            telemetry.addData("Plane", airplane.getPosition());
            telemetry.addData("Slide pos: ", slidePos);
            //telemetry.addData("Reset: ", reset);
            telemetry.update();

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


//            if(slidePos>MAX_SLIDE_POS && !gamepad2.b) { //gamepad2.b is a manual override. BE CAREFUL USING THIS
//                slidePos = MAX_SLIDE_POS;
//            }






            //////SLIDE CODE

            switch(state) {
                //Shove: close: -100; open: -3
                case retracting:
                    //if the slide is at the position where we should start shoving it, stop retracting,
                    // (not really relative now) when the shove is already retracted run or slide not in the range yet retract to position 0 probably
                    if(slide.getCurrentPosition() < 1100) { // might need to change the value where the shove start shoving
                        //                shoveSystem.runShove(-100, 1);
                        tarShovePos = SHOVE_CLOSE; //close
                        state = STATE.shoving;
                    }

                    if(slide.getCurrentPosition() < 3) { // not gonna happen, just for safety, since it's the first stage of retracting
                        state = STATE.normal; // when it fully retracts
                        tarShovePos = SHOVE_OPEN; // opening the shove
                    }

                    //running the motors
                    shoveSystem.runShove((int) tarShovePos, 0.9);
                    linearSlide.runSlide((int) slidePos, SLIDE_POW);
                    break;
                case retracting2: //just retracting all the way

                    if(slide.getCurrentPosition() < 3) {
                        state = STATE.normal; // when it fully retracts
                        tarShovePos = SHOVE_OPEN; // opening the shove
                    }

                case normal: //manual
                    if(gamepad2.left_stick_y>0.1 || gamepad2.left_stick_y<-0.1){
                        slidePos += -gamepad2.left_stick_y*5; //was *10
                        slidePos = (int) slidePos;
                    }
                    if(gamepad2.right_stick_y>0.1 || gamepad2.right_stick_y<-0.1){
                        tarShovePos = shove.getCurrentPosition() + -gamepad2.right_stick_y*5; //was *10
                        tarShovePos = (int) tarShovePos;
                    }
                    if(gamepad2.y) { //Reset button
                        slidePos = 0;
                        // state = STATE.retracting; // don't know why it's here
//                linearSlide.resetEncoders();
                    }

                    if(gamepad2.y) { //retract trigger, idk the tolerance or threshold put 0.2
                        targetSlidePos = 0;
                        state = STATE.retracting;
                    }

                    //running the motors
                    shoveSystem.runShove((int) tarShovePos, 0.9);
                    linearSlide.runSlide((int) slidePos, SLIDE_POW);
                    break;
                case shoving:
                    if(shove.getCurrentPosition() > -5){ // when close switch to 2nd stage of retracting
                        state = STATE.retracting2;
                    }

                    //running the motors
                    shoveSystem.runShove((int) tarShovePos, 0.9);
                    linearSlide.runSlide((int) slidePos, SLIDE_POW);
                default:
                    // code block
            }

            //state machine reset
            if(gamepad2.left_stick_y>0.1 || gamepad2.left_stick_y<-0.1 || gamepad2.right_stick_y>0.1 || gamepad2.right_stick_y<-0.1) {
                state = STATE.normal; // resetting the state during retracting
            }

            //end of reset

            if(gamepad2.dpad_up) {
                targetSlidePos = slide.getCurrentPosition();
                state = STATE.normal; // resetting the state during retracting
            }

            if(gamepad2.dpad_down) {
                slidePos = targetSlidePos;
            }


            //linearSlide.runSlide((int) slidePos, SLIDE_POW);



            if(gamepad2.a){
                delivery.dropPixel();
            }
            else{
                delivery.closeDrop();
            }


            if(gamepad2.right_stick_y>0.1 || gamepad2.right_stick_y<-0.1){
               tarShovePos = shove.getCurrentPosition() + -gamepad2.right_stick_y*5; //was *10
                tarShovePos = (int) tarShovePos;
                state = STATE.normal; // resetting the state during retracting
            }

            //shoveSystem.runShove((int) tarShovePos, 0.9);




            /*

            INTAKE CODE

             */

            if(gamepad2.right_trigger>0.1){
                intake.runIntake(-INTAKE_SPEED);
            }
            else if(gamepad2.right_bumper){
                intake.runIntake(INTAKE_SPEED);
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

            if(elevatorDist.getDistance(DistanceUnit.INCH) < ELEVATOR_PIXEL) {

            }



        }
    }
}
