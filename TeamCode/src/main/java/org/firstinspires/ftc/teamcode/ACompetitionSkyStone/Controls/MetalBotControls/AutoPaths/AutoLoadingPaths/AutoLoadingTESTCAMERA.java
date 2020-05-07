package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoPaths.AutoLoadingPaths;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoLoading;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;

@Autonomous(name = "Loading:TEST CAMERA", group = "Lab")
@Disabled
public class AutoLoadingTESTCAMERA extends AutoLoading {

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


            Bot.led_control(RevBlinkinLedDriver.BlinkinPattern.WHITE); //turns on LEDs
            sleep(sleepTime);
            Bot.activateTracking();
            sleep (500);
//            Bot.detectSkyStone();
//            sleep(500);     // added after testing on 1/23/20

            manipulateIntake(Bot,"flip down");
            sleep(200);

            Bot.driveForward(lowSpeed, 1.1);
            Bot.detectSkyStone(); //was here - but moved to detect as soon as activates.  2/14/20.
            sleep(600);
            Bot.driveForward(lowSpeed,.1);
            sleep(200);
        Bot.driveForward(lowSpeed,.1); // in case does not detect initially.
        sleep(200);
//            Bot.deActivateTracking();
        telemetry.addData("SKYSTONE Y: ", Bot.skyStoneValue);
        telemetry.update();
//            Bot.driveForward(lowSpeed,0.3);

//            Bot.detectSkyStone(); //was here - but moved to detect as soon as activates.  2/14/20.
//            sleep(500);     // added after testing on 1/23/20
        telemetry.addData("SKYSTONE Y: ", Bot.skyStoneValue);
        telemetry.update();
            sleep(5000);


//            Bot.deActivateTracking();
            Bot.led_control(RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_PARTY_PALETTE);




            /*

            sleep(200);
// STRAFE DISTANCE & ANGLE TO GET BLOCKS - AutoMain
            driveToSkyStone(Bot, "Red");
            sleep(500);


            manipulateIntake(Bot,"inward");

// DISTANCE FROM INTAKE MOTORS ENGAGE TO GET BLOCKS.


            Bot.driveForward(midSpeed, 2.0);

            sleep(1500);
            manipulateIntake(Bot, "stop");

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

       //     idle();
//            requestOpModeStop();
//            idle();
      //  }
//        idle();


        */

    }
}
