package Wyt;

import robocode.*;
import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;
import java.awt.*;

public class Wyt extends AdvancedRobot{
	
	public void run() {
		setColors(Color.yellow, Color.black, Color.red, Color.pink, Color.green);
		turnGunRight(90);
		while (true) {
			setTurnRight(10000);
			setMaxVelocity(5);
			ahead(10000);
		}
		
	}
	public void onScannedRobot(ScannedRobotEvent e) {
		fire(2);
	}
	public void onHitRobot(HitRobotEvent e) {
		if (e.getBearing() > -10 && e.getBearing() < 10) {
			fire(3);
		}
		if (e.isMyFault()) {
			turnRight(10);
		}
	}
}