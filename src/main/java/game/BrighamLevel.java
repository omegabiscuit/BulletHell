package game;

import engine.display.Sprite;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Brigadoon on 4/23/2017.
 */
public class BrighamLevel extends Room {

    Sprite map;
    Sprite startTile;



    Platform collider1;
    Platform collider2;
    Platform collider3;
    Platform collider4;
    Platform leftCollider;
    Platform rightCollider;

    Platform doorCollider;
    Platform wallCollider;
    Platform thinWallLeft;
    Platform thinWallRight;

    Platform bottomCollider;
    Platform bottomCollider2;
    Platform bottomCollider3;


    Platform bottomLeft;
    Platform bottomRight;
    Platform bottom;


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




        enemies = new ArrayList<>();
        enemies.add(enemy01);
        enemies.add(enemy02);
        enemies.add(enemy03);

        random = new Random();
        enemies.get(random.nextInt(enemies.size())).addKey();



        //map stuff
        map = new Sprite("map", "BrighamLevelFinal.png");
        map.setPositionX(200);
        map.setPositionY(-1872);


        addChild(map);

        door1 = new LockedDoor("door", "resources/door_opening.png", "door_closed");
        door1.setSpriteSheetJson("resources/door_opening.json");
        door1.setDelay(100);
        door1.setPositionX(500);
        door1.setPositionY(50 + map.getPositionY());
        addChild(door1);

        startTile = new Sprite("startTile", "tile.png");
        startTile.setPositionX(628);
        startTile.setPositionY(map.getPositionY() + 810);
        addChild(startTile);

        bottom = new Platform("collider", "alpha_1x1.png");
        bottom.setPositionX(startTile.getPositionX());
        bottom.setPositionY(startTile.getPositionY()+128);
        addChild(bottom);

        bottomLeft = new Platform("collider", "alpha_3x1.png");
        bottomLeft.setPositionX(map.getPositionX());
        bottomLeft.setPositionY(map.getPositionY()+map.getUnscaledHeight());
        addChild(bottomLeft);

        bottomRight = new Platform("collider", "alpha_3x1.png");
        bottomRight.setPositionX(map.getPositionX()+504);
        bottomRight.setPositionY(map.getPositionY()+map.getUnscaledHeight());
        addChild(bottomRight);


        collider1 = new Platform("collider", "alpha_3x1.png");
        collider1.setPositionX(640);
        collider1.setPositionY(-20);
        coverList.add(convertToCover(collider1));
        collider1.setPositionY(-20 + map.getPositionY());
        addChild(collider1);

        collider2 = new Platform("collider", "alpha_2x1.png");
        collider2.setPositionX(200);
        collider2.setPositionY(-20);
        coverList.add(convertToCover(collider2));
        collider2.setPositionY(-20 + map.getPositionY());
        addChild(collider2);

        collider3 = new Platform("collider", "alpha_1x1.png");
        collider3.setPositionX(180);
        collider3.setPositionY(-20);
        coverList.add(convertToCover(collider3));
        collider3.setPositionY(-20 + map.getPositionY());
        addChild(collider3);

        collider4 = new Platform("collider", "alpha_2x1.png");
        collider4.setPositionX(270);
        collider4.setPositionY(-20 + map.getPositionY());
        addChild(collider4);

        leftCollider = new Platform("collider","alpha_1x6.png");
        leftCollider.setPositionX(102);
        leftCollider.setPositionY(-20);
        coverList.add(convertToCover(leftCollider));
        leftCollider.setPositionY(-20 + map.getPositionY());
        addChild(leftCollider);

        rightCollider = new Platform("collider","alpha_1x6.png");
        rightCollider.setPositionX(1006);
        rightCollider.setPositionY(-20);
        coverList.add(convertToCover(rightCollider));
        rightCollider.setPositionY(-20 + map.getPositionY());
        addChild(rightCollider);

        doorCollider = new Platform("collider", "alpha_2x1.png");
        doorCollider.setPositionX(456);
        doorCollider.setPositionY(-50 + map.getPositionY());
        addChild(doorCollider);

        wallCollider = new Platform("collider", "alpha_3x1.png");
        wallCollider.setPositionX(450);
        wallCollider.setPositionY(410);
        coverList.add(convertToCover(wallCollider));
        wallCollider.setPositionY(410 + map.getPositionY());
        addChild(wallCollider);

        thinWallLeft = new Platform("collider", "alpha_thinWallLong.png");
        thinWallLeft.setPositionX(wallCollider.getPositionX()-thinWallLeft.getUnscaledWidth()+10);
        thinWallLeft.setPositionY(400);
        coverList.add(convertToCover(thinWallLeft));
        thinWallLeft.setPositionY(400 + map.getPositionY());
        addChild(thinWallLeft);

        thinWallRight = new Platform("collider", "alpha_thinWallLong.png");
        thinWallRight.setPositionX(wallCollider.getPositionX()+wallCollider.getUnscaledWidth()-10);
        thinWallRight.setPositionY(400);
        coverList.add(convertToCover(thinWallRight));
        thinWallRight.setPositionY(400 + map.getPositionY());
        addChild(thinWallRight);


        bottomCollider = new Platform("collider", "alpha_6x1.png");
        bottomCollider.setPositionX(map.getPositionX() - 128*2 - 2*128/3);
        bottomCollider.setPositionY(map.getPositionY() + map.getUnscaledHeight());
        coverList.add(convertToCover(bottomCollider));
        addChild(bottomCollider);

        bottomCollider2 = new Platform("collider", "alpha_6x1.png");
        bottomCollider2.setPositionX(bottomCollider.getPositionX() + bottomCollider.getUnscaledWidth() + 128);
        bottomCollider2.setPositionY(bottomCollider.getPositionY());
        coverList.add(convertToCover(bottomCollider2));
        addChild(bottomCollider2);

        bottomCollider3 = new Platform("collider", "alpha_1x1.png");
        bottomCollider3.setPositionX(bottomCollider2.getPositionX() - bottomCollider3.getUnscaledWidth());
        bottomCollider3.setPositionY(bottomCollider2.getPositionY() + bottomCollider3.getUnscaledHeight());
        coverList.add(convertToCover(bottomCollider3));
        addChild(bottomCollider3);


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
        collisionArray.add(bottomCollider);
        collisionArray.add(bottomCollider2);
        collisionArray.add(bottomCollider3);
        //collisionArray.add(door1);

        collisionArray.add(bottom);
        collisionArray.add(bottomLeft);
        collisionArray.add(bottomRight);




        doors.add(door1);


    }


    public void draw(Graphics g) {
        super.draw(g);
        map.draw(g);
        startTile.draw(g);
        door1.draw(g);
        bottomCollider.draw(g);
        bottomCollider2.draw(g);
        bottomCollider3.draw(g);



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
