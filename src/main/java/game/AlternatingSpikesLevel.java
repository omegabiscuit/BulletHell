package game;


import engine.display.Sprite;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by Tyler on 4/29/2017.
 */
public class AlternatingSpikesLevel extends Room{

    Sprite map;

    LockedDoor door1;

    SpikeTile spikes;
    SpikeTile spikes1;
    SpikeTile spikes2;
    SpikeTile spikes3;
    SpikeTile spikes4;
    SpikeTile spikes5;
    SpikeTile spikes6;
    SpikeTile spikes7;
    SpikeTile spikes8;

    Platform cover1;
    Sprite coverBottom1;
    Sprite coverShadow1;

    Platform cover2;
    Sprite coverBottom2;
    Sprite coverShadow2;

    Platform cover3;
    Sprite coverBottom3;
    Sprite coverShadow3;

    Platform cover4;
    Sprite coverBottom4;
    Sprite coverShadow4;

    Platform cover5;
    Sprite coverBottom5;
    Sprite coverShadow5;

    Platform cover6;
    Sprite coverBottom6;
    Sprite coverShadow6;

    Platform cover7;
    Sprite coverBottom7;
    Sprite coverShadow7;

    Platform cover8;
    Sprite coverBottom8;
    Sprite coverShadow8;

    Platform col1;
    Platform col2;
    Platform col3;
    Platform col4;
    Platform col5;
    Platform col6;

    TreasureChest chest;

    Platform col7;
    Platform col8;

    Enemy enemy01;


    public AlternatingSpikesLevel(String id) {
        super(id);
    }

    public void run() {
        coverList = new ArrayList<Rectangle2D>();
        enemies = new ArrayList<Enemy>();
        chests = new ArrayList<TreasureChest>();

        map = new Sprite("map", "alternating_spikes_level.png");
        map.setPositionX(200);
        map.setPositionY(-2450 - 100 - 615);
        addChild(map);

        door1 = new LockedDoor("door", "resources/door_opening.png", "door_closed");
        door1.setSpriteSheetJson("resources/door_opening.json");
        door1.setDelay(100);
        door1.setPositionX(map.getPositionX() + 128*2 + 48);
        door1.setPositionY(map.getPositionY() + 48);
        doors.add(door1);
        addChild(door1);

        spikes = new SpikeTile("spikes", "resources/spikes.png", "idle down");
        spikes.setSpriteSheetJson("resources/spikes.json");
        spikes.setPositionX(door1.getPositionX() - 128);
        spikes.setPositionY(door1.getPositionY() + 128*5);
        addChild(spikes);
        spikeList.add(spikes);

        spikes1 = new SpikeTile("spikes", "resources/spikes.png", "idle up");
        spikes1.setSpriteSheetJson("resources/spikes.json");
        spikes1.setPositionX(door1.getPositionX() + 128);
        spikes1.setPositionY(door1.getPositionY() + 128*5);
        addChild(spikes1);
        spikeList.add(spikes1);

        spikes2 = new SpikeTile("spikes", "resources/spikes.png", "idle up");
        spikes2.setSpriteSheetJson("resources/spikes.json");
        spikes2.setPositionX(door1.getPositionX() + 128*2);
        spikes2.setPositionY(door1.getPositionY() + 128*4);
        addChild(spikes2);
        spikeList.add(spikes2);

        spikes3 = new SpikeTile("spikes", "resources/spikes.png", "idle up");
        spikes3.setSpriteSheetJson("resources/spikes.json");
        spikes3.setPositionX(door1.getPositionX() + 128*2);
        spikes3.setPositionY(door1.getPositionY() + 128*3);
        addChild(spikes3);
        spikeList.add(spikes3);

        spikes4 = new SpikeTile("spikes", "resources/spikes.png", "idle up");
        spikes4.setSpriteSheetJson("resources/spikes.json");
        spikes4.setPositionX(spikes3.getPositionX() - 128);
        spikes4.setPositionY(spikes3.getPositionY());
        addChild(spikes4);
        spikeList.add(spikes4);

        spikes5 = new SpikeTile("spikes", "resources/spikes.png", "idle down");
        spikes5.setSpriteSheetJson("resources/spikes.json");
        spikes5.setPositionX(spikes4.getPositionX() - 128*2);
        spikes5.setPositionY(spikes4.getPositionY());
        addChild(spikes5);
        spikeList.add(spikes5);

        spikes6 = new SpikeTile("spikes", "resources/spikes.png", "idle down");
        spikes6.setSpriteSheetJson("resources/spikes.json");
        spikes6.setPositionX(spikes5.getPositionX() - 128);
        spikes6.setPositionY(spikes5.getPositionY());
        addChild(spikes6);
        spikeList.add(spikes6);

        spikes7 = new SpikeTile("spikes", "resources/spikes.png", "idle down");
        spikes7.setSpriteSheetJson("resources/spikes.json");
        spikes7.setPositionX(spikes5.getPositionX() - 128);
        spikes7.setPositionY(spikes5.getPositionY() - 128);
        addChild(spikes7);
        spikeList.add(spikes7);

        spikes8 = new SpikeTile("spikes", "resources/spikes.png", "idle down");
        spikes8.setSpriteSheetJson("resources/spikes.json");
        spikes8.setPositionX(spikes7.getPositionX());
        spikes8.setPositionY(spikes7.getPositionY() - 128);
        addChild(spikes8);
        spikeList.add(spikes8);

        cover1 = new Platform("tile", "cover_top_only_horizontal.png");
        cover1.setPositionX(spikes1.getPositionX());
        cover1.setPositionY(spikes1.getPositionY() - 128);
        addChild(cover1);
        coverList.add(convertToCover(cover1));
        collisionArray.add(cover1);
        coverBottom1 = new Sprite("corner", "cover_bottom.png");
        coverBottom1.setPositionX(cover1.getPositionX());
        coverBottom1.setPositionY(cover1.getPositionY() + cover1.getUnscaledHeight());
        addChild(coverBottom1);
        coverShadow1 = new Sprite("corner", "cover_shadow_right_bottom.png");
        coverShadow1.setPositionX(cover1.getPositionX() - 16);
        coverShadow1.setPositionY(cover1.getPositionY());
        addChild(coverShadow1);

        cover2 = new Platform("tile", "cover_top_only_horizontal.png");
        cover2.setPositionX(cover1.getPositionX() - 128);
        cover2.setPositionY(cover1.getPositionY());
        addChild(cover2);
        coverList.add(convertToCover(cover2));
        collisionArray.add(cover2);
        coverBottom2 = new Sprite("corner", "cover_bottom.png");
        coverBottom2.setPositionX(cover2.getPositionX());
        coverBottom2.setPositionY(cover2.getPositionY() + cover2.getUnscaledHeight());
        addChild(coverBottom2);
        coverShadow2 = new Sprite("corner", "cover_shadow_bottom.png");
        coverShadow2.setPositionX(cover2.getPositionX() - 16);
        coverShadow2.setPositionY(cover2.getPositionY());
        addChild(coverShadow2);

        cover3 = new Platform("tile", "cover_top_only_horizontal.png");
        cover3.setPositionX(cover2.getPositionX() - 128);
        cover3.setPositionY(cover2.getPositionY());
        addChild(cover3);
        coverList.add(convertToCover(cover3));
        collisionArray.add(cover3);
        coverBottom3 = new Sprite("corner", "cover_bottom.png");
        coverBottom3.setPositionX(cover3.getPositionX());
        coverBottom3.setPositionY(cover3.getPositionY() + cover3.getUnscaledHeight());
        addChild(coverBottom3);
        coverShadow3 = new Sprite("corner", "cover_shadow_bottom.png");
        coverShadow3.setPositionX(cover3.getPositionX() - 16);
        coverShadow3.setPositionY(cover3.getPositionY());
        addChild(coverShadow3);

        cover4 = new Platform("tile", "cover_top_only_horizontal.png");
        cover4.setPositionX(cover3.getPositionX() - 128);
        cover4.setPositionY(cover3.getPositionY());
        addChild(cover4);
        coverList.add(convertToCover(cover4));
        collisionArray.add(cover4);
        coverBottom4 = new Sprite("corner", "cover_bottom.png");
        coverBottom4.setPositionX(cover4.getPositionX());
        coverBottom4.setPositionY(cover4.getPositionY() + cover4.getUnscaledHeight());
        addChild(coverBottom4);
        coverShadow4 = new Sprite("corner", "cover_shadow_bottom.png");
        coverShadow4.setPositionX(cover4.getPositionX() - 16);
        coverShadow4.setPositionY(cover4.getPositionY());
        addChild(coverShadow4);

        cover5 = new Platform("tile", "cover_top_only_horizontal.png");
        cover5.setPositionX(cover3.getPositionX());
        cover5.setPositionY(cover3.getPositionY() - 128*2);
        addChild(cover5);
        coverList.add(convertToCover(cover5));
        collisionArray.add(cover5);
        coverBottom5 = new Sprite("corner", "cover_bottom.png");
        coverBottom5.setPositionX(cover5.getPositionX());
        coverBottom5.setPositionY(cover5.getPositionY() + cover5.getUnscaledHeight());
        addChild(coverBottom5);
        coverShadow5 = new Sprite("corner", "cover_shadow_bottom.png");
        coverShadow5.setPositionX(cover5.getPositionX() - 16);
        coverShadow5.setPositionY(cover5.getPositionY());
        addChild(coverShadow5);

        cover6 = new Platform("tile", "cover_top_only_horizontal.png");
        cover6.setPositionX(cover5.getPositionX() + 128);
        cover6.setPositionY(cover5.getPositionY());

        addChild(cover6);
        coverList.add(convertToCover(cover6));
        collisionArray.add(cover6);
        coverBottom6 = new Sprite("corner", "cover_bottom.png");
        coverBottom6.setPositionX(cover6.getPositionX());
        coverBottom6.setPositionY(cover6.getPositionY() + cover6.getUnscaledHeight());
        addChild(coverBottom6);
        coverShadow6 = new Sprite("corner", "cover_shadow_left_bottom.png");
        coverShadow6.setPositionX(cover6.getPositionX() - 16);
        coverShadow6.setPositionY(cover6.getPositionY());
        addChild(coverShadow6);

        cover7 = new Platform("tile", "cover_top_only_horizontal.png");
        cover7.setPositionX(cover5.getPositionX() + 128*2);
        cover7.setPositionY(cover5.getPositionY());

        addChild(cover7);
        coverList.add(convertToCover(cover7));
        collisionArray.add(cover7);
        coverBottom7 = new Sprite("corner", "cover_bottom.png");
        coverBottom7.setPositionX(cover7.getPositionX());
        coverBottom7.setPositionY(cover7.getPositionY() + cover7.getUnscaledHeight());
        addChild(coverBottom7);
        coverShadow7 = new Sprite("corner", "cover_shadow_bottom.png");
        coverShadow7.setPositionX(cover7.getPositionX() - 16);
        coverShadow7.setPositionY(cover7.getPositionY());
        addChild(coverShadow7);

        cover8 = new Platform("tile", "cover_top_only_horizontal.png");
        cover8.setPositionX(cover5.getPositionX() + 128*3);
        cover8.setPositionY(cover5.getPositionY());
        addChild(cover8);
        coverList.add(convertToCover(cover8));
        collisionArray.add(cover8);
        coverBottom8 = new Sprite("corner", "cover_bottom.png");
        coverBottom8.setPositionX(cover8.getPositionX());
        coverBottom8.setPositionY(cover8.getPositionY() + cover8.getUnscaledHeight());
        addChild(coverBottom8);
        coverShadow8 = new Sprite("corner", "cover_shadow_bottom.png");
        coverShadow8.setPositionX(cover8.getPositionX() - 16);
        coverShadow8.setPositionY(cover8.getPositionY());
        addChild(coverShadow8);

        col1 = new Platform("collider", "alpha_1x8.png");
        col1.setPositionX(map.getPositionX() + 48 - 128);
        col1.setPositionY(map.getPositionY());
        addChild(col1);
        coverList.add(convertToCover(col1));
        collisionArray.add(col1);

        col2 = new Platform("collider", "alpha_1x8.png");
        col2.setPositionX(map.getPositionX() + map.getUnscaledWidth() - 48);
        col2.setPositionY(map.getPositionY());
        addChild(col2);
        coverList.add(convertToCover(col2));
        collisionArray.add(col2);

        col3 = new Platform("collider", "alpha_3x1.png");
        col3.setPositionX(map.getPositionX() - 84);
        col3.setPositionY(map.getPositionY() + 48);
        addChild(col3);
        coverList.add(convertToCover(col3));
        collisionArray.add(col3);

        col4 = new Platform("collider", "alpha_8x1.png");
        col4.setPositionX(map.getPositionX() - 128);
        col4.setPositionY(map.getPositionY() + map.getUnscaledHeight() - 128);
        addChild(col4);
        coverList.add(convertToCover(col4));
        collisionArray.add(col4);

        col5 = new Platform("collider", "alpha_3x1.png");
        col5.setPositionX(col3.getPositionX() + col3.getUnscaledWidth() + 128);
        col5.setPositionY(col3.getPositionY());
        addChild(col5);
        coverList.add(convertToCover(col5));
        collisionArray.add(col5);

        col6 = new Platform("collider", "alpha_3x1.png");
        col6.setPositionX(col3.getPositionX() + col3.getUnscaledWidth() - 128);
        col6.setPositionY(col3.getPositionY() - 128);
        addChild(col6);
        coverList.add(convertToCover(col6));
        collisionArray.add(col6);

        col7 = new Platform("collider", "alpha_3x1.png");
        col7.setPositionX(490);
        col7.setPositionY(220);
        //addChild(col7);
        coverList.add(convertToCover(col7));
        collisionArray.add(col7);

        col8 = new Platform("collider", "alpha_8x1.png");
        col8.setPositionX(map.getPositionX() - 128);
        col8.setPositionY(map.getPositionY()-50);
        addChild(col8);
        coverList.add(convertToCover(col8));
        collisionArray.add(col8);


        chest = new TreasureChest("chest", "resources/treasure_chest.png", "closed");
        chest.setSpriteSheetJson("resources/treasure_chest.json");
        chest.setPositionX(cover8.getPositionX() + 24);
        chest.setPositionY(cover8.getPositionY() - 115);
        chest.placeItemInChest("key");
        addChild(chest);
        chests.add(chest);

        enemy01 = new Enemy("enemy", "resources/gator_sheet.png", "walk right");
        enemy01.setSpriteSheetJson("resources/gator_sheet.json");
        enemy01.setDelay(100);
        enemy01.setPositionX(chest.getPositionX() - 128*3 - 40);
        enemy01.setPositionY(chest.getPositionY());
        enemy01.addRoute(128*3, 0, 1, 2);
        enemy01.addRoute(-128*3, 0, 1, 4);
        enemies.add(enemy01);
        addChild(enemy01);
    }

    public void update() {
        super.update();

        door1.update();

        for(int i = 0; i < spikeList.size(); i++) {
            spikeList.get(i).update();
        }

        for(int i = 0; i < chests.size(); i++) {
            chests.get(i).update();
        }

//        for(int i = 0; i < enemies.size(); i++) {
//            enemies.get(i).update();
//        }

    }

    public void draw(Graphics g) {
        super.draw(g);

        map.draw(g);

        door1.draw(g);

        for(int i = 0; i < spikeList.size(); i++) {
            spikeList.get(i).draw(g);
        }

        for(int i = 0; i < chests.size(); i++) {
            chests.get(i).draw(g);
        }


        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(g);
        }

        cover1.draw(g);
        coverBottom1.draw(g);
        coverShadow1.draw(g);
        cover2.draw(g);
        coverBottom2.draw(g);
        coverShadow2.draw(g);
        cover3.draw(g);
        coverBottom3.draw(g);
        coverShadow3.draw(g);
        cover4.draw(g);
        coverBottom4.draw(g);
        coverShadow4.draw(g);
        cover5.draw(g);
        coverBottom5.draw(g);
        coverShadow5.draw(g);
        cover6.draw(g);
        coverBottom6.draw(g);
        coverShadow6.draw(g);
        cover7.draw(g);
        coverBottom7.draw(g);
        coverShadow7.draw(g);
        cover8.draw(g);
        coverBottom8.draw(g);
        coverShadow8.draw(g);


        col1.draw(g);
        col2.draw(g);
        col3.draw(g);
        col4.draw(g);
        col5.draw(g);
        col6.draw(g);
    }



}
