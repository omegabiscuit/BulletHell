package game;

import engine.display.DisplayObject;
import engine.display.Game;
import engine.display.Sprite;



import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Brigadoon on 4/17/2017.
 */


public class Level0 {

    public ArrayList<Platformer> collisionArray = new ArrayList<>();

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

    Platformer collider1;
    Platformer collider2;
    Platformer collider3;
    Platformer collider4;
    Platformer collider5;
    Platformer collider6;

    public void run() {
        //map stuff
        tile1 = new Sprite("tile1", "tile.png");
        tile1.setPositionX(256);
        tile1.setPositionY(300);

        tile2 = new Sprite("tile2", "tile.png");
        tile2.setPositionX(tile1.getPositionX());
        tile2.setPositionY(tile1.getPositionY() + tile1.getUnscaledHeight());

        tile3 = new Sprite("tile3", "tile.png");
        tile3.setPositionX(tile1.getPositionX());
        tile3.setPositionY(tile2.getPositionY() + tile2.getUnscaledHeight());

        tile4 = new Sprite("tile4", "dark_brick.png");
        tile4.setPositionX(tile1.getPositionX());
        tile4.setPositionY(tile3.getPositionY() + tile3.getUnscaledHeight());

        collider4 = new Platformer("collider", "alpha_3x1.png");
        collider4.setPositionX(tile4.getPositionX() - tile4.getUnscaledWidth());
        collider4.setPositionY(tile4.getPositionY());

        tile5 = new Sprite("tile5", "tile.png");
        tile5.setPositionX(tile1.getPositionX() + tile1.getUnscaledWidth());
        tile5.setPositionY(tile1.getPositionY());

        tile6 = new Sprite("tile6", "tile.png");
        tile6.setPositionX(tile2.getPositionX() + tile2.getUnscaledWidth());
        tile6.setPositionY(tile2.getPositionY());


        tile7 = new Sprite("tile7", "tile.png");
        tile7.setPositionX(tile3.getPositionX() + tile3.getUnscaledWidth());
        tile7.setPositionY(tile3.getPositionY());

        tile8 = new Sprite("tile8", "dark_brick.png");
        tile8.setPositionX(tile4.getPositionX() + tile4.getUnscaledWidth());
        tile8.setPositionY(tile4.getPositionY());

        tile9 = new Sprite("tile9", "tile.png");
        tile9.setPositionX(tile5.getPositionX() + tile5.getUnscaledWidth());
        tile9.setPositionY(tile5.getPositionY());

        tile10 = new Sprite("tile10", "tile.png");
        tile10.setPositionX(tile6.getPositionX() + tile6.getUnscaledWidth());
        tile10.setPositionY(tile6.getPositionY());

        tile11 = new Sprite("tile11", "tile.png");
        tile11.setPositionX(tile7.getPositionX() + tile7.getUnscaledWidth());
        tile11.setPositionY(tile7.getPositionY());

        tile12 = new Sprite("tile12", "tile_dark.png");
        tile12.setPositionX(tile8.getPositionX() + tile8.getUnscaledWidth());
        tile12.setPositionY(tile8.getPositionY());

        collider6 = new Platformer("collider", "alpha_3x1.png");
        collider6.setPositionX(tile12.getPositionX() - tile12.getUnscaledWidth());
        collider6.setPositionY(tile12.getPositionY() + tile12.getUnscaledHeight());

        tile13 = new Sprite("tile13", "tile.png");
        tile13.setPositionX(tile9.getPositionX() + tile9.getUnscaledWidth());
        tile13.setPositionY(tile9.getPositionY());

        tile14 = new Sprite("tile14", "tile.png");
        tile14.setPositionX(tile10.getPositionX() + tile10.getUnscaledWidth());
        tile14.setPositionY(tile10.getPositionY());

        tile15 = new Sprite("tile15", "tile.png");
        tile15.setPositionX(tile11.getPositionX() + tile11.getUnscaledWidth());
        tile15.setPositionY(tile11.getPositionY());

        tile16 = new Sprite("tile16", "dark_brick.png");
        tile16.setPositionX(tile12.getPositionX() + tile12.getUnscaledWidth());
        tile16.setPositionY(tile12.getPositionY());

        collider5 = new Platformer("collider", "alpha_1x1.png");
        collider5.setPositionX(tile16.getPositionX());
        collider5.setPositionY(tile16.getPositionY());


        ctile1 = new Sprite("ctile1", "tile_with_column.png");
        ctile1.setPositionX(tile1.getPositionX());
        ctile1.setPositionY(tile1.getPositionY() - tile1.getUnscaledHeight());


        ctile2 = new Sprite("ctile2", "tile_with_column.png");
        ctile2.setPositionX(tile5.getPositionX());
        ctile2.setPositionY(tile5.getPositionY() - tile5.getUnscaledHeight());

        ctile3 = new Sprite("ctile3", "tile.png");
        ctile3.setPositionX(tile9.getPositionX());
        ctile3.setPositionY(tile9.getPositionY() - tile9.getUnscaledHeight());

        ctile4 = new Sprite("ctile4", "tile_with_column.png");
        ctile4.setPositionX(tile13.getPositionX());
        ctile4.setPositionY(tile13.getPositionY() - tile13.getUnscaledHeight());

        back1 = new Sprite("back", "pillar_door_left.png");
        back1.setPositionX(ctile1.getPositionX());
        back1.setPositionY(ctile1.getPositionY() - ctile1.getUnscaledHeight());

        collider1 = new Platformer("collider", "alpha_6x1.png");
        collider1.setPositionX(back1.getPositionX() - back1.getUnscaledWidth());
        collider1.setPositionY(back1.getPositionY());

        back2 = new Sprite("back", "pillar_door_left.png");
        back2.setPositionX(ctile2.getPositionX());
        back2.setPositionY(ctile2.getPositionY() - ctile2.getUnscaledHeight());

        back3 = new Sprite("back", "door_locked.png");
        back3.setPositionX(ctile3.getPositionX());
        back3.setPositionY(ctile3.getPositionY() - ctile3.getUnscaledHeight());

        back4 = new Sprite("back", "pillar_door_right.png");
        back4.setPositionX(ctile4.getPositionX());
        back4.setPositionY(ctile4.getPositionY() - ctile4.getUnscaledHeight());

        toptile1 = new Sprite("tt", "top_tile_1.png");
        toptile1.setPositionX(back1.getPositionX());
        toptile1.setPositionY(back1.getPositionY());
        toptile1.setRotation(-3.14 / 2);

        toptile2 = new Sprite("tt", "top_tile_2.png");
        toptile2.setPositionX(back2.getPositionX());
        toptile2.setPositionY(back2.getPositionY());
        toptile2.setRotation(-3.14 / 2);

        toptile3 = new Sprite("tt", "top_tile_1.png");
        toptile3.setPositionX(back3.getPositionX());
        toptile3.setPositionY(back3.getPositionY());
        toptile3.setRotation(-3.14 / 2);

        toptile4 = new Sprite("tt", "top_tile_3.png");
        toptile4.setPositionX(back4.getPositionX());
        toptile4.setPositionY(back4.getPositionY());
        toptile4.setRotation(-3.14 / 2);

        toptile5 = new Sprite("tt", "top_tile_3.png");
        toptile5.setPositionX(back4.getPositionX() + back4.getUnscaledWidth());
        toptile5.setPositionY(back4.getPositionY());

        toptile6 = new Sprite("tt", "top_tile_1.png");
        toptile6.setPositionX(toptile5.getPositionX());
        toptile6.setPositionY(toptile5.getPositionY() + toptile5.getUnscaledHeight());

        toptile7 = new Sprite("tt", "top_tile_4.png");
        toptile7.setPositionX(toptile6.getPositionX());
        toptile7.setPositionY(toptile6.getPositionY() + toptile6.getUnscaledHeight());

        toptile8 = new Sprite("tt", "top_tile_1.png");
        toptile8.setPositionX(toptile7.getPositionX());
        toptile8.setPositionY(toptile7.getPositionY() + toptile7.getUnscaledHeight());

        toptile9 = new Sprite("tt", "brick_wall_thin.png");
        toptile9.setPositionX(toptile8.getPositionX() + toptile9.getUnscaledWidth());
        toptile9.setPositionY(toptile8.getPositionY() + toptile8.getUnscaledHeight());
        toptile9.setScaleX(-1);

        toptile10 = new Sprite("tt", "top_tile_1.png");
        toptile10.setPositionX(back1.getPositionX());
        toptile10.setPositionY(back1.getPositionY());
        toptile10.setScaleX(-1);

        collider2 = new Platformer("collider", "alpha_1x4.png");
        collider2.setPositionX(back1.getPositionX() - back1.getUnscaledWidth());
        collider2.setPositionY(back1.getPositionY() + back1.getUnscaledHeight());

        collider3 = new Platformer("collider", "alpha_1x4.png");
        collider3.setPositionX(back4.getPositionX() + back4.getUnscaledWidth());
        collider3.setPositionY(back4.getPositionY() + back4.getUnscaledHeight());


        toptile11 = new Sprite("tt", "top_tile_3.png");
        toptile11.setPositionX(toptile10.getPositionX());
        toptile11.setPositionY(toptile10.getPositionY() + toptile10.getUnscaledHeight());
        toptile11.setScaleX(-1);

        toptile12 = new Sprite("tt", "top_tile_2.png");
        toptile12.setPositionX(toptile11.getPositionX());
        toptile12.setPositionY(toptile11.getPositionY() + toptile11.getUnscaledHeight());
        toptile12.setScaleX(-1);

        toptile13 = new Sprite("tt", "top_tile_1.png");
        toptile13.setPositionX(toptile12.getPositionX());
        toptile13.setPositionY(toptile12.getPositionY() + toptile12.getUnscaledHeight());
        toptile13.setScaleX(-1);

        toptile14 = new Sprite("tt", "brick_wall_thin.png");
        toptile14.setPositionX(toptile13.getPositionX() - toptile14.getUnscaledWidth());
        toptile14.setPositionY(toptile13.getPositionY() + toptile13.getUnscaledHeight());

        dbthin1 = new Sprite("tt", "dark_brick_thin.png");
        dbthin1.setPositionX(toptile14.getPositionX());
        dbthin1.setPositionY(toptile14.getPositionY() + toptile14.getUnscaledHeight());

        dbthin2 = new Sprite("tt", "dark_brick_thin.png");
        dbthin2.setPositionX(toptile9.getPositionX() - dbthin2.getUnscaledWidth());
        dbthin2.setPositionY(toptile9.getPositionY() + toptile9.getUnscaledHeight());


        corner1 = new Sprite("corner", "top_corner_right.png");
        corner1.setPositionX(toptile4.getPositionX() + toptile4.getUnscaledHeight());
        corner1.setPositionY(toptile4.getPositionY() - toptile4.getUnscaledWidth());

        corner2 = new Sprite("corner", "top_corner_right.png");
        corner2.setPositionX(toptile1.getPositionX());
        corner2.setPositionY(toptile1.getPositionY() - toptile1.getUnscaledWidth());
        corner2.setScaleX(-1);

        collisionArray.add(collider1);
        collisionArray.add(collider2);
        collisionArray.add(collider3);
        collisionArray.add(collider4);
        collisionArray.add(collider5);
        collisionArray.add(collider6);
    }

    public void draw(Graphics g) {
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
        back3.draw(g);
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
        back3.draw(g);
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
    }
}
