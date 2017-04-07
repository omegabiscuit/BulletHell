package game;

import engine.display.AnimatedSprite;
import engine.util.GameClock;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * Created by Brigadoon on 3/30/2017.
 */
public class Enemy extends AnimatedSprite implements ItemListener {
    ArrayList<double[]> routePatternTemplate = new ArrayList<>();
    ArrayList<double[]> routePattern = new ArrayList<>();
    GameClock clock = new GameClock();
    Boolean stall = false;
    //double speed = 1;
    public boolean dead = false;

    public Enemy(String id) {
        super(id);
    }

    public Enemy(String id, String fileName) {
        super(id, fileName);
    }

    public void addRoute(double x, double y, double speed) {//enter -1 for no change in that axis
        routePatternTemplate.add(new double[]{x, y, speed});
    }

    public void clearRoute() {
        routePatternTemplate = new ArrayList<>();
        routePattern = new ArrayList<>();
    }

    public double getPathX() {
        if (routePatternTemplate.isEmpty()) {
            return 0;
        }
        if (routePattern.isEmpty()) {
            routePattern = new ArrayList<>(routePatternTemplate);
        } else if (routePattern.get(0)[0] == 0 && routePattern.get(0)[1] == 0 && routePattern.get(0)[2] != 0) { //remove path instruction once complete
            routePattern.remove(0);
            return 0;
        }
        double currentX = routePattern.get(0)[0];
        if (routePattern.get(0)[2] == 0) { //when speed=0, x=amount of time enemy stalls
            if(!stall){
                clock.resetGameClock();
                stall = true;
            }
            if (clock.getElapsedTime()/1000 > routePattern.get(0)[0]) {
                routePattern.remove(0);
                stall = false;
            }

            System.out.println(clock.getElapsedTime());
            return 0;
        }
        if (currentX > 0) { //used to decide if enemy is moving left or right
            double x = routePattern.get(0)[0] - routePattern.get(0)[2]; //used to prevent enemy from moving past its destination point
            if (x >= 0) {
                routePattern.set(0, new double[]{x, routePattern.get(0)[1], routePattern.get(0)[2]});
                return routePattern.get(0)[2];
            } else {
                routePattern.set(0, new double[]{0, routePattern.get(0)[1], routePattern.get(0)[2]});
                return routePattern.get(0)[2];
            }
        } else if (currentX < 0) {
            double x = routePattern.get(0)[0] + routePattern.get(0)[2]; //used to prevent enemy from moving past its destination point
            if (x <= 0) {
                routePattern.set(0, new double[]{x, routePattern.get(0)[1], routePattern.get(0)[2]});
                return -routePattern.get(0)[2];
            } else {
                routePattern.set(0, new double[]{0, routePattern.get(0)[1], routePattern.get(0)[2]});
                return -routePattern.get(0)[2];
            }

        }
        return 0;
    }

    public double getPathY() {
        if (routePatternTemplate.isEmpty()) {
            return 0;
        }
        if (routePattern.isEmpty()) {
            routePattern = new ArrayList<>(routePatternTemplate);
        }
        if (routePattern.get(0)[0] == 0 && routePattern.get(0)[1] == 0 && routePattern.get(0)[2] != 0) { //remove path instruction once complete
            routePattern.remove(0);
            return 0;
        }
        double currentY = routePattern.get(0)[1];
        if (routePattern.get(0)[2] == 0) {
//            if (this.getDeltaTime() > routePattern.get(0)[0]) {
//                routePattern.remove(0);
//
//            }
            return 0;
        }
        if (currentY > 0) { //used to decide if enemy is moving left or right
            double y = routePattern.get(0)[1] - routePattern.get(0)[2]; //used to prevent enemy from moving past its destination point
            if (y >= 0) {
                routePattern.set(0, new double[]{routePattern.get(0)[0], y, routePattern.get(0)[2]});
                return -routePattern.get(0)[2];
            } else {
                routePattern.set(0, new double[]{routePattern.get(0)[0], 0, routePattern.get(0)[2]});
                return -routePattern.get(0)[2];
            }
        } else if (currentY < 0) {
            double y = routePattern.get(0)[1] + routePattern.get(0)[2]; //used to prevent enemy from moving past its destination point
            if (y <= 0) {
                routePattern.set(0, new double[]{routePattern.get(0)[0], y, routePattern.get(0)[2]});
                return routePattern.get(0)[2];
            } else {
                routePattern.set(0, new double[]{routePattern.get(0)[0], 0, routePattern.get(0)[2]});
                return routePattern.get(0)[2];
            }
        }
        return 0;
    }

//    public void setSpeed(double speed) {
//        this.speed = speed;
//    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
