package engine.display;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import engine.util.GameClock;

public class AnimatedSprite extends Sprite {

	//private int currentFrame = 0;
	private int startIndex;
	private int endIndex;
	private int frameCount;
	private int animationSpeed = 1;
	private String animateType;
	boolean playing = true;
	private GameClock clock;

	private AnimatedSprite animation;
	private BufferedImage[] notMoving;
	private BufferedImage[] walkingSprite;
	private BufferedImage[] jumpingSprite;
	private BufferedImage[] fallingSprite;
	private BufferedImage[] walkingMario;
	private BufferedImage[] walkingSpriteRight;
	private boolean facingLeft;

	public BufferedImage[] frames;
	public int currentFrame;
	private long startTime;
	private long delay;

	long terminalSpeed = 100;
	public boolean isJumping = false;
	public boolean canJump = true;
	int gravity = 1;
	int terminalVelocity = 10;
	int verticalSpeed = 0;



	public AnimatedSprite(String id){
		super(id);
		try{
			walkingSprite = new BufferedImage[8];
			notMoving = new BufferedImage[7];
			jumpingSprite = new BufferedImage[1];
			fallingSprite = new BufferedImage[1];
			walkingSpriteRight = new BufferedImage[8];

			BufferedImage image2 = ImageIO.read(new File("resources/idle_front.png"));
			jumpingSprite[0] = ImageIO.read(new File("resources/kirbyjump4.0.png"));
			fallingSprite[0] = ImageIO.read(new File("resources/kirbyfall4.0.png"));
			BufferedImage image = ImageIO.read(new File("resources/run_front.png"));
			BufferedImage image1 = ImageIO.read(new File("resources/run_front.png"));
			for(int i = 0; i < walkingSprite.length;i++){
				walkingSprite[i] = image.getSubimage(i*76,0,76,92);
				walkingSpriteRight[i] = image1.getSubimage(i*76,0,76,92);

			}

			for(int i = 0; i < notMoving.length;i++){
				notMoving[i] = image2.getSubimage(i*76,0,76,92);

			}


		}catch (Exception e){
			e.printStackTrace();
		}


		setFrames(notMoving);
		setDelay(500);

		setPositionX(250);
		setPositionY(180);

	}




	public AnimatedSprite(String id, String fileName){
		super(id,fileName);




	}
	public void setFrames(BufferedImage[] images){
		frames = images;
		if(currentFrame>=frames.length){
			currentFrame = 0;

		}

	}
	//set speed with this method
	public void setDelay(long d){
		delay = d;
	}


	public void walkEast(){
		setFrames(walkingSpriteRight);
		setDelay(100);



		setPositionX(getPositionX()+10);




	}
	public void walkWest(){
		setFrames(walkingSprite);
		setDelay(100);
		setPositionX(getPositionX()-3);
	}

	public void runRight(){
		setFrames(walkingSprite);
		setDelay(1);
		setPositionX(getPositionX()+3);
	}
	public void walkNorth(){
		setFrames(jumpingSprite);
		setDelay(500);
		setPositionY(getPositionY()-3);
	}
	public void walkSouth(){
		setFrames(fallingSprite);
		setDelay(500);
		setPositionY(getPositionY()+3);



	}





	public long getDelay(){
		return delay;
	}

	@Override
	public void update(ArrayList<String> pressedKeys){
		super.update(pressedKeys);


	}
	public void update(){
		if(delay == -1){
			return;
		}
		long elapsed = (System.nanoTime()-startTime)/1000000;
		if(elapsed> delay){
			currentFrame++;
			startTime = System.nanoTime();

		}
		if(currentFrame == frames.length){
			currentFrame = 0;
			setFrames(notMoving);
			setDelay(100);
		}

		super.setImage(frames[currentFrame]);
	}

	public BufferedImage getImage(){
		return frames[currentFrame];
	}

	@Override
	public void draw(Graphics g)
	{
	/* Call the super draw method in DisplayObject class */
		super.draw(g);

	}


}
