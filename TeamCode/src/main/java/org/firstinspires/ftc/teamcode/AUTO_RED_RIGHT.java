package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name="AUTO_RED_RIGHT", group="Autonomous")
public class AUTO_RED_RIGHT extends AUTO_PRIME {

    @Override
    public void runOpMode() throws InterruptedException{
        initAuto();
        /*
        Program-specific setup processes here
         */

        Pose2d turnEST = new Pose2d(START_X + 5.7, START_Y, START_ANG);
        Pose2d startPose = new Pose2d(START_X, START_Y, Math.toRadians(START_ANG));
        robot.telemetry("Ready for start!", "");
        TrajectorySequence move = robot.drive.trajectorySequenceBuilder(startPose)
//                .lineTo(new Vector2d(START_X, 10))
//                .lineTo(new Vector2d(10, 10))
                .forward(5.8)
                .build();

        waitForStart();
        /*
        Operational Program! :D
         */

        robot.drive.followTrajectorySequence(move);
        robot.pause(1);
        telemetry.addData("Auto", robot.dropAuto.getPosition());
        telemetry.update();
        robot.delivery.openDropAuto(0);
        robot.pause(5);
        //robot.pause(TIME_WAIT);

    }

}
