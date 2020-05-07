package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoPaths.AutoLoadingPaths;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoLoading;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoLoadingWood;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;

@Autonomous(name = "Testing Timeout Issues", group = "Lab")
@Disabled
public class AutoTestTimeout extends AutoLoading {

    public MetalBot Bot = new MetalBot();

//    public myTime = ElapsedTime time();
    public ElapsedTime runTime;

    @Override
    public void runOpMode() throws InterruptedException {

        runTime = new ElapsedTime();
        runTime.reset();

        Bot.initRobot(hardwareMap, "Auto");
        Bot.setLinearOp(this);

        setLinearOp(this);

        waitForStart();

        while (opModeIsActive()) {

            runTime.reset();



            while (runTime.time() <= 35 && opModeIsActive()) {
                telemetry.addData("time: ", runTime.time());
                telemetry.update();
            }

            Bot.activateTracking();

//            manipulateIntake(Bot,"flip down");
//            sleep(200);
//            Bot.intakePushNeutral();

//            Bot.driveForward(lowSpeed, 1.8);

//            Bot.detectSkyStone();
//            sleep(500);     // added after testing on 1/23/20

//            Bot.deActivateTracking();

//            driveToSkyStone(Bot, "Red");


//            manipulateIntake(Bot,"inward");


//            Bot.driveForward(midSpeed, 1.4);

//            sleep(1000);
//            manipulateIntake(Bot, "stop");

//            Bot.intakePushIn();
//            sleep(100);


//            removeSkyStoneInner(Bot);
//            sleep(sleepTime);


//            manipulateIntake(Bot, "flip_up");

//            rotateToDriveDropStone(Bot, "Red");
//            sleep(sleepTime);

//            driveToPlate("Red", Bot);
//            sleep(sleepTime);

//            Bot.intakePushIn();
//            sleep(100);

//            Bot.HookRelease();

//            dropSkyStone(Bot, "Red");
//            sleep(sleepTime);

//            alignGrabPlate(Bot, "Red");
//            sleep(sleepTime);

//            orientBuildPlate(Bot, "Red");
//            sleep(sleepTime);

//            parkInner(Bot, "Red");
//            sleep(sleepTime);

            sleep(1000);
//hello
            idle();
            requestOpModeStop();
            idle();
        }
        idle();

    }
}
