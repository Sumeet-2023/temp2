package de.tum.cit.ase.maze;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import games.spooky.gdx.nativefilechooser.NativeFileChooser;

/**
 * The MazeRunnerGame class represents the core of the Maze Runner game.
 * It manages the screens and global resources like SpriteBatch and Skin.
 */
public class MazeRunnerGame extends Game {
    private Music backgroundMusic;
    private Sprite sprite;
    // Screens
    private MenuScreen menuScreen;
    private GameScreen gameScreen;
    private CreditScreen creditScreen;

    // Sprite Batch for rendering
    private SpriteBatch spriteBatch;

    // UI Skin
    private Skin skin;

    // Character animation downwards
    private Animation<TextureRegion> characterDownAnimation;

    // Fire Animation
    private Animation<TextureRegion> fireAnimation;

    // Door TextureRegions
    private TextureRegion door;

    // Wall TextureRegions
    private TextureRegion horiWall;
    private TextureRegion vertiWall;
    private TextureRegion cornerWall;
    private TextureRegion floor;
    private Animation<TextureRegion> characterRightAnimation;
    private Animation<TextureRegion> characterUpAnimation;
    private Animation<TextureRegion> characterLeftAnimation;
    private Animation<TextureRegion> alienDownAnimation;
    private Animation<TextureRegion> alienLeftAnimation;
    private Animation<TextureRegion> alienRightAnimation;
    private Animation<TextureRegion> alienUpAnimation;
    private Animation<TextureRegion> ghostDownAnimation;
    private Animation<TextureRegion> ghostLeftAnimation;
    private Animation<TextureRegion> ghostRightAnimation;
    private Animation<TextureRegion> ghostUpAnimation;
    private Animation<TextureRegion> doorOpenAnimation;
    private Animation<TextureRegion> treasureBoxAnimation;
    private Animation<TextureRegion> spikeAnimation;
    private Animation<TextureRegion> potBreakAnimation;
     /**
     * Constructor for MazeRunnerGame.
     *
     * @param fileChooser The file chooser for the game, typically used in desktop environment.
     */
    public MazeRunnerGame(NativeFileChooser fileChooser) {
        super();
    }

    /**
     * Called when the game is created. Initializes the SpriteBatch and Skin.
     */
    @Override
    public void create() {
        spriteBatch = new SpriteBatch(); // Create SpriteBatch
        skin = new Skin(Gdx.files.internal("craft/craftacular-ui.json")); // Load UI skin
        this.loadDownCharacterAnimation(); // Load Down character animation
        this.loadRightCharacterAnimation();
        this.loadUpCharacterAnimation();
        this.loadLeftCharacterAnimation();
        this.loadFireAnimation(); // Load fire animation
        this.loadDoor(); // Load door TextureRegion
        this.loadHoriWall(); // Load wall TextureRegion
        this.loadVertiWall();
        this.loadCornerWall();
        this.loadDownAlienAnimation();
        this.loadLeftCharacterAnimation();
        this.loadRightCharacterAnimation();
        this.loadUpCharacterAnimation();
        this.loadDownAlienAnimation();
        this.loadLeftAlienAnimation();
        this.loadRightGhostAnimation();
        this.loadUpGhostAnimation();
        this.loadDoorOpening();
        this.loadTreasureBoxOpening();
        this.loadSpikeAnimation();
        this.loadPotBreaking();
        this.loadFloor();

        // Play some background music
        // Background sound
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("background.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();

        goToMenu(); // Navigate to the menu screen
    }

    /**
     * Switches to the menu screen.
     */
    public void goToMenu() {
        this.setScreen(new MenuScreen(this)); // Set the current screen to MenuScreen
        if (gameScreen != null) {
            gameScreen.dispose(); // Dispose the game screen if it exists
            gameScreen = null;
        }
    }
    public void goToEscMenu() {
        this.setScreen(new EscMenuScreen(this)); // Set the current screen to MenuScreen
        if (gameScreen != null) {
            gameScreen.dispose(); // Dispose the game screen if it exists
            gameScreen = null;
        }
    }

    /**
     * Switches to the game screen.
     */
    public void goToGame() {
        this.setScreen(new GameScreen(this)); // Set the current screen to GameScreen
        if (menuScreen != null) {
            menuScreen.dispose(); // Dispose the menu screen if it exists
            menuScreen = null;
        }
        backgroundMusic.dispose();
    }

    /**
     * Switches to the Credits screen.
     */
    public void goToCredits()
    {
        this.setScreen(new CreditScreen(this));
        if (creditScreen != null){
            creditScreen.dispose();
            creditScreen = null;
        }
    }
    /**
     * Loads the fire animation from the objects.png file.
     */
    public void loadFireAnimation() {
        Texture fireSheet = new Texture(Gdx.files.internal("objects.png"));

        int frameWidth = 16;
        int frameHeight = 16;
        int animationFrame = 7;

        Array<TextureRegion> fireFrames = new Array<>(TextureRegion.class);

        for (int col = 4; col < animationFrame + 4; col++){
            fireFrames.add(new TextureRegion(fireSheet, col * frameWidth, 3 * frameHeight, frameWidth, frameHeight));
        }
        fireAnimation = new Animation<>(0.1f, fireFrames);
    }

    // TODO: Create methods for loading animation for different direction of character.
    /**
     * Loads the character animation from the character.png file.
     */
    private void loadDownCharacterAnimation() {
        Texture walkSheet = new Texture(Gdx.files.internal("character.png"));

        int frameWidth = 16;
        int frameHeight = 32;
        int animationFrames = 4;

        // libGDX internal Array instead of ArrayList because of performance
        Array<TextureRegion> walkFrames = new Array<>(TextureRegion.class);

        // Add all frames to the animation
        for (int col = 0; col < animationFrames; col++) {
            walkFrames.add(new TextureRegion(walkSheet, col * frameWidth, 0, frameWidth, frameHeight));
        }
        characterDownAnimation = new Animation<>(0.1f, walkFrames);
    }
    public void loadRightCharacterAnimation(){
        Texture walkSheet = new Texture(Gdx.files.internal("character.png"));
        int frameWidth=16;
        int frameHeight=32;
        int animationFrame=4;
        Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
        for (int col =0;col< animationFrame;col++){
            walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,frameHeight,frameWidth,frameHeight));
        }
        characterRightAnimation =new Animation<>(0.1f,walkFrames);
    }
    public void loadUpCharacterAnimation(){
        Texture walkSheet = new Texture(Gdx.files.internal("character.png"));
        int frameWidth=16;
        int frameHeight=32;
        int animationFrame=4;
        Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
        for (int col =0;col< animationFrame;col++){
            walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,frameHeight*2,frameWidth,frameHeight));
        }
        characterUpAnimation =new Animation<>(0.1f,walkFrames);
    }
    public void loadLeftCharacterAnimation(){
        Texture walkSheet = new Texture(Gdx.files.internal("character.png"));
        int frameWidth=16;
        int frameHeight=32;
        int animationFrame=4;
        Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
        for (int col =0;col< animationFrame;col++){
            walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,frameHeight*3,frameWidth,frameHeight));
        }
        characterLeftAnimation =new Animation<>(0.1f,walkFrames);
    }

    // TODO: Create method for loading animation for different direction of different enemies.
    // 1st AlienAnimation
    public void loadDownAlienAnimation(){
        Texture walkSheet = new Texture(Gdx.files.internal("mobs.png"));
        int frameWidth=16;
        int frameHeight=16;
        int animationFrame=3;
        Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
        for (int col =9;col< animationFrame+9;col++){
            walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,0,frameWidth,frameHeight));
        }
        alienDownAnimation =new Animation<>(0.1f,walkFrames);
    }
    public void loadLeftAlienAnimation(){
        Texture walkSheet = new Texture(Gdx.files.internal("mobs.png"));
        int frameWidth=16;
        int frameHeight=16;
        int animationFrame=3;
        Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
        for (int col =9;col< animationFrame+9;col++){
            walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,frameHeight,frameWidth,frameHeight));
        }
        alienLeftAnimation =new Animation<>(0.1f,walkFrames);
    }
    public void loadRightAlienAnimation(){
        Texture walkSheet = new Texture(Gdx.files.internal("mobs.png"));
        int frameWidth=16;
        int frameHeight=16;
        int animationFrame=3;
        Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
        for (int col =9;col< animationFrame+9;col++){
            walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,2*frameHeight,frameWidth,frameHeight));
        }
        alienRightAnimation =new Animation<>(0.1f,walkFrames);
    }
    public void loadUpAlienAnimation(){
        Texture walkSheet = new Texture(Gdx.files.internal("mobs.png"));
        int frameWidth=16;
        int frameHeight=16;
        int animationFrame=3;
        Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
        for (int col =9;col< animationFrame+9;col++){
            walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,3*frameHeight,frameWidth,frameHeight));
        }
        alienUpAnimation =new Animation<>(0.1f,walkFrames);
    }
    //2nd GhostAnimation
    public void loadDownGhostAnimation(){
        Texture walkSheet = new Texture(Gdx.files.internal("mobs.png"));
        int frameWidth=16;
        int frameHeight=16;
        int animationFrame=3;
        Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
        for (int col =6;col< animationFrame+6;col++){
            walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,0,frameWidth,frameHeight));
        }
        ghostDownAnimation =new Animation<>(0.1f,walkFrames);
    }
    public void loadLeftGhostAnimation(){
        Texture walkSheet = new Texture(Gdx.files.internal("mobs.png"));
        int frameWidth=16;
        int frameHeight=16;
        int animationFrame=3;
        Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
        for (int col =6;col< animationFrame+6;col++){
            walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,frameHeight,frameWidth,frameHeight));
        }
        ghostLeftAnimation =new Animation<>(0.1f,walkFrames);
    }
    public void loadRightGhostAnimation(){
        Texture walkSheet = new Texture(Gdx.files.internal("mobs.png"));
        int frameWidth=16;
        int frameHeight=16;
        int animationFrame=3;
        Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
        for (int col =6;col< animationFrame+6;col++){
            walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,2*frameHeight,frameWidth,frameHeight));
        }
        ghostRightAnimation =new Animation<>(0.1f,walkFrames);
    }
    public void loadUpGhostAnimation(){
        Texture walkSheet = new Texture(Gdx.files.internal("mobs.png"));
        int frameWidth=16;
        int frameHeight=16;
        int animationFrame=3;
        Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
        for (int col =6;col< animationFrame+6;col++){
            walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,3*frameHeight,frameWidth,frameHeight));
        }
        ghostUpAnimation =new Animation<>(0.1f,walkFrames);
    }
    // TODO: Create method for loading animation for door opening;
    public void loadDoorOpening(){
        Texture doorSheet =new Texture(Gdx.files.internal("things.png"));
        int animationFrame=4;
        Array<TextureRegion> doorFrames=new Array<>(TextureRegion.class);
        for(int row=0;row<animationFrame;row++){
            doorFrames.add(new TextureRegion(doorSheet,0,row*16,16,16));
        }
        doorOpenAnimation=new Animation<>(0.1f,doorFrames);
    }
    public void loadHoriWall() {
        Texture wallTexture = new Texture(Gdx.files.internal("basictiles.png"));
        horiWall = new TextureRegion(wallTexture, 16*4, 0, 16, 16);
    }
    public void loadVertiWall() {
        Texture wallTexture = new Texture(Gdx.files.internal("basictiles.png"));
         sprite = new Sprite(wallTexture,16*4,0,16,16);
    }
    public void loadCornerWall() {
        Texture wallTexture = new Texture(Gdx.files.internal("basictiles.png"));
        cornerWall = new TextureRegion(wallTexture, 16*7, 0, 16, 16);
    }


    public void loadDoor() {
        Texture doorTexture = new Texture(Gdx.files.internal("things.png"));
        door = new TextureRegion(doorTexture, 1, 0, 16, 16);
    }
    public void loadFloor(){
        Texture floorTexture=new Texture(Gdx.files.internal("basictiles.png"));
        floor = new TextureRegion(floorTexture,0,16,16,16);
    }

    // TODO: Create method for Loading TextureRegion for Key.
    // TODO: Create methods for Loading TextureRegion for different directions of walls.
    // TreasureBoxOpening
    public void loadTreasureBoxOpening(){
        Texture treasureSheet =new Texture(Gdx.files.internal("things.png"));
        int animationFrame=4;
        Array<TextureRegion> treasureFrames=new Array<>(TextureRegion.class);
        for(int row=0;row<animationFrame;row++){
            treasureFrames.add(new TextureRegion(treasureSheet,6*16,row*16,16,16));
        }
        treasureBoxAnimation=new Animation<>(0.1f,treasureFrames);
    }
    public void loadPotBreaking(){
        Texture potSheet =new Texture(Gdx.files.internal("things.png"));
        int animationFrame=4;
        Array<TextureRegion> potFrames=new Array<>(TextureRegion.class);
        for(int row=0;row<animationFrame;row++){
            potFrames.add(new TextureRegion(potSheet,9*16,row*16,16,16));
        }
        potBreakAnimation=new Animation<>(0.1f,potFrames);
    }
    public void loadSpikeAnimation(){

        Texture spikeSheet =new Texture(Gdx.files.internal("things.png"));
        int animationFrame=3;
        Array<TextureRegion> spikeFrames=new Array<>(TextureRegion.class);
        for(int col=6;col<animationFrame+6;col++){
            spikeFrames.add(new TextureRegion(spikeSheet,col*16,6*16,16,16));
        }
        spikeAnimation=new Animation<>(0.6f,spikeFrames);
    }



    /**
     * Cleans up resources when the game is disposed.
     */
    @Override
    public void dispose() {
        getScreen().hide(); // Hide the current screen
        getScreen().dispose(); // Dispose the current screen
        spriteBatch.dispose(); // Dispose the spriteBatch
        skin.dispose(); // Dispose the skin

    }

    // Getter methods
    public Skin getSkin() {
        return skin;
    }

    public Animation<TextureRegion> getCharacterDownAnimation() {
        return characterDownAnimation;
    }
    public Animation<TextureRegion> getCharacterRightAnimation() {
        return characterRightAnimation;
    }
    public Animation<TextureRegion> getCharacterUpAnimation() {
        return characterUpAnimation;
    }
    public Animation<TextureRegion> getCharacterLeftAnimation() {
        return characterLeftAnimation;
    }
    public Animation<TextureRegion> getAlienDownAnimation() {
        return alienDownAnimation;
    }
    public Animation<TextureRegion> getAlienLeftAnimation() {
        return alienLeftAnimation;
    }
    public Animation<TextureRegion> getAlienRightAnimation() {
        return alienRightAnimation;
    }
    public Animation<TextureRegion> getAlienUpAnimation() {
        return alienUpAnimation;
    }
    public Animation<TextureRegion> getGhostDownAnimation() {
        return ghostDownAnimation;
    }
    public Animation<TextureRegion> getGhostLeftAnimation() {
        return ghostLeftAnimation;
    }
    public Animation<TextureRegion> getGhostRightAnimation() {
        return ghostRightAnimation;
    }
    public Animation<TextureRegion> getGhostUpAnimation() {
        return ghostUpAnimation;
    }
    public Animation<TextureRegion> getDoorOpenAnimation() {
        return doorOpenAnimation;
    }
    public Animation<TextureRegion> getFireAnimation() { return fireAnimation; }

    public Animation<TextureRegion> getTreasureBoxAnimation() {
        return treasureBoxAnimation;
    }

    public Animation<TextureRegion> getSpikeAnimation() {
        return spikeAnimation;
    }

    public Animation<TextureRegion> getPotBreakAnimation() {
        return potBreakAnimation;
    }


    public TextureRegion getDoor() { return door; }
    public TextureRegion getHoriWall() { return horiWall; }

    public TextureRegion getVertiWall() {
        return vertiWall;
    }

    public TextureRegion getCornerWall() {
        return cornerWall;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public TextureRegion getFloor() {
        return floor;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }
}
