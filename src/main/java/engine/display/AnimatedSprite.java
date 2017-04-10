package engine.display;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileReader;
import java.util.Iterator;

import javax.imageio.ImageIO;

import engine.util.GameClock;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;


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
	private BufferedImage[] notMovingLeft;
	private BufferedImage[] walkingSprite;
	//private BufferedImage[] jumpingSprite;
	//private BufferedImage[] fallingSprite;
	//private BufferedImage[] walkingMario;
	private BufferedImage[] walkingSpriteRight;
	private boolean facingLeft;

	public JSONArray frames;
	public int currentFrame;
	private long startTime;
	private long delay;

	long terminalSpeed = 100;
	public boolean isJumping = false;
	public boolean canJump = true;
	int gravity = 1;
	int terminalVelocity = 10;
	int verticalSpeed = 0;


	public String stateName;
	public String spriteSheetJson;
	public JSONObject jsonObject;

	public int endFrame;
	public int startFrame;
	public int frameNum;

	public BufferedImage spriteSheet;

	public ArrayList<BufferedImage> stateFrames;

	public AnimatedSprite(String id, String fileName, String startState){
		super(id);

		stateName = startState;

		stateFrames = new ArrayList<BufferedImage>();

		currentFrame = 0;

		try {
			spriteSheet = ImageIO.read(new File(fileName));
		}catch (Exception e){
			e.printStackTrace();
		}

	}




	public AnimatedSprite(String id, String fileName){
		super(id,fileName);


	}


	public void setSpriteSheetJson(String json_file){
		JSONParser parser = new JSONParser();


		try {

			Object obj = parser.parse(new FileReader(json_file));

			jsonObject = (JSONObject) obj;

			frames = (JSONArray) jsonObject.get("frames");

		}catch (Exception e) {
			e.printStackTrace();
		}

		setAnimationState(stateName);

	}

	public void setAnimationState(String state) {
		JSONParser parser = new JSONParser();

		stateFrames.clear();

		stateName = state;

		boolean startFrameFound = false;
		//int frame_num = 0;
		//endFrame = frames.size() - 1;
		Iterator<JSONObject> iterator = frames.iterator();
		while (iterator.hasNext()) {

			try {

				JSONObject frame = (JSONObject)parser.parse(iterator.next().toString());
				String state_name = (String) frame.get("filename");
				if(state_name.equals(stateName)) {
					if (!startFrameFound) {
						startFrameFound = true;
					}

					JSONObject frameSpecs = (JSONObject)parser.parse(frame.get("frame").toString());
					int frameX = ((Long) frameSpecs.get("x")).intValue();
					int frameY = ((Long) frameSpecs.get("y")).intValue();
					int frameW = ((Long) frameSpecs.get("w")).intValue();
					int frameH = ((Long) frameSpecs.get("h")).intValue();

					BufferedImage frameImage = spriteSheet.getSubimage(frameX, frameY, frameW, frameH);

					stateFrames.add(frameImage);

				} else {
					if(startFrameFound) {

						break;
					}
				}

			}catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public String getStateName() {
		return this.stateName;
	}

	//set speed with this method
	public void setDelay(long d){
		delay = d;
	}



	public long getDelay(){
		return delay;
	}

	@Override
	public void update(ArrayList<String> pressedKeys){
		super.update(pressedKeys);


	}
	public void update(){
		if(this.getId().equals("enemy")) {
			System.out.println(delay);
		}

		if(delay == -1){
			return;
		}
		long elapsed = (System.nanoTime()-startTime)/1000000;
		if(elapsed> delay){
			currentFrame++;
			startTime = System.nanoTime();

		}
		if(currentFrame == stateFrames.size()){
			currentFrame = 0;
		}
		super.setImage(stateFrames.get(currentFrame));
	}

	public BufferedImage getImage(){
		return stateFrames.get(currentFrame);
	}

	@Override
	public void draw(Graphics g)
	{
	/* Call the super draw method in DisplayObject class */
		super.draw(g);

	}


}
