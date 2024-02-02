package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="LM_Drop_Pos_TeleOp", group="TeleOp")
public class LM_Drop_Pos_TeleOp extends LinearOpMode {

    @Override
    public void runOpMode(){
        ElapsedTime timer = new ElapsedTime();
        Robot robot = new Robot(this,hardwareMap,telemetry,timer,true);

        waitForStart();

        while(!isStopRequested()){

            /*

            DROP CODE

             */

            if(!gamepad2.a){ //Stop movement if something is going very wrong
                for(int i = 0; i<=1; i+=0.05){
                    telemetry.addData("Current Pos: ", i);
                    telemetry.update();
                    robot.pause(0.25);
                }
            }

        }
    }
}
