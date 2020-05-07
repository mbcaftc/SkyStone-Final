package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoPaths.AutoBuildingPaths;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoBuilding;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoBuildingWood;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;

@Autonomous(name = "Blue:Building Plate:Inner")
public class AutoBlueBuildingPlate extends AutoBuilding {

    public MetalBot Bot = new MetalBot();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap, "Build");
        Bot.setLinearOp(this);
        //Bot.HookRelease();

        setLinearOp(this);



        waitForStart();

        while (opModeIsActive()) {

            Bot.HookRelease();

            goToBuildPlate(Bot, "Blue");

            orientBuildPlateBuild(Bot, "Blue");

            pushBuildPlate(Bot, "Blue");

            parkBuildingPlateInner(Bot, "Blue");

            idle();
            requestOpModeStop();
        }
        idle();

    }
}
