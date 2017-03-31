package main.game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import Tweens.*;
import main.engine.display.AnimatedSprite;
import main.engine.display.Game;
import main.engine.display.Sprite;
import main.engine.events.Event;
import main.engine.util.GameClock;


public class ProjectGame extends Game {


    /* Create a sprite object for our game. We'll use mario */
    Sprite mario = new Sprite("Mario", "Mario.png");

    //TweenJuggler tweenJuggler;

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

    private AnimatedSprite animation;
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







        animation = new AnimatedSprite("animate");

        platform.setPositionX(50);
        platform.setPositionY(550);


        platform1.setPositionX(150);
        platform1.setPositionY(150);

        music.playMusic("resources/bowsersound.mp3");


        animation.setPositionX(51);
        animation.setPositionY(450);
        

        enemy = new Enemy("enemy","Mario.png");
        enemy.setPositionX(300);
        enemy.setPositionY(150);
        enemy.addRoute(500,0);//create square route
        enemy.addRoute(0,-500);
        enemy.addRoute(-500,0);
        enemy.addRoute(0,500);


        enemy.setSpeed(4);


        //tweenJuggler = new TweenJuggler();
        TweenTransitions animationIntro = new TweenTransitions("linearTransition");
        Tween animationTween = new Tween(animation, animationIntro);
        animationTween.animate(TweenableParams.Y, 1000, 500, 2);


        TweenTransitions coinCatch = new TweenTransitions("easeInOut");


        TweenJuggler.getInstance().add(animationTween);
        //tweenJuggler.add(animationTween);


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
        if (animation != null) {
            if (animation.collidesWith(enemy)){
                animation.toggleVisibility();
                complete = true;
            }
            //float deltaX = (float) (mousePosition.getX()-animation.getPositionX());
            //float deltaY = (float) (mousePosition.getY()-animation.getPositionY());
            mario.update(pressedKeys);
            //animation.setRotation((360 + Math.toDegrees(Math.atan2(deltaY, deltaX))) % 360);
            animation.update();
            checkCollisions(animation);
            TweenJuggler.getInstance().nextFrame();

        }


        if (pressedKeys.contains("W")) {
           animation.walkNorth();

        }

        if (pressedKeys.contains("S")) {


            animation.walkSouth();


            music.playSoundEffect("resources/song100.wav");

        }
        if (pressedKeys.contains("D")) {

            mario.setPositionX(mario.getPositionX() + 10);

            animation.walkEast();


        }
        if (pressedKeys.contains("A")) {
            animation.walkWest();
        }


        /*
        if (pressedKeys.contains("A")) {
            mario.setScaleX(mario.getScaleX() + 0.1);
            animation.setScaleX(animation.getScaleX() + 0.1);

        }
        if (pressedKeys.contains("S")) {
            mario.setScaleY(mario.getScaleY() - 0.1);
            animation.setScaleY(animation.getScaleY() - 0.1);
        }
        if (pressedKeys.contains("Q")) {
            mario.setRotation(mario.getRotation() + 1);

            animation.setRotation(animation.getRotation() + 1);

        }
        if (pressedKeys.contains("W")) {
            mario.setRotation(mario.getRotation() - 1);

            animation.setRotation(animation.getRotation() - 1);
        }
        if (pressedKeys.contains("Z")) {
            mario.setTransparency(mario.getTransparency() + 0.01f);
            animation.setTransparency(animation.getTransparency() + 0.01f);

        }
        if (pressedKeys.contains("X")) {

            mario.setTransparency(mario.getTransparency() - 0.01f);
            animation.setTransparency(animation.getTransparency() - 0.01f);


        }
        if (pressedKeys.contains("I")) {
            mario.setPivotY(mario.getPivotY() - 3);
            animation.setPivotY(animation.getPivotY() - 3);

        }
        if (pressedKeys.contains("K")) {
            mario.setPivotY(mario.getPivotY() + 3);
            animation.setPivotY(animation.getPivotY() + 3);

        }
        if (pressedKeys.contains("J")) {
            mario.setPivotX(mario.getPivotX() - 3);
            animation.setPivotX(animation.getPivotX() - 3);

        }
        if (pressedKeys.contains("L")) {
            mario.setPivotX(mario.getPivotX() + 3);

            animation.setPivotX(animation.getPivotX() + 3);
        }
        if (pressedKeys.contains("V")) {

            mario.toggleVisibility();
            animation.toggleVisibility();


        }
        */
        if (enemy != null) {
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

        if (marioBackground != null) {
            marioBackground.draw(g);

        }
        if (animation != null) {
            animation.draw(g);

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

        double minWc = animation.getCenterX() - (animation.getUnscaledWidth() / 2);
        double minHc = animation.getCenterY() - animation.getUnscaledHeight() / 2;

        double maxWc = animation.getCenterX() + (animation.getUnscaledWidth() / 2);
        double maxHc = animation.getCenterY() + animation.getUnscaledHeight() / 2 + 20;

        double mouseX = e.getX();
        double mouseY = e.getY();


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
