package game;

import java.awt.*;
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

    GameClock GlobalCooldown;
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
    //  Platformer platform = new Platformer("Rectangele", "platform.png");
    //  Platformer platform1 = new Platformer("Rectangele", "platform.png");

    boolean start = false;

    Sprite background = new Sprite("Background", "background.png");
    ArrayList<Platformer> collisionArray = new ArrayList<>();
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
    private Enemy enemy01;
    private Enemy enemy02;


    int offset = 1;

    int damageCap = 100;
    int damageTimer;
    int currentLevel;

    Level0 myLevel;
    Level1 myLevel1;

    ArrayList<Bullet> playerBullets = new ArrayList<Bullet>();
    ArrayList<Enemy> enemies;

    Enemy pickpocketEnemy;


    static GameClock clock;

    SoundManagerClass soundEffects = new SoundManagerClass();


    Room currentRoom;
    Room queuedRoom;

    double transitionY;
    double transitionYSpeed = 5;
    double transitionYCurrent;

    /**
     * Constructor. See constructor in Game.java for details on the parameters given
     */
    public ProjectGame() {
        super("BulletHell", 1200, 900);
        die = new Event();
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

        currentLevel = 0;

        damageTimer = 100;


        PickedUpEvent.setEventType("CoinPickedUp");

        this.addEventListener(myQuestManager, collidedEvent.getEventType());


        background.setScaleX(5);
        background.setScaleY(5);


//        lifeArray.add(life1);
//        lifeArray.add(life2);
//        lifeArray.add(life3);
//        lifeCount = lifeArray.size();
        life1.setPositionX(390);
        life1.setPositionY(40);
        life2.setPositionX(420);
        life2.setPositionY(40);
        life3.setPositionX(450);
        life3.setPositionY(40);
        background.setScaleX(5);
        background.setScaleY(5);


        //player = new AnimatedSprite("player", "resources/player_sheet.png", "idle_right");
        player = new Player("player", "resources/player_sheet.png", "idle_right");
        player.setSpriteSheetJson("resources/player_sheet.json");
        player.setDelay(100);
        // player.setHasPhysics(true);
        keyCount = 0;
        knifeCount = 0;
        // player.setHasPhysics(true);

        //  platform.setPositionX(50);
        //  platform.setPositionY(550);


        //  platform1.setPositionX(150);
        //  platform1.setPositionY(150);


        player.setPositionX(550);
        player.setPositionY(700);
        enemies = new ArrayList<>();
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

        coin.setPositionY(250);
        coin.setPositionX(660);
        //Rectangle2D rect = new Rectangle2D.Float(600,400,700,500);
        //coverList = new ArrayList<Rectangle2D>(); //list of cover sprites
        //coverList.add(rect);

        ///////////////////////////////////////LEVEL 0 ////////////////////////////////////////////////////////////////
        if (currentLevel == 0) {

            enemies.add(enemy01);
            enemies.add(enemy02);
            myLevel = new Level0("Room1");
            addChild(myLevel);
            myLevel.registerEnemy(enemy01);
            myLevel.registerEnemy(enemy02);
            myLevel.run();

            myLevel1 = new Level1("Room2");
            addChild(myLevel1);
           // myLevel1.registerEnemy(enemy01);
          //  myLevel.registerEnemy(enemy02);

            myLevel1.run();
            myLevel1.hide();
            
            myLevel.mapDoorToRoom(0, myLevel1);

            currentRoom = myLevel;

        }

        pickpocketEnemy = null;

        transitionY = 615;
        transitionYCurrent = 615;

    }


    /**
     * Engine will automatically call sprite update method once per frame and pass to us
     * the set of keys (as strings) that are currently being pressed down
     */
    @Override
    public void update(ArrayList<String> pressedKeys) {


        super.update(pressedKeys);

        //moveGameY(1);


        if (player != null && !player.isDead) {
            player.update(pressedKeys);
        }

        if (damageTimer < damageCap) {
            damageTimer++;
        }

        if(transitionYCurrent < transitionY) {
            moveGameY(transitionYSpeed);
            transitionYCurrent += transitionYSpeed;
        }


        if (player != null && !player.isDead) {
            for (int i = 0; i < enemies.size(); i++) {
                if (player.playerCollidesWith(enemies.get(i)) && enemies.get(i).dead == false && damageTimer >= damageCap) {
                    damageTimer = 0;
                    if(player.getLifeCount() != 0)
                        player.getLifeArray().get(player.getLifeCount()-1).handleEvent(reduceLife);
                    player.setLifeCount(player.getLifeCount()-1);
                    if (player.getLifeCount() == 0) {
                        complete = true;
                    }
                }
            }
            if (player.playerCollidesWith(coin)) {
                coin.handleEvent(collidedEvent);
                myQuestManager.handleEvent(PickedUpEvent);
            }
            player.update();
            checkCollisions(player);


            for (int i = 0; i < playerBullets.size(); i++) {
                Bullet bul = playerBullets.get(i);
                bul.update(pressedKeys);
                if (bul.getShotTimer() >= bul.getShotCap()) {

                    playerBullets.remove(i);
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
            if(!pickpocket)
                pickpocketEnemy = null;
        }

        if (pressedKeys.contains("E")) {
//            Random rand = new Random();
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
                    } else if(player.getHitBox().intersects(currentRoom.getDoors().get(i).getDoorCollider()) && currentRoom.getDoors().get(i).stateName == "door_open") {
                        currentRoom.fadeOut();
                        queuedRoom = currentRoom.getDoors().get(i).getNextRoom();
                        queuedRoom.fadeIn();
                        transitionYCurrent = 0;
                    }
                }
            

            pressedKeys.remove("E");
        }

        if (pressedKeys.contains("P")) {
            for (int i = 0; i < player.getLifeArray().size(); i++) {
                complete = false;
                player.isDead = false;
                player.getLifeArray().get(i).toggleVisibility();
                player.setLifeCount(player.getLifeArray().size());
                player.setPositionX(550);
                player.setPositionY(700);
            }
        }
        for (int i = 0; i < enemies.size(); i++) {
            Enemy currentEnemy = enemies.get(i);
            currentEnemy.update();
            if (!currentEnemy.dead) {
                if (currentEnemy.isInView(player, currentRoom.coverList)) {
                    if (complete == false) {
                        if (currentEnemy.enemyBullet == null) {
                            currentEnemy.bulletClock = new GameClock();
                            currentEnemy.enemyBullet = new Bullet("bullet", "knife.png", 0.2);
                            currentEnemy.enemyBullet.setStart(currentEnemy.getPositionX() + currentEnemy.getUnscaledWidth() / 2, currentEnemy.getPositionY() + currentEnemy.getUnscaledHeight() / 2);
                            currentEnemy.enemyBullet.setEnd(player.getPositionX(), player.getPositionY());
                            TweenTransitions enemyBulletPath = new TweenTransitions("linearTransition");
                            Tween enemyBulletmovement = new Tween(currentEnemy.enemyBullet, enemyBulletPath);
                            enemyBulletmovement.animate(TweenableParams.X, currentEnemy.enemyBullet.startValX, currentEnemy.enemyBullet.endValX, 0.2);
                            enemyBulletmovement.animate(TweenableParams.Y, currentEnemy.enemyBullet.startValY, currentEnemy.enemyBullet.endValY, 0.2);
                            TweenJuggler.getInstance().add(enemyBulletmovement);
                            currentEnemy.handleEvent(throwKnife);
                        }
                    }
                    if (currentEnemy.bulletClock != null) { //delete bullet after .2 seconds
                        if (currentEnemy.bulletClock.getElapsedTime() > 300) {
                            currentEnemy.bulletClock = null;
                            currentEnemy.enemyBullet = null;
                        }
                    }
                    if (currentEnemy.enemyBullet != null) {
                        if (currentEnemy.enemyBullet.collidesWith(player)) {
                            player.getLifeArray().get(player.getLifeCount() - 1).handleEvent(reduceLife);////AMED PLEASE FIX THIS LINE FOR ME!!!!
                            currentEnemy.enemyBullet = null;
                            player.setLifeCount(player.getLifeCount() - 1);
                            if (player.getLifeCount() == 0) {
                                complete = true;
                                player.isDead = true;
                                break;
                            }
                        }
                    }
                }


                for (int j = 0; j < playerBullets.size(); j++) {
                    Bullet bul = playerBullets.get(j);
                    for (int k = 0; k < currentRoom.collisionArray.size(); k++) {
                        if (bul.collidesWith(currentRoom.collisionArray.get(k))) {
                            playerBullets.remove(j);
                         //  System.out.println("collided with cover");
                            break;
                        }
                    }
                    if (bul.collidesWith(enemies.get(i))) {
                        enemies.get(i).dead = true;
                        enemies.get(i).enemyBullet = null;
                         if(enemies.get(i).getStateName().contains("right")) {
                             enemies.get(i).setDelay(90);
                             enemies.get(i).setAnimationState("dying right", "dead right");
                         } else {
                             enemies.get(i).setDelay(90);
                             enemies.get(i).setAnimationState("dying left", "dead left");
                         }
                        playerBullets.remove(j);
                    }
                }
            }
        }

        currentRoom.update();

        if(queuedRoom != null) {
            queuedRoom.update();

            if(queuedRoom.getDoneFading() && currentRoom.getDoneFading()) {
                
                queuedRoom.setDoneFading(false);
                currentRoom.setDoneFading(false);
                currentRoom = queuedRoom;
                queuedRoom = null;
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


    public void collide(Sprite sprite, Platformer platform) {
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


        if (background != null) {
            background.draw(g);

        }


        if(queuedRoom != null) {
            queuedRoom.draw(g);
        }

        if(currentRoom != null) {
            currentRoom.draw(g);
        }


        for (int i = 0; i < playerBullets.size(); i++) {
            Bullet bul = playerBullets.get(i);
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
        if (complete == true) {
            g.drawString("You are dead!", 400, 40);
            g.drawString("Press P to play again", 400, 400);

        }
        if (keyCount == 5) {
            g.drawString("Congrats, you win!", 400, 40);
            pause();
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


        if (player.life1 != null) {
            for (int i = 0; i < player.getLifeArray().size(); i++) {
                if (player.getLifeArray().get(i).getVisibility())
                    player.getLifeArray().get(i).draw(g);

            }
        }

        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.setColor(Color.RED);
        g.drawString("--LIFE--", 400, 30);

        // g.drawString("Coin Count: " + Integer.toString(coinCount),200,30);
        g.drawString("Key Count: " + Integer.toString(keyCount), 200, 60);
        g.drawString(itemString, 200, 90);


        if (pickpocket) {

            g.drawString("Press E to pickpocket", 400, 400);

        }


    }


    @Override
    public void mousePressed(MouseEvent e) {
        if (!player.isDead) {
            Bullet bul = new Bullet("bullet", "knife.png", 0.2);
            double mouseX = e.getX();
            double mouseY = e.getY();

            bul.setStart(player.getPositionX() + player.getUnscaledWidth() / 2, player.getPositionY() + player.getUnscaledHeight() / 2);
            bul.setEnd(mouseX, mouseY);


            TweenTransitions bulletPath = new TweenTransitions("linearTransition");
            Tween bulletmovement = new Tween(bul, bulletPath);
            bulletmovement.animate(TweenableParams.X, bul.startValX, bul.endValX, 0.2);
            bulletmovement.animate(TweenableParams.Y, bul.startValY, bul.endValY, 0.2);
            TweenJuggler.getInstance().add(bulletmovement);

            playerBullets.add(bul);

            player.handleEvent(throwKnife);

        /*
        double minWc = player.getCenterX() - (player.getUnscaledWidth() / 2);
        double minHc = player.getCenterY() - player.getUnscaledHeight() / 2;

        double maxWc = player.getCenterX() + (player.getUnscaledWidth() / 2);
        double maxHc = player.getCenterY() + player.getUnscaledHeight() / 2 + 20;
        */
        }
    }


    public void lootEnemy(Enemy enemy) {
        keyCount += enemy.getKeyCount();
        knifeCount += enemy.getKnifeCount();

        if(enemy.getKeyCount() > 0) {
            itemString = "+" + Integer.toString(enemy.getKeyCount()) + " key";
        }
        else if(enemy.getKnifeCount() > 0) {
            itemString = "+" + Integer.toString(enemy.getKnifeCount()) + " knife";
        }
        else if(enemy.getKnifeCount() > 0 && enemy.getKeyCount() > 0) {
            itemString = "+" + Integer.toString(enemy.getKnifeCount()) + " knife & +" + Integer.toString(enemy.getKeyCount()) + " key";
        }
        else if(enemy.getKnifeCount() == 0 && enemy.getKeyCount() == 0) {
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
              if(child instanceof Room)
                  ((Room)child).moveRoomX(movex);
          }
    }

    public void moveGameY(double movey) {

       ArrayList<DisplayObjectContainer> children = getChildren();
       for (int i = 0; i < children.size(); i++) {
           DisplayObjectContainer child = children.get(i);
           if(child instanceof Room)
               ((Room)child).moveRoomY(movey);
       }                                                              }

    public void setGamePositionY(double y) {

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
