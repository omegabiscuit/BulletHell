package edu.virginia.lab1test;

import edu.virginia.engine.display.AnimatedSprite;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Brigadoon on 3/30/2017.
 */
public class Enemy extends AnimatedSprite implements ItemListener {
    ArrayList<double[]> routePatternTemplate;
    ArrayList<double[]> routePattern;
    double speed = .1;
    public Enemy(String id) {
        super(id);
    }

    public Enemy(String id, String fileName) {
        super(id, fileName);
    }

    public void addroute(double x, double y){//enter -1 for no change in that axis
        routePattern.add(new double[] {x,y});
    }

    public double getPathX(){

        if(routePattern.isEmpty()){
            routePattern = routePatternTemplate;
        }
        if(routePattern.get(0)[0] == -1){
            return 0;
        }
        double x = routePattern.get(0)[0]-speed;
        if(x > 0 ){
            routePattern.set(0, new double[] {x,routePattern.get(0)[1]} );
            return speed;
        }
        else{
            
        }
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
