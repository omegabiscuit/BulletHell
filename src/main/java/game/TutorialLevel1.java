package game;

import engine.display.Sprite;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Brigham on 4/27/2017.
 */
public class TutorialLevel1 extends Room {


    Sprite tile1;
    Sprite tile2;
    Sprite tile3;

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

    Sprite corner1;
    Sprite corner2;


    Sprite wall1;
    Sprite wall2;
    Sprite wall3;
    Sprite wall4;
    Sprite wall5;
    Sprite wall6;
    Sprite wall7;
    Sprite wall8;
    Sprite wall9;
    Sprite wall10;
    Sprite wall11;
    Sprite wall12;
    Sprite wall13;
    Sprite wall14;
    Sprite wall15;

    Sprite back1;
    Sprite back2;
    Sprite back3;

    Platform collider1;
    Platform collider2;
    Platform collider3;
    Platform collider4;
    Platform collider5;
    Platform collider6;
    Platform collider7;
    Platform collider8;

    Platform cover1;
    Platform cover2;

    Platform doorCollider;

    Sprite coverBottom1;
    Sprite coverBottom2;

    Sprite coverShadow1;
    Sprite coverShadow2;

    LockedDoor door1;

    SpikeTile spikes;

    Enemy enemy;

    Sprite instructions;


    public TutorialLevel1(String id) {
        super(id);
    }

    public void run() {

        instructions = new Sprite("instructions", "instructions.png");
        instructions.setPositionY(300);
        addChild(instructions);
        //map stuff


        tile1 = new Sprite("tile", "tile_dark.png");
        tile1.setPositionX(540);
        tile1.setPositionY(690);
        addChild(tile1);

        tile2 = new Sprite("tile", "dark_brick.png");
        tile2.setPositionX(tile1.getPositionX() - tile1.getUnscaledWidth());
        tile2.setPositionY(tile1.getPositionY());
        addChild(tile2);

        collider5 = new Platform("collider", "alpha_2x1.png");
        collider5.setPositionX(tile2.getPositionX() - tile2.getUnscaledWidth());
        collider5.setPositionY(tile2.getPositionY());
        addChild(collider5);

        tile3 = new Sprite("tile", "dark_brick.png");
        tile3.setPositionX(tile1.getPositionX() + tile1.getUnscaledWidth());
        tile3.setPositionY(tile1.getPositionY());
        addChild(tile3);

        collider6 = new Platform("collider", "alpha_2x1.png");
        collider6.setPositionX(tile3.getPositionX());
        collider6.setPositionY(tile3.getPositionY());
        addChild(collider6);

        wall1 = new Sprite("tile", "dark_brick_thin.png");
        wall1.setPositionX(tile3.getPositionX() + tile3.getUnscaledWidth() - 4);
        wall1.setPositionY(tile3.getPositionY());
        addChild(wall1);

        wall2 = new Sprite("tile", "brick_wall_thin.png");
        wall2.setPositionX(wall1.getPositionX() + wall1.getUnscaledWidth());
        wall2.setPositionY(wall1.getPositionY() - wall1.getUnscaledHeight());
        wall2.setScaleX(-1);
        addChild(wall2);

        wall3 = new Sprite("tile", "top_tile_1.png");
        wall3.setPositionX(wall2.getPositionX() - wall1.getUnscaledWidth());
        wall3.setPositionY(wall2.getPositionY() - wall2.getUnscaledHeight());
        addChild(wall3);

        wall4 = new Sprite("tile", "top_tile_1.png");
        wall4.setPositionX(wall3.getPositionX());
        wall4.setPositionY(wall3.getPositionY() - wall3.getUnscaledHeight());
        addChild(wall4);

        wall5 = new Sprite("tile", "top_tile_1.png");
        wall5.setPositionX(wall4.getPositionX());
        wall5.setPositionY(wall4.getPositionY() - wall4.getUnscaledHeight());
        addChild(wall5);

        wall6 = new Sprite("tile", "top_tile_1.png");
        wall6.setPositionX(wall5.getPositionX());
        wall6.setPositionY(wall5.getPositionY() - wall5.getUnscaledHeight());
        addChild(wall6);

        wall7 = new Sprite("tile", "dark_brick_thin.png");
        wall7.setPositionX(tile2.getPositionX() - wall7.getUnscaledWidth() + 4);
        wall7.setPositionY(tile2.getPositionY());
        addChild(wall7);

        wall8 = new Sprite("tile", "brick_wall_thin.png");
        wall8.setPositionX(wall7.getPositionX());
        wall8.setPositionY(wall7.getPositionY() - wall7.getUnscaledHeight());
        addChild(wall8);

        wall9 = new Sprite("tile", "top_tile_1.png");
        wall9.setPositionX(wall8.getPositionX());
        wall9.setPositionY(wall8.getPositionY() - wall8.getUnscaledHeight());
        addChild(wall9);

        wall10 = new Sprite("tile", "top_tile_1.png");
        wall10.setPositionX(wall9.getPositionX());
        wall10.setPositionY(wall9.getPositionY() - wall9.getUnscaledHeight());
        addChild(wall10);

        wall11 = new Sprite("tile", "top_tile_1.png");
        wall11.setPositionX(wall10.getPositionX());
        wall11.setPositionY(wall10.getPositionY() - wall10.getUnscaledHeight());
        addChild(wall11);

        wall12 = new Sprite("tile", "top_tile_1.png");
        wall12.setPositionX(wall11.getPositionX());
        wall12.setPositionY(wall11.getPositionY() - wall11.getUnscaledHeight());
        addChild(wall12);

        tile5 = new Sprite("tile", "tile.png");
        tile5.setPositionX(tile1.getPositionX());
        tile5.setPositionY(tile1.getPositionY() - tile1.getUnscaledHeight() * 1);
        addChild(tile5);

        tile6 = new Sprite("tile", "tile.png");
        tile6.setPositionX(tile1.getPositionX());
        tile6.setPositionY(tile1.getPositionY() - tile1.getUnscaledHeight() * 2);
        addChild(tile6);

        tile7 = new Sprite("tile", "tile.png");
        tile7.setPositionX(tile1.getPositionX());
        tile7.setPositionY(tile1.getPositionY() - tile1.getUnscaledHeight() * 3);
        addChild(tile7);

        tile8 = new Sprite("tile", "tile.png");
        tile8.setPositionX(tile1.getPositionX());
        tile8.setPositionY(tile1.getPositionY() - tile1.getUnscaledHeight() * 4);
        addChild(tile8);

        back1 = new Sprite("tile", "door_locked.png");
        back1.setPositionX(tile1.getPositionX());
        back1.setPositionY(tile1.getPositionY() - tile1.getUnscaledHeight() * 5);
        addChild(back1);

        wall13 = new Sprite("tile", "top_tile_horizontal.png");
        wall13.setPositionX(back1.getPositionX());
        wall13.setPositionY(back1.getPositionY() - wall13.getUnscaledHeight());
        addChild(wall13);

        tile9 = new Sprite("tile", "tile.png");
        tile9.setPositionX(tile2.getPositionX() + 4);
        tile9.setPositionY(tile2.getPositionY() - tile2.getUnscaledHeight() * 1);
        addChild(tile9);

        tile10 = new Sprite("tile", "tile.png");
        tile10.setPositionX(tile2.getPositionX() + 4);
        tile10.setPositionY(tile2.getPositionY() - tile2.getUnscaledHeight() * 2);
        addChild(tile10);

        cover1 = new Platform("tile", "cover_top_only_horizontal.png");
        cover1.setPositionX(tile10.getPositionX());
        cover1.setPositionY(tile10.getPositionY());
        coverList = new ArrayList<>(); //list of cover sprites
        coverList.add(convertToCover(cover1));
        addChild(cover1);
        coverBottom1 = new Sprite("corner", "cover_bottom.png");
        coverBottom1.setPositionX(cover1.getPositionX());
        coverBottom1.setPositionY(cover1.getPositionY() + cover1.getUnscaledHeight());
        addChild(coverBottom1);
        coverShadow1 = new Sprite("corner", "cover_shadow_right_bottom.png");
        coverShadow1.setPositionX(cover1.getPositionX() - 16);
        coverShadow1.setPositionY(cover1.getPositionY());
        addChild(coverShadow1);

        tile11 = new Sprite("tile", "tile.png");
        tile11.setPositionX(tile2.getPositionX() + 4);
        tile11.setPositionY(tile2.getPositionY() - tile2.getUnscaledHeight() * 3);
        addChild(tile11);

        tile12 = new Sprite("tile", "tile_with_column.png");
        tile12.setPositionX(tile2.getPositionX() + 4);
        tile12.setPositionY(tile2.getPositionY() - tile2.getUnscaledHeight() * 4);
        addChild(tile12);

        back2 = new Sprite("tile", "pillar_door_right.png");
        back2.setPositionX(tile2.getPositionX() + 4);
        back2.setPositionY(tile2.getPositionY() - tile2.getUnscaledHeight() * 5);
        addChild(back2);

        wall14 = new Sprite("tile", "top_tile_horizontal.png");
        wall14.setPositionX(back2.getPositionX());
        wall14.setPositionY(back2.getPositionY() - wall14.getUnscaledHeight());
        addChild(wall14);

        collider4 = new Platform("collider", "alpha_1x6.png");
        collider4.setPositionX(back2.getPositionX() - back2.getUnscaledWidth());
        collider4.setPositionY(back2.getPositionY() - back2.getUnscaledHeight());
        addChild(collider4);

        collider7 = new Platform("collider", "alpha_2x1.png");
        collider7.setPositionX(back2.getPositionX() - back2.getUnscaledWidth());
        collider7.setPositionY(back2.getPositionY());
        addChild(collider7);


        tile13 = new Sprite("tile", "tile.png");
        tile13.setPositionX(tile3.getPositionX() - 4);
        tile13.setPositionY(tile3.getPositionY() - tile3.getUnscaledHeight() * 1);
        addChild(tile13);

        tile14 = new Sprite("tile", "tile.png");
        tile14.setPositionX(tile3.getPositionX() - 4);
        tile14.setPositionY(tile3.getPositionY() - tile3.getUnscaledHeight() * 2);
        addChild(tile14);

        cover2 = new Platform("tile", "cover_top_only_horizontal.png");
        cover2.setPositionX(tile14.getPositionX());
        cover2.setPositionY(tile14.getPositionY());
        coverList.add(convertToCover(cover2));
        addChild(cover2);
        coverBottom2 = new Sprite("corner", "cover_bottom.png");
        coverBottom2.setPositionX(cover2.getPositionX());
        coverBottom2.setPositionY(cover2.getPositionY() + cover2.getUnscaledHeight());
        addChild(coverBottom2);
        coverShadow2 = new Sprite("corner", "cover_shadow_left_bottom.png");
        coverShadow2.setPositionX(cover2.getPositionX() - 16);
        coverShadow2.setPositionY(cover2.getPositionY());
        addChild(coverShadow2);


        tile15 = new Sprite("tile", "tile.png");
        tile15.setPositionX(tile3.getPositionX() - 4);
        tile15.setPositionY(tile3.getPositionY() - tile3.getUnscaledHeight() * 3);
        addChild(tile15);

        tile16 = new Sprite("tile", "tile_with_column.png");
        tile16.setPositionX(tile3.getPositionX() - 4);
        tile16.setPositionY(tile3.getPositionY() - tile3.getUnscaledHeight() * 4);
        addChild(tile16);


        back3 = new Sprite("tile", "pillar_door_left.png");
        back3.setPositionX(tile3.getPositionX() - 4);
        back3.setPositionY(tile3.getPositionY() - tile3.getUnscaledHeight() * 5);
        addChild(back3);

        wall15 = new Sprite("tile", "top_tile_horizontal.png");
        wall15.setPositionX(back3.getPositionX());
        wall15.setPositionY(back3.getPositionY() - wall15.getUnscaledHeight());
        addChild(wall15);

        collider3 = new Platform("collider", "alpha_1x6.png");
        collider3.setPositionX(back3.getPositionX() + back3.getUnscaledWidth());
        collider3.setPositionY(back3.getPositionY() - back3.getUnscaledHeight());
        addChild(collider3);

        collider8 = new Platform("collider", "alpha_2x1.png");
        collider8.setPositionX(back3.getPositionX());
        collider8.setPositionY(back3.getPositionY());
        addChild(collider8);


        spikes = new SpikeTile("spikes", "resources/spikes.png", "idle down");
        spikes.setSpriteSheetJson("resources/spikes.json");
        spikes.setPositionX(tile6.getPositionX());
        spikes.setPositionY(tile6.getPositionY());
        addChild(spikes);
        spikeList.add(spikes);


        collider1 = new Platform("collider", "alpha_3x1.png");
        collider1.setPositionX(tile1.getPositionX() - tile1.getUnscaledWidth());
        collider1.setPositionY(tile1.getPositionY() + tile1.getUnscaledHeight());
        addChild(collider1);


        collisionArray.add(collider1);
        collisionArray.add(collider3);
        collisionArray.add(collider4);
        collisionArray.add(collider5);
        collisionArray.add(collider6);
        collisionArray.add(collider7);
        collisionArray.add(collider8);
        collisionArray.add(cover1);
        collisionArray.add(cover2);

        door1 = new LockedDoor("door", "resources/door_opening.png", "door_closed");
        door1.setSpriteSheetJson("resources/door_opening.json");
        door1.setDelay(100);
        door1.setPositionX(back1.getPositionX());
        door1.setPositionY(back1.getPositionY());
        addChild(door1);

        doorCollider = new Platform("collide", "alpha_6x1.png");
        doorCollider.setPositionX(door1.getPositionX());
        doorCollider.setPositionY(door1.getPositionY() - 90);
        addChild(doorCollider);

        corner1 = new Sprite("corner", "top_corner_right.png");
        corner1.setPositionX(wall6.getPositionX());
        corner1.setPositionY(wall6.getPositionY() - corner1.getUnscaledHeight());
        addChild(corner1);

        corner2 = new Sprite("corner", "top_corner_right.png");
        corner2.setPositionX(wall12.getPositionX() + corner2.getUnscaledWidth());
        corner2.setPositionY(wall12.getPositionY() - corner2.getUnscaledHeight());
        corner2.setScaleX(-1);
        addChild(corner2);

        collisionArray.add(doorCollider);

        doors.add(door1);
        removeChild(back1);

        enemy = new Enemy("enemy", "resources/gator_sheet.png", "idle left");
        enemy.setSpriteSheetJson("resources/gator_sheet.json");
        enemy.setDelay(75);
        enemy.setPositionX(450);
        enemy.setPositionY(300);
        enemy.addRoute(500, 0, 2, 2);
        enemy.addRoute(2, 0, 0, 2);
        enemy.addRoute(-500, 0, 2, 4);
        enemy.addRoute(2, 0, 0, 2);
        enemies.add(enemy);
        enemies.get(0).addKey();

    }

    public void draw(Graphics g) {
        super.draw(g);

        tile1.draw(g);
        tile2.draw(g);
        tile3.draw(g);
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


        wall1.draw(g);
        wall2.draw(g);
        wall3.draw(g);
        wall4.draw(g);
        wall5.draw(g);
        wall6.draw(g);
        wall7.draw(g);
        wall8.draw(g);
        wall9.draw(g);
        wall10.draw(g);
        wall11.draw(g);
        wall12.draw(g);
        wall13.draw(g);
        wall14.draw(g);
        wall15.draw(g);

        back1.draw(g);
        back2.draw(g);
        back3.draw(g);

        spikes.draw(g);

        coverShadow1.draw(g);
        coverShadow2.draw(g);

        coverBottom1.draw(g);
        coverBottom2.draw(g);

        cover1.draw(g);
        cover2.draw(g);

        corner1.draw(g);
        corner2.draw(g);

        collider1.draw(g);
        collider3.draw(g);
        collider4.draw(g);
        collider5.draw(g);
        collider6.draw(g);
        collider7.draw(g);
        collider8.draw(g);
        door1.draw(g);

        instructions.draw(g);



    }

    public void update() {
        super.update();
        spikes.update();

        door1.update();

    }


}


