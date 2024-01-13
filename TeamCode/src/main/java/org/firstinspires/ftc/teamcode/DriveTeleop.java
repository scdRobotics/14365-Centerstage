package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "DeliveryTeleOp", group = "TeleOp")
public class DriveTeleop extends LinearOpMode{
    @Override
    public void runOpMode() {

        ElapsedTime timer = new ElapsedTime();
        Robot robot = new Robot(this, hardwareMap, telemetry, timer, true);
        double slow = 1;

        waitForStart();

        while(!isStopRequested()) {
            double y = -gamepad1.left_stick_y; // Remember, this is reversed!
            double x = gamepad1.left_stick_x; // Counteract imperfect strafing
            //was x = gamepad1.left_stick_x * 1.1
            double rx = gamepad1.right_stick_x;
            if(gamepad1.left_bumper && (rx>0.1 || rx<-0.1)){
                slow=3;
            }
            else if(gamepad1.left_bumper && (y>0.1 || x>0.1 || y<-0.1 || x<-0.1)){
                slow=2;
            }
            else{
                slow=1;
            }

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            if(y<0.075 || x<0.0825 || rx<0.075){ //Account for potential joystick drift
                robot.frontLeftM.setPower(frontLeftPower/slow);
                robot.backLeftM.setPower(backLeftPower/slow);
                robot.frontRightM.setPower(frontRightPower/slow);
                robot.backRightM.setPower(backRightPower/slow);
            }
            else{
                robot.frontLeftM.setPower(0);
                robot.frontRightM.setPower(0);
                robot.backLeftM.setPower(0);
                robot.backRightM.setPower(0);
            }
            if(gamepad1.a) {

            }

        }

        }
    }
