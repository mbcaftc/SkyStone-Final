package org.firstinspires.ftc.teamcode.MrDuVal;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp (name = "Servo Test: Tape Measure", group = "lab")
public class ServoTestTapeMeasure extends OpMode {

    CRServo testServo;

//    double servoPos = .5;

    double incVal = 0.001;




    @Override
    public void init() {
        testServo = hardwareMap.crservo.get("tape_measure");
//        testServo.setPosition(servoPos);
    }

    @Override
    public void loop() {
        testServo.setPower(gamepad1.left_stick_y);


//        testServo.setPosition(servoPos);
        telemetry.addLine("GP1.Left Stick Y to control servo.");
        telemetry.addLine("x = set to .90, y = set to 0.10");
        telemetry.addData("TestS ervo Positiom: ", testServo.getPower());
//        telemetry.addData("Servo Variable Position: ", servoPos);
        telemetry.update();
    }
}


//hook right OPEN - 0.1
// hook right GRAB = .557