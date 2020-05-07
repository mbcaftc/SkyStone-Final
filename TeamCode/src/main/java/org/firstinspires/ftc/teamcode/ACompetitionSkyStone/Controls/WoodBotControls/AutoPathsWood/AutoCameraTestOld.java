package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoPathsWood;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoBuildingWood;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.EOCVWebCam;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.SkystoneLocation;

@Autonomous (name = "CAMERA AUTO", group = "lab")
@Disabled

public class AutoCameraTestOld extends AutoBuildingWood {

    public EOCVWebCam Cam = new EOCVWebCam();
    public WoodBot Bot = new WoodBot();

    SkystoneLocation skyLocation;

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap, "Auto");
        Cam.initCamera(hardwareMap);
        Bot.setLinearOp(this);
        setLinearOp(this);

        waitForStart();

        telemetry.addData("Skystone ", skyLocation);
        telemetry.update();




            /*
            alignBuildPlate(Bot, "Blue");
            sleep(sleepTime);

            goToSkystones(Bot, "Blue");
            sleep(sleepTime);

            Bot.driveBackward(midSpeed, .5);

            //detectStoneDistance(Bot);

            detectSkyStone (Bot, "Blue");
            sleep(sleepTime);

            detectStoneDistance(Bot);

            manipulateStone(Bot, "grab");
            sleep(sleepTime);

            removeSkyStoneInner(Bot, "Blue");

            adjustToDropSkyStone(Bot, "Blue");

            dropStone(Bot);

            park (Bot, "Blue");


             */
            requestOpModeStop();
    }
}
