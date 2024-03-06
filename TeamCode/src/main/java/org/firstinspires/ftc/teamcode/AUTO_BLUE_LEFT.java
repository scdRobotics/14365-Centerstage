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

        Pose2d turnEST = new Pose2d(START_X + 5.7, START_Y, START_ANG);
        Pose2d startPose = new Pose2d(START_X, START_Y, Math.toRadians(START_ANG));
        Pose2d move2EST = new Pose2d(turnEST.getX(), turnEST.getY(), Math.toRadians(22.5));
        robot.telemetry("Ready for start!", "");
        TrajectorySequence move = robot.drive.trajectorySequenceBuilder(startPose)
//                .lineTo(new Vector2d(START_X, 10))
//                .lineTo(new Vector2d(10, 10))
                .forward(5.7)
                        .build();

        TrajectorySequence turn = robot.drive.trajectorySequenceBuilder(turnEST)
                .turn(Math.toRadians(22.5))
//                .forward(4)
                        .build();

        TrajectorySequence move2 = robot.drive.trajectorySequenceBuilder(move2EST)
                        .forward(6)
                                .build();
        waitForStart();
        /*
        Operational Program! :D
         */

        robot.drive.followTrajectorySequence(move);
        robot.delivery.openDropAuto();
        robot.drive.followTrajectorySequence(turn);
        robot.pause(0.3);
        robot.drive.followTrajectorySequence(move2);
        robot.telemetry("BBBBB", "");
        robot.linearSlide.runSlide(1000, 0.4);
        robot.telemetry("AAAAAA", "");
//        robot.linearSlide.runSlide(0.7, );
        //robot.pause(TIME_WAIT);

    }

}
