package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name="AUTO_BLUE_LEFT", group="Autonomous")
public class AUTO_BLUE_LEFT extends AUTO_PRIME {

    @Override
    public void runOpMode() throws InterruptedException{
        initAuto();
        /*
        Program-specific setup processes here
         */

        Pose2d startPose = new Pose2d(START_X, START_Y, Math.toRadians(START_ANG));
        robot.telemetry("Ready for start!", "");
        TrajectorySequence move = robot.drive.trajectorySequenceBuilder(startPose)
                .lineTo(new Vector2d(40, 40))
                        .build();

        waitForStart();
        /*
        Operational Program! :D
         */


        robot.drive.followTrajectorySequence(move);
        //robot.pause(TIME_WAIT);

    }

}
