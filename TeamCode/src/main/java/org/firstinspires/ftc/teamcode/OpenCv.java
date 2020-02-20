package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

@Autonomous(name="OpenCv")
public class OpenCv extends LinearOpMode {
    private OpenCvCamera phone;
    Pipeline pipe = new Pipeline();

    @Override
    public void runOpMode(){
        int currentMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("currentMonitor","id",hardwareMap.appContext.getPackageName());
        phone = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.FRONT, currentMonitorViewId);

        phone.openCameraDevice();

        phone.setPipeline(pipe);

        phone.startStreaming(320,240, OpenCvCameraRotation.UPRIGHT);

        waitForStart();
        while(opModeIsActive()){
            telemetry.addData("STONE Position" , pipe.position);
            telemetry.update();
        }
    }
}
