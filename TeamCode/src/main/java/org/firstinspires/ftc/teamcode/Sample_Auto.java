package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Sample_Auto", group="Autonomous")
public class Sample_Auto extends AUTO_PRIME {

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
        robot.telemetry("Program running!", "");
        robot.pause(5);
    }

}
