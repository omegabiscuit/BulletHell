package game;

import engine.Tweens.Tween;
import engine.Tweens.TweenJuggler;
import engine.Tweens.TweenTransitions;
import engine.Tweens.TweenableParams;
import engine.display.Sprite;
import engine.events.IEventDispatcher;
import engine.util.GameClock;
import engine.events.Event;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Brigadoon on 4/24/2017.
 */
public class BossLevel extends Room {

    Sprite map;
    Platform collider1;
    Platform collider2;
    Platform collider3;
    Platform collider4;
    Platform collider5;
    Platform collider6;

    SpikeTile spikes1;
    SpikeTile spikes2;
    SpikeTile spikes3;
    SpikeTile spikes4;

    Bullet missle1;
    Bullet missle2;
    Bullet missle3;
    Bullet missle4;
    Bullet missle5;
    Bullet missle6;
    Bullet missle7;
    Bullet missle8;
    Bullet missle9;
    Bullet missle10;
    Random random;

    Player player;

    TurtleBoss turtleBoss;


    GameClock missleClock;
    Event explode;
    Event reduceLife;
    double missleLaunch;//time of last missle attack




    public ArrayList<Bullet> missles;


    /****Treasure Chesty Stuff****/
    TreasureChest chest;
    int chestTimer;
    SoundManagerClass soundManager;

    public BossLevel(String id) {
        super(id);
    }

    public BossLevel(String id, Player player) {
        super(id);
        this.player = player;

    }

    public void run() {

        soundManager = new SoundManagerClass();

        random = new Random();
        reduceLife = new Event();
        reduceLife.setEventType("collision");

        map = new Sprite("map", "ahmedslevel.png");
        map.setPositionX(300);
        map.setPositionY(-2450);
        addChild(map);

        turtleBoss = new TurtleBoss("boss", "resources/turtle_boss.png", "idle");
        turtleBoss.setSpriteSheetJson("resources/turtle_boss.json");
        turtleBoss.setPositionX(map.getPositionX() + 500);
        turtleBoss.setPositionY(map.getPositionY());
        addChild(turtleBoss);


        missle1 = new Bullet("missle", "missle.png", 4);
        missle2 = new Bullet("missle", "missle.png", 4);
        missle3 = new Bullet("missle", "missle.png", 4);
        missle4 = new Bullet("missle", "missle.png", 4);
        missle5 = new Bullet("missle", "missle.png", 4);
        missle6 = new Bullet("missle", "missle.png", 4);
        missle7 = new Bullet("missle", "missle.png", 4);
        missle8 = new Bullet("missle", "missle.png", 4);
        missle9 = new Bullet("missle", "missle.png", 4);
        missle10 = new Bullet("missle", "missle.png", 4);
        missles = new ArrayList<>();
        missles.add(missle1);
        missles.add(missle2);
        missles.add(missle3);
        missles.add(missle4);
        missles.add(missle5);
        missles.add(missle6);
        missles.add(missle7);
        missles.add(missle8);
        missles.add(missle9);


        coverList = new ArrayList<>();

        missleClock = new GameClock();
        missleClock.resetGameClock();
        missleLaunch = missleClock.getElapsedTime();
        collider1 = new Platform("collider", "alpha_3x1.png");
        collider2 = new Platform("collider2", "alpha_3x1.png");
        collider3 = new Platform("collider3", "alpha_1x6.png");
        collider4 = new Platform("collider4", "alpha_1x6.png");
        collider5 = new Platform("collider5", "alpha_3x1.png");
        collider6 = new Platform("collider6", "alpha_6x1.png");

        addChild(collider1);
        addChild(collider2);
        addChild(collider3);
        addChild(collider4);
        addChild(collider5);
        addChild(collider6);

        collider1.setPositionX(320);
        collider1.setPositionY(-50 + map.getPositionY());

        collider2.setPositionX(700);
        collider2.setPositionY(-10 + map.getPositionY());

        collider3.setPositionX(200);
        collider3.setPositionY(100 + map.getPositionY());

        collider4.setPositionX(1000);
        collider4.setPositionY(60 + map.getPositionY());

        collider5.setPositionX(220);
        collider5.setPositionY(-10 + map.getPositionY());

        collider6.setPositionX(map.getPositionX());
        collider6.setPositionY(map.getPositionY() + map.getUnscaledHeight());

        spikes1 = new SpikeTile("spikes", "resources/spikes.png", "idle down");
        spikes1.setSpriteSheetJson("resources/spikes.json");
        spikes1.setPositionX(map.getPositionX() + map.getUnscaledWidth()/2 - 128/2 - 4);
        spikes1.setPositionY(map.getPositionY() + map.getUnscaledHeight()/2 + 32 - 8 - 2);
        addChild(spikes1);
        spikeList.add(spikes1);

        spikes2 = new SpikeTile("spikes", "resources/spikes.png", "idle down");
        spikes2.setSpriteSheetJson("resources/spikes.json");
        spikes2.setPositionX(spikes1.getPositionX() + 128*2);
        spikes2.setPositionY(spikes1.getPositionY());
        addChild(spikes2);
        spikeList.add(spikes2);

        spikes3 = new SpikeTile("spikes", "resources/spikes.png", "idle down");
        spikes3.setSpriteSheetJson("resources/spikes.json");
        spikes3.setPositionX(spikes1.getPositionX() - 128*2);
        spikes3.setPositionY(spikes1.getPositionY());
        addChild(spikes3);
        spikeList.add(spikes3);

        /****Treasure Chesty Stuff****/
        chest = new TreasureChest("chest", "resources/treasure_chest.png", "closed");
        chest.setSpriteSheetJson("resources/treasure_chest.json");
        chest.setPositionX(turtleBoss.getPositionX() - 128*3);
        chest.setPositionY(turtleBoss.getPositionY() + 128*2);
        chest.placeItemInChest("knife");
        addChild(chest);
        chests.add(chest);







        collisionArray.add(collider1);
        collisionArray.add(collider2);
        collisionArray.add(collider3);
        collisionArray.add(collider4);
        collisionArray.add(collider5);
        collisionArray.add(collider6);

    }

    public void update() {
        super.update();
        turtleBoss.update();
        if (map.getPositionY() >= 0) {
            if (missleClock.getElapsedTime() - missleLaunch > 5000 && !turtleBoss.isDead()) {
                int offset = 0;

                int randomDirection = random.nextInt(4);
                missleLaunch = missleClock.getElapsedTime();
                if (randomDirection == 0) {
                    int randomNumber = random.nextInt(9);
                    for (int i = 0; i < missles.size(); i++) {
                        if (i != randomNumber) {
                            missles.get(i).setStart(map.getPositionX() + 30 + offset, 0);
                            missles.get(i).setEnd(map.getPositionX() + 30 + offset, 1300);
                            TweenTransitions misslePath = new TweenTransitions("linearTransition");
                            Tween misslemovement = new Tween(missles.get(i), misslePath);
                            misslemovement.animate(TweenableParams.X, missles.get(i).startValX, missles.get(i).endValX, 4);
                            misslemovement.animate(TweenableParams.Y, missles.get(i).startValY, missles.get(i).endValY, 4);
                            TweenJuggler.getInstance().add(misslemovement);
                        }
                        offset += 80;
                    }
                } else if (randomDirection == 1) {
                    int randomNumber = random.nextInt(7);
                    for (int i = 0; i < missles.size() - 2; i++) {
                        if (i != randomNumber) {
                            missles.get(i).setStart(0, map.getPositionY() + 160 + offset);
                            missles.get(i).setEnd(1300, map.getPositionY() + 160 + offset);
                            TweenTransitions misslePath = new TweenTransitions("linearTransition");
                            Tween misslemovement = new Tween(missles.get(i), misslePath);
                            misslemovement.animate(TweenableParams.X, missles.get(i).startValX, missles.get(i).endValX, 4);
                            misslemovement.animate(TweenableParams.Y, missles.get(i).startValY, missles.get(i).endValY, 4);
                            TweenJuggler.getInstance().add(misslemovement);
                        }
                        offset += 90;
                    }
                } else if (randomDirection == 2) {
                    int randomNumber = random.nextInt(7);
                    for (int i = 0; i < missles.size() - 2; i++) {
                        if (i != randomNumber) {
                            missles.get(i).setStart(1300, map.getPositionY() + 160 + offset);
                            missles.get(i).setEnd(-50, map.getPositionY() + 160 + offset);
                            TweenTransitions misslePath = new TweenTransitions("linearTransition");
                            Tween misslemovement = new Tween(missles.get(i), misslePath);
                            misslemovement.animate(TweenableParams.X, missles.get(i).startValX, missles.get(i).endValX, 4);
                            misslemovement.animate(TweenableParams.Y, missles.get(i).startValY, missles.get(i).endValY, 4);
                            TweenJuggler.getInstance().add(misslemovement);
                        }
                        offset += 90;
                    }
                }
                if (randomDirection == 3) {
                    int randomNumber = random.nextInt(9);
                    for (int i = 0; i < missles.size(); i++) {
                        if (i != randomNumber) {
                            missles.get(i).setStart(map.getPositionX() + 30 + offset, 1300);
                            missles.get(i).setEnd(map.getPositionX() + 30 + offset, -50);
                            TweenTransitions misslePath = new TweenTransitions("linearTransition");
                            Tween misslemovement = new Tween(missles.get(i), misslePath);
                            misslemovement.animate(TweenableParams.X, missles.get(i).startValX, missles.get(i).endValX, 4);
                            misslemovement.animate(TweenableParams.Y, missles.get(i).startValY, missles.get(i).endValY, 4);
                            TweenJuggler.getInstance().add(misslemovement);
                        }
                        offset += 80;
                    }
                }
            }
            for (int i = 0; i < missles.size(); i++) {
                if (player.playerCollidesWith(missles.get(i)) && player.canGetHurt()) {
                    damageThePlayer();
                    soundManager.playMusic("resources/turtle_laugh.mp3");
                }
            }
            for (int i = 0; i < player.playerBullets.size(); i++) {
                if (player.playerBullets.get(i).collidesWith(turtleBoss)) {
                    turtleBoss.health--;
                    turtleBoss.gotHurt();
                    player.playerBullets.remove(i);
                    break;
                }
            }
        }
        else{
            missleLaunch = missleClock.getElapsedTime();
        }



        /****Treasure Chesty Stuff****/
        if(chestTimer < 700) {
            chestTimer++;
        } else if(chestTimer >= 700 && chest.getStateName() == "open") {
            chest = new TreasureChest("chest", "resources/treasure_chest.png", "closed");
            chest.setSpriteSheetJson("resources/treasure_chest.json");
            chest.setPositionX(turtleBoss.getPositionX() - 128*3);
            chest.setPositionY(turtleBoss.getPositionY() + 128*2);
            chest.placeItemInChest("knife");
            addChild(chest);
            chests.clear();
            chests.add(chest);
            chestTimer = 0;
        }

        chest.update();

        spikes1.update();
        spikes2.update();
        spikes3.update();
    }

    private void damageThePlayer() {
        if (player.getLifeCount() != 0) {
            player.gotHurt();
            player.handleEvent(reduceLife);
        }
        if (player.getLifeCount() == 0) {
            player.isDead = true;
        }
    }

    public void draw(Graphics g) {
        super.draw(g);


        map.draw(g);


        turtleBoss.draw(g);
        spikes1.draw(g);
        spikes2.draw(g);
        spikes3.draw(g);

        /****Treasure Chesty Stuff****/
        chest.draw(g);


        for (int i = 0; i < missles.size(); i++) {
            missles.get(i).draw(g);
        }




    }
}
