package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoPaths.AutoBuildingPaths;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoBuilding;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoBuildingWood;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;

@Autonomous(name = "Red:Building Plate:Inner")
public class AutoRedBuildingPlate extends AutoBuilding {

    public MetalBot Bot = new MetalBot();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap, "Build");
        Bot.setLinearOp(this);
        Bot.HookRelease();
        //Bot.autoRaiseStone();
        //Bot.grabStone();
        //Bot.setServos();

        setLinearOp(this);



        waitForStart();

        while (opModeIsActive()) {

            goToBuildPlate(Bot, "Red");

            orientBuildPlateBuild(Bot, "Red");

            pushBuildPlate(Bot, "Red");

            parkBuildingPlateInner(Bot, "Red");


            idle();
            requestOpModeStop();
        }
        idle();

    }
}
