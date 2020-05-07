package org.firstinspires.ftc.teamcode.MrDuVal;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;

@TeleOp (name = "Test WebCam Only + LED Servo ('led')!!!", group = "Lab")
//@Disabled
public class WebCamOnly extends OpMode {
    public VuforiaWebcam Cam = new VuforiaWebcam();

    Servo led;
    double ledInc = .001;
    double ledMin = .2525;
    double ledMax = .7475;
    double ledValue = ledMin;

    boolean camTracking;

    public RevBlinkinLedDriver blinkinLedDriver;
    public RevBlinkinLedDriver.BlinkinPattern pattern;

    public ElapsedTime myTime = new ElapsedTime();


    @Override
    public void init() {
        Cam.initCamera(hardwareMap);
        Cam.activateTracking();
        camTracking = true;
//        led = hardwareMap.servo.get("led");
//        led.setPosition(ledValue);

        //Define & Initialize LEDTester Lights
        blinkinLedDriver = hardwareMap.get(RevBlinkinLedDriver.class, "led");
        pattern = RevBlinkinLedDriver.BlinkinPattern.WHITE;
        blinkinLedDriver.setPattern(pattern);
    }

    @Override
    public void start() {
        myTime.reset();
    }

    @Override
    public void loop() {
//        ledLoop();
//        pattern = RevBlinkinLedDriver.BlinkinPattern.WHITE;
//        blinkinLedDriver.setPattern(pattern);
        if (gamepad1.dpad_down && camTracking == true) {
            Cam.deActivateTracking();
            camTracking = false;
        }
        if (gamepad1.dpad_up && camTracking == false) {
            Cam.activateTracking();
            camTracking = true;
        }
        if (camTracking == true) {
            Cam.trackObjects();
        }
        if (gamepad1.dpad_right) {
            Vuforia.deinit();
        }

        if (gamepad1.dpad_left) {
            Vuforia.init();
        }

        if(gamepad1.y) {
            pattern = RevBlinkinLedDriver.BlinkinPattern.WHITE;
            blinkinLedDriver.setPattern(pattern);
        }
        if (gamepad1.b) {
            pattern = RevBlinkinLedDriver.BlinkinPattern.BLACK;
            blinkinLedDriver.setPattern(pattern);
        }
        telemetryOutput();
    }

    private void telemetryOutput () {
        telemetry.addLine("dpad: Up = Vuf off, Down = Vuf on, R = VufDEinit, L = VufInit");
        telemetry.addData("Current Time: ", myTime.time());
        telemetry.addData("Camera Visible Target", Cam.targetName);
        telemetry.addData("Camera Pos (in)", "{X, Y, Z} = %.1f, %.1f, %.1f", Cam.targetX, Cam.targetY, Cam.targetZ);
        telemetry.addData("Camera Rot (deg)", "{Roll, Pitch, Heading} = %.0f, %.0f, %.0f", Cam.targetRoll, Cam.targetPitch, Cam.targetHeading);
//        telemetry.addData("ledValue: ", ledValue);
//        telemetry.addData("LED Servo Pos: ", led.getPosition());
//        telemetry.addData("ledInc", ledInc);
    }
    public void ledLoop () {

        if (gamepad1.a){
            ledValue = .3;
        }
        if (gamepad1.x) {
            ledValue = .4;
        }
        if (gamepad1.b) {
            ledValue = .6;
        }
        if (gamepad1.y) {
            ledValue = .7;
        }
        if (gamepad1.right_bumper) {
            ledValue += ledInc;
        }
        if (gamepad1.left_bumper) {
            ledValue -= ledInc;
        }


        /*
        if (ledValue >= .7475) {
            ledInc *= -1;
        }
        if (ledValue <= .2525) {
            ledInc *= -1;
        }*/



//        ledValue += ledInc;
        ledValue = Range.clip(ledValue, ledMin, ledMax);
        led.setPosition(ledValue);
    }

}
