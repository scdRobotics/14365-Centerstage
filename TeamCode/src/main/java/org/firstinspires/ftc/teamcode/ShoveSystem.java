package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.motors.RevRoboticsCoreHexMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ShoveSystem extends Subsystem{
    private final RevRoboticsCoreHexMotor shove;
    public ShoveSystem(Telemetry telemetry, HardwareMap hardwareMap, ElapsedTime timer, RevRoboticsCoreHexMotor shove) {
        super(telemetry, hardwareMap, timer);
        this.shove=shove;
    }


}