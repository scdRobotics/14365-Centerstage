package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="LM_TeleOp", group="TeleOp")
public class LM_TeleOp extends LinearOpMode {

    final double SLIDE_POW = 0.25;

    final int MAX_SLIDE_POS = 1000; //Placeholder; find real value with LM_Slide_Pos_TeleOp

    final double INTAKE_SPEED = 0.8;
    final double INTAKE_REVERSE_SPEED = -0.3;

    @Override
    public void runOpMode(){
        ElapsedTime timer = new ElapsedTime();
        Robot robot = new Robot(this,hardwareMap,telemetry,timer,true);

        double slow = 1;
        double slidePos = 0;

        double y = 0;
        double x = 0;
        double rx = 0;

        double denominator = 0;
        double frontLeftPower = 0;
        double backLeftPower = 0;
        double frontRightPower = 0;
        double backRightPower = 0;

        waitForStart();

        while(!isStopRequested()){

            /*

            DRIVE CODE

             */

            y = -gamepad1.left_stick_y;
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
            frontLeftPower = (y + x + rx) / denominator;
            backLeftPower = (y - x + rx) / denominator;
            frontRightPower = (y - x - rx) / denominator;
            backRightPower = (y + x - rx) / denominator;

            if(y<0.075 || x<0.0825 || rx<0.075){ //Account for potential joystick drift
                robot.frontLeftM.setPower(frontLeftPower/slow);
                robot.backLeftM.setPower(backLeftPower/slow);
                robot.frontRightM.setPower(frontRightPower/slow);
                robot.backRightM.setPower(backRightPower/slow);
            }
            else{
                robot.frontLeftM.setPower(0);
                robot.frontRightM.setPower(0);
                robot.backLeftM.setPower(0);
                robot.backRightM.setPower(0);
            }



            /*

            SLIDE & SERVO CODE

             */

            //NOTE: Currently manual only. Look at last year's "slidePosIdx" for an example implementation

            if(gamepad2.left_stick_y>0.1 || gamepad2.left_stick_y<-0.1){
                slidePos += -gamepad2.left_stick_y*10;
                slidePos = (int) slidePos;
            }

            if(gamepad2.y) { //Reset button
                slidePos = 0;
                robot.linearSlide.resetEncoders();
            }

            if(slidePos>MAX_SLIDE_POS && !gamepad2.b) { //gamepad2.b is a manual override. BE CAREFUL USING THIS
                slidePos = MAX_SLIDE_POS;
            }

            robot.linearSlide.runSlide((int) slidePos, SLIDE_POW);

            if(gamepad2.a){
                robot.delivery.dropPixel();
            }
            else{
                robot.delivery.closeDrop();
            }




            /*

            INTAKE CODE

             */

            if(gamepad1.right_trigger>0.1){
                robot.intake.runIntake(INTAKE_SPEED);
            }
            else if(gamepad1.right_bumper){
                robot.intake.runIntake(-INTAKE_REVERSE_SPEED);
            }
            else{
                robot.intake.runIntake(0);
            }



            /*

            AIRPLANE LAUNCHER CODE

             */

            if(gamepad1.left_bumper){ //Rudimentary implementation, may need to hold down bumper until airplane is expelled
                robot.delivery.useAirplane();
            }
            //NOTE: No return servo pos, because once airplane is launched, no reason to reset servo




        }
    }
}
