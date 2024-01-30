package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;


import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake extends Subsystem{
    private final DcMotorEx wheels;
    public Intake(Telemetry telemetry, HardwareMap hardwareMap, ElapsedTime timer, DcMotorEx wheels) {
        super(telemetry, hardwareMap, timer);
        this.wheels = wheels;
    }

    public void runIntake(double power)
    {
        wheels.setPower(power);
    }
    //test

}