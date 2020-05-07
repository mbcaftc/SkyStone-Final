package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoPathsWood;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoBuildingWood;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoLoadingWood;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.EOCVWebCam;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.SkystoneLocation;
import org.firstinspires.ftc.teamcode.EasyOpenCV.WebCameraExample_nonGamepad;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous (name = "CAMERA AUTO2", group = "lab")

public class AutoCameraTest extends AutoBuildingWood {

//    public EOCVWebCam Cam = new EOCVWebCam();
    public WoodBot Bot = new WoodBot();
    OpenCvWebcam webCam = null;
    WebcamName webcamName = null;
    public String skyLocation = null;


//    SkystoneLocation skyLocation;

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap, "Auto");
        webcamName = hardwareMap.get(WebcamName.class, "WebCam");
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webCam = new OpenCvWebcam(webcamName, cameraMonitorViewId);
        webCam.openCameraDevice();
        SamplePipeline pipeline = new SamplePipeline();
        webCam.setPipeline(pipeline);
        webCam.startStreaming(320, 240, OpenCvCameraRotation.SIDEWAYS_LEFT);
        Bot.setLinearOp(this);
        setLinearOp(this);

        waitForStart();

        while ((opModeIsActive())){
// did not work - was trying to get skyLocation when used proper class hierachy.            telemetry.addData("Skystone ", skyLocation);
            skyLocation = String.valueOf(pipeline.location);
            telemetry.addData("Skystone Location: ", pipeline.location);
            telemetry.update();

            Bot.AutoClawRelease();
            driveToQuarry(Bot, "Red");
            sleep(sleepTime);
            driveGetSkyStone(Bot, skyLocation, "Red");

            sleep(sleepTime);



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
            idle();
        }
        requestOpModeStop();
        idle();
    }

    class SamplePipeline extends OpenCvPipeline {
        private Mat mat0;
        private Mat mat1;
        private Mat mat2;

        private Mat mask0;
        private Mat mask1;
        private Mat mask2;

        private boolean madeMats = false;

        private Scalar BLACK = new Scalar(0, 0, 0);
        private Scalar WHITE = new Scalar(255, 255, 255);
        private Scalar RED = new Scalar(255, 0, 0);

        private double cx0 = 125;
        private double cy0 = 50;
        private double cx1 = 125;
        private double cy1 = 150;
        private double cx2 = 125;
        private double cy2 = 250;

        private int r = 5;
        private int strokeWidth = 3;

        public org.firstinspires.ftc.teamcode.EasyOpenCV.SkystoneLocation location = org.firstinspires.ftc.teamcode.EasyOpenCV.SkystoneLocation.right;

        @Override
        public Mat processFrame(Mat frame) {
            int h = frame.height();
            int w = frame.width();

            int type = frame.type();
            if (!madeMats) {
                mask0 = new Mat(h, w, type);
                mask1 = new Mat(h, w, type);
                mask2 = new Mat(h, w, type);
                mat0 = new Mat();
                mat1 = new Mat();
                mat2 = new Mat();
                madeMats = true;
            }

            mask0.setTo(BLACK);
            mask1.setTo(BLACK);
            mask2.setTo(BLACK);

            Imgproc.circle(mask0, new Point(cx0, cy0), r, WHITE, Core.FILLED);
            Imgproc.circle(mask1, new Point(cx1, cy1), r, WHITE, Core.FILLED);
            Imgproc.circle(mask2, new Point(cx2, cy2), r, WHITE, Core.FILLED);

            Core.bitwise_and(mask0, frame, mat0);
            Core.bitwise_and(mask1, frame, mat1);
            Core.bitwise_and(mask2, frame, mat2);

            double val0 = Core.sumElems(mat0).val[0] + Core.sumElems(mat0).val[1] + Core.sumElems(mat0).val[2];
            double val1 = Core.sumElems(mat1).val[0] + Core.sumElems(mat1).val[1] + Core.sumElems(mat1).val[2];
            double val2 = Core.sumElems(mat2).val[0] + Core.sumElems(mat2).val[1] + Core.sumElems(mat2).val[2];


            /*  ----- This Telemetry Not needed for Auto Init -------
            telemetry.addData("Val0: ", val0);
            telemetry.addData("Val1: ", val1);
            telemetry.addData("Val2: ", val2);
             */

            if (val0 < val1 && val0 < val2) {
                location = org.firstinspires.ftc.teamcode.EasyOpenCV.SkystoneLocation.right;
            } else if (val1 < val0 && val1 < val2) {
                location = org.firstinspires.ftc.teamcode.EasyOpenCV.SkystoneLocation.middle;
            } else {
                location = org.firstinspires.ftc.teamcode.EasyOpenCV.SkystoneLocation.left;
            }

            Scalar s0 = WHITE;
            Scalar s1 = WHITE;
            Scalar s2 = WHITE;

            if (location == org.firstinspires.ftc.teamcode.EasyOpenCV.SkystoneLocation.right) {
                s0 = RED;
            } else if (location == org.firstinspires.ftc.teamcode.EasyOpenCV.SkystoneLocation.left) {
                s2 = RED;
            } else {
                s1 = RED;
            }

            Imgproc.line(frame, new Point(0, 275), new Point(300, 275), new Scalar(0, 255, 0));
            Imgproc.circle(frame, new Point(cx0, cy0), r, s0, Core.FILLED);
            Imgproc.circle(frame, new Point(cx1, cy1), r, s1, Core.FILLED);
            Imgproc.circle(frame, new Point(cx2, cy2), r, s2, Core.FILLED);

            return frame;
        }
    }
}
