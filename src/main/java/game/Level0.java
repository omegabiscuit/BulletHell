package game;

import engine.display.DisplayObject;
import engine.display.Game;
import engine.display.Sprite;
import engine.display.AnimatedSprite;



import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by Brigadoon on 4/17/2017.
 */


public class Level0 extends Room {


    SoundManagerClass music = new SoundManagerClass();

    private Enemy enemy01;
    private Enemy enemy02;

    Sprite tile1;
    Sprite tile2;
    Sprite tile3;
    Sprite tile4;
    Sprite tile5;
    Sprite tile6;
    Sprite tile7;
    Sprite tile8;
    Sprite tile9;
    Sprite tile10;
    Sprite tile11;
    Sprite tile12;
    Sprite tile13;
    Sprite tile14;
    Sprite tile15;
    Sprite tile16;

    Sprite ctile1;
    Sprite ctile2;
    Sprite ctile3;
    Sprite ctile4;

    Sprite back1;
    Sprite back2;
    Sprite back3;
    Sprite back4;

    Sprite toptile1;
    Sprite toptile2;
    Sprite toptile3;
    Sprite toptile4;

    Sprite corner1;
    Sprite corner2;

    Sprite toptile5;
    Sprite toptile6;
    Sprite toptile7;
    Sprite toptile8;
    Sprite toptile9;
    Sprite toptile10;
    Sprite toptile11;
    Sprite toptile12;
    Sprite toptile13;
    Sprite toptile14;

    Sprite dbthin1;
    Sprite dbthin2;

    Platform crate;
    Sprite coverBottom1;
    Sprite coverShadow1;

    Platform collider1;
    Platform collider2;
    Platform collider3;
    Platform collider4;
    Platform collider5;
    Platform collider6;
    Platform collider7;
    Platform collider8;

    LockedDoor door1;

  // TurtleBoss tb;



    public Level0(String id) {
        super(id);
    }

    public void run() {

        enemy01 = new Enemy("enemy", "resources/gator_sheet.png", "idle left");
        enemy01.setSpriteSheetJson("resources/gator_sheet.json");
        enemy01.setDelay(75);
        enemy01.setPositionX(250);
        enemy01.setPositionY(550);
        enemy01.addRoute(0, 800, 2, 1);
        enemy01.addRoute(400, 0, 2, 2);
        enemy01.addRoute(0, -800, 2, 3);
        enemy01.addRoute(-400, 0, 4, 4);
        enemy01.addKey();


        enemy02 = new Enemy("enemy", "resources/gator_sheet.png", "idle left");
        enemy02.setSpriteSheetJson("resources/gator_sheet.json");
        enemy02.setDelay(75);
        enemy02.setPositionX(700);
        enemy02.setPositionY(150);
        enemy02.addRoute(0, -800, 2, 3);
        enemy02.addRoute(-400, 0, 2, 4);
        enemy02.addRoute(0, 800, 2, 1);
        enemy02.addRoute(400, 0, 2, 2);
        enemy02.addKnife();

        enemies = new ArrayList<>();
        enemies.add(enemy01);
        enemies.add(enemy02);


        //map stuff
        tile1 = new Sprite("tile1", "tile.png");
        tile1.setPositionX(256);
        tile1.setPositionY(300);
        addChild(tile1);

        tile2 = new Sprite("tile2", "tile.png");
        tile2.setPositionX(tile1.getPositionX());
        tile2.setPositionY(tile1.getPositionY() + tile1.getUnscaledHeight());
        addChild(tile2);

        tile3 = new Sprite("tile3", "tile.png");
        tile3.setPositionX(tile1.getPositionX());
        tile3.setPositionY(tile2.getPositionY() + tile2.getUnscaledHeight());
        addChild(tile3);

        tile4 = new Sprite("tile4", "dark_brick.png");
        tile4.setPositionX(tile1.getPositionX());
        tile4.setPositionY(tile3.getPositionY() + tile3.getUnscaledHeight());
        addChild(tile4);

        collider4 = new Platform("collider", "alpha_3x1.png");
        collider4.setPositionX(tile4.getPositionX() - tile4.getUnscaledWidth());
        collider4.setPositionY(tile4.getPositionY());
        addChild(collider4);

        tile5 = new Sprite("tile5", "tile.png");
        tile5.setPositionX(tile1.getPositionX() + tile1.getUnscaledWidth());
        tile5.setPositionY(tile1.getPositionY());
        addChild(tile5);

        tile6 = new Sprite("tile6", "tile.png");
        tile6.setPositionX(tile2.getPositionX() + tile2.getUnscaledWidth());
        tile6.setPositionY(tile2.getPositionY());
        addChild(tile6);


        tile7 = new Sprite("tile7", "tile.png");
        tile7.setPositionX(tile3.getPositionX() + tile3.getUnscaledWidth());
        tile7.setPositionY(tile3.getPositionY());
        addChild(tile7);

        tile8 = new Sprite("tile8", "dark_brick.png");
        tile8.setPositionX(tile4.getPositionX() + tile4.getUnscaledWidth());
        tile8.setPositionY(tile4.getPositionY());
        addChild(tile8);

        tile9 = new Sprite("tile9", "tile.png");
        tile9.setPositionX(tile5.getPositionX() + tile5.getUnscaledWidth());
        tile9.setPositionY(tile5.getPositionY());
        addChild(tile9);

        tile10 = new Sprite("tile10", "tile.png");
        tile10.setPositionX(tile6.getPositionX() + tile6.getUnscaledWidth());
        tile10.setPositionY(tile6.getPositionY());
        addChild(tile10);

        tile11 = new Sprite("tile11", "tile.png");
        tile11.setPositionX(tile7.getPositionX() + tile7.getUnscaledWidth());
        tile11.setPositionY(tile7.getPositionY());
        addChild(tile11);

        tile12 = new Sprite("tile12", "tile_dark.png");
        tile12.setPositionX(tile8.getPositionX() + tile8.getUnscaledWidth());
        tile12.setPositionY(tile8.getPositionY());
        addChild(tile12);

        collider6 = new Platform("collider", "alpha_3x1.png");
        collider6.setPositionX(tile12.getPositionX() - tile12.getUnscaledWidth());
        collider6.setPositionY(tile12.getPositionY() + tile12.getUnscaledHeight());
        addChild(collider6);

        tile13 = new Sprite("tile13", "tile.png");
        tile13.setPositionX(tile9.getPositionX() + tile9.getUnscaledWidth());
        tile13.setPositionY(tile9.getPositionY());
        addChild(tile13);

        tile14 = new Sprite("tile14", "tile.png");
        tile14.setPositionX(tile10.getPositionX() + tile10.getUnscaledWidth());
        tile14.setPositionY(tile10.getPositionY());
        addChild(tile14);

        tile15 = new Sprite("tile15", "tile.png");
        tile15.setPositionX(tile11.getPositionX() + tile11.getUnscaledWidth());
        tile15.setPositionY(tile11.getPositionY());
        addChild(tile15);

        tile16 = new Sprite("tile16", "dark_brick.png");
        tile16.setPositionX(tile12.getPositionX() + tile12.getUnscaledWidth());
        tile16.setPositionY(tile12.getPositionY());
        addChild(tile16);

        collider5 = new Platform("collider", "alpha_1x1.png");
        collider5.setPositionX(tile16.getPositionX());
        collider5.setPositionY(tile16.getPositionY());
        addChild(collider5);


        ctile1 = new Sprite("ctile1", "tile_with_column.png");
        ctile1.setPositionX(tile1.getPositionX());
        ctile1.setPositionY(tile1.getPositionY() - tile1.getUnscaledHeight());
        addChild(ctile1);

        ctile2 = new Sprite("ctile2", "tile_with_column.png");
        ctile2.setPositionX(tile5.getPositionX());
        ctile2.setPositionY(tile5.getPositionY() - tile5.getUnscaledHeight());
        addChild(ctile2);

        ctile3 = new Sprite("ctile3", "tile.png");
        ctile3.setPositionX(tile9.getPositionX());
        ctile3.setPositionY(tile9.getPositionY() - tile9.getUnscaledHeight());
        addChild(ctile3);

        ctile4 = new Sprite("ctile4", "tile_with_column.png");
        ctile4.setPositionX(tile13.getPositionX());
        ctile4.setPositionY(tile13.getPositionY() - tile13.getUnscaledHeight());
        addChild(ctile4);

        back1 = new Sprite("back", "pillar_door_left.png");
        back1.setPositionX(ctile1.getPositionX());
        back1.setPositionY(ctile1.getPositionY() - ctile1.getUnscaledHeight());
        addChild(back1);

        collider1 = new Platform("collider", "alpha_3x1.png");
        collider1.setPositionX(back1.getPositionX() - back1.getUnscaledWidth());
        collider1.setPositionY(back1.getPositionY());
        addChild(collider1);

        back2 = new Sprite("back", "pillar_door_left.png");
        back2.setPositionX(ctile2.getPositionX());
        back2.setPositionY(ctile2.getPositionY() - ctile2.getUnscaledHeight());
        addChild(back2);

      //  back3 = new Sprite("back", "door_locked.png");
       // back3.setPositionX(ctile3.getPositionX());
       // back3.setPositionY(ctile3.getPositionY() - ctile3.getUnscaledHeight());

        door1 = new LockedDoor("door", "resources/door_opening.png", "door_closed");
        door1.setSpriteSheetJson("resources/door_opening.json");
        door1.setDelay(100);
        door1.setPositionX(ctile3.getPositionX());
        door1.setPositionY(ctile3.getPositionY() - ctile3.getUnscaledHeight());
        addChild(door1);


        collider8 = new Platform("collider", "alpha_3x1.png");
        collider8.setPositionX(door1.getPositionX() - tile1.getUnscaledWidth());
        collider8.setPositionY(door1.getPositionY() - 3*tile1.getUnscaledHeight()/4);
        addChild(collider8);

        back4 = new Sprite("back", "pillar_door_right.png");
        back4.setPositionX(ctile4.getPositionX());
        back4.setPositionY(ctile4.getPositionY() - ctile4.getUnscaledHeight());
        addChild(back4);

        collider7 = new Platform("collider", "alpha_2x1.png");
        collider7.setPositionX(back4.getPositionX());
        collider7.setPositionY(back4.getPositionY());
        addChild(collider7);

        toptile1 = new Sprite("tt", "top_tile_1.png");
        toptile1.setPositionX(back1.getPositionX());
        toptile1.setPositionY(back1.getPositionY());
        toptile1.setRotation(-3.14 / 2);
        addChild(toptile1);

        toptile2 = new Sprite("tt", "top_tile_2.png");
        toptile2.setPositionX(back2.getPositionX());
        toptile2.setPositionY(back2.getPositionY());
        toptile2.setRotation(-3.14 / 2);
        addChild(toptile2);

        toptile3 = new Sprite("tt", "top_tile_1.png");
        toptile3.setPositionX(door1.getPositionX());
        toptile3.setPositionY(door1.getPositionY());
        toptile3.setRotation(-3.14 / 2);
        addChild(toptile3);

        toptile4 = new Sprite("tt", "top_tile_3.png");
        toptile4.setPositionX(back4.getPositionX());
        toptile4.setPositionY(back4.getPositionY());
        toptile4.setRotation(-3.14 / 2);
        addChild(toptile4);

        toptile5 = new Sprite("tt", "top_tile_3.png");
        toptile5.setPositionX(back4.getPositionX() + back4.getUnscaledWidth());
        toptile5.setPositionY(back4.getPositionY());
        addChild(toptile5);

        toptile6 = new Sprite("tt", "top_tile_1.png");
        toptile6.setPositionX(toptile5.getPositionX());
        toptile6.setPositionY(toptile5.getPositionY() + toptile5.getUnscaledHeight());
        addChild(toptile6);

        toptile7 = new Sprite("tt", "top_tile_4.png");
        toptile7.setPositionX(toptile6.getPositionX());
        toptile7.setPositionY(toptile6.getPositionY() + toptile6.getUnscaledHeight());
        addChild(toptile7);

        toptile8 = new Sprite("tt", "top_tile_1.png");
        toptile8.setPositionX(toptile7.getPositionX());
        toptile8.setPositionY(toptile7.getPositionY() + toptile7.getUnscaledHeight());
        addChild(toptile8);

        toptile9 = new Sprite("tt", "brick_wall_thin.png");
        toptile9.setPositionX(toptile8.getPositionX() + toptile9.getUnscaledWidth());
        toptile9.setPositionY(toptile8.getPositionY() + toptile8.getUnscaledHeight());
        toptile9.setScaleX(-1);
        addChild(toptile9);

        toptile10 = new Sprite("tt", "top_tile_1.png");
        toptile10.setPositionX(back1.getPositionX());
        toptile10.setPositionY(back1.getPositionY());
        toptile10.setScaleX(-1);
        addChild(toptile10);

        collider2 = new Platform("collider", "alpha_1x4.png");
        collider2.setPositionX(back1.getPositionX() - back1.getUnscaledWidth());
        collider2.setPositionY(back1.getPositionY() + back1.getUnscaledHeight());
        addChild(collider2);

        collider3 = new Platform("collider", "alpha_1x4.png");
        collider3.setPositionX(back4.getPositionX() + back4.getUnscaledWidth());
        collider3.setPositionY(back4.getPositionY() + back4.getUnscaledHeight());
        addChild(collider3);

        toptile11 = new Sprite("tt", "top_tile_3.png");
        toptile11.setPositionX(toptile10.getPositionX());
        toptile11.setPositionY(toptile10.getPositionY() + toptile10.getUnscaledHeight());
        toptile11.setScaleX(-1);
        addChild(toptile11);

        toptile12 = new Sprite("tt", "top_tile_2.png");
        toptile12.setPositionX(toptile11.getPositionX());
        toptile12.setPositionY(toptile11.getPositionY() + toptile11.getUnscaledHeight());
        toptile12.setScaleX(-1);
        addChild(toptile12);

        toptile13 = new Sprite("tt", "top_tile_1.png");
        toptile13.setPositionX(toptile12.getPositionX());
        toptile13.setPositionY(toptile12.getPositionY() + toptile12.getUnscaledHeight());
        toptile13.setScaleX(-1);
        addChild(toptile13);

        toptile14 = new Sprite("tt", "brick_wall_thin.png");
        toptile14.setPositionX(toptile13.getPositionX() - toptile14.getUnscaledWidth());
        toptile14.setPositionY(toptile13.getPositionY() + toptile13.getUnscaledHeight());
        addChild(toptile14);

        dbthin1 = new Sprite("tt", "dark_brick_thin.png");
        dbthin1.setPositionX(toptile14.getPositionX());
        dbthin1.setPositionY(toptile14.getPositionY() + toptile14.getUnscaledHeight());
        addChild(dbthin1);

        dbthin2 = new Sprite("tt", "dark_brick_thin.png");
        dbthin2.setPositionX(toptile9.getPositionX() - dbthin2.getUnscaledWidth());
        dbthin2.setPositionY(toptile9.getPositionY() + toptile9.getUnscaledHeight());
        addChild(dbthin2);

        corner1 = new Sprite("corner", "top_corner_right.png");
        corner1.setPositionX(toptile4.getPositionX() + toptile4.getUnscaledHeight());
        corner1.setPositionY(toptile4.getPositionY() - toptile4.getUnscaledWidth());
        addChild(corner1);

        corner2 = new Sprite("corner", "top_corner_right.png");
        corner2.setPositionX(toptile1.getPositionX());
        corner2.setPositionY(toptile1.getPositionY() - toptile1.getUnscaledWidth());
        corner2.setScaleX(-1);
        addChild(corner2);

        crate = new Platform("cover", "cover_top_only_horizontal.png");
        crate.setPositionX(tile9.getPositionX());
        crate.setPositionY(tile9.getPositionY() + 64);
        coverList = new ArrayList<>(); //list of cover sprites
        coverList.add(convertToCover(crate));
        addChild(crate);
        coverBottom1 = new Sprite("corner", "cover_bottom.png");
        coverBottom1.setPositionX(crate.getPositionX());
        coverBottom1.setPositionY(crate.getPositionY() + crate.getUnscaledHeight());
        addChild(coverBottom1);
        coverShadow1 = new Sprite("corner", "cover_shadow.png");
        coverShadow1.setPositionX(crate.getPositionX() - 16);
        coverShadow1.setPositionY(crate.getPositionY());
        addChild(coverShadow1);


//        tb = new TurtleBoss("tb", "resources/turtle_boss.png", "idle");
//        tb.setSpriteSheetJson("resources/turtle_boss.json");
//        tb.setPositionX(tile11.getPositionX() );
//        tb.setPositionY(tile11.getPositionY());
//        addChild(tb);

        collisionArray.add(collider1);
        collisionArray.add(collider2);
        collisionArray.add(collider3);
        collisionArray.add(collider4);
        collisionArray.add(collider5);
        collisionArray.add(collider6);
        collisionArray.add(collider7);
        collisionArray.add(collider8);
        collisionArray.add(crate);

        doors.add(door1);


    }

    public void draw(Graphics g) {
        super.draw(g);
        tile1.draw(g);
        tile2.draw(g);
        tile3.draw(g);
        tile4.draw(g);
        tile5.draw(g);
        tile6.draw(g);
        tile7.draw(g);
        tile8.draw(g);
        tile9.draw(g);
        tile10.draw(g);
        tile11.draw(g);
        tile12.draw(g);
        tile13.draw(g);
        tile14.draw(g);
        tile15.draw(g);
        tile16.draw(g);
        ctile1.draw(g);
        ctile2.draw(g);
        ctile3.draw(g);
        ctile4.draw(g);
        back1.draw(g);
        back2.draw(g);
      //  back3.draw(g);
        back4.draw(g);


        tile1.draw(g);
        tile2.draw(g);
        tile3.draw(g);
        tile4.draw(g);
        tile5.draw(g);
        tile6.draw(g);
        tile7.draw(g);
        tile8.draw(g);
        tile9.draw(g);
        tile10.draw(g);
        tile11.draw(g);
        tile12.draw(g);
        tile13.draw(g);
        tile14.draw(g);
        tile15.draw(g);
        tile16.draw(g);
        ctile1.draw(g);
        ctile2.draw(g);
        ctile3.draw(g);
        ctile4.draw(g);
        back1.draw(g);
        back2.draw(g);
        //back3.draw(g);
        back4.draw(g);
        toptile1.draw(g);
        toptile2.draw(g);
        toptile3.draw(g);
        toptile4.draw(g);
        toptile5.draw(g);
        toptile6.draw(g);
        toptile7.draw(g);
        toptile8.draw(g);
        toptile9.draw(g);
        toptile10.draw(g);
        toptile11.draw(g);
        toptile12.draw(g);
        toptile13.draw(g);
        toptile14.draw(g);
        dbthin1.draw(g);
        dbthin2.draw(g);
        corner1.draw(g);
        corner2.draw(g);
        collider1.draw(g);
        collider2.draw(g);
        collider3.draw(g);
        collider4.draw(g);
        collider5.draw(g);
        collider6.draw(g);
        collider7.draw(g);
        collider8.draw(g);

       // tb.draw(g);

        coverShadow1.draw(g);

        door1.draw(g);

        crate.draw(g);
        coverBottom1.draw(g);
    }

    public void update() {
        super.update();
        door1.update();
      //  tb.update();
    }

    public ArrayList<Enemy> getEnemies(){
        return this.enemies;
    }
}
