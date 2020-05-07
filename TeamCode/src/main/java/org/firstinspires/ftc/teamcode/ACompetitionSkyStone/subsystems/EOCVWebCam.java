package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.SkystoneLocation;
//import org.firstinspires.ftc.teamcode.EasyOpenCV.Stickygamepad;
//import org.firstinspires.ftc.teamcode.EasyOpenCV.WebCameraExample_Gamepad;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;
import org.openftc.easyopencv.OpenCvPipeline;


public class EOCVWebCam {


    OpenCvCamera webCam = null;
    WebcamName webCamName = null;

    private enum Location {
        left, middle, right, line
    }

    public String skyLoc = null;

    public HardwareMap hwCam   =  null;


    public void initCamera (HardwareMap hwMap) {

        /*
         * Instantiate an OpenCvCamera object for the camera we'll be using.
         * In this sample, we're using the phone's internal camera. We pass it a
         * CameraDirection enum indicating whether to use the front or back facing
         * camera, as well as the view that we wish to use for camera monitor (on
         * the RC phone). If no camera monitor is desired, use the alternate
         * single-parameter constructor instead (commented out below)
         */
        hwCam = hwMap;
        webCamName = hwCam.get(WebcamName.class, "WebCam");
        int cameraMonitorViewId = hwCam.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwCam.appContext.getPackageName());
        webCam = new OpenCvWebcam(webCamName, cameraMonitorViewId);

        // OR...  Do Not Activate the Camera Monitor View
        //phoneCam = new OpenCvInternalCamera(OpenCvInternalCamera.CameraDirection.BACK);

        /*
         * Open the connection to the camera device
         */
        webCam.openCameraDevice();

        /*
         * Specify the image processing pipeline we wish to invoke upon receipt
         * of a frame from the camera. Note that switching pipelines on-the-fly
         * (while a streaming session is in flight) *IS* supported.
         */
        /*
         * Specify the image processing pipeline we wish to invoke upon receipt
         * of a frame from the camera. Note that switching pipelines on-the-fly
         * (while a streaming session is in flight) *IS* supported.
         */

//        SamplePipeline.pipeline = new SamplePipeline();


        SamplePipeline pipeline = new SamplePipeline();
        webCam.setPipeline(pipeline);

        webCam.startStreaming(320, 240, OpenCvCameraRotation.SIDEWAYS_LEFT);

        /* ---everything together, for initting during the autonomous class w/o using initcam
        public HardwareMap hwCam   =  null;
        hwCam = hwMap;
        webCamName = hwCam.get(WebcamName.class, "WebCam");


        int cameraMonitorViewId = hwCam.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwCam.appContext.getPackageName());
        webCam = new OpenCvWebcam(webCamName, cameraMonitorViewId);
        webCam.openCameraDevice();
        SamplePipeline pipeline = new SamplePipeline();
        webCam.setPipeline(pipeline);

        webCam.startStreaming(320, 240, OpenCvCameraRotation.SIDEWAYS_LEFT);



         */
    }

    class SamplePipeline extends OpenCvPipeline
    {
        private Mat mat0;
        private Mat mat1;
        private Mat mat2;

        private Mat mask0;
        private Mat mask1;
        private Mat mask2;

        private boolean madeMats = false;

        private Scalar BLACK = new Scalar(0,0,0);
        private Scalar WHITE = new Scalar(255,255,255);
        private Scalar RED = new Scalar(255, 0, 0);

        public double cx0 = 125;
        public double cy0 = 50;
        public double cx1 = 125;
        public double cy1 = 150;
        public double cx2 = 125;
        public double cy2 = 250;

        public double lineY = 110;

        private int r = 5;
        private int strokeWidth = 3;

        public SkystoneLocation location = SkystoneLocation.right;

//        public org.firstinspires.ftc.teamcode.EasyOpenCV.SkystoneLocation location = org.firstinspires.ftc.teamcode.EasyOpenCV.SkystoneLocation.right;

        /*
         * NOTE: if you wish to use additional Mat objects in your processing pipeline, it is
         * highly recommended to declare them here as instance variables and re-use them for
         * each invocation of processFrame(), rather than declaring them as new local variables
         * each time through processFrame(). This removes the danger of causing a memory leak
         * by forgetting to call mat.release(), and it also reduces memory pressure by not
         * constantly allocating and freeing large chunks of memory.
         */



        @Override
        public Mat processFrame(Mat frame)
        {
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


            if (val0 < val1 && val0 < val2) {
                location = SkystoneLocation.right;
            } else if (val1 < val0 && val1 < val2) {
                location = SkystoneLocation.middle;
            } else {
                location = SkystoneLocation.left;
            }
            skyLoc = String.valueOf(location);
            /*
             * IMPORTANT NOTE: the input Mat that is passed in as a parameter to this method
             * will only dereference to the same image for the duration of this particular
             * invocation of this method. That is, if for some reason you'd like to save a copy
             * of this particular frame for later use, you will need to either clone it or copy
             * it to another Mat.
             */

            /*
             * Draw a simple box around the middle 1/2 of the entire frame
             */
            Scalar s0 = WHITE;
            Scalar s1 = WHITE;
            Scalar s2 = WHITE;

            if (location == org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.SkystoneLocation.right) {
                s0 = RED;
            } else if (location == SkystoneLocation.left) {
                s2 = RED;
            } else {
                s1 = RED;
            }

            Imgproc.line(frame, new Point(0, lineY), new Point(300, lineY), new Scalar(0, 255, 0));
            Imgproc.circle(frame, new Point(cx0, cy0), r, s0, Core.FILLED);
            Imgproc.circle(frame, new Point(cx1, cy1), r, s1, Core.FILLED);
            Imgproc.circle(frame, new Point(cx2, cy2), r, s2, Core.FILLED);

            /**
             * NOTE: to see how to get data from your pipeline to your OpMode as well as how
             * to change which stage of the pipeline is rendered to the viewport when it is
             * tapped, please see {@link PipelineStageSwitchingExample}
             */

            return frame;
        }
    }
}
