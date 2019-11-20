/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.Sensor_Concepts;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

/**
 * This OpMode scans a single continuous rotation servo back and forwards until Stop is pressed.
 * The code is structured as a LinearOpMode
 *
 * Power Settings for HiTec HSR-1425CR function just like motor power.  At very low settings
 * (< 0.08) no rotation occurs.  Beyond 0.25 there is very little difference in the rotation
 * under no load.
 *
 */
@TeleOp(name = "Concept: Scan CRServo", group = "Concept")
//
// @Disabled
public class ConceptScanContServo extends LinearOpMode {

    // Define class members
    CRServo crServo;
    double  crPower = 0.09;

    @Override
    public void runOpMode() {

        // Change the text in quotes to match any servo name on your robot.
        crServo = hardwareMap.get(CRServo.class, "CRServo");

        // Wait for the start button
        telemetry.addData(">", "Press Start to scan Servo." );
        telemetry.update();
        waitForStart();


        // Scan servo till stop pressed.
        while(opModeIsActive()){

            crServo.setPower(crPower);
            sleep(5000);
            crServo.setPower(-crPower);
            sleep(5000);

        }

        // Signal done;
        telemetry.addData(">", "Done");
        telemetry.update();
    }
}
