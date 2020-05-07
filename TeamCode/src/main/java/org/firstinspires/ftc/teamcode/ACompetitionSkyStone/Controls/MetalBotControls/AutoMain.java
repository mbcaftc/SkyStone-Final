package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.SkystoneLocation;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;
import org.openftc.easyopencv.OpenCvWebcam;
import org.openftc.easyopencv.OpenCvPipeline;
import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;



public abstract class AutoMain extends LinearOpMode {

    // Variables & Constants used for MetalBot across All Autonomous Paths

    public final long sleepTime = 15;
    public final double maxSpeed = 1;
    public final double highSpeed = .7;
    public final double midSpeed = .65;
    public final double lowSpeed = .4;
    public LinearOpMode linearOp = null;
    public final double gyroSPD = .25;
    public final double gyroSPDdouble = .20;

    // Color Sensor Variables & Constants used across All Autonomous Paths

    public final int colorImage = 20;               // was 15 ... color of image is 18, 27 in MBCA BNI room  15 comp = 20
    public final int colorYellow = 40;              //40 in MBCA BNI room b  30
    public final int colorNoBackground = 35;          //is 35 ... was 60 in MBCA BNI room
    public double tracker = 0;

    // Elapsed Time and Sleep Time Variables and Constants used across all Autonomous Paths

    public double timeThreshold = 0;
    public ElapsedTime skyStoneTime = new ElapsedTime();

    // SkyStone Positioning & Dynamic Encoder Drivng Variables & Constants used across All Autonomous Paths

    public org.firstinspires.ftc.teamcode.EasyOpenCV.SkystoneLocation location = org.firstinspires.ftc.teamcode.EasyOpenCV.SkystoneLocation.right;
    OpenCvWebcam webCam = null;
    WebcamName webcamName = null;

    SkystoneLocation skyLocation;

    public int skyStonePosition = 1;
    public int encoderPosition1 = 4400 + 400;
    public int encoderPosition2 = 4500;
    public int encoderPosition3 = 4400 - 300;

    public int encoderPosition1Blue = 1100; //1100, now 1300
    public int encoderPosition2Blue = 1400; //1400, now 1600
    public int encoderPosition3Blue = 1700; //1550, now 1750


    // Vuforia Variables & Constants used across All Autonoumous Paths

    public static final float mmPerInch = 25.4f;
    public static final float mmTargetHeight = (6) * mmPerInch;          // the height of the center of the target image above the floor
    public static final float stoneZ = 2.00f * mmPerInch;
    public boolean targetVisible = false;
    public float phoneXRotate = 0;
    public float phoneYRotate = 0;
    public float phoneZRotate = 0;
    public String targetName = null;
    public double targetX;
    public double targetY;
    public double targetZ;
    public double targetRoll;
    public double targetPitch;
    public double targetHeading;


    public void setLinearOp(LinearOpMode Op) {

        linearOp = Op;
    }


    // Methods used for both Building and Loading on the Field

    public void manipulateIntake(MetalBot Bot, String position) {
        if (position == "flip down") {
            Bot.intakeDeployLower();
        } else if (position == "flip_up") {
            Bot.intakeDeployRaise();
        } else if (position == "inward") {
            Bot.intakeSpinInwardAutoSlow();
        } else if (position == "outward") {
            Bot.intakeSpinOutward();
        } else if (position == "stop") {
            Bot.intakeSpinOff();
        }
    }


    public void removeSkyStoneOuter(MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {


        } else if (Alliance == "Blue") {


        }
    }

    public void removeSkyStoneInner(MetalBot Bot) {
        Bot.driveBackward(lowSpeed, 1.4);     //was working at 1.8, after watching video changed spped from .2 - .5 (Boone 11:36 jan 20)
    }


    public void adjustToDropSkyStone(MetalBot Bot, String Alliance) {

        if (Alliance == "Red") {
            Bot.gyroCorrection(.2, 0);

        } else if (Alliance == "Blue") {
            Bot.gyroCorrection(.2, 0);
        }

    }

    public void driveToPlate(String Alliance, MetalBot Bot) throws InterruptedException { //modified by Boone for dynamic drive 11:30 jan 20
        if (Alliance == "Red") {
            if (skyStonePosition == 1) {
                Bot.driveGyroStraight(encoderPosition1, .7, "backward");
            } else if (skyStonePosition == 2) {
                Bot.driveGyroStraight(encoderPosition2, .7, "backward");
            } else if (skyStonePosition == 3) {
                Bot.driveGyroStraight(encoderPosition3, .7, "backward");
            }
        } else if (Alliance == "Blue") {
            if (skyStonePosition == 1) {
                Bot.driveGyroStraight(encoderPosition1Blue, .7, "backward");
            } else if (skyStonePosition == 2) {
                Bot.driveGyroStraight(encoderPosition2Blue, .7, "backward");
            } else if (skyStonePosition == 3) {
                Bot.driveGyroStraight(encoderPosition3Blue, .7, "backward");
            }
        }
    }


    public void goToFirstLocation(MetalBot Bot, String Alliance) {
        Bot.frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Bot.frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        if (Alliance == "Red") {
          //  Bot.detectSkyStone();
        } else if (Alliance == "Blue") {
            // gyro correction
        }
        while (Math.abs(Bot.frontLeftMotor.getCurrentPosition()) < tracker && linearOp.opModeIsActive()) {
            linearOp.telemetry.addData("Current Left Motor Position: ", Math.abs(Bot.frontLeftMotor.getCurrentPosition()));
            linearOp.telemetry.addData("Target Left Motor Position: ", tracker);
            linearOp.telemetry.update();
            if (Alliance == "Red") {
                Bot.strafeRight(lowSpeed);


            } else if (Alliance == "Blue") {
                Bot.strafeRight(lowSpeed);
            }

        }
        Bot.stopMotors();
    }

    public void driveToSkyStone(MetalBot Bot, String Alliance) {

        // Positive target value is from the center (0) to the right
        // Negative target value is from the center (0) to the left
        if (Alliance == "Red") {

            //disabaling for testing

             if (skyLocation == SkystoneLocation.left) {
                //
                ////position 1 (LEFT)

//                Bot.strafeLeft(.3, .4);  // original value - 2/19/20
//This worke by accident when detected skystone 2...
                Bot.strafeRight(.5, .1);     // was .1

                skyStonePosition = 1;
                //  } else if (Bot.skyStoneValue == 0.0) {      //position RIGHT (default)                                                                       // position 3


                //THIS WORKS FOR POSITION 3!!!
                Bot.strafeRight(.3, 1.8);       // was 1.9
                skyStonePosition = 3;

                //   } else if (Bot.skyStoneValue > 0) {       // position 2 (MIDDLE)

                //     Bot.strafeRight(.5, .9);     // was .1
                //    skyStonePosition = 2;
                // }

//            HARD CODE IF TESTING!
//            Bot.strafeLeft(.3, .45);
//            skyStonePosition = 1;

                //ANGLE TO APPROACH BLOCK AT.
                Bot.rotateLeft(lowSpeed, .3);
            } else if (Alliance == "Blue") {


                if (Bot.skyStoneValue < 0) {           //position 1 (LEFT)
                    Bot.strafeLeft(.3, .5);
                    Bot.rotateLeft(lowSpeed, .35);       // was .4
                    skyStonePosition = 1;
                } else if (Bot.skyStoneValue == 0.0) {                                                                        // position 3
                    Bot.strafeRight(.3, 1.6); //was 1.4, then 1.2, now 1.25
                    Bot.rotateLeft(lowSpeed, .3);
                    skyStonePosition = 3;
                } else if (Bot.skyStoneValue > 0) {      //position 2 (MIDDLE)

                    Bot.strafeRight(.3, .4);     // .6
                    Bot.rotateLeft(lowSpeed, .3);      // changed to right
                    skyStonePosition = 2;
                }


                //  Bot.strafeLeft(.3, .50);
                // Bot.rotateLeft(lowSpeed, .35);       // was .4, .3
                // skyStonePosition = 1;

            /*   //THIS WORKS!
            Bot.strafeRight(.3, 1.6); //was 1.4, then 1.2, now 1.25
                Bot.rotateLeft(lowSpeed, .3);
                skyStonePosition = 3;
             */


                //Bot.rotateLeft(lowSpeed, .3);


            }
        }
    }
}
