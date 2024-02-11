package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Delivery extends Subsystem{

    private final Servo drop;
    private final Servo airplane;

    //Placeholders; find real values with LM_Drop_Pos_TeleOp
    private final double DROP_POS = 1;
    private final double CLOSE_POS = 0;

    public Delivery(Telemetry telemetry, HardwareMap hardwareMap, ElapsedTime timer, Servo drop, Servo airplane) {
        super(telemetry, hardwareMap, timer);
        this.drop = drop;
        this.airplane = airplane;
    }

    public void runDrop(double pos){
        drop.setPosition(pos);
    }

    public void dropPixel() {
        drop.setPosition(DROP_POS);
    }

    public void closeDrop() {
        drop.setPosition(CLOSE_POS);
    }

    public void useAirplane(){
        airplane.setPosition(-1); //Will require proper tuning/placement of servo
    }



}