package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;


public abstract class AutoBuilding extends AutoMain {


    public void goToBuildPlate (MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.strafeLeft(midSpeed, 2);
            Bot.driveBackward(midSpeed, 2.8);
            Bot.driveBackward(lowSpeed,.2);

        }
        else if (Alliance == "Blue") {
            Bot.strafeRight(midSpeed, 1.7);
            Bot.driveBackward(lowSpeed, 2.975);
        }

    }

    public void orientBuildPlateBuild (MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.HookGrab();
            sleep(1000);
            Bot.driveForward(midSpeed,2);
            Bot.strafeRight(midSpeed, 2);
            Bot.rotateRight(midSpeed, .5);
          //  Bot.gyroCorrection(.3, -30);
            Bot.strafeRight(.4, 1.5);
            Bot.rotateRight(midSpeed, 1);
            Bot.gyroCorrection(gyroSPD, -90);
            Bot.strafeLeft(lowSpeed, 2);
            Bot.rotateRight(midSpeed,.5);

        }
        else if (Alliance == "Blue") {
            Bot.HookGrab();
            sleep(1000);
            Bot.driveForward(midSpeed, 2);
            Bot.rotateLeft(lowSpeed, .5);
            Bot.driveForward(midSpeed,.7);
            Bot.rotateLeft(lowSpeed,.8);
            Bot.HookRelease();
            Bot.strafeLeft(midSpeed,1.5);


        //    Bot.gyroCorrection(.3, 30);
           // Bot.strafeRight(.4, 1.5);
           // Bot.rotateLeft(midSpeed, .5);
           // Bot.gyroCorrection(gyroSPD, 90);

        }
    }

    public void pushBuildPlate (MetalBot Bot, String Alliance) {
        Bot.driveBackward(midSpeed, 3.3);
        Bot.HookRelease();
        sleep(100);
    }

    public void parkBuildingPlateInner (MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {

            Bot.gyroCorrection(gyroSPD, -90);
            Bot.strafeRight(midSpeed,.8);
            Bot.driveForward(midSpeed,3.8);

            //gyro correct, drive, strafe?



//            Bot.strafeRight(lowSpeed, .4);
//            Bot.rotateRight(midSpeed, 2);
//            Bot.gyroCorrection(gyroSPD, -179);      // was 179
//            Bot.rotateLeft(midSpeed, .1);
//            Bot.driveForward(midSpeed, 3.6);

//            Bot.strafeRight(lowSpeed, .8);
//            Bot.driveForward(midSpeed, 1);
//            Bot.strafeRight(lowSpeed, .8);

        }

        else if (Alliance == "Blue") {
            Bot.gyroCorrection(gyroSPD,90);
            Bot.driveForward(midSpeed, 3.8);
            Bot.strafeLeft(lowSpeed,.6);

        }
    }

    public void parkBuildingPlateOuter(MetalBot Bot, String Alliance) {

        if (Alliance == "Red") {
            Bot.strafeLeft(lowSpeed, 2);
            Bot.gyroCorrection(gyroSPD,-90);
            Bot.driveForward(midSpeed, 3.5);
        }
        else if (Alliance == "Blue") {
            Bot.strafeRight(midSpeed,2);
            Bot.gyroCorrection(gyroSPD,90);
            Bot.driveForward(midSpeed,3.4);
            Bot.strafeRight(lowSpeed,1);
        }

    }






}
