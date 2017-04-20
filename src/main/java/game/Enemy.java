package game;


import com.sun.javafx.geom.Vec2d;
import engine.Tweens.Tween;
import engine.Tweens.TweenJuggler;
import engine.Tweens.TweenTransitions;
import engine.Tweens.TweenableParams;
import engine.display.AnimatedSprite;
import engine.display.DisplayObject;
import engine.display.Sprite;
import engine.events.*;
import engine.events.Event;
import engine.util.GameClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import static java.lang.Math.PI;

/**
 * Created by Brigadoon on 3/30/2017.
 */
public class Enemy extends AnimatedSprite implements IEventListener {
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

    Rectangle pickpocketRect;

    public Enemy(String id) {
        super(id, "", "");
        pickpocketRect = new Rectangle(570, 300, getUnscaledWidth() + 110, getUnscaledHeight() + 110);
    }

    public Enemy(String id, String fileName) {
        super(id, fileName);
        pickpocketRect = new Rectangle(570, 300, getUnscaledWidth() + 110, getUnscaledHeight() + 110);
    }

    public Enemy(String id, String fileName, String startState) {
        super(id, fileName, startState);
        pickpocketRect = new Rectangle(570, 300, getUnscaledWidth() + 110, getUnscaledHeight() + 110);
    }


    public void addRoute(double x, double y, double speed, double direction) {//enter -1 for no change in that axis
        routePatternTemplate.add(new double[]{x, y, speed, direction});
    }


    public void clearRoute() {
        routePatternTemplate = new ArrayList<>();
        routePattern = new ArrayList<>();
    }

    public Rectangle getPickpocketRect() {
        return pickpocketRect;
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



    public void update(){
        super.update();

        Double y = getPositionY() + getPathY();
        Double x = getPositionX() + getPathX();
        Integer yLoc = y.intValue();
        Integer xLoc = x.intValue();
        pickpocketRect.setLocation(xLoc - 10, yLoc - 10);
    }

    public boolean isInView(AnimatedSprite player, ArrayList<Rectangle2D> coverList){
        this.setPositionY(this.getPositionY() + this.getPathY());
        this.setPositionX(this.getPositionX() + this.getPathX());
        this.isFacing();
        Vec2d enemyFacing;
        Vec2d enemyPos = new Vec2d(this.getPositionX() + this.getUnscaledWidth() / 2, this.getPositionY() + this.getUnscaledHeight() / 2);
        Vec2d playerPos = new Vec2d(player.getPositionX() + player.getUnscaledWidth() / 2, player.getPositionY() + player.getUnscaledHeight() / 2);
        Vec2d enemyToPlayer = new Vec2d(playerPos.x - enemyPos.x, playerPos.y - enemyPos.y);
        if (this.getDirection() == 1) {
            enemyFacing = new Vec2d(this.getUnscaledWidth() / 2 + this.getPositionX(), this.getPositionY() - 10000);
        } else if (this.getDirection() == 2) {
            enemyFacing = new Vec2d(this.getPositionX() + this.getUnscaledWidth() + 1000, this.getPositionY() + this.getUnscaledHeight() / 2);
        } else if (this.getDirection() == 3) {
            enemyFacing = new Vec2d(this.getUnscaledWidth() / 2 + this.getPositionX(), this.getPositionY() + this.getUnscaledHeight() + 10000);
        } else {
            enemyFacing = new Vec2d(this.getPositionX() - 10000, this.getPositionY() + this.getUnscaledHeight() / 2);
        }

        //NORMALIZE VECTORS//
        double length = Math.sqrt(Math.pow(enemyFacing.x, 2) + Math.pow(enemyFacing.y, 2));
        enemyFacing.x = enemyFacing.x / length;
        enemyFacing.y = enemyFacing.y / length;
        length = Math.sqrt(Math.pow(enemyToPlayer.x, 2) + Math.pow(enemyToPlayer.y, 2));
        enemyToPlayer.x = enemyToPlayer.x / length;
        enemyToPlayer.y = enemyToPlayer.y / length;
        double angle = Math.toDegrees(Math.acos(enemyToPlayer.x * enemyFacing.x + enemyToPlayer.y * enemyFacing.y));

        if (angle <= this.getFieldOfView() / 2) {
            Line2D line = new Line2D.Double( this.getPositionX(),  this.getPositionY(), player.getPositionX(),player.getPositionY());
            for(int i=0;i<coverList.size();i++){
                if(line.intersects(coverList.get(i))){
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void draw(Graphics g)
    {
	/* Call the super draw method in DisplayObject class */
        super.draw(g);
        ((Graphics2D) g).draw(pickpocketRect);

    }

    @Override
    public void handleEvent(Event event) {
        if(event.getEventType() == "throwKnife"){
            //soundEffects.playSoundEffect("knife1.wav");
            System.out.println("play Enemy Knife sound");
        }
    }

    @Override
    public void handleEvent(Event event, Sprite sprite) {

    }

    public double getFieldOfView() {
        return fieldOfView;
    }


    public double getDirection() {
        return direction;
    }
}
