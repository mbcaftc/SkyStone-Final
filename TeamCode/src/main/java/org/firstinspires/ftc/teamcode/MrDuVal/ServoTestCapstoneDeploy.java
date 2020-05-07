package org.firstinspires.ftc.teamcode.MrDuVal;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp (name = "Servo Test: Capstone Deploy", group = "lab")
public class ServoTestCapstoneDeploy extends OpMode {

    Servo testServo;

    double servoPos = .5;

    double incVal = 0.001;




    @Override
    public void init() {
        testServo = hardwareMap.servo.get("capstone_deploy");
        testServo.setPosition(servoPos);
    }

    @Override
    public void loop() {
        if (gamepad1.right_bumper) {
            servoPos +=incVal;
            servoPos = Range.clip (servoPos,0,1);
//            StoneGrabberHinge.setPosition(hingePos);
            telemetry.addLine("Increase Servo!");
        }
        if (gamepad1.left_bumper) {
            servoPos -= incVal;
            servoPos = Range.clip (servoPos,0,1);
//            StoneGrabberHinge.setPosition(hingePos);
            telemetry.addLine("Decrease Servo!");
        }

        if (gamepad1.x) {
            servoPos = .68;  // CHILL
            telemetry.addLine("Set Servo to .9!");
        }
        if (gamepad1.y) {
            servoPos = .3669;  // DEPLPY
            telemetry.addLine("Set Servo to .1!");

        }
        testServo.setPosition(servoPos);
        telemetry.addLine("RB: increase, LB: Decrease");
        telemetry.addLine("x = DEPLPOY, y = UP");
        telemetry.addData("TestS ervo Positiom: ", testServo.getPosition());
        telemetry.addData("Servo Variable Position: ", servoPos);
        telemetry.update();
    }
}


//hook right OPEN - 0.1
// hook right GRAB = .557