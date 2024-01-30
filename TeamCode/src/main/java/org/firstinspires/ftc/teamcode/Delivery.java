package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Delivery extends Subsystem{

    private final Servo drop;
    public Delivery(Telemetry telemetry, HardwareMap hardwareMap, ElapsedTime timer, Servo drop) {
        super(telemetry, hardwareMap, timer);
        this.drop = drop;
    }

    public void runDrop(double pos){
        drop.setPosition(pos);
    }

    public void dropPixel() {
        drop.setPosition(1);
    }

    public void closeDrop() {
        drop.setPosition(0);
    }

}