/**
 * This OpMode is made to test motor direction and drive characteristics of a new holonomic drive
 * system using AndyMark NeverRest 20:1, 40:1 or 60:1 motors.
 *
 * Adapted from OpMode created by Maddie, FTC Team 4962, The Rockettes (version 1.0 Aug 11, 2016)
 * This OpMode utilizes the following equipment on the robot
 *  1 - REV Hub
 *  4 - AndyMark Neverest Motors with endcoders connected
 *  4 - Tetrix dual omni wheels
 *
 *  robot configuration file:
 *  Holonomic concepts from:
 *  http://www.vexforum.com/index.php/12370-holonomic-drives-2-0-a-video-tutorial-by-cody/0
 *
 */

/*
	Holonomic concepts from:
	http://www.vexforum.com/index.php/12370-holonomic-drives-2-0-a-video-tutorial-by-cody/0
   Robot wheel mapping:
          X FRONT X
        X           X
      X  FL       FR  X
              X
             XXX
              X
      X  BL       BR  X
        X           X
          X       X
*/

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


@Autonomous(name = "Concept: Holonomic Motor Cycle", group = "Concept")
//@Disabled
public class Holonomic_Motor_Test_Cycle extends LinearOpMode {

    DcMotor motorFrontLeft;         // yellow 0
    DcMotor motorFrontRight;        // green 1
    DcMotor motorBackRight;         // blue 2
    DcMotor motorBackLeft;          // red 3

    // Uncomment the motor used (AndyMark NeveRest)
    // int type = 1680;        // Neverest 60
    int type = 1120;        // Neverest 40
    // int type = 560;         // Neverest 20

    double power = 0.5;

    @Override
    public void runOpMode () {

        /*
         * Use the hardwareMap to get the dc motors and servos by name. Note
         * that the names of the devices must match the names used when you
         * configured your robot and created the configuration file.
         */

        motorFrontLeft = hardwareMap.dcMotor.get("Front Left");
        motorFrontRight = hardwareMap.dcMotor.get("Front right");
        motorBackRight = hardwareMap.dcMotor.get("Back Right");
        motorBackLeft = hardwareMap.dcMotor.get("Back Left");

        //These work without reversing (Tetrix motors).
        //AndyMark motors may be opposite, in which case uncomment these lines:
        //motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
        //motorBackLeft.setDirection(DcMotor.Direction.REVERSE);
        //motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
        //motorBackRight.setDirection(DcMotor.Direction.REVERSE);

        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Wait for the start button
        waitForStart();

        while(opModeIsActive()) {

            // Turn all motors in one direction
            motorFrontLeft.setPower(power);
            motorFrontRight.setPower(power);
            motorBackRight.setPower(power);
            motorBackLeft.setPower(power);
            sleep(5000);
            motorFrontLeft.setPower(0);
            motorFrontRight.setPower(0);
            motorBackRight.setPower(0);
            motorBackLeft.setPower(0);
            sleep(1000);

            // Turn all motors in the other
            motorFrontLeft.setPower(-power);
            motorFrontRight.setPower(-power);
            motorBackRight.setPower(-power);
            motorBackLeft.setPower(-power);
            sleep(5000);
            motorFrontLeft.setPower(0);
            motorFrontRight.setPower(0);
            motorBackRight.setPower(0);
            motorBackLeft.setPower(0);
            sleep(1000);

            // Drive forward
            driveDirDist('f', power, type, 0.5);
            // Drive backward
            // Strafe Right
            // Strafe Left

            killBot();

        }

    }

    //TODO make these work
    public void driveDirDist (char dir, double power, int motorType, double distance) {

        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        int turns = (int) (distance * motorType);

        if (dir == 'f'){
            motorFrontLeft.setPower(power);
            motorFrontRight.setPower(power);
            motorBackRight.setPower(power);
            motorBackLeft.setPower(power);

            motorFrontLeft.setTargetPosition(turns);
            motorFrontRight.setTargetPosition(turns);
            motorBackRight.setTargetPosition(turns);
            motorBackLeft.setTargetPosition(turns);
        }

        else if (dir == 'b'){

        }

        else if (dir == 'r'){

        }

        else if (dir == 'l'){

        }

        else {
            telemetry.addLine("In the OpMode enter a one letter direction (f, b, r, l)");
            telemetry.update();
            sleep(2000);
            killBot();
        }

    }

    public void killBot(){

        // method stops the robot
        telemetry.addLine("Killing the Bot!");
        telemetry.update();
        motorFrontLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackRight.setPower(0);
        motorBackLeft.setPower(0);
        sleep(1500);

        requestOpModeStop();
        stop();
    }

}


