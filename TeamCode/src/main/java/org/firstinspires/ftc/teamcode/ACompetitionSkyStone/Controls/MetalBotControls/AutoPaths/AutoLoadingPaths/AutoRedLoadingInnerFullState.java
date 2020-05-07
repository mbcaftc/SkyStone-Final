package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoPaths.AutoLoadingPaths;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoLoading;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;

@Autonomous(name = "Red:Loading:Inner:Full:STATE")
//@Disabled
public class AutoRedLoadingInnerFullState extends AutoLoading {

    public MetalBot Bot = new MetalBot();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap, "Auto");
        Bot.setLinearOp(this);

        setLinearOp(this);

        waitForStart();

     //   while (opModeIsActive()) {

            Bot.capStoneChill();
            Bot.HookRelease();
            Bot.intakePushNeutral();
            Bot.clawGrabberRelease();
            Bot.clawExtenderRetract2();


            Bot.led_control(RevBlinkinLedDriver.BlinkinPattern.WHITE); //turns on LEDs
            sleep(sleepTime);
         //   Bot.activateTracking();
            sleep (500);
//            Bot.detectSkyStone();
//            sleep(500);     // added after testing on 1/23/20

            manipulateIntake(Bot,"flip down");
            sleep(200);
        Bot.intakePushNeutral();


        Bot.driveForward(lowSpeed, 1.0);
           // Bot.detectSkyStone(); //was here - but moved to detect as soon as activates.  2/14/20.
            sleep(600);
        Bot.driveForward(lowSpeed,.1); // in case does not detect initially.
        sleep(200);
        Bot.driveForward(lowSpeed,.1); // in case does not detect initially.
        sleep(200);
      //  Bot.deActivateTracking();
        //telemetry.addData("TARGET Y: ", Bot.skyStoneValue);
       // telemetry.update();
//            Bot.driveForward(lowSpeed,0.3);

//            Bot.detectSkyStone(); //was here - but moved to detect as soon as activates.  2/14/20.
//            sleep(500);     // added after testing on 1/23/20

//            Bot.deActivateTracking();
            Bot.led_control(RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_PARTY_PALETTE);
            sleep(200);
// STRAFE DISTANCE & ANGLE TO GET BLOCKS - AutoMain
            driveToSkyStone(Bot, "Red");
     //   telemetry.addData("TARGET Y: ", Bot.skyStoneValue);
      //  telemetry.addData("skyStonePos: ", skyStonePosition);
      //  telemetry.update();
            sleep(200);


            manipulateIntake(Bot,"inward");

            //partially extend extender
            Bot.clawExtenderAutoIntake2();
            Bot.stackingArmDown();
            sleep(150);
            Bot.stackingArmOff();

// DISTANCE FROM INTAKE MOTORS ENGAGE TO GET BLOCKS.


            Bot.driveForward(lowSpeed, 2.3);

            sleep(800);
        Bot.intakeSpinInwardAutoFast();
        sleep(500);
            manipulateIntake(Bot, "stop");
            Bot.stackingArmUp();
            // Retract extender back into position.
            Bot.clawExtenderRetract2();
            sleep(200);
            Bot.stackingArmOff();
            Bot.intakePushIn();
            sleep(100);

// DISTANCE FROM QUARRY TO DRIVE BACK! - Aut Main
            removeSkyStoneInner(Bot);
            sleep(sleepTime);


            manipulateIntake(Bot, "flip_up");

// ANGLE FOR DRIVING TO PLATE - Auto loading
            rotateToDriveDropStone(Bot, "Red");
            sleep(sleepTime);
//Distance once angled correctly - from quarry to the build plate - AutoMain
            driveToPlate("Red", Bot);
            sleep(sleepTime);

            Bot.intakePushIn();
            sleep(100);

            dropSkyStone(Bot, "Red");
            sleep(sleepTime);

//            dropSkyStone2(Bot, "Red");
//            sleep(sleepTime);

           // alignGrabPlate(Bot, "Red");
           // sleep(sleepTime);


            orientBuildPlate(Bot, "Red");
            sleep(sleepTime);

            parkInner(Bot, "Red");
       //     sleep(sleepTime);

        idle();
        telemetry.addLine("BEFORE OPMODE STOP!!!");
        telemetry.update();
        requestOpModeStop();
        requestOpModeStop();
        requestOpModeStop();
        requestOpModeStop();
        sleep(sleepTime);
        telemetry.addLine("AFTER OPMODE STOP!!!");
        telemetry.update();

       //     idle();
//            requestOpModeStop();
//            idle();
      //  }
//        idle();
    }
}
