package main;

import background.TileManager;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.*;

import dungeon.Forest_of_dispair;
import dungeon.Ice_dungeon;
import entity.Player;


public class GamePanel extends JPanel implements Runnable{
    
    final int originalTileSize = 16; //Tout en 16*16
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //  la fenêtre en 48*48
    public static int maxScreenCol = 16;
    public static int maxScreenRow = 9;
    public int screenWidth = tileSize * maxScreenCol; // 768 pixels de largeur
    public int screenHeight = tileSize * maxScreenRow;//576 pixels de longueur

    int FPS = 60;
    public static Forest_of_dispair forest_of_dispair = new Forest_of_dispair();;
    public static Ice_dungeon iceDungeon = new Ice_dungeon();
    public static dungeon.Dungeon currentDungeon;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    Player player = new Player(this,keyH);
    public UI ui = new UI(this);
    public GameAction gameAction;
    public Sound sound = new Sound();
    public characters.Team playerTeam = new characters.Team("Player", 1);

    //Player default position and speed
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public static int gameState;
    public static final int introState = 0;
    public static final int dungeonState = 1;
    public static final int menuState = 2;
    public static final int optionState = 3;
    public static final int forestOfDispair = 4;
    public static final int IceDungeon = 5;
    public static final int winState = 6;
    public static final int loseState = 7;
    public static final int dungeonSelectionState = 8;
    public static final int loadingState = 9;
    public static final int teamManagerState = 10;

    public GamePanel(){
//        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
//        screenWidth = screensize.width;
//        screenHeight = screensize.height;

        gameAction = new GameAction(this);
        if (gameState == forestOfDispair) {
            currentDungeon = forest_of_dispair;
        } else if (gameState == IceDungeon) {
            currentDungeon = iceDungeon;
        }

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void resize(){
        screenWidth = tileSize * maxScreenCol;
        screenHeight = tileSize * maxScreenRow;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        
        // Rafraîchir la fenêtre
        if (this.getParent() != null) {
            JFrame window = (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.pack();
                window.setLocationRelativeTo(null);
            }
        }
        
        // Forcer le redessin immédiat
        this.revalidate();
        this.repaint();
    }

    public void setupGame() {
        gameState = introState; // Commencer par l'écran d'introduction
        // La musique démarrera après l'intro
        // Initialiser le volume par défaut
        sound.setVolume(ui.volume);
    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){

        double drawInterval = 1000000000f/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        while(gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1){
                //the Update info pour la position des sprites:
                update();
                repaint();
                delta--;
            }
            if (timer >= 1000000000){
                timer = 0;
            }
        }
    }
    public void update(){

        forest_of_dispair.update();
        iceDungeon.update();
        if (currentDungeon != null) {
            gameAction.update();
        }

    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;


        if (gameState == introState) {
            ui.draw(g2);
        } else if (gameState == loadingState) {
            ui.draw(g2);
        } else if (gameState == menuState) {
            tileM.draw(g2);
            ui.draw(g2);
        } else if (gameState == dungeonSelectionState) {
            tileM.draw(g2);
            ui.draw(g2);
        } else if (gameState == forestOfDispair || gameState == IceDungeon) {
//            System.out.println(currentDungeon);
            tileM.draw(g2);
            if (currentDungeon != null) {
                currentDungeon.draw(g2, this);
            }
            ui.draw(g2);
        } else if (gameState == optionState) {
            tileM.draw(g2);
            ui.draw(g2);
        } else if (gameState == teamManagerState) {
            tileM.draw(g2);
            ui.draw(g2);
        } else if (gameState == winState || gameState == loseState) {
            ui.draw(g2);
        }
        
        // Appliquer l'overlay de luminosité 
        if (gameState != introState && gameState != optionState && ui.brightness < 1.0f) {
            g2.setColor(new Color(0, 0, 0, (int)((1.0f - ui.brightness) * 200)));
            g2.fillRect(0, 0, screenWidth, screenHeight);
        }

        g2.dispose();

    }
}
