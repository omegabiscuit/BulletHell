package game;

/**
 * Created by Ahmed on 4/23/2017.
 */

import engine.display.DisplayObject;
import engine.display.Game;
import engine.display.Sprite;
import engine.display.AnimatedSprite;


import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

public class ahmedslevel extends Room {


    SoundManagerClass music = new SoundManagerClass();
    Sprite map;
    Platform collider1;
    Platform collider2;
    Platform collider3;
    Platform collider4;
    Platform collider5;
    Platform collider6;
    Platform collider7;
    Platform collider8;

    private Enemy enemy01;
    private Enemy enemy02;

    Platform cover1;
    Platform cover2;
    Platform cover3;
    Platform cover4;
    Platform cover5;
    Platform cover6;
    Platform cover7;
    Platform cover8;
    Sprite coverShadow1;
    Sprite coverShadow2;
    Sprite coverShadow3;
    Sprite coverShadow4;
    Sprite coverShadow5;
    Sprite coverShadow6;


    Platform bottomCollider;


    Platform topcover1;
    Platform topcover2;
    Platform topcover3;
    Platform topcover4;
    Platform topcover5;
    Platform topcover6;
    Platform topcover7;
    Platform topcover8;
    LockedDoor door1;

    SpikeTile spikes;
    SpikeTile spikes2;

    Player player;


    public ahmedslevel(String id, Player player) {
        super(id);
        this.player = player;
    }


    public void run() {
        map = new Sprite("map", "ahmedslevel.png");
        map.setPositionX(300);
        map.setPositionY(-1815);
//        coverList = new ArrayList<>();

        door1 = new LockedDoor("door", "resources/door_opening.png", "door_closed");
        door1.setSpriteSheetJson("resources/door_opening.json");
        door1.setDelay(100);

        door1.setPositionX(600);
        door1.setPositionY(-1765);
        addChild(door1);


        this.enemy01 = new Enemy("enemy", "resources/gator_sheet.png", "idle left");
        this.enemy01.setSpriteSheetJson("resources/gator_sheet.json");
        this.enemy01.setDelay(75L);
        this.enemy01.setPositionX(500.0D);
        this.enemy01.setPositionY(550.0D);
        this.enemy01.addRoute(0, 400.0D, 2.0D, 1.0D);
        this.enemy01.addRoute(500.0D, 0.0D, 2.0D, 2.0D);
        this.enemy01.addRoute(0.0D, -400.0D, 2.0D, 3.0D);
        this.enemy01.addRoute(-500.0D, 0.0D, 4.0D, 4.0D);



        this.enemy02 = new Enemy("enemy", "resources/gator_sheet.png", "idle left");
        this.enemy02.setSpriteSheetJson("resources/gator_sheet.json");
        this.enemy02.setDelay(75L);
        this.enemy02.setPositionX(800.0D);
        this.enemy02.setPositionY(200.0D);

        this.enemy02.addRoute(-500.0D, 0.0D, 2.0D, 4.0D);

        this.enemy02.addRoute(500.0D, 0.0D, 4.0D, 2.0D);



        addChild(map);


        collider1 = new Platform("collider", "alpha_3x1.png");
        collider2 = new Platform("collider2", "alpha_3x1.png");
        collider3 = new Platform("collider3", "alpha_1x6.png");
        collider4 = new Platform("collider4", "alpha_1x6.png");
        collider5 = new Platform("collider5", "alpha_3x1.png");
        collider6 = new Platform("collider6", "alpha_1x6.png");

        cover1 = new Platform("cover1", "cover1.png");
        cover1.setPositionX(345);
        cover1.setPositionY(300);
        coverList.add(convertToCover(cover1));
        cover1.setPositionY(map.getPositionY() + 300);
        coverShadow1 = new Sprite("covershadow", "cover_shadow_bottom.png");
        coverShadow1.setPositionX(cover1.getPositionX());
        coverShadow1.setPositionY(cover1.getPositionY());
        addChild(coverShadow1);

        cover2 = new Platform("cover2", "cover1.png");
        cover2.setPositionX(346);
        cover2.setPositionY(550);
        coverList.add(convertToCover(cover2));
        cover2.setPositionY(map.getPositionY() + 550);
        coverShadow2 = new Sprite("covershadow", "cover_shadow_right_bottom.png");
        coverShadow2.setPositionX(cover2.getPositionX() - 16);
        coverShadow2.setPositionY(cover2.getPositionY());
        addChild(coverShadow2);

        cover3 = new Platform("cover2", "cover1.png");
        cover3.setPositionX(445);
        cover3.setPositionY(300);
        coverList.add(convertToCover(cover3));
        cover3.setPositionY(map.getPositionY() + 300);
        coverShadow3 = new Sprite("covershadow", "cover_shadow_right_bottom.png");
        coverShadow3.setPositionX(cover3.getPositionX() - 16);
        coverShadow3.setPositionY(cover3.getPositionY());
        addChild(coverShadow3);

        spikes = new SpikeTile("spikes", "resources/spikes.png", "idle down");
        spikes.setSpriteSheetJson("resources/spikes.json");
        spikes.setPositionX(cover3.getPositionX() + spikes.getUnscaledWidth() + 28);
        spikes.setPositionY(cover3.getPositionY() - 4 + 128*2);
        addChild(spikes);
        spikeList.add(spikes);

        cover4 = new Platform("cover2", "cover1.png");
        cover4.setPositionX(600);
        cover4.setPositionY(450);
        coverList.add(convertToCover(cover4));
        cover4.setPositionY(map.getPositionY() + 450);
        coverShadow4 = new Sprite("covershadow", "cover_shadow.png");
        coverShadow4.setPositionX(cover4.getPositionX() - 16);
        coverShadow4.setPositionY(cover4.getPositionY());
        addChild(coverShadow4);


        spikes2 = new SpikeTile("spikes", "resources/spikes.png", "idle down");
        spikes2.setSpriteSheetJson("resources/spikes.json");
        spikes2.setPositionX(cover4.getPositionX() + spikes.getUnscaledWidth() + 128 + 4 );
        spikes2.setPositionY(cover4.getPositionY() - 32 + 2);
        addChild(spikes2);
        spikeList.add(spikes2);

        cover5 = new Platform("cover2", "cover1.png");
        cover5.setPositionX(850);
        cover5.setPositionY(300);
        coverList.add(convertToCover(cover5));
        cover5.setPositionY(map.getPositionY() + 300);
        coverShadow5 = new Sprite("covershadow", "cover_shadow_left_bottom.png");
        coverShadow5.setPositionX(cover5.getPositionX() - 16);
        coverShadow5.setPositionY(cover5.getPositionY());
        addChild(coverShadow5);

        cover6 = new Platform("cover2", "cover1.png");
        cover6.setPositionX(850);
        cover6.setPositionY(600);
        coverList.add(convertToCover(cover6));
        cover6.setPositionY(map.getPositionY() + 600);
        coverShadow6 = new Sprite("covershadow", "cover_shadow_left_bottom.png");
        coverShadow6.setPositionX(cover6.getPositionX() - 16);
        coverShadow6.setPositionY(cover6.getPositionY());
        addChild(coverShadow6);

//        cover7 = new Platform("cover2","cover1.png");
//
//        cover8 = new Platform("cover2","cover1.png");


        topcover1 = new Platform("cover1", "cover_top_only.png");
        topcover1.setPositionX(345);
        topcover1.setPositionY(map.getPositionY() + 300);


        topcover2 = new Platform("cover2", "cover_top_only.png");
        topcover2.setPositionX(346);
        topcover2.setPositionY(map.getPositionY() + 550);

        topcover3 = new Platform("cover2", "cover_top_only.png");
        topcover3.setPositionX(445);
        topcover3.setPositionY(map.getPositionY() + 300);

        topcover4 = new Platform("cover2", "cover_top_only.png");
        topcover4.setPositionX(600);
        topcover4.setPositionY(map.getPositionY() + 450);

        topcover5 = new Platform("cover2", "cover_top_only.png");
        topcover5.setPositionX(850);
        topcover5.setPositionY(map.getPositionY() + 300);

        topcover6 = new Platform("cover2", "cover_top_only.png");
        topcover6.setPositionX(850);
        topcover6.setPositionY(map.getPositionY() + 600);

        bottomCollider = new Platform("collider", "alpha_8x1.png");
        bottomCollider.setPositionX(map.getPositionX() - 128);
        bottomCollider.setPositionY(map.getPositionY() + map.getUnscaledHeight());
        coverList.add(convertToCover(bottomCollider));
        addChild(bottomCollider);

//        topcover7 = new Platform("cover2","cover_top_only.png");
//        topcover8 = new Platform("cover2","cover_top_only.png");


        addChild(cover1);
        addChild(cover2);
        addChild(cover3);
        addChild(cover4);
        addChild(cover5);
        addChild(cover6);


        addChild(topcover1);
        addChild(topcover2);
        addChild(topcover3);
        addChild(topcover4);
        addChild(topcover5);
        addChild(topcover6);


        //shadow1.setPositionY(cover1.getPositionY()+cover1.getUnscaledWidth() + 50);


        collider1.setPositionX(320);
        collider1.setPositionY(map.getPositionY() + -50);

        collider2.setPositionX(700);
        collider2.setPositionY(map.getPositionY() + -10);

        collider3.setPositionX(200);
        collider3.setPositionY(map.getPositionY() + 100);

        collider4.setPositionX(1000);
        collider4.setPositionY(map.getPositionY() + 60);
        collider4.setPositionY(map.getPositionY() + 60);

        collider5.setPositionX(220);
        collider5.setPositionY(map.getPositionY() + -10);


        addChild(collider1);
        addChild(collider2);
        addChild(collider3);
        addChild(collider4);
        addChild(collider5);

        collisionArray.add(collider1);
        collisionArray.add(collider2);
        collisionArray.add(collider3);
        collisionArray.add(collider4);
        collisionArray.add(collider5);
        collisionArray.add(topcover1);
        collisionArray.add(topcover2);
        collisionArray.add(topcover3);
        collisionArray.add(topcover4);
        collisionArray.add(topcover5);
        collisionArray.add(topcover6);
        collisionArray.add(bottomCollider);




        enemies = new ArrayList<>();
        enemies.add(enemy01);
        enemies.add(enemy02);

        random = new Random();
        enemies.get(random.nextInt(enemies.size())).addKey();


        doors.add(door1);


    }

    public void draw(Graphics g) {
        super.draw(g);

        map.draw(g);

        spikes.draw(g);
        spikes2.draw(g);

        cover1.draw(g);
        //shadow1.draw(g);
        cover2.draw(g);

        cover3.draw(g);
        cover4.draw(g);
        cover5.draw(g);
        cover6.draw(g);

        topcover1.draw(g);
        //shadow1.draw(g);
        topcover2.draw(g);

        topcover3.draw(g);
        topcover4.draw(g);
        topcover5.draw(g);
        topcover6.draw(g);



        bottomCollider.draw(g);

        door1.draw(g);

        coverShadow1.draw(g);
        coverShadow2.draw(g);
        coverShadow3.draw(g);
        coverShadow4.draw(g);
        coverShadow5.draw(g);
        coverShadow6.draw(g);
    }

    public void update() {
        super.update();
        door1.update();

        spikes.update();
        spikes2.update();
    }


}