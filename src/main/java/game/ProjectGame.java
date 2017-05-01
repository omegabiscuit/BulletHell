package game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import com.sun.javafx.geom.Line2D;
import com.sun.javafx.geom.Vec2d;
import engine.Tweens.*;
import engine.display.AnimatedSprite;
import engine.display.DisplayObjectContainer;
import engine.display.Game;
import engine.display.Sprite;
import engine.events.Event;
import engine.util.GameClock;
import javafx.scene.shape.Line;

import game.Level0;


public class ProjectGame extends Game {

    SoundManagerClass backgroundMusic;
    QuestManager myQuestManager = new QuestManager();
    Event PickedUpEvent;
    Event fadeOutEvent;
    Event die;
    Event reduceLife;
    TweenEvent tweenEvent;
    boolean pause = false;
    boolean complete = false;
    Event collidedEvent;
    Event throwKnife;
    Coin coin = new Coin("coin", "Coin4.png");

    boolean start = false;

    Sprite background = new Sprite("Background", "background.png");
    Sprite menuScreen = new Sprite("menuScreen", "menu-screen.jpg");
    private Player player;

    Heart life1 = new Heart("Heart", "heart.png");
    Heart life2 = new Heart("Heart", "heart.png");
    Heart life3 = new Heart("Heart", "heart.png");

    int keyCount;
    int knifeCount;
    String itemString = "";
    boolean pickpocket = false;
    Rectangle2D cover;
    //ArrayList<Rectangle2D> coverList;

    ///Level 0////


    // int damageCap = 100;

    int currentLevel;

    Level0 myLevel;
    Level1 myLevel1;

    ahmedslevel myLevel2;

    BossLevel bossLevel;

    BrighamLevel myLevel3;


    ArrayList<Enemy> enemies;

    Enemy pickpocketEnemy;


    static GameClock clock;

    SoundManagerClass soundEffects = new SoundManagerClass();

    private enum STATE {MENU, GAME, PAUSE}

    ;

    private STATE state = STATE.MENU;


    Room currentRoom;
    Room queuedRoom;

    double transitionY;
    double transitionYSpeed = 9;
    double transitionYCurrent;
    boolean transitionPhase = false;


    int x;

    /**
     * Constructor. See constructor in Game.java for details on the parameters given
     */
    public ProjectGame() {

        super("BulletHell", 1200, 900);
        die = new Event();
        backgroundMusic = new SoundManagerClass();
        x = 0;
        reduceLife = new Event();
        fadeOutEvent = new Event();
        PickedUpEvent = new Event();
        die.setEventType("playerDeath");
        fadeOutEvent.setEventType("FadeOut");
        reduceLife.setEventType("Collision");
        this.addEventListener(myQuestManager, PickedUpEvent.getEventType());
        this.addEventListener(myQuestManager, die.getEventType());
        this.addEventListener(myQuestManager, reduceLife.getEventType());
        collidedEvent = new Event();
        collidedEvent.setEventType("CollidedEvent");
        throwKnife = new Event();
        throwKnife.setEventType("throwKnife");


        currentLevel = 0;//0 = base , 3=brigham's level


        PickedUpEvent.setEventType("CoinPickedUp");

        this.addEventListener(myQuestManager, collidedEvent.getEventType());


        background.setScaleX(5);
        background.setScaleY(5);

        background.setScaleX(5);
        background.setScaleY(5);


        //player = new AnimatedSprite("player", "resources/player_sheet.png", "idle_right");
        player = new Player("player", "resources/player_sheet.png", "idle_right");
        player.setSpriteSheetJson("resources/player_sheet.json");
        player.setDelay(100);

        player.getLifeArray().get(0).setPositionX(390);
        player.getLifeArray().get(0).setPositionY(40);
        player.getLifeArray().get(1).setPositionX(420);
        player.getLifeArray().get(1).setPositionY(40);
        player.getLifeArray().get(2).setPositionX(450);
        player.getLifeArray().get(2).setPositionY(40);
        // player.setHasPhysics(true);
        keyCount = 0;
        knifeCount = 4;
        // player.setHasPhysics(true);

        //  platform.setPositionX(50);
        //  platform.setPositionY(550);


        //  platform1.setPositionX(150);
        //  platform1.setPositionY(150);


        player.setPositionX(550);
        player.setPositionY(700);


        coin.setPositionY(250);
        coin.setPositionX(660);
        //Rectangle2D rect = new Rectangle2D.Float(600,400,700,500);
        //coverList = new ArrayList<Rectangle2D>(); //list of cover sprites
        //coverList.add(rect);

        ///////////////////////////////////////LEVEL 0 ////////////////////////////////////////////////////////////////

        if (currentLevel == 0) {

            myLevel = new Level0("Room1");
            myLevel.run();
            addChild(myLevel);


            myLevel1 = new Level1("Room2");
            myLevel1.run();
            myLevel1.hide();
            addChild(myLevel1);

            myLevel2 = new ahmedslevel("Room3", player);
            myLevel2.run();
            myLevel2.hide();
            addChild(myLevel2);

            myLevel3 = new BrighamLevel("Room4");
            myLevel3.run();
            myLevel3.hide();
            addChild(myLevel3);

            bossLevel = new BossLevel("Room5", player);
            bossLevel.run();
            bossLevel.hide();
            addChild(bossLevel);

            myLevel.mapDoorToRoom(0, myLevel1);
            myLevel1.mapDoorToRoom(0, myLevel2);
            myLevel2.mapDoorToRoom(0, myLevel3);
            myLevel3.mapDoorToRoom(0, bossLevel);
            //myLevel3.mapDoorToRoom(0,bossLevel);


            currentRoom = myLevel;

            //myLevel1.mapDoorToRoom(0,myLevel2);

            //myLevel2.run();
            //myLevel2.hide();


            //myLevel2.mapDoorToRoom(0,myLevel3);
            //myLevel3.run();
            //myLevel3.hide();

        }

        if (currentLevel == 4) {


            bossLevel = new BossLevel("Room4", player);
            bossLevel.run();
            currentRoom = bossLevel;


        }

        enemies = currentRoom.enemies;
        pickpocketEnemy = null;

        transitionY = 615;
        transitionYCurrent = 615;


        backgroundMusic.playMusic("resources/oceanOperator.mp3");

    }


    /**
     * Engine will automatically call sprite update method once per frame and pass to us
     * the set of keys (as strings) that are currently being pressed down
     */
    @Override
    public void update(ArrayList<String> pressedKeys) {


        super.update(pressedKeys);
//        if(!backgroundMusic.play && x==0){
//            x=1;
//            backgroundMusic.playMusic("resources/oceanOperator.mp3");
//        }

        if (state == STATE.MENU) {

        }

        //moveGameY(1);

        else if (state == STATE.GAME) {
            if (player != null && !player.isDead) {
                player.update(pressedKeys);
            }


            if (transitionYCurrent < transitionY) {
                moveGameY(transitionYSpeed);
                transitionYCurrent += transitionYSpeed;
            }


            if (player != null && !player.isDead) {
//                for (int i = 0; i < enemies.size(); i++) {
//                    if (player.playerCollidesWith(enemies.get(i)) && enemies.get(i).dead == false && player.canGetHurt()) {
//                        damageThePlayer();
//                    }
//                }

                for (int i = 0; i < currentRoom.getSpikeList().size(); i++) {
                    SpikeTile spikes = currentRoom.getSpikeList().get(i);
                    if (player.feetCollideWith(spikes) && spikes.getStateName() == "idle up" && player.canGetHurt()) {
                        damageThePlayer();
                    }
                }


                if (player.playerCollidesWith(coin)) {
                    coin.handleEvent(collidedEvent);
                    myQuestManager.handleEvent(PickedUpEvent);
                }
                player.update();
                checkCollisions(player);


                for (int i = 0; i < player.playerBullets.size(); i++) {
                    Bullet bul = player.playerBullets.get(i);
                    bul.update(pressedKeys);
                    if (bul.getShotTimer() >= bul.getShotCap()) {

                        player.playerBullets.remove(i);
                    }
                }

                TweenJuggler.getInstance().nextFrame();

                boolean pickpocketTrigger = false;
                for (int i = 0; i < enemies.size(); i++) {

                    Enemy enemy = enemies.get(i);
                    if (player.getHitBox().intersects(enemy.getPickpocketRect())) {
                        pickpocketTrigger = true;
                        pickpocketEnemy = enemy;
                    }

                }
                pickpocket = pickpocketTrigger;
                if (!pickpocket)
                    pickpocketEnemy = null;
            }
            if (pressedKeys.contains(KeyEvent.getKeyText(KeyEvent.VK_W))){
                for (int i = 0; i < currentRoom.getDoors().size(); i++) {
                    if (player.getHitBox().intersects(currentRoom.getDoors().get(i).getDoorCollider()) && currentRoom.getDoors().get(i).stateName == "door_open" && transitionPhase == false) {
                        transitionPhase = true;
                        currentRoom.fadeOut();
                        queuedRoom = currentRoom.getDoors().get(i).getNextRoom();
                        queuedRoom.fadeIn();
                        transitionYCurrent = 0;
                        enemies = new ArrayList<>();
                        pressedKeys.remove(KeyEvent.getKeyText(KeyEvent.VK_W));
                    }
                }

            }

            if (pressedKeys.contains(KeyEvent.getKeyText(KeyEvent.VK_E))) {
                if (pickpocket == true && pickpocketEnemy != null) {

                    lootEnemy(pickpocketEnemy);
//                int n = rand.nextInt(3) + 1;
//                if (n == 1) {
//
//                    itemString = "You found a knife!";
//                } else if (n == 2) {
//                    keyCount++;
//                    itemString = "You found a key!";
//
//                } else if (n == 3) {
//                    itemString = "No items found.";
//                }
                }


                for (int i = 0; i < currentRoom.getDoors().size(); i++) {

                    if (player.getHitBox().intersects(currentRoom.getDoors().get(i).getDoorCollider()) && currentRoom.getDoors().get(i).stateName == "door_closed") {
                        if (keyCount > 0) {
                            soundEffects.playMusic("resources/chains.wav");
                            currentRoom.getDoors().get(i).setAnimationState("door_opening", "door_open");
                            itemString = "Door unlocked";
                            keyCount--;
                        } else {
                            soundEffects.playMusic("resources/door_locked.wav");
                        }
                    } else if (player.getHitBox().intersects(currentRoom.getDoors().get(i).getDoorCollider()) && currentRoom.getDoors().get(i).stateName == "door_open" && transitionPhase == false) {
                        transitionPhase = true;
                        currentRoom.fadeOut();
                        queuedRoom = currentRoom.getDoors().get(i).getNextRoom();
                        queuedRoom.fadeIn();
                        transitionYCurrent = 0;
                        enemies = new ArrayList<>();
                    }
                }

                for (int i = 0; i < currentRoom.getChests().size(); i++) {
                    TreasureChest chest = currentRoom.getChests().get(i);
                    if (player.feetCollideWith(chest) && chest.getStateName() == "closed") {
                        chest.setAnimationState("open", "");
                        if (chest.getItem().equals("key")) {
                            keyCount++;
                        } else if (chest.getItem().equals("knife")) {
                            knifeCount++;
                        }
                    }
                }


                pressedKeys.remove(KeyEvent.getKeyText(KeyEvent.VK_E));
            }

            if (pressedKeys.contains(KeyEvent.getKeyText(KeyEvent.VK_P))) {
                if (complete == true) {
                    complete = false;
                    currentLevel = 0;
                    myLevel = new Level0("Room1");
                    myLevel.run();
                    addChild(myLevel);


                    myLevel1 = new Level1("Room2");
                    myLevel1.run();
                    myLevel1.hide();
                    addChild(myLevel1);

                    myLevel2 = new ahmedslevel("Room3", player);
                    myLevel2.run();
                    myLevel2.hide();
                    addChild(myLevel2);

                    myLevel3 = new BrighamLevel("Room4");
                    myLevel3.run();
                    myLevel3.hide();
                    addChild(myLevel3);

                    bossLevel = new BossLevel("Room5", player);
                    bossLevel.run();
                    bossLevel.hide();
                    addChild(bossLevel);

                    myLevel.mapDoorToRoom(0, myLevel1);
                    myLevel1.mapDoorToRoom(0, myLevel2);
                    myLevel2.mapDoorToRoom(0, myLevel3);
                    myLevel3.mapDoorToRoom(0, bossLevel);
                    currentRoom = myLevel;

                    enemies = currentRoom.enemies;

                    player.isDead = false;
                    player.setLifeCount(3);
                    player.setPositionX(550);
                    player.setPositionY(700);
                    knifeCount = 4;
                } else {
                    state = STATE.PAUSE;
                }
            }

            if (pressedKeys.contains("K")) {
                state = STATE.PAUSE;
            }
            for (int i = 0; i < enemies.size(); i++) {
                Enemy currentEnemy = enemies.get(i);
                currentEnemy.update();
                if (!currentEnemy.dead) {
                    if (currentEnemy.enemyBullet != null) {
                        currentEnemy.enemyBullet.update(pressedKeys);
                        if (currentEnemy.enemyBullet.getShotTimer() >= currentEnemy.enemyBullet.getShotCap()) {
                            currentEnemy.enemyBullet = null;
                        }
                    }
                    if (currentEnemy.isInView(player, currentRoom.coverList)) {
//                        System.out.println(currentRoom.coverList.get(0));
                        if (complete == false) {
                            if (currentEnemy.enemyBullet == null) {
                                currentEnemy.enemyBullet = new Bullet("bullet", "knife.png", 0.4);
                                currentEnemy.enemyBullet.setStart(currentEnemy.getPositionX() + currentEnemy.getUnscaledWidth() / 2, currentEnemy.getPositionY() + currentEnemy.getUnscaledHeight() / 2);
                                currentEnemy.enemyBullet.setEnd(player.getPositionX(), player.getPositionY());
                                TweenTransitions enemyBulletPath = new TweenTransitions("linearTransition");
                                Tween enemyBulletmovement = new Tween(currentEnemy.enemyBullet, enemyBulletPath);
                                enemyBulletmovement.animate(TweenableParams.X, currentEnemy.enemyBullet.startValX, currentEnemy.enemyBullet.endValX, 0.4);
                                enemyBulletmovement.animate(TweenableParams.Y, currentEnemy.enemyBullet.startValY, currentEnemy.enemyBullet.endValY, 0.4);
                                TweenJuggler.getInstance().add(enemyBulletmovement);
                                currentEnemy.handleEvent(throwKnife);
                            }
                        }
                        if (currentEnemy.enemyBullet != null) {
//                            System.out.println(currentEnemy.enemyBullet.getShotTimer());
                            if (currentEnemy.enemyBullet.collidesWith(player) && player.canGetHurt()) {
                                damageThePlayer();
                                currentEnemy.enemyBullet = null;
                            }

                        }
                    }


                    for (int j = 0; j < player.playerBullets.size(); j++) {
                        Bullet bul = player.playerBullets.get(j);
                        for (int k = 0; k < currentRoom.collisionArray.size(); k++) {
                            if (bul.collidesWith(currentRoom.collisionArray.get(k))) {
                                player.playerBullets.remove(j);
                                break;
                            }
                        }
                        if (bul != null) {
                            if (bul.collidesWith(enemies.get(i))) {
                                enemies.get(i).dead = true;
                                enemies.get(i).enemyBullet = null;
                                if (enemies.get(i).getStateName().contains("right")) {
                                    enemies.get(i).setDelay(90);
                                    enemies.get(i).setAnimationState("dying right", "dead right");
                                } else {
                                    enemies.get(i).setDelay(90);
                                    enemies.get(i).setAnimationState("dying left", "dead left");
                                }
                                if(player.playerBullets.size() > j)
                                    player.playerBullets.remove(j);
                            }

                        }
                    }
                }
            }

            currentRoom.update();

            if (queuedRoom != null) {
                queuedRoom.update();

                if (queuedRoom.getDoneFading() && currentRoom.getDoneFading()) {

                    queuedRoom.setDoneFading(false);
                    currentRoom.setDoneFading(false);
                    currentRoom = queuedRoom;
                    enemies = currentRoom.enemies;
                    queuedRoom = null;
                    transitionPhase = false;
                }
            }
        }
    }


    public void checkCollisions(AnimatedSprite sprite) {
        for (int i = 0; i < currentRoom.collisionArray.size(); i++) {
            collide(sprite, currentRoom.collisionArray.get(i));

        }

    }

//    public void randomPositions() {
//
//        Random rand = new Random();
//        offset++;
//        for (int i = 0; i < collisionArray.size(); i++) {
//            int n = rand.nextInt(10) + 1;
//
//
//            if (offset > 300) {
//                offset = 1;
//            }
//
//
//            if (offset < 150 && offset > 1) {
//                collisionArray.get(i).setPositionX(collisionArray.get(i).getPositionX() + Math.random() * n);
//            }
//
//
//            if (offset < 300 && offset > 150) {
//                collisionArray.get(i).setPositionX(collisionArray.get(i).getPositionX() - Math.random() * n);
//            }
//
//
//        }
//
//
//    }


    public void collide(Sprite sprite, Platform platform) {
        if (sprite.collidesWith(platform)) {
            if (sprite.getPositionX() + sprite.getUnscaledWidth() > platform.getPositionX()
                    && sprite.getPositionY() + sprite.getUnscaledHeight() / 2 > platform.getPositionY() && sprite.getPositionY() < platform.getPositionY() + platform.getUnscaledHeight()
                    && sprite.getPositionX() < platform.getPositionX()) {
                sprite.setPositionX(platform.getPositionX() - sprite.getUnscaledWidth());
            } else if (sprite.getPositionX() <= platform.getPositionX() + platform.getUnscaledWidth() && sprite.getPositionY() + sprite.getUnscaledHeight() / 2 > platform.getPositionY() && sprite.getPositionY() < platform.getPositionY() + platform.getUnscaledHeight()
                    && sprite.getPositionX() + sprite.getUnscaledWidth() > platform.getPositionX() + platform.getUnscaledWidth()) {
                sprite.setPositionX(platform.getPositionX() + platform.getUnscaledWidth());
            } else if (sprite.getPositionX() + sprite.getUnscaledWidth() / 2 < platform.getPositionX() + platform.getUnscaledWidth() && sprite.getPositionX() > platform.getPositionX()) {
                if (sprite.getPositionY() < platform.getPositionY() + platform.getUnscaledHeight() && sprite.getPositionY() + sprite.getUnscaledHeight() > platform.getPositionY() + platform.getUnscaledHeight()) {
                    sprite.setPositionY(platform.getPositionY() + platform.getUnscaledHeight());
                } else if (sprite.getPositionY() + sprite.getUnscaledHeight() > platform.getPositionY() && sprite.getPositionY() < platform.getPositionY()) {
                    sprite.setPositionY(platform.getPositionY() - sprite.getUnscaledHeight());
                }

            }
        }
    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);

        if (state == STATE.MENU) {
            if (menuScreen != null) {
                menuScreen.draw(g);
                g.setFont(new Font("Helvetica", Font.BOLD, 36));
                g.setColor(Color.RED);
                g.drawString("TINY THIEF", 450, 120);
                g.setColor(Color.WHITE);
                g.drawString("Play", 470, 210);
                g.drawRect(getUnscaledWidth() / 2 + 480, 170, 180, 50);
//                g.drawString("Help",getUnscaledWidth()/2+550,310);
//                g.drawRect(getUnscaledWidth()/2+ 530,270,150,50);
                g.drawString("Quit", getUnscaledWidth() / 2 + 570, 350);
                g.drawRect(getUnscaledWidth() / 2 + 530, 320, 150, 50);
            }
        } else if (state == STATE.GAME) {
            if (background != null) {
                background.draw(g);

            }


            if (queuedRoom != null) {
                queuedRoom.draw(g);
            }

            if (currentRoom != null) {
                currentRoom.draw(g);
            }


            for (int i = 0; i < player.playerBullets.size(); i++) {
                Bullet bul = player.playerBullets.get(i);
                if (bul != null) {
                    bul.draw(g);
                }
            }

            for (int i = 0; i < enemies.size(); i++) {
                if (enemies.get(i) != null) {
                    enemies.get(i).draw(g);
                    if (enemies.get(i).enemyBullet != null) {
                        enemies.get(i).enemyBullet.draw(g);
                    }
                }
            }


            if (player != null) {
                player.draw(g);
            }


            g.setFont(new Font("ARIAL", Font.PLAIN, 48));
            if (complete == true || player.isDead) {
                g.drawString("You are dead!", 400, 40);
                g.drawString("Press P to play again", 400, 400);

            }


            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 140, 30);


            g.setColor(Color.RED);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));


            g.setColor(Color.BLACK);


            g.setColor(Color.RED);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));

//        for(int i=0;i<coverList.size();i++){ //COVER TESTING
//            g2d.draw(coverList.get(i));
//        }


//            for (int i = 0; i < collisionArray.size(); i++) {
//                collisionArray.get(i).draw(g);
//            }


            if (player.lifeCount > 0) {
                player.getLifeArray().get(0).draw(g);
                if (player.lifeCount > 1) {
                    player.getLifeArray().get(1).draw(g);
                    if (player.lifeCount > 2) {
                        player.getLifeArray().get(2).draw(g);
                    }
                }
            }

            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.setColor(Color.RED);
            g.drawString("--LIFE--", 400, 30);

            // g.drawString("Coin Count: " + Integer.toString(coinCount),200,30);
            g.drawString("Key Count: " + Integer.toString(keyCount), 200, 60);
            g.drawString(itemString, 200, 90);
            g.drawString("Knife Count: " + Integer.toString(knifeCount), 200, 150);


            if (pickpocket) {

                g.drawString("Press E to pickpocket", 400, 400);

            }
        } else if (state == STATE.PAUSE) {
            if (menuScreen != null) {
                menuScreen.draw(g);
                g.setFont(new Font("Helvetica", Font.BOLD, 36));
                g.setColor(Color.RED);
                g.drawString("GAME PAUSED", 450, 120);

                g.setColor(Color.WHITE);
                g.drawString("Resume", getUnscaledWidth() / 2 + 550, 210);
                g.drawRect(getUnscaledWidth() / 2 + 530, 170, 180, 50);
//                g.drawString("Help", getUnscaledWidth() / 2 + 550, 310);
//                g.drawRect(getUnscaledWidth() / 2 + 530, 270, 150, 50);
                g.drawString("Quit", getUnscaledWidth() / 2 + 550, 410);
                g.drawRect(getUnscaledWidth() / 2 + 530, 370, 150, 50);
            }
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
        if (state == STATE.MENU) {
            double mouseX = e.getX();
            double mouseY = e.getY();
            if (mouseX >= getUnscaledWidth() / 2 + 530 && mouseX <= getUnscaledWidth() / 2 + 680) {
                if (mouseY >= 200 && mouseY <= 260) {
                    state = STATE.GAME;
                }
            }
            if (mouseX >= getUnscaledWidth() / 2 + 530 && mouseX <= getUnscaledWidth() / 2 + 680) {
                if (mouseY >= 380 && mouseY <= 450) {

                    System.exit(1);
                }

            }
        } else if (state == STATE.GAME) {
            if (!player.isDead && knifeCount != 0) {
                Bullet bul = new Bullet("bullet", "knife.png", 0.2);
                double mouseX = e.getX();
                double mouseY = e.getY();
                double[] pressed = {mouseX, mouseY};
//                System.out.print(pressed[0]);

                knifeCount--;
                bul.setStart(player.getPositionX() + player.getUnscaledWidth() / 2, player.getPositionY() + player.getUnscaledHeight() / 2);
                bul.setEnd(mouseX, mouseY);


                TweenTransitions bulletPath = new TweenTransitions("linearTransition");
                Tween bulletmovement = new Tween(bul, bulletPath);
                bulletmovement.animate(TweenableParams.X, bul.startValX, bul.endValX, 0.2);
                bulletmovement.animate(TweenableParams.Y, bul.startValY, bul.endValY, 0.2);
                TweenJuggler.getInstance().add(bulletmovement);

                player.playerBullets.add(bul);

                player.handleEvent(throwKnife);
            }
        } else if (state == STATE.PAUSE) {
            double mouseX = e.getX();
            double mouseY = e.getY();

            if (mouseX >= getUnscaledWidth() / 2 + 530 && mouseX <= getUnscaledWidth() / 2 + 680) {
                if (mouseY >= 170 && mouseY <= 220) {
                    state = STATE.GAME;
                }
            }
            if (mouseX >= getUnscaledWidth() / 2 + 530 && mouseX <= getUnscaledWidth() / 2 + 680) {
                if (mouseY >= 270 && mouseY <= 320) {
                    System.out.println("help button");
                }
            }
            if (mouseX >= getUnscaledWidth() / 2 + 530 && mouseX <= getUnscaledWidth() / 2 + 680) {
                if (mouseY >= 370 && mouseY <= 420) {
                    System.out.println("quit");
                    System.exit(1);
                }

            }
        }

        /*
        double minWc = player.getCenterX() - (player.getUnscaledWidth() / 2);
        double minHc = player.getCenterY() - player.getUnscaledHeight() / 2;

        double maxWc = player.getCenterX() + (player.getUnscaledWidth() / 2);
        double maxHc = player.getCenterY() + player.getUnscaledHeight() / 2 + 20;
        */

    }


    public void lootEnemy(Enemy enemy) {
        keyCount += enemy.getKeyCount();
        knifeCount += enemy.getKnifeCount();

        if (enemy.getKeyCount() > 0) {
            itemString = "+" + Integer.toString(enemy.getKeyCount()) + " key";
        } else if (enemy.getKnifeCount() > 0) {
            itemString = "+" + Integer.toString(enemy.getKnifeCount()) + " knife";
        } else if (enemy.getKnifeCount() > 0 && enemy.getKeyCount() > 0) {
            itemString = "+" + Integer.toString(enemy.getKnifeCount()) + " knife & +" + Integer.toString(enemy.getKeyCount()) + " key";
        } else if (enemy.getKnifeCount() == 0 && enemy.getKeyCount() == 0) {
            itemString = "inventory empty";
        }
        enemy.emptyEnemyInventory();
    }

    public void switchRooms(Room original, Room next) {

    }


    public void moveGameX(double movex) {

        ArrayList<DisplayObjectContainer> children = getChildren();
        for (int i = 0; i < children.size(); i++) {
            DisplayObjectContainer child = children.get(i);
            if (child instanceof Room)
                ((Room) child).moveRoomX(movex);
        }
    }

    public void moveGameY(double movey) {

        ArrayList<DisplayObjectContainer> children = getChildren();
        for (int i = 0; i < children.size(); i++) {
            DisplayObjectContainer child = children.get(i);
            if (child instanceof Room)
                ((Room) child).moveRoomY(movey);
        }
    }

    public void damageThePlayer() {
        if (player.getLifeCount() != 0) {
            player.gotHurt();
            player.handleEvent(reduceLife);
            //player.setLifeCount(player.getLifeCount() - 1);
        }
        if (player.getLifeCount() == 0) {
            complete = true;
            player.isDead = true;
        }
    }


    /**
     * Quick main class that simply creates an instance of our game and starts the timer
     * that calls update() and draw() every frame
     */

    public static void main(String[] args) {
        clock = new GameClock();
        ProjectGame game = new ProjectGame();
        game.start();


    }

}
