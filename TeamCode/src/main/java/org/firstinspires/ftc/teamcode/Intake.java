package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;


import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake extends Subsystem{
    private final DcMotorEx wheels;
    private final Servo elevator;
    public Intake(Telemetry telemetry, HardwareMap hardwareMap, ElapsedTime timer, DcMotorEx wheels, Servo elevator) {
        super(telemetry, hardwareMap, timer);
        this.wheels = wheels;
        this.elevator = elevator;
    }

    public void runIntake(double power)
    {
        wheels.setPower(power);
    }

}