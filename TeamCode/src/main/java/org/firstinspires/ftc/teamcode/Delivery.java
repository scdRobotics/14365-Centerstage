package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Delivery extends Subsystem{

    private final Servo drop1;
    private final Servo drop2;
    private final Servo airplane;
    private final Servo dropAuto;
    private final CRServo hang;
    private final CRServo hang2;
    //Placeholders; find real values with LM_Drop_Pos_TeleOp
    private final double DROP1_POS = 1;
    private final double CLOSE1_POS = 0;

    private final double DROP2_POS = 0;
    private final double CLOSE2_POS = 1;

    public Delivery(Telemetry telemetry, HardwareMap hardwareMap, ElapsedTime timer, Servo drop1, Servo drop2, Servo dropAuto, Servo airplane, CRServo hang, CRServo hang2) {
        super(telemetry, hardwareMap, timer);
        this.drop1 = drop1;
        this.drop2 = drop2;
        this.dropAuto = dropAuto;
        this.airplane = airplane;
        this.hang = hang;
        this.hang2 = hang2;
    }

    public void runDrop(double pos){
        drop1.setPosition(pos);
        drop2.setPosition(pos);
    }

    public void dropPixel() {
        drop1.setPosition(DROP1_POS);
        drop2.setPosition(DROP2_POS);
    }

    public void closeDrop() {
        drop1.setPosition(CLOSE1_POS);
        drop2.setPosition(CLOSE2_POS);
    }

    public void useAirplane(){
        airplane.setPosition(0); //Will require proper tuning/placement of servo

    }

    public void useHang(int power) {
        hang.setPower(power);
        hang2.setPower(power);
    }

    public void openDropAuto(double pos) {
        dropAuto.setPosition(pos);
    }
    public void closeDropAuto() {
        dropAuto.setPosition(0);
    }

    public void resetPlane() {
        airplane.setPosition(1);
    }



}