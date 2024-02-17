package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="AUTO_RED_LEFT", group="Autonomous")
public class AUTO_RED_LEFT extends AUTO_PRIME {

    @Override
    public void runOpMode(){
        initAuto();
        /*
        Program-specific setup processes here
         */
        robot.telemetry("Ready for start!", "");
        waitForStart();
        /*
        Operational Program! :D
         */

        robot.pause(TIME_WAIT);

        encoderForward(0.3, 20);
        encoderTurnRight(0.3, 20);
        encoderForward(0.3, 200);
    }

}
