package myrobots;

import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;

// 继承AdvancedRobot类，它允许更复杂的行为，如独立控制机器人的各个部分
public class MyCombatRobot extends AdvancedRobot {

    public void run() {
        // 设置雷达、枪和身体分离
        setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);
        
        while (true) {
            // 转动雷达进行全方位扫描
            turnRadarRight(360);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        // 基础的目标锁定
        double absoluteBearing = getHeading() + e.getBearing();
        double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getGunHeading());
        
        // 如果枪准备好了就射击
        if (Math.abs(bearingFromGun) <= 3) {
            turnGunRight(bearingFromGun);
            // 控制射击的火力，距离越近，火力越大
            if (getGunHeat() == 0) {
                fire(Math.min(4.5 - Math.abs(bearingFromGun) / 2 - e.getDistance() / 200, getEnergy() - .1));
            }
        } else {
            turnGunRight(bearingFromGun);
        }
        
        // 雷达锁定
        if (bearingFromGun == 0) {
            scan();
        }
    }

    public void onHitByBullet(HitByBulletEvent e) {
        // 被击中时的简单避开策略
        double bearing = e.getBearing();
        turnRight(-bearing);
        ahead(100);
    }

    public void onHitWall(HitWallEvent e) {
        // 碰到墙壁时后退
        back(100);
        turnRight(90); // 随机改变一个角度
    }
}