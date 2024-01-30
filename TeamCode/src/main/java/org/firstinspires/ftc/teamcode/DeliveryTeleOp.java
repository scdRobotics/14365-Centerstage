package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;



@TeleOp(name = "DeliveryTeleOp", group = "TeleOp")
public class DeliveryTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() {

        ElapsedTime timer = new ElapsedTime();
        Robot robot = new Robot(this, hardwareMap, telemetry, timer, true);

        boolean bButtonHeld = false;

        waitForStart();

        while (!isStopRequested()) {

            if (!gamepad2.b) {
                bButtonHeld = false;
            } else if (gamepad2.b && !bButtonHeld) {
                bButtonHeld = true;
            }

            if (gamepad2.a) {
                robot.delivery.runDrop(1);
            } else {
                robot.delivery.runDrop(0);
            }

            if (bButtonHeld) {
                robot.intake.runIntake(1);
            } else {
                robot.intake.runIntake(0);
            }
        }
    }

}