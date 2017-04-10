package engine.display;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;


/**
 * A very basic display object for a java based gaming engine
 * 
 * */

public class DisplayObject {
	
	

	boolean visible = true;
	double positionX = 0;
	double positionY = 0;
//	double velocityX = 0;
//	double velocityY = 0;
//	double frictionCoefficient = 0.001;
//	double maxSpeed = 10.0;
	int pivotPoint;
	double pivotX = 0;
	double pivotY = 0;
	double scaleX = 1.0;
	double scaleY = 1.0;
	double degrees;
	float alpha = 1.00f;
	Point2D screenPoint;
	boolean objColliding = false;
	private DisplayObject parent;
	public static boolean hasPhysics;

	public static long deltaTime;
	public static long lastUpdate;
	
	
	/* All DisplayObject have a unique id */
	private String id;

	/* The image that is displayed by this object */
	private BufferedImage displayImage;
	
	
	

	/**
	 * Constructors: can pass in the id OR the id and image's file path and
	 * position OR the id and a buffered image and position
	 */
	public DisplayObject(String id) {
		this.setId(id);
		Calendar cal = Calendar.getInstance();
		this.lastUpdate = cal.getTimeInMillis();
	}
	

	public DisplayObject(String id, String fileName) {
		this.setId(id);
		this.setImage(fileName);
		Calendar cal = Calendar.getInstance();
		this.lastUpdate = cal.getTimeInMillis();
	}
	
	public DisplayObject(DisplayObject obj,String id, String fileName) {
		this.setId(id);
		this.setImage(fileName);
		this.setParent(obj);
		Calendar cal = Calendar.getInstance();
		this.lastUpdate = cal.getTimeInMillis();
	}
	public void setParent(DisplayObject parent){
		this.parent = parent;
		
	}
	public DisplayObject getParent(){
		return parent;
		
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	


	/**
	 * Returns the unscaled width and height of this display object
	 * */
	public int getUnscaledWidth() {
		if(displayImage == null) return 0;
		return displayImage.getWidth();
	}

	public int getUnscaledHeight() {
		if(displayImage == null) return 0;
		return displayImage.getHeight();
	}
	
	

	public BufferedImage getDisplayImage() {
		return this.displayImage;
	}

	protected void setImage(String imageName) {
		if (imageName == null) {
			return;
		}
		displayImage = readImage(imageName);
		if (displayImage == null) {
			System.err.println("[DisplayObject.setImage] ERROR: " + imageName + " does not exist!");
		}
	}
	public boolean checkObjColliding(){
		if(objColliding){
			return true;
		}else{
			return false;
		}
		
		
		
	}
	public Rectangle collidedRectangle(DisplayObject obj){
		return this.getHitBox().intersection(obj.getHitBox());
		
	}
	
	public boolean collidesWith(DisplayObject obj){
		
		
		if(this.getHitBox().intersects(obj.getHitBox())){
			
			objColliding = true;
		
			return true;
		}
	
		
		return false;
	}
	
	
	public Rectangle getHitBox(){
		return new Rectangle((int)this.getPositionX(),(int)this.getPositionY(),this.getUnscaledWidth(),this.getUnscaledHeight());
		
	}
	
	

	/**
	 * Helper function that simply reads an image from the given image name
	 * (looks in resources\\) and returns the bufferedimage for that filename
	 * */
	public BufferedImage readImage(String imageName) {
		BufferedImage image = null;
		try {
			String file = ("resources" + File.separator + imageName);
			image = ImageIO.read(new File(file));
			
			
		} catch (IOException e) {
			System.out.println("[Error in DisplayObject.java:readImage] Could not read image " + imageName);
			e.printStackTrace();
		}
		return image;
	}

	public void setImage(BufferedImage image) {
		if(image == null) return;
		displayImage = image;
		
		}
	
	
	/**
	 * Invoked on every frame before drawing. Used to update this display
	 * objects state before the draw occurs. Should be overridden if necessary
	 * to update objects appropriately.
	 * */
//	protected void update(ArrayList<String> pressedKeys) {
//		this.computeDeltaTime();
//
//		if(hasPhysics) {
//			this.setPositionX(this.getPositionX() + this.getDeltaTime() * this.getVelocityX());
//			this.setPositionY(this.getPositionY() + this.getDeltaTime() * this.getVelocityY());
//			this.applyFriction();
//		}
//
//	}

	protected void computeDeltaTime() {
		Calendar cal = Calendar.getInstance();
		long now = cal.getTimeInMillis();

		this.deltaTime = now - this.lastUpdate;

		this.lastUpdate = now;
	}

//	protected void moveHorizontallyWithMomentum(double acceleration) {
//		if(this.maxSpeed > Math.abs(this.getVelocityX())) {
//			System.out.println(this.getDeltaTime());
//			this.setVelocityX(this.getVelocityX() + this.getDeltaTime()*acceleration);
//
//		}
//	}

//	protected void moveVerticallyWithMomentum(double acceleration) {
//		if(this.maxSpeed > Math.abs(this.getVelocityY())) {
//			this.setVelocityY(this.getVelocityY() + this.getDeltaTime()*acceleration);
//		}
//	}

//	protected void applyFriction() {
//		if(Math.abs(this.getVelocityX()) < 0.01) {
//			this.setVelocityX(0);
//		} else if(this.getVelocityX() > 0) {
//			this.setVelocityX(this.getVelocityX() - this.getDeltaTime()*this.frictionCoefficient);
//		} else if(this.getVelocityX() < 0) {
//			this.setVelocityX(this.getVelocityX() + this.getDeltaTime()*this.frictionCoefficient);
//		}
//
//		if(Math.abs(this.getVelocityY()) < 0.01) {
//			this.setVelocityY(0);
//		} else if(this.getVelocityY() > 0) {
//			this.setVelocityY(this.getVelocityY() - this.getDeltaTime()*this.frictionCoefficient);
//		} else if(this.getVelocityY() < 0) {
//			this.setVelocityY(this.getVelocityY() + this.getDeltaTime()*this.frictionCoefficient);
//		}
//	}


	/**
	 * Draws this image. This should be overloaded if a display object should
	 * draw to the screen differently. This method is automatically invoked on
	 * every frame.
	 * */
	
	public void draw(Graphics g) {
			
		
		if (displayImage != null) {
			
			/*
			 * Get the graphics and apply this objects transformations
			 * (rotation, etc.)
			 */
			

			Graphics2D g2d = (Graphics2D) g;
			applyTransformations(g2d);
			
			
			  g2d.setComposite(AlphaComposite.getInstance(
	                     AlphaComposite.SRC_OVER, getTransparency()));
	            
			/* Actually draw the image, perform the pivot point translation here */
			g2d.drawImage(displayImage, 0, 0,
					(int) (getUnscaledWidth()),
					(int) (getUnscaledHeight()), null);
			

			
			//g2d.setColor(Color.RED);
			//g2d.drawOval((int)getPivotX(),(int) getPivotY(), 10, 10);
			
	
			
			   Point2D c = new Point2D.Double(
	                    (displayImage.getMinX() +displayImage.getWidth())/2, 
	                    (displayImage.getMinY() +displayImage.getHeight())/2);

	            AffineTransform at = g2d.getTransform();
	             screenPoint = at.transform(c, new Point2D.Double());

	                     
	           
			
			reverseTransformations(g2d);
		}
	}



	/**
	 * Applies transformations for this display object to the given graphics
	 * object
	 * */
	protected void applyTransformations(Graphics2D g2d) {
		
	
		
	g2d.translate(getPositionX(),getPositionY());
	
	g2d.scale(getScaleX(), getScaleY());
	
	
	AffineTransform at = new AffineTransform();
	
	 at.rotate(getRotation(), getPivotX(), getPivotY());
	 
	g2d.transform(at);
	
	}

	/**
	 * Reverses transformations for this display object to the given graphics
	 * object
	 * */
	protected void reverseTransformations(Graphics2D g2d) {
		
		//g2d.rotate(-getRotation());
		AffineTransform at = new AffineTransform();
		
		 at.rotate(-1*getRotation(), getPivotX(), getPivotY());
		 
		g2d.transform(at);
		
		
		g2d.scale(1/getScaleX(), 1/getScaleY());
		g2d.translate(-1*getPositionX(), -1 * getPositionY());

	}
	//getters & setters
	public void setVisibleState(boolean c){
		
		
		visible = c;
						
	}
	public void toggleVisibility(){
		try {
			if(getVisibility()){
				
				
				setVisibleState(false);
				
				
			}else{
				
				
				setVisibleState(true);
				
				
			}
			TimeUnit.MILLISECONDS.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public boolean getVisibility(){
		return visible;
		
	}
	
	public void setPositionX(double x){
		
		positionX = x;
		
	}
	public double  getPositionX(){
		
		return positionX;
	}
	public void setPositionY(double y){
		
		positionY = y;
	}
	public double  getPositionY(){
	
		return positionY;
	}

//	public void setVelocityX(double velX) {
//		this.velocityX = velX;
//	}
//
//	public void setVelocityY(double velY) {
//		this.velocityY = velY;
//	}
//
//	public double getVelocityX() {
//		return this.velocityX;
//	}
//
//	public double getVelocityY() {
//		return this.velocityY;
//	}
	

	
	public void pivotPoint(int p){
		pivotPoint = p;
		
	}
	public int getPivotPoint(){
		return pivotPoint;
		
	}
	public void setScaleX(double x){
		scaleX = x;
		
		
	}
	public void setScaleY(double y){
		scaleY = y;
		
	}
	public double getScaleX(){
		return scaleX;
		
	}
	public double getScaleY(){
		return scaleY;
		
	}
	
	
	public void setRotation(double d){
		
		//degrees = Math.toRadians(d);
		
		degrees = d;
		
	}
	public double getRotation(){
		return degrees;
		
	}
	public void setTransparency(float a){
		if(a<1 && a>0){
		alpha = a;
		}
		
	}
	public float getTransparency(){
		return alpha;
	}
	public void setPivotX(double p){
		pivotX = p;
		
	}
	public void setPivotY(double p){
		pivotY = p;
	}
	
	public double getPivotX(){
		
		return pivotX;
	}
	public double getPivotY(){
		return pivotY;
	}
	public double getCenterX(){
		return screenPoint.getX();
	}
	public double getCenterY(){
		return screenPoint.getY();
	}
	
	public long getDeltaTime() { return deltaTime; }
//
//	public void setHasPhysics(boolean phys) {
//		hasPhysics = phys;
//	}
}