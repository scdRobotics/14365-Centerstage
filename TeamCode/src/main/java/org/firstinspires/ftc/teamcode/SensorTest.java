package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import java.util.ArrayDeque;
import java.util.Stack;

@Autonomous(name="SensorTest", group="Autonomous")
public class SensorTest extends AUTO_PRIME {

    ArrayDeque<Integer> pixelColorsQueue = new ArrayDeque<Integer>();
    Stack<Integer> pixelColorsStack = new Stack<Integer>();

    final int WHITE = 1;
    final int YELLOW = 2;
    final int GREEN = 3;
    final int PURPLE = 4;

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

        /*
         * Color sensor order logic
         *
         * NOTE: STACK OR QUEUE DEPENDS ON ORDER OF INTAKE/OUTTAKE.
         * SEE BELOW COMMENTS FOR HELP FIGURING OUT WHICH TO USE.
         *
         * CURRENTLY, SEEMS LIKE IT WILL USE A STACK :DDDD
         */

        /*
         * Example Stack Logic
         * 
         * pixelColorsStack.add(YELLOW)
         * 
         *      pixelColorsStack = 2
         * 
         * pixelColorsStack.add(WHITE)
         * 
         *      pixelColorsStack = 2 1
         *
         * pixelColorsStack.remove()
         * 
         *      returns 1
         * 
         *      pixelColorsStack = 2
         * 
         * pixelColorsStack.add(PURPLE)
         * 
         *      pixelColorsStack = 2 4
         * 
         * pixelColorsStack.remove()
         * 
         *      returns 4
         * 
         *      pixelColorsStack = 2
         * 
         * pixelColorsStack.remove()
         *      
         *      returns 2
         * 
         *      pixelColorsStack is EMPTY
         *
         */

        /*
         * Example Queue Logic
         *
         * pixelColorsQueue.add(YELLOW)
         *
         *      pixelColorsQueue = 2
         *
         * pixelColorsQueue.add(WHITE)
         *
         *      pixelColorsQueue = 2 1
         *
         * pixelColorsQueue.remove()
         *
         *      returns 2
         *
         *      pixelColorsQueue = 1
         *
         * pixelColorsQueue.add(PURPLE)
         *
         *      pixelColorsQueue = 1 4
         *
         * pixelColorsQueue.remove()
         *
         *      returns 1
         *
         *      pixelColorsQueue = 4
         *
         * pixelColorsQueue.remove()
         *
         *      returns 4
         *
         *      pixelColorsQueue is EMPTY
         */

        robot.telemetry("Program running!", "");
        robot.pause(5);
    }

}
