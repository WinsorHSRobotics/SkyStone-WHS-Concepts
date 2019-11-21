/**
 *
 * Adapted from OpMode created by TEAM 5115 WizardsRobotics
 *
 */

package org.firstinspires.ftc.teamcode.Drive_Systems;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

/*
    Holonomic concept using a Trigonometric wave function to calculate motor power.
    This concept was implemented in the 2017-18 FTC season and written by Anthony Romero,
    Jordan Newport and Dominic Scolari on TEAM 5115 WizardsRobotics

       Robot wheel mapping: //TODO Determine if this is correct
          X FRONT X
        X           X
      X  F1       F2  X
              X
             XXX
              X
      X  R1       R2  X
        X           X
          X       X

*/
@TeleOp(name = "Concept: Holonomic with Trig", group = "Concept")
@Disabled
public class Holonomic_w_Trig extends LinearOpMode {

    DcMotor F1;
    DcMotor F2;
    DcMotor R1;
    DcMotor R2;

    double angle;
    double leftstick_x_sqr;
    double leftstick_y_sqr;
    double power;
    double powerF1, powerF2, powerR1, powerR2;

    @Override
    public void runOpMode () {


        /*
         * Use the hardwareMap to get the dc motors and servos by name. Note
         * that the names of the devices must match the names used when you
         * configured your robot and created the configuration file.
         *
         * //TODO Determine location of each motor on robot and rename to fit other Holonomic code conventions
         */


        telemetry.addData("Status", "Initialized");

        F1 = hardwareMap.get(DcMotor.class, "F1");
        F2 = hardwareMap.get(DcMotor.class, "F2");
        R1 = hardwareMap.get(DcMotor.class, "R1");
        R2 = hardwareMap.get(DcMotor.class, "R2");

        F1.setDirection(DcMotorSimple.Direction.FORWARD);
        F2.setDirection(DcMotorSimple.Direction.FORWARD);
        R1.setDirection(DcMotorSimple.Direction.FORWARD);
        R2.setDirection(DcMotorSimple.Direction.FORWARD);

        F1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        F2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status", "Initialized v1");

        // Wait for the start button
        waitForStart();

        while(opModeIsActive()) {

            angle = Math.atan2(gamepad1.left_stick_x, gamepad1.left_stick_y); //finds angle of joystick
            telemetry.addData("angle: ", angle); //adds telemetry for troubleshooting code

            leftstick_x_sqr = gamepad1.left_stick_x * gamepad1.left_stick_x;
            leftstick_y_sqr = gamepad1.left_stick_y * gamepad1.left_stick_y;
            power = Math.sqrt(leftstick_x_sqr + leftstick_y_sqr);//finds relative power of joystick using pythagorean theorem
            telemetry.addData("power: ", power);

            double Pi = Math.PI/4;//to avoid having to type this everytime

            powerF2 = (power * Math.cos(angle - Pi)) + gamepad1.right_stick_x;
            powerF1 = (power * -Math.cos(angle + Pi)) + gamepad1.right_stick_x;//assigning each motor power based on calculated sin wave
            powerR1 = (power * -Math.cos(angle - Pi)) + gamepad1.right_stick_x;//right stick is used for y axis rotation
            powerR2 = (power * Math.cos(angle + Pi)) + gamepad1.right_stick_x;


            powerF1 = Range.clip(powerF1,-1.0, 1.0);//clips values to avoid program errors
            powerF2 = Range.clip(powerF2,-1.0, 1.0);
            powerR1 = Range.clip(powerR1,-1.0, 1.0);
            powerR2 = Range.clip(powerR2,-1.0, 1.0);

            telemetry.addData("right stick x: ", gamepad1.right_stick_x);

            if(power > .1 || Math.abs(gamepad1.right_stick_x)>.1){
                F1.setPower(powerF1);
                F2.setPower(powerF2);//applies motor power if joystick is moved
                R1.setPower(powerR1);
                R2.setPower(powerR2);
            }
            else{
                F1.setPower(0);
                F2.setPower(0);
                R1.setPower(0);//stops robot when no joystick is pressed
                R2.setPower(0);
            }
            telemetry.update();

        }

    }

}


