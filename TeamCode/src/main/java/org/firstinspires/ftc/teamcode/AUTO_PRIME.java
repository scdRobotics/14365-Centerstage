package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "AUTO_PRIME", group = "Autonomous")
@Disabled
public class AUTO_PRIME extends LinearOpMode {

    public Robot robot;

    /*
    Put Autonomous value constants
     */

    /*
    Put any computationally-heavy assignments or processing work that's SHARED ACROSS ALL AUTO PROGRAMS here
     */
    void initAuto(){
        ElapsedTime timer = new ElapsedTime();
        this.robot = new Robot(this, hardwareMap, telemetry, timer, true);
    }

    @Override
    public void runOpMode() throws InterruptedException{

    }





}