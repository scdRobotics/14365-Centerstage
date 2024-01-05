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


        while(!isStopRequested()){
            telemetry.addData("Alpha Readout: ", robot.sensors.returnAlphaReadout());
            telemetry.addData("Blue Readout: ", robot.sensors.returnBlueReadout());
            telemetry.addData("Red Readout: ", robot.sensors.returnRedReadout());
            telemetry.addData("Green Readout: ", robot.sensors.returnGreenReadout());
            telemetry.addData("Purple Readout: ", robot.sensors.returnPurpleReadout());
            telemetry.update();
        }
    }

}
