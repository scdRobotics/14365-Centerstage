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
    private final DcMotor shove;
    public ShoveSystem(Telemetry telemetry, HardwareMap hardwareMap, ElapsedTime timer, DcMotor shove) {
        super(telemetry, hardwareMap, timer);
        this.shove=shove;
    }

    public void runShove(int pos, double power){
        shove.setTargetPosition(pos);
        shove.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        shove.setPower(power);
    }

    public void resetEncoders(){
        shove.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }



}