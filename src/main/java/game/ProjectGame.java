package game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;

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
    boolean complete = false;
    Event collidedEvent;
    Event throwKnife;
    Coin coin = new Coin("coin", "Coin4.png");

    Sprite background = new Sprite("Background", "background.png");
    Sprite menuScreen = new Sprite("menuScreen", "menu-screen.jpg");
    private Player player;


    int keyCount;
    int knifeCount;
    String itemString = "";
    boolean pickpocket = false;

    ///Level 0////

    int currentLevel;

    Level0 myLevel;
    Level1 myLevel1;

    ahmedslevel myLevel2;

    BossLevel bossLevel;

    BrighamLevel myLevel3;

    AlternatingSpikesLevel level4;

    TutorialLevel1 tutorial;


    ArrayList<Enemy> enemies;

    Enemy pickpocketEnemy;


    static GameClock clock;

    SoundManagerClass soundEffects = new SoundManagerClass();

    private enum STATE {MENU, GAME, PAUSE,COMPLETE}



    private STATE state = STATE.MENU;


    Room currentRoom;
    Room queuedRoom;

    double transitionY;
    double transitionYSpeed = 9;
    double transitionYCurrent;
    boolean transitionPhase = false;


    Sprite pressE;
    Sprite keyIcon = new Sprite("keyIcon", "key_icon.png");
    Sprite knifeIcon = new Sprite("knifeIcon", "knife_icon.png");

    Sprite findKey = new Sprite("findKey", "find_key.png");

    Sprite getToRoom = new Sprite("getToRoom", "get_to_room.png");

    Sprite killTurtle = new Sprite("Keith", "kill_turtle.png");
    Sprite playButton = new Sprite("play","play.png");
    Sprite quitButton = new Sprite("quit","quit.png");


    int currentQuestObjective;

    /**
     * Constructor. See constructor in Game.java for details on the parameters given
     */
    public ProjectGame() {

        super("BulletHell", 1200, 900);
        die = new Event();
        backgroundMusic = new SoundManagerClass();
        playButton.setPositionX(420);
        playButton.setPositionY(180);
        quitButton.setPositionX(623);
        quitButton.setPositionY(181);
        //x = 0;
        reduceLife = new Event();
        fadeOutEvent = new Event();
        PickedUpEvent = new Event();
        die.setEventType("playerDeath");
        fadeOutEvent.setEventType("FadeOut");
        reduceLife.setEventType("Collision");
        collidedEvent = new Event();
        collidedEvent.setEventType("CollidedEvent");
        throwKnife = new Event();
        throwKnife.setEventType("throwKnife");


        keyIcon.setPositionX(200);
        keyIcon.setPositionY(40);

        knifeIcon.setPositionX(205);
        knifeIcon.setPositionY(95);

        currentLevel = 0;//0 = base , 3=brigham's level


        PickedUpEvent.setEventType("CoinPickedUp");

        this.addEventListener(myQuestManager, collidedEvent.getEventType());


        background.setScaleX(5);
        background.setScaleY(5);

        background.setScaleX(5);
        background.setScaleY(5);


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

        pressE = new Sprite("pressE", "pressE.png");
        player.addChild(pressE);
        pressE.setPositionY(-50);


        coin.setPositionY(250);
        coin.setPositionX(660);

        findKey.setPositionX(500);
        findKey.setPositionY(-20);

        getToRoom.setPositionX(500);
        getToRoom.setPositionY(-20);

        killTurtle.setPositionX(500);
        killTurtle.setPositionY(-20);


        //Rectangle2D rect = new Rectangle2D.Float(600,400,700,500);
        //coverList = new ArrayList<Rectangle2D>(); //list of cover sprites
        //coverList.add(rect);

        ///////////////////////////////////////LEVEL 0 ////////////////////////////////////////////////////////////////

        if (currentLevel == 0) {

            tutorial = new TutorialLevel1("Tutorial");
            tutorial.run();
            addChild(tutorial);

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

            level4 = new AlternatingSpikesLevel("Room6");
            level4.run();
            level4.hide();
            addChild(level4);

            bossLevel = new BossLevel("Room5", player);
            bossLevel.run();
            bossLevel.hide();
            addChild(bossLevel);



            tutorial.mapDoorToRoom(0,myLevel);
            myLevel.mapDoorToRoom(0, myLevel1);
            myLevel1.mapDoorToRoom(0, myLevel2);
            myLevel2.mapDoorToRoom(0, myLevel3);
            myLevel3.mapDoorToRoom(0, level4);
            level4.mapDoorToRoom(0, bossLevel);
            //myLevel3.mapDoorToRoom(0,bossLevel);


            currentRoom = tutorial;

            //myLevel1.mapDoorToRoom(0,myLevel2);

            //myLevel2.run();
            //myLevel2.hide();


            //myLevel2.mapDoorToRoom(0,myLevel3);
            //myLevel3.run();
            //myLevel3.hide();
            currentQuestObjective = 0;

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


        backgroundMusic.playSoundEffect("resources/oceanOperator.wav", 100);

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
            if (player.getLifeCount() <= 0) {
                complete = true;
                player.isDead = true;
            }


            if (transitionYCurrent < transitionY) {
                moveGameY(transitionYSpeed);
                transitionYCurrent += transitionYSpeed;
            }

            if(complete && !player.isDead){
                System.out.println("you won!");
                state = STATE.COMPLETE;


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
            if (pressedKeys.contains(KeyEvent.getKeyText(KeyEvent.VK_W))) {
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
                    keyCount += pickpocketEnemy.pickpocketKeys();
                    int knives = pickpocketEnemy.pickpocketKnives();
                    knifeCount += knives;
                    System.out.println(knives);
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
                            soundEffects.playSoundEffect("resources/chains.wav",0);
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
                    removeAll();
                    complete = false;
                    currentLevel = 0;


                    tutorial = new TutorialLevel1("Tutorial");
                    tutorial.run();
                    addChild(tutorial);


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

                    level4 = new AlternatingSpikesLevel("Room6");
                    level4.run();
                    level4.hide();
                    addChild(level4);


                    bossLevel = new BossLevel("Room5", player);
                    bossLevel.run();
                    bossLevel.hide();
                    addChild(bossLevel);


                    tutorial.mapDoorToRoom(0,myLevel);
                    myLevel.mapDoorToRoom(0, myLevel1);
                    myLevel1.mapDoorToRoom(0, myLevel2);
                    myLevel2.mapDoorToRoom(0, myLevel3);
                    myLevel3.mapDoorToRoom(0, level4);
                    level4.mapDoorToRoom(0, bossLevel);
                    currentRoom = tutorial;

                    enemies = currentRoom.enemies;

                    player.isDead = false;
                    player.setLifeCount(3);
                    player.setPositionX(550);
                    player.setPositionY(700);
                    knifeCount = 4;
                    keyCount = 0;
                    backgroundMusic.stop();
                    backgroundMusic.playSoundEffect("resources/oceanOperator.wav", 100);
                } else {
                    state = STATE.PAUSE;

                }
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
                                break;
                            }
                            for (int j = 0; j < currentRoom.collisionArray.size(); j++) {
                                if (currentEnemy.enemyBullet.collidesWith(currentRoom.collisionArray.get(j))) {
                                    currentEnemy.enemyBullet = null;
                                    break;
                                }
                            }

                        }
                    }


                    for (int j = 0; j < player.playerBullets.size(); j++) {
                        Bullet bul = player.playerBullets.get(j);
//                        for (int k = 0; k < currentRoom.collisionArray.size(); k++) {
//                            if (bul.collidesWith(currentRoom.collisionArray.get(k))) {
//                                player.playerBullets.remove(j);
//                                break;
//                            }
//                        }
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
                                if (player.playerBullets.size() > j)
                                    player.playerBullets.remove(j);
                            }

                        }
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
            }

            currentRoom.update();

            if (queuedRoom != null) {
                queuedRoom.update();

                if (queuedRoom.getDoneFading() && currentRoom.getDoneFading()) {

                    queuedRoom.setDoneFading(false);
                    currentRoom.setDoneFading(false);
                    if(queuedRoom == bossLevel){
                        backgroundMusic.stop();
                       bossLevel.intro();
                    }
                    currentRoom = queuedRoom;
                    enemies = currentRoom.enemies;
                    queuedRoom = null;
                    transitionPhase = false;
                    currentQuestObjective = 0;
                }
            }
        }
        if (keyCount > 0){
            currentQuestObjective = 1;
        }
        else if(currentRoom == bossLevel){
            currentQuestObjective = 2;
        }

        if(bossLevel.endgame==true){
            state = STATE.COMPLETE;
            System.out.println("you won!");
            quitButton.setPositionX(300);

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
                g.setFont(new Font("Papyrus", Font.BOLD, 48));
                g.setColor(Color.RED);


                playButton.draw(g);
                quitButton.draw(g);

                g.drawString("TINY THIEF", 420, 120);
                /*
                g.seolor(Color.RED);
                g.drawString("PLAY", getUnscaledWidth()/2 + 533, 210);
                g.drawRect(getUnscaledWidth() / 2 + 520, 170, 150, 50);
//                g.drawString("Help",getUnscaledWidth()/2+550,310);
//                g.drawRect(getUnscaledWidth()/2+ 530,270,150,50);
                g.drawString("QUIT", getUnscaledWidth() / 2 + 563, 300);
                g.drawRect(getUnscaledWidth() / 2 + 530, 320, 150, 50);
        */



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
            if (complete == true && player.isDead) {

                Sprite gameOver = new Sprite("gameOver", "gameOver.png");
                gameOver.setPositionX(300);
                gameOver.setPositionY(300);
                gameOver.draw(g);

            }
            else if(complete == true && !player.isDead){
                //congrats screen
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

            g.setFont(new Font("Papyrus", Font.PLAIN, 34));
            g.setColor(Color.WHITE);
            //g.drawString("--LIFE--", 400, 30);

            // g.drawString("Coin Count: " + Integer.toString(coinCount),200,30);
           // g.drawString("Key Count: " + Integer.toString(keyCount), 200, 60);
            keyIcon.draw(g);
            g.drawString("x" + Integer.toString(keyCount), 235, 75);
           // g.drawString(itemString, 200, 90);
           // g.drawString("Knife Count: " + Integer.toString(knifeCount), 200, 150);
            knifeIcon.draw(g);
            g.drawString("x" + Integer.toString(knifeCount), 235, 125);

            if (pickpocket) {
                pressE.setTransparency(1);
                //g.drawString("Press E to pickpocket", 400, 400);
            }
            else{
                pressE.setTransparency(0);
            }
            if(currentQuestObjective == 0){
                findKey.draw(g);
            }
            else if(currentQuestObjective ==1){
                getToRoom.draw(g);
            }
            else if(currentQuestObjective == 2){
                killTurtle.draw(g);
            }
        } else if (state == STATE.PAUSE) {
            if (menuScreen != null) {
                menuScreen.draw(g);
                menuScreen.draw(g);
                g.setFont(new Font("Papyrus", Font.BOLD, 34));
                g.setColor(Color.RED);


                playButton.draw(g);
                quitButton.draw(g);

                g.drawString("Game Paused: Press Play to Resume", 350, 120);
            }

        }else if(state == STATE.COMPLETE){
            if(menuScreen!=null) {
                menuScreen.draw(g);

                g.setFont(new Font("Papyrus", Font.BOLD, 34));
                g.setColor(Color.RED);


                quitButton.draw(g);

                g.drawString("Congratulations, you won!", 350, 120);
            }
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
        if (state == STATE.MENU) {
            double mouseX = e.getX();
            double mouseY = e.getY();

            if (mouseX >= playButton.getPositionX() && mouseX <=playButton.getPositionX()+ playButton.getUnscaledWidth()) {
                if (mouseY >= playButton.getPositionY()+20 && mouseY <= playButton.getPositionY() + playButton.getUnscaledHeight()+20) {
                    state = STATE.GAME;
                    System.out.println("pressed");
                }
            }
            if (mouseX >= quitButton.getPositionX() && mouseX <= quitButton.getPositionX()+quitButton.getUnscaledWidth()) {
                if (mouseY >= quitButton.getPositionY()+20 && mouseY <= quitButton.getPositionY()+quitButton.getUnscaledHeight()+20) {

                    System.exit(1);
                }

            }
        } else if (state == STATE.GAME) {
            if (!player.isDead && knifeCount != 0) {

                double mouseX = e.getX();
                double mouseY = e.getY();
                System.out.println(mouseX);
                System.out.println(mouseY);
                Bullet bul = new Bullet("bullet", "knife.png", 0.2, player.getPositionX() + player.getUnscaledWidth() / 2, player.getPositionY() + player.getUnscaledHeight() / 2, mouseX, mouseY);

//                System.out.print(pressed[0]);

                knifeCount--;


//                bul.setStart(player.getPositionX() + player.getUnscaledWidth() / 2, player.getPositionY() + player.getUnscaledHeight() / 2);
//                bul.setEnd(mouseX+this.getUnscaledWidth(), mouseY+this.getUnscaledHeight());


//                TweenTransitions bulletPath = new TweenTransitions("linearTransition");
//                Tween bulletmovement = new Tween(bul, bulletPath);
//                bulletmovement.animate(TweenableParams.X, bul.startValX, bul.endValX+this.getUnscaledWidth(), 0.2);
//                bulletmovement.animate(TweenableParams.Y, bul.startValY, bul.endValY+this.getUnscaledHeight(), 0.2);
//                TweenJuggler.getInstance().add(bulletmovement);

                player.playerBullets.add(bul);

                player.handleEvent(throwKnife);
            }
        } else if (state == STATE.PAUSE) {
            double mouseX = e.getX();
            double mouseY = e.getY();

            if (mouseX >= playButton.getPositionX() && mouseX <=playButton.getPositionX()+ playButton.getUnscaledWidth()) {
                if (mouseY >= playButton.getPositionY()+20 && mouseY <= playButton.getPositionY() + playButton.getUnscaledHeight()+20) {
                    state = STATE.GAME;
                    System.out.println("pressed");
                }
            }
            if (mouseX >= quitButton.getPositionX() && mouseX <= quitButton.getPositionX()+quitButton.getUnscaledWidth()) {
                if (mouseY >= quitButton.getPositionY()+20 && mouseY <= quitButton.getPositionY()+quitButton.getUnscaledHeight()+20) {

                    System.exit(1);
                }

            }
        }else if(state == STATE.COMPLETE) {

            double mouseX = e.getX();
            double mouseY = e.getY();
            if (mouseX >= quitButton.getPositionX() && mouseX <= quitButton.getPositionX() + quitButton.getUnscaledWidth()) {
                if (mouseY >= quitButton.getPositionY() + 20 && mouseY <= quitButton.getPositionY() + quitButton.getUnscaledHeight() + 20) {

                    System.exit(1);
                }

            }



        /*
        double minWc = player.getCenterX() - (player.getUnscaledWidth() / 2);
        double minHc = player.getCenterY() - player.getUnscaledHeight() / 2;

        double maxWc = player.getCenterX() + (player.getUnscaledWidth() / 2);
        double maxHc = player.getCenterY() + player.getUnscaledHeight() / 2 + 20;
        */
        }

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
        }
        if (player.getLifeCount() <= 0) {
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
