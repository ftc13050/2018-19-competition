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

package org.firstinspires.ftc.teamcode;

import com.qualcomm.ftccommon.configuration.EditServoListActivity;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

/**
 * This file provides basic Telop driving for a Pushbot robot.
 * The code is structured as an Iterative OpMode
 *
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwarePushbot class.
 *
 * This particular OpMode executes a basic Tank Drive Teleop for a PushBot
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="motor_test")
//@Disabled
public class motor_test extends OpMode{

    /* Declare OpMode members. */
    HardwarePushbot robot       = new HardwarePushbot();// use the class created to define a Pushbot's hardware
    // could also use HardwarePushbotMatrix class.
    double          clawOffset  = 0.0 ;                  // Servo mid position
    final double    CLAW_SPEED  = 0.02 ;                 // sets rate to move servo
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    /*
    The following code is for autonomous and teleop.
    All the commented code is for the actual robot. The uncommented is for the motor test.
    For the actual robot, uncomment the commented stuff in the function,
    and delete the two lines of code for motor test.
    My servo code needs to be checked.
    */

    //Override
    public void power(double leftPower, double rightPower/*, double pinionPower, double intakePower, double boxPower, double armPower*/)
    {
        robot.leftDrive.setPower(leftPower);
        robot.rightDrive.setPower(rightPower);

        /*
        //This int check is declared and is used the check whether the box is manually turned or not. If it is manually turned, it = 1.
        //Otherwise, it = 0.
        int check;
        check = 0;
        mDrv_r0.setPower(rightPower);
        mDrv_r1.setPower(rightPower);
        mDrv_l0.setPower(leftPower);
        mDrv_l1.setPower(leftPower);
        mPin.setPower(pinionPower);
        mItk.setPower(intakePower);
        //I dunno if this works. sBox.getPowerFloat(); is something you can do, but i dunno how to work that.
        if (box.getPower() > 10)
        {
            sBox.setDirection(Servo.Direction.FORWARD);
            check = 1;
        }
        else if (box.getPower() < -10)
        {
            sBox.setDirection(Servo.Direction.REVERSE);
        }
        if (armPower > 10)
        {
            sArm.setDirection(Servo.Direction.FORWARD);
            if (!(check == 1))
            if (!(check == 1))
            {
                sBox.setDirection(Servo.Direction.REVERSE);
            }
        }
        else if (armPower < -10)
        {
            sArm.setDirection(Servo.Direction.REVERSE);
            if (!(check == 1))
            {
                sBox.setDirection(Servo.Direction.FORWARD);
            }
        }
        */
    }
    @Override
    public void loop() {
        double leftMotor;
        double rightMotor;
        double pinion;
        double intake;
        double box;
        double arm;

        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)

        leftMotor = -gamepad1.left_stick_y;
        rightMotor = -gamepad1.right_stick_y;
        pinion = -gamepad2.left_stick_y;
        intake = -gamepad2.left_stick_x;
        box = -gamepad2.right_stick_x * 10;
        arm = -gamepad2.right_stick_y * 10;

        //uncomment pinion and intake when used for the actual robot

        power(leftMotor, rightMotor/*, pinion, intake, box, arm*/);

        // Instead of using the two lines of code below, you can just use my method.
        //robot.leftDrive.setPower(left);
        //robot.rightDrive.setPower(right);

        // Use gamepad left & right Bumpers to open and close the claw

        if (gamepad1.right_bumper)
            clawOffset += CLAW_SPEED;
        else if (gamepad1.left_bumper)
            clawOffset -= CLAW_SPEED;

        // Move both servos to new position.  Assume servos are mirror image of each other.
        clawOffset = Range.clip(clawOffset, -0.5, 0.5);
        robot.leftClaw.setPosition(robot.MID_SERVO + clawOffset);
        robot.rightClaw.setPosition(robot.MID_SERVO - clawOffset);

        // Use gamepad buttons to move the arm up (Y) and down (A)
        if (gamepad1.y)
            robot.leftArm.setPower(robot.ARM_UP_POWER);
        else if (gamepad1.a)
            robot.leftArm.setPower(robot.ARM_DOWN_POWER);
        else
            robot.leftArm.setPower(0.0);

        // Send telemetry message to signify robot running;
        telemetry.addData("claw",  "Offset = %.2f", clawOffset);
        // telemetry.addData("left",  "%.2f", left);
        // telemetry.addData("right", "%.2f", right);
    }


    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
