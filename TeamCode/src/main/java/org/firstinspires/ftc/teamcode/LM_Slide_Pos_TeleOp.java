package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="LM_Slide_Pos_TeleOp", group="TeleOp")
public class LM_Slide_Pos_TeleOp extends LinearOpMode {

    final double SLIDE_POW = 0.25;

    //final int MAX_SLIDE_POS = 1000; //Placeholder; find real value with LM_Slide_Pos_TeleOp

    @Override
    public void runOpMode(){
        ElapsedTime timer = new ElapsedTime();
        Robot robot = new Robot(this,hardwareMap,telemetry,timer,true);

        double slow = 1;
        double slidePos = 0;

        robot.linearSlide.resetEncoders();

        waitForStart();

        while(!isStopRequested()){

            /*

            SLIDE CODE

             */

            if(gamepad2.left_stick_y>0.1 || gamepad2.left_stick_y<-0.1) {
                slidePos += -gamepad2.left_stick_y * 10;
                slidePos = (int) slidePos;
            }

            if(gamepad2.y) { //Reset button
                slidePos = 0;
                robot.linearSlide.resetEncoders();
            }

            robot.linearSlide.runSlide((int) slidePos, SLIDE_POW);

            telemetry.addData("Curr Slide Pos: ", slidePos);
            telemetry.update();

        }
    }
}
