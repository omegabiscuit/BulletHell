package game;

import engine.display.Sprite;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by Brigadoon on 4/23/2017.
 */
public class BrighamLevel extends Room {

    Sprite map;
    Sprite startTile;



    Platform collider1;
    Platform collider2;
    Platform leftCollider;
    Platform collider3;
    Platform rightCollider;
    Platform collider4;
    Platform doorCollider;
    Platform wallCollider;
    Platform thinWallLeft;
    Platform thinWallRight;

    Enemy enemy01;
    Enemy enemy02;
    Enemy enemy03;


    LockedDoor door1;

    public BrighamLevel(String id) {
        super(id);
    }

    public void run() {
        enemy01 = new Enemy("enemy", "resources/gator_sheet.png", "idle left");
        enemy01.setSpriteSheetJson("resources/gator_sheet.json");
        enemy01.setDelay(75);
        enemy01.setPositionX(250);
        enemy01.setPositionY(680);
        enemy01.addRoute(0, 1100, 2, 1);
        enemy01.addRoute(800, 0, 4, 2);
        enemy01.addRoute(0, -300, 2, 3);
        enemy01.addRoute(-800, 0, 2, 4);
        enemy01.addRoute(0, -800, 2, 3);

        enemy01.addRoute(0, 1100, 10, 1);
        enemy01.addRoute(800, 0, 1, 2);
        enemy01.addRoute(0, -300, 5, 3);
        enemy01.addRoute(-800, 0, 4, 4);
        enemy01.addRoute(0, -800, 4, 3);
        enemy01.addKey();

        enemy02 = new Enemy("enemy", "resources/gator_sheet.png","idle left");
        enemy02.setSpriteSheetJson("resources/gator_sheet.json");
        enemy02.setDelay(75);
        enemy02.setPositionX(900);
        enemy02.setPositionY(200);
        enemy02.addRoute(0,-800,2,3);
        enemy02.addRoute(0,800,4,1);
        enemy02.addRoute(3,0,0,2);

        enemy03 = new Enemy("enemy", "resources/gator_sheet.png","idle left");
        enemy03.setSpriteSheetJson("resources/gator_sheet.json");
        enemy03.setDelay(75);
        enemy03.setPositionX(800);
        enemy03.setPositionY(300);
        enemy03.addRoute(-800,0,2,3);
        enemy03.addRoute(800,0,2,2);




//        enemy02 = new Enemy("enemy", "resources/gator_sheet.png", "idle left");
//        enemy02.setSpriteSheetJson("resources/gator_sheet.json");
//        enemy02.setDelay(75);
//        enemy02.setPositionX(700);
//        enemy02.setPositionY(150);
//        enemy02.addRoute(0, -800, 2, 3);
//        enemy02.addRoute(-400, 0, 2, 4);
//        enemy02.addRoute(0, 800, 2, 1);
//        enemy02.addRoute(400, 0, 2, 2);
//        enemy02.addKnife();

        enemies = new ArrayList<>();
        enemies.add(enemy01);
        enemies.add(enemy02);
        enemies.add(enemy03);
//        enemies.add(enemy02);


        //map stuff
        map = new Sprite("map", "BrighamLevelFinal.png");
        map.setPositionX(200);
        map.setPositionY(-1950);
        addChild(map);

        door1 = new LockedDoor("door", "resources/door_opening.png", "door_closed");
        door1.setSpriteSheetJson("resources/door_opening.json");
        door1.setDelay(100);
        door1.setPositionX(500);
        door1.setPositionY(50);
        addChild(door1);

        startTile = new Sprite("startTile", "tile.png");
        startTile.setPositionX(500);
        startTile.setPositionY(810);

        collider1 = new Platform("collider", "alpha_3x1.png");
        collider1.setPositionX(640);
        collider1.setPositionY(-20);
        addChild(collider1);

        collider2 = new Platform("collider", "alpha_2x1.png");
        collider2.setPositionX(200);
        collider2.setPositionY(-20);
        addChild(collider2);

        collider3 = new Platform("collider", "alpha_1x1.png");
        collider3.setPositionX(180);
        collider3.setPositionY(-20);
        addChild(collider3);

        collider4 = new Platform("collider", "alpha_2x1.png");
        collider4.setPositionX(270);
        collider4.setPositionY(-20);
        addChild(collider4);

        leftCollider = new Platform("collider","alpha_1x6.png");
        leftCollider.setPositionX(102);
        leftCollider.setPositionY(-20);
        addChild(leftCollider);

        rightCollider = new Platform("collider","alpha_1x6.png");
        rightCollider.setPositionX(1006);
        rightCollider.setPositionY(-20);
        addChild(rightCollider);

        doorCollider = new Platform("collider", "alpha_2x1.png");
        doorCollider.setPositionX(456);
        doorCollider.setPositionY(-50);
        addChild(doorCollider);

        wallCollider = new Platform("collider", "alpha_3x1.png");
        wallCollider.setPositionX(450);
        wallCollider.setPositionY(410);
        addChild(wallCollider);

        thinWallLeft = new Platform("collider", "alpha_thinWallLong.png");
        thinWallLeft.setPositionX(wallCollider.getPositionX()-thinWallLeft.getUnscaledWidth()+10);
        thinWallLeft.setPositionY(400);
        addChild(thinWallLeft);

        thinWallRight = new Platform("collider", "alpha_thinWallLong.png");
        thinWallRight.setPositionX(wallCollider.getPositionX()+wallCollider.getUnscaledWidth()-10);
        thinWallRight.setPositionY(400);
        addChild(thinWallRight);

        coverList = new ArrayList<>(); //list of cover sprites






        collisionArray.add(collider1);
        collisionArray.add(collider2);
        collisionArray.add(collider3);
        collisionArray.add(collider4);
        collisionArray.add(leftCollider);
        collisionArray.add(rightCollider);
        collisionArray.add(doorCollider);
        collisionArray.add(wallCollider);
        collisionArray.add(thinWallLeft);
        collisionArray.add(thinWallRight);
        //collisionArray.add(door1);

        coverList.add(convertToCover(collider1));
        coverList.add(convertToCover(collider2));
        coverList.add(convertToCover(collider3));
        coverList.add(convertToCover(leftCollider));
        coverList.add(convertToCover(rightCollider));
        coverList.add(convertToCover(wallCollider));
        coverList.add(convertToCover(doorCollider));
        coverList.add(convertToCover(thinWallLeft));
        coverList.add(convertToCover(thinWallRight));

        doors.add(door1);


    }


    public void draw(Graphics g) {
        super.draw(g);
        map.draw(g);
        startTile.draw(g);
        door1.draw(g);

//        collider1.draw(g);
//        collider2.draw(g);
//        collider3.draw(g);
//        collider4.draw(g);
//        collider5.draw(g);
//        collider6.draw(g);
//        collider7.draw(g);
//        collider8.draw(g);





    }

    public void update() {
        super.update();
        door1.update();
    }

    public ArrayList<Rectangle2D> getCoverList(){
        return coverList;
    }

    public ArrayList<Platform> getCollisionArray(){
        return collisionArray;
    }
}
