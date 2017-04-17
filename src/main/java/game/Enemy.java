package game;

import engine.display.AnimatedSprite;
import engine.display.DisplayObject;
import engine.util.GameClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import static java.lang.Math.PI;

/**
 * Created by Brigadoon on 3/30/2017.
 */
public class Enemy extends AnimatedSprite implements ItemListener {
    ArrayList<double[]> routePatternTemplate = new ArrayList<>();
    ArrayList<double[]> routePattern = new ArrayList<>();
    GameClock clock = new GameClock();
    Boolean stall = false;
    double fieldOfView = 80;
    double direction;//direction enemy is facing [xpos,ypos] of focal point
    double awareness=0; //how aware the enemy is to the player's presence
    public Boolean shooting = false;
    public GameClock bulletClock = null;
    public Bullet enemyBullet = null;
    public boolean dead = false;

    public Enemy(String id) {
        super(id, "", "");
    }

    public Enemy(String id, String fileName) {
        super(id, fileName);
    }

    public Enemy(String id, String fileName, String startState) {
        super(id, fileName, startState);
    }


    public void addRoute(double x, double y, double speed, double direction) {//enter -1 for no change in that axis
        routePatternTemplate.add(new double[]{x, y, speed, direction});
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
            if (!stall) {
                clock.resetGameClock();
                stall = true;
            }
            if (clock.getElapsedTime() / 1000 > routePattern.get(0)[0]) {
                routePattern.remove(0);
                stall = false;
            }
            return 0;
        }
        if (currentX > 0) { //used to decide if enemy is moving left or right
            double x = routePattern.get(0)[0] - routePattern.get(0)[2]; //used to prevent enemy from moving past its destination point
            if (x >= 0) {
                routePattern.set(0, new double[]{x, routePattern.get(0)[1], routePattern.get(0)[2],routePattern.get(0)[3]});
                return routePattern.get(0)[2];
            } else {
                routePattern.set(0, new double[]{0, routePattern.get(0)[1], routePattern.get(0)[2],routePattern.get(0)[3]});
                return routePattern.get(0)[2];
            }
        } else if (currentX < 0) {
            double x = routePattern.get(0)[0] + routePattern.get(0)[2]; //used to prevent enemy from moving past its destination point
            if (x <= 0) {
                routePattern.set(0, new double[]{x, routePattern.get(0)[1], routePattern.get(0)[2],routePattern.get(0)[3]});
                return -routePattern.get(0)[2];
            } else {
                routePattern.set(0, new double[]{0, routePattern.get(0)[1], routePattern.get(0)[2],routePattern.get(0)[3]});
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
            return 0;
        }
        if (currentY > 0) { //used to decide if enemy is moving left or right
            double y = routePattern.get(0)[1] - routePattern.get(0)[2]; //prevent enemy from moving past its dest.
            if (y >= 0) {
                routePattern.set(0, new double[]{routePattern.get(0)[0], y, routePattern.get(0)[2],routePattern.get(0)[3]});
                return -routePattern.get(0)[2];
            } else {
                routePattern.set(0, new double[]{routePattern.get(0)[0], 0, routePattern.get(0)[2],routePattern.get(0)[3]});
                return -routePattern.get(0)[2];
            }
        } else if (currentY < 0) {
            double y = routePattern.get(0)[1] + routePattern.get(0)[2]; //prevent enemy from moving past its dest.
            if (y <= 0) {
                routePattern.set(0, new double[]{routePattern.get(0)[0], y, routePattern.get(0)[2],routePattern.get(0)[3]});
                return routePattern.get(0)[2];
            } else {
                routePattern.set(0, new double[]{routePattern.get(0)[0], 0, routePattern.get(0)[2],routePattern.get(0)[3]});
                return routePattern.get(0)[2];
            }
        }
        return 0;
    }

    public void isFacing(){
        if (routePattern.isEmpty()) {
            routePattern = new ArrayList<>(routePatternTemplate);
        }
        double direction = routePattern.get(0)[3];
        if (direction == 1) { //face north
            this.direction=1;
        } else if (direction == 2) {//face east
            this.direction=2;
        } else if (direction == 3) {//face south
            this.direction=3;
        } else if (direction == 4) { //face west
            this.direction=4;
        }
    }

    public double getFieldOfView() {
        return fieldOfView;
    }


    public double getDirection() {
        return direction;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    public void update(){
        super.update();
    }

    @Override
    public void draw(Graphics g)
    {
	/* Call the super draw method in DisplayObject class */
        super.draw(g);

    }
}
