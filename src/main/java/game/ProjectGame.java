package game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import engine.Tweens.*;
import engine.display.AnimatedSprite;
import engine.display.Game;
import engine.display.Sprite;
import engine.events.Event;
import engine.util.GameClock;


public class ProjectGame extends Game {


    /* Create a sprite object for our game. We'll use mario */
    Sprite mario = new Sprite("Mario", "gator.png");


    QuestManager myQuestManager = new QuestManager();
    Event PickedUpEvent;
    Event fadeOutEvent;
    Event die;
    TweenEvent tweenEvent;
    boolean complete = false;
    Event collidedEvent;
    Platformer platform = new Platformer("Rectangele", "platform.png");
    Platformer platform1 = new Platformer("Rectangele", "platform.png");
    boolean start = false;
    SoundManagerClass music = new SoundManagerClass();
    Sprite marioBackground = new Sprite("Background", "dungeon_concept.png");
    ArrayList<Platformer> collisionArray = new ArrayList<Platformer>();
    Bullet bullet;
    private AnimatedSprite player;
    private Enemy enemy;

    ArrayList<Double> listArray = new ArrayList<Double>();


    int offset = 1;


    static GameClock clock;

    /**
     * Constructor. See constructor in Game.java for details on the parameters given
     */
    public ProjectGame() {
        super("Lab Four Test Game", 1200, 900);
        die = new Event();
        fadeOutEvent = new Event();
        PickedUpEvent = new Event();
        die.setEventType("playerDeath");
        fadeOutEvent.setEventType("FadeOut");
        this.addEventListener(myQuestManager, PickedUpEvent.getEventType());
        this.addEventListener(myQuestManager, die.getEventType());
        collidedEvent = new Event();
        collidedEvent.setEventType("CollidedEvent");
        this.addEventListener(myQuestManager, collidedEvent.getEventType());


        collisionArray.add(platform);







        player = new AnimatedSprite("animate");
       // player.setHasPhysics(true);

        platform.setPositionX(50);
        platform.setPositionY(550);


        platform1.setPositionX(150);
        platform1.setPositionY(150);

        music.playMusic("resources/bowsersound.mp3");


        player.setPositionX(750);
        player.setPositionY(450);


        enemy = new Enemy("enemy","gator.png");
        enemy.setPositionX(570);
        enemy.setPositionY(200);
        enemy.addRoute(150,0);//create square route
        enemy.addRoute(0,-300);
        enemy.addRoute(-150,0);
        enemy.addRoute(0,300);



        enemy.setSpeed(3);


        TweenTransitions playerIntro = new TweenTransitions("linearTransition");
        Tween playerTween = new Tween(player, playerIntro);
        playerTween.animate(TweenableParams.Y, 1000, 650, 2);


        TweenTransitions coinCatch = new TweenTransitions("easeInOut");


        TweenJuggler.getInstance().add(playerTween);
        //tweenJuggler.add(playerTween);


    }



    /**
     * Engine will automatically call sprite update method once per frame and pass to us
     * the set of keys (as strings) that are currently being pressed down
     */
    @Override
    public void update(ArrayList<String> pressedKeys) {
        Point mousePosition = MouseInfo.getPointerInfo().getLocation();

        super.update(pressedKeys);
        if (start) {
            randomPositions();
        }

		
		/* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialispriteed */
        if (player != null) {
            if (player.collidesWith(enemy)){
                player.toggleVisibility();
                complete = true;
            }
            //float deltaX = (float) (mousePosition.getX()-player.getPositionX());
            //float deltaY = (float) (mousePosition.getY()-player.getPositionY());
            mario.update(pressedKeys);
            //player.setRotation((360 + Math.toDegrees(Math.atan2(deltaY, deltaX))) % 360);
            player.update();
            checkCollisions(player);
            TweenJuggler.getInstance().nextFrame();

        }

        if (bullet != null) {
            //bullet.setPositionX(bullet.startValX);
            if(bullet.collidesWith(enemy)){
                enemy.dead = true;
            }
        }


        if (pressedKeys.contains("W")) {
           player.walkNorth();
        }

        if (pressedKeys.contains("S")) {


            player.walkSouth();


           // music.playSoundEffect("resources/song100.wav");

        }
        if (pressedKeys.contains("D")) {

            mario.setPositionX(mario.getPositionX() + 10);

            player.walkEast();


        }
        if (pressedKeys.contains("A")) {
            player.walkWest();
        }


        /*
        if (pressedKeys.contains("A")) {
            mario.setScaleX(mario.getScaleX() + 0.1);
            player.setScaleX(player.getScaleX() + 0.1);

        }
        if (pressedKeys.contains("S")) {
            mario.setScaleY(mario.getScaleY() - 0.1);
            player.setScaleY(player.getScaleY() - 0.1);
        }
        if (pressedKeys.contains("Q")) {
            mario.setRotation(mario.getRotation() + 1);

            player.setRotation(player.getRotation() + 1);

        }
        if (pressedKeys.contains("W")) {
            mario.setRotation(mario.getRotation() - 1);

            player.setRotation(player.getRotation() - 1);
        }
        if (pressedKeys.contains("Z")) {
            mario.setTransparency(mario.getTransparency() + 0.01f);
            player.setTransparency(player.getTransparency() + 0.01f);

        }
        if (pressedKeys.contains("X")) {

            mario.setTransparency(mario.getTransparency() - 0.01f);
            player.setTransparency(player.getTransparency() - 0.01f);


        }
        if (pressedKeys.contains("I")) {
            mario.setPivotY(mario.getPivotY() - 3);
            player.setPivotY(player.getPivotY() - 3);

        }
        if (pressedKeys.contains("K")) {
            mario.setPivotY(mario.getPivotY() + 3);
            player.setPivotY(player.getPivotY() + 3);

        }
        if (pressedKeys.contains("J")) {
            mario.setPivotX(mario.getPivotX() - 3);
            player.setPivotX(player.getPivotX() - 3);

        }
        if (pressedKeys.contains("L")) {
            mario.setPivotX(mario.getPivotX() + 3);

            player.setPivotX(player.getPivotX() + 3);
        }
        if (pressedKeys.contains("V")) {

            mario.toggleVisibility();
            player.toggleVisibility();


        }
        */
        if (enemy != null && enemy.dead == false) {
            enemy.setPositionY(enemy.getPositionY() + enemy.getPathY());
            enemy.setPositionX(enemy.getPositionX() + enemy.getPathX());
        }

    }

    public void checkCollisions(AnimatedSprite sprite) {
        for (int i = 0; i < collisionArray.size(); i++) {
            collide(sprite, collisionArray.get(i));

        }

    }

    public void randomPositions() {

        Random rand = new Random();
        double distanceTraveled;

        boolean forward = true;
        offset++;
        for (int i = 0; i < collisionArray.size(); i++) {
            int n = rand.nextInt(10) + 1;


            if (offset > 300) {
                offset = 1;
            }


            if (offset < 150 && offset > 1) {
                collisionArray.get(i).setPositionX(collisionArray.get(i).getPositionX() + Math.random() * n);
            }


            if (offset < 300 && offset > 150) {
                collisionArray.get(i).setPositionX(collisionArray.get(i).getPositionX() - Math.random() * n);
            }


        }


    }

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
        if(bullet != null){
            bullet.draw(g);
        }

        if (marioBackground != null) {
            marioBackground.draw(g);

        }
        if (player != null) {
            player.draw(g);

        }
        if (enemy != null) {
            enemy.draw(g);
        }


        g.setFont(new Font("ARIAL", Font.PLAIN, 48));
        if (complete == true) {
            g.drawString("You are dead!", 400, 40);
            pause();

        }


        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 140, 30);


        g.setColor(Color.RED);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));


        g.setColor(Color.BLACK);


        g.setColor(Color.RED);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        if (platform != null) {


            for (int i = 0; i < collisionArray.size(); i++) {
                collisionArray.get(i).draw(g);

            }


        }


    }




    @Override
    public void mouseClicked(MouseEvent e) {
        double mouseX = e.getX();
        double mouseY = e.getY();
        bullet = new Bullet("bullet", "Coin.png");
        bullet.setStart(player.getPositionX(),player.getPositionY());
        bullet.setEnd(mouseX,mouseY);


        TweenTransitions bulletPath = new TweenTransitions("linearTransition");
        Tween bulletmovement = new Tween(bullet, bulletPath);
        bulletmovement.animate(TweenableParams.X, bullet.startValX, bullet.endValX, 1);
        bulletmovement.animate(TweenableParams.Y, bullet.startValY, bullet.endValY, 1);
        TweenJuggler.getInstance().add(bulletmovement);
        /*
        double minWc = player.getCenterX() - (player.getUnscaledWidth() / 2);
        double minHc = player.getCenterY() - player.getUnscaledHeight() / 2;

        double maxWc = player.getCenterX() + (player.getUnscaledWidth() / 2);
        double maxHc = player.getCenterY() + player.getUnscaledHeight() / 2 + 20;
        */

    }

    /**
     * Quick main class that simply creates an instance of our game and starts the timer
     * that calls update() and draw() every frame
     */


    static long startTime;
    int currentTime;

    public static void main(String[] args) {
        clock = new GameClock();

        ProjectGame game = new ProjectGame();


        game.start();


    }
}
