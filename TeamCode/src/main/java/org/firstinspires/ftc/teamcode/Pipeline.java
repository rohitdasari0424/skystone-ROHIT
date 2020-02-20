package org.firstinspires.ftc.teamcode;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class Pipeline extends OpenCvPipeline {
        private Mat currentMatrix = new Mat();
        public String position = "LEFT";

        @Override
        public final Mat processFrame(Mat input){
            input.copyTo(currentMatrix);

            if(currentMatrix.empty()){
                return input;
            }

            Imgproc.cvtColor(currentMatrix,currentMatrix,Imgproc.COLOR_RGB2YCrCb);
            Mat leftMat = currentMatrix.submat(120,150,10,50);
            Mat centerMat = currentMatrix.submat(120,150,10,50);
            Mat rightMat = currentMatrix.submat(120,150,10,50);

            Imgproc.rectangle(currentMatrix, new Rect(10,120,40,30), new Scalar(0,0,0));
            Imgproc.rectangle(currentMatrix, new Rect(80,120,40,30), new Scalar(0,0,0));
            Imgproc.rectangle(currentMatrix, new Rect(150,120,40,30), new Scalar(0,0,0));

            double totalLeft = Core.sumElems(leftMat).val[2];
            double totalCenter = Core.sumElems(centerMat).val[2];
            double totalRight = Core.sumElems(rightMat).val[2];

            if(totalLeft > totalCenter && totalLeft > totalRight){
                position = "LEFT";
            }
            if(totalCenter > totalLeft && totalCenter > totalRight){
                position = "CENTER";
            }
            if(totalRight > totalLeft && totalRight > totalCenter){
                position = "RIGHT";
            }
            return currentMatrix;
        }
}
