package background;

import main.GamePanel;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.util.Random;

public class TileManager {
    
    GamePanel gp;
    Background[] background;
    private ImageIcon[] menuGifs;
    private int currentMenuGifIndex;
    private ImageIcon settingsGif;
    private Random random;

    public TileManager(GamePanel gp){

        this.gp =  gp;

        background = new Background[3];
        random = new Random();

        getTileImage();
        loadMenuGifs();
        loadSettingsGif();
        selectRandomMenuGif();
    }

    public void getTileImage(){
        try {

            background[0] = new Background();
            background[0].image = ImageIO.read(getClass().getResource("/Biome/biome_foret_morte.png"));

            background[1] = new Background();
            background[1].image = ImageIO.read(getClass().getResource("/Biome/biome_neige.png"));

            background[2] = new Background();
            background[2].image = ImageIO.read(getClass().getResource("/Biome/biome_plaine.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMenuGifs() {
        try {
            
            
            menuGifs = new ImageIcon[] {
                new ImageIcon(getClass().getResource("/Biome/menu_bg_1.gif")),
                new ImageIcon(getClass().getResource("/Biome/menu_bg_2.gif")),
                new ImageIcon(getClass().getResource("/Biome/menu_bg_3.gif"))
            };
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement des GIFs du menu: " + e.getMessage());
            
            menuGifs = null;
        }
    }

    private void loadSettingsGif() {
        try {
            settingsGif = new ImageIcon(getClass().getResource("/Biome/settings_bg.gif"));
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement du GIF des settings: " + e.getMessage());
            settingsGif = null;
        }
    }

    public void selectRandomMenuGif() {
        if (menuGifs != null && menuGifs.length > 0) {
            currentMenuGifIndex = random.nextInt(menuGifs.length);
        }
    }
    public void draw(Graphics2D g2){
        if (GamePanel.gameState == GamePanel.forestOfDispair) {
            g2.drawImage(background[0].image, 0, 0, gp.screenWidth, gp.screenHeight, null);
        } else if (GamePanel.gameState == GamePanel.menuState || GamePanel.gameState == GamePanel.dungeonSelectionState) {
            // Dessiner le GIF animé du menu si disponible
            if (menuGifs != null && menuGifs.length > 0) {
                Image gifImage = menuGifs[currentMenuGifIndex].getImage();
                g2.drawImage(gifImage, 0, 0, gp.screenWidth, gp.screenHeight, null);
            } else {
                // Fallback sur l'image statique si les GIFs ne sont pas disponibles
                g2.drawImage(background[1].image, 0, 0, gp.screenWidth, gp.screenHeight, null);
            }
        } else if (GamePanel.gameState == GamePanel.optionState) {
            // GIF fixe pour les settings (ne change jamais)
            if (settingsGif != null) {
                Image gifImage = settingsGif.getImage();
                g2.drawImage(gifImage, 0, 0, gp.screenWidth, gp.screenHeight, null);
            } else {
                // Fallback sur l'image statique si le GIF n'est pas disponible
                g2.drawImage(background[1].image, 0, 0, gp.screenWidth, gp.screenHeight, null);
            }
        } else if (GamePanel.gameState == GamePanel.IceDungeon){
            g2.drawImage(background[2].image, 0, 0, gp.screenWidth, gp.screenHeight, null);
        }
    }
}
