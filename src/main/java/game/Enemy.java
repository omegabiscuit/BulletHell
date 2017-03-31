package main.java.game;

import main.java.engine.display.AnimatedSprite;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * Created by Brigadoon on 3/30/2017.
 */
public class Enemy extends AnimatedSprite implements ItemListener {
    ArrayList<double[]> routePatternTemplate = new ArrayList<>();
    ArrayList<double[]> routePattern = new ArrayList<>();
    double speed = 1;
    public Enemy(String id) {
        super(id);
    }

    public Enemy(String id, String fileName) {
        super(id, fileName);
    }

    public void addRoute(double x,double y){//enter -1 for no change in that axis
        routePatternTemplate.add(new double[] {x,y});
    }

    public void clearRoute(){
        routePatternTemplate = new ArrayList<>();
        routePattern = new ArrayList<>();
    }

    public double getPathX(){
        if(routePatternTemplate.isEmpty()){
            return 0;
        }
        if(routePattern.isEmpty()){
            routePattern = new ArrayList<>(routePatternTemplate);
        }
        else if(routePattern.get(0)[0] == 0 && routePattern.get(0)[1] == 0){ //remove path instruction once complete
            routePattern.remove(0);
            return 0;
        }
        double currentX = routePattern.get(0)[0];
        if(currentX > 0 ){ //used to decide if enemy is moving left or right
            double x = routePattern.get(0)[0]-speed; //used to prevent enemy from moving past its destination point
            if(x>=0) {
                routePattern.set(0, new double[]{x, routePattern.get(0)[1]});
                return speed;
            }
            else{
                routePattern.set(0, new double[]{0, routePattern.get(0)[1]});
                return speed;
            }
        }
        else if(currentX < 0){
            double x = routePattern.get(0)[0]+speed; //used to prevent enemy from moving past its destination point
            if(x<=0) {
                routePattern.set(0, new double[]{x, routePattern.get(0)[1]});
                return -speed;
            }
            else{
                routePattern.set(0, new double[]{0, routePattern.get(0)[1]});
                return -speed;
            }

        }
        return 0;
    }

    public double getPathY(){
        if(routePatternTemplate.isEmpty()){
            return 0;
        }
        if(routePattern.isEmpty()){
            routePattern = new ArrayList<>(routePatternTemplate);
        }
        if(routePattern.get(0)[0] == 0 && routePattern.get(0)[1] == 0){ //remove path instruction once complete
            routePattern.remove(0);
            return 0;
        }
        double currentY = routePattern.get(0)[1];
        if(currentY > 0 ){ //used to decide if enemy is moving left or right
            double y = routePattern.get(0)[1]-speed; //used to prevent enemy from moving past its destination point
            if(y>=0) {
                routePattern.set(0, new double[]{ routePattern.get(0)[0],y});
                return -speed;
            }
            else{
                routePattern.set(0, new double[]{routePattern.get(0)[0],0});
                return -speed;
            }
        }
        else if(currentY < 0){
            double y = routePattern.get(0)[1]+speed; //used to prevent enemy from moving past its destination point
            if(y<=0) {
                routePattern.set(0, new double[]{routePattern.get(0)[0],y});
                return speed;
            }
            else{
                routePattern.set(0, new double[]{routePattern.get(0)[0],0});
                return speed;
            }
        }
        return 0;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
