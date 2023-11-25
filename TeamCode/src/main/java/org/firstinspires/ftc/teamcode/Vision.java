package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Vision extends Subsystem{
    public Vision(Telemetry telemetry, HardwareMap hardwareMap, ElapsedTime timer) {
        super(telemetry, hardwareMap, timer);
    }
}
