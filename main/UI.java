package main;

import characters.Attack;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class UI {

    GamePanel gp;
    public int commandNum = 0;
    public int optionNum = 0;
    public int teamManagerSlot = 0; // Slot sélectionné (0-3)
    public int heroSelectionIndex = 0; // Héros sélectionné dans la liste
    private BufferedImage forestWorld;
    private BufferedImage iceWorld;
    private BufferedImage desertWorld;
    private BufferedImage mountainWorld;
    
    // Paramètres audio et visuels
    public float volume = 0.7f; // Volume entre 0.0 et 1.0 (70% par défaut)
    public float brightness = 1.0f; // Luminosité entre 0.3 et 1.0 (100% par défaut)
    
    // Variables pour l'écran d'introduction
    private long introStartTime = 0;
    private final long INTRO_GIF_DURATION = 10000; // 10 secondes pour le GIF
    private final long INTRO_TEAM_DURATION = 4000; // 4 secondes pour la présentation de l'équipe
    private final long INTRO_TEXT_DURATION = 3000; // 3 secondes pour le texte
    private float introAlpha = 0f;
    private javax.swing.ImageIcon introGif;
    
    // Noms des membres de l'équipe (PERSONNALISEZ ICI)
    private final String[] teamMembers = {
        "PAR_24",
        "Ilann",
        "Tyliann",
        "Naigel"
    };
    
    // Variables pour l'écran de chargement
    private long loadingStartTime = 0;
    private float loadingProgress = 0f;
    private String loadingText = "Loading";
    private int loadingDots = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        loadWorldAssets();
        loadIntroGif();
        introStartTime = System.currentTimeMillis();
    }
    
    private void loadIntroGif() {
        try {
            java.net.URL gifUrl = getClass().getResource("/intro/intro.gif");
            if (gifUrl != null) {
                introGif = new javax.swing.ImageIcon(gifUrl);
            } else {
                System.err.println("Fichier intro.gif non trouvé dans /intro/");
            }
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement du GIF d'intro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadWorldAssets() {
        try {
            // Charger les images individuelles des worlds
            forestWorld = ImageIO.read(getClass().getResourceAsStream("/worlds/forest_world.png"));
            iceWorld = ImageIO.read(getClass().getResourceAsStream("/worlds/ice_world.png"));
            desertWorld = ImageIO.read(getClass().getResourceAsStream("/worlds/desert_world.png"));
            mountainWorld = ImageIO.read(getClass().getResourceAsStream("/worlds/mountain_world.png"));
            
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement des world assets: " + e.getMessage());
            e.printStackTrace();
            // Les images resteront null si non trouvées, on utilisera le fallback texte
        }
    }

    // Constantes pour les couleurs
    private static final Color SEMI_TRANSPARENT_GRAY = new Color(200, 200, 200);
    private static final Color LIGHT_BLUE = new Color(100, 150, 255);
    private static final Color BUTTON_BG = new Color(50, 50, 50, 100);
    private static final Color BUTTON_SELECT_BG = new Color(100, 100, 100, 250);
    
    // Méthodes pour obtenir des polices adaptatives
    private Font getLargeFont() {
        return new Font("Arial", Font.PLAIN, Math.max(30, gp.screenHeight / 15));
    }

    private Font getSmallFont() {
        return new Font("Arial", Font.PLAIN, Math.max(12, gp.screenHeight / 35));
    }
    
    private Font getMediumFont() {
        return new Font("Arial", Font.PLAIN, Math.max(20, gp.screenHeight / 25));
    }
    
    // Méthodes utilitaires pour le texte
    private int getTextWidth(Graphics2D g2, String text) {
        return g2.getFontMetrics().stringWidth(text);
    }
    
    private int getCenterX(Graphics2D g2, String text) {
        return gp.screenWidth / 2 - getTextWidth(g2, text) / 2;
    }
    
    private void drawCenteredText(Graphics2D g2, String text, int y) {
        g2.drawString(text, getCenterX(g2, text), y);
    }
    
    // Calcul du fade in/out pour les transitions
    private float calculateFadeAlpha(long elapsed, long duration) {
        long fadeInEnd = duration / 4;
        long fadeOutStart = duration * 3 / 4;
        
        if (elapsed < fadeInEnd) {
            return (float)elapsed / fadeInEnd;
        } else if (elapsed < fadeOutStart) {
            return 1.0f;
        } else {
            return 1.0f - (float)(elapsed - fadeOutStart) / (duration / 4);
        }
    }

    public static void makeButton(Graphics2D g2d, int x, int y, int width, int height, boolean selected) {
        g2d.setColor(selected ? BUTTON_SELECT_BG : BUTTON_BG);
        g2d.fillRect(x, y, width, height);
        g2d.setColor(Color.WHITE);
        g2d.drawRect(x, y, width, height);
    }

    public void draw(Graphics2D g2) {
        if (GamePanel.gameState == GamePanel.introState) {
            drawIntroScreen(g2);
        } else if (GamePanel.gameState == GamePanel.loadingState) {
            drawLoadingScreen(g2);
        } else if (GamePanel.gameState == GamePanel.menuState) {
            HomeMenu(g2);
        } else if (GamePanel.gameState == GamePanel.dungeonSelectionState) {
            DungeonSelectionMenu(g2);
        } else if (GamePanel.gameState == GamePanel.forestOfDispair || GamePanel.gameState == GamePanel.IceDungeon){
            if (GameAction.turn == GameAction.playerTurn) {
                drawPlayerTurn(g2);
            } else if (GameAction.turn == GameAction.iATurn) {
                TestIa(g2);
            }
        } else if (GamePanel.gameState == GamePanel.optionState) {
            OptionMenu(g2);
        } else if (GamePanel.gameState == GamePanel.teamManagerState) {
            drawTeamManager(g2);
        } else if (GamePanel.gameState == GamePanel.winState) {
            WinScreen(g2);
        } else if (GamePanel.gameState == GamePanel.loseState) {
            LoseScreen(g2);
        }
    }
    
    private void drawIntroScreen(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        long elapsed = System.currentTimeMillis() - introStartTime;
        
        if (elapsed < INTRO_GIF_DURATION) {
            drawIntroGifPhase(g2, elapsed);
        } else if (elapsed < INTRO_GIF_DURATION + INTRO_TEAM_DURATION) {
            drawIntroTeamPhase(g2, elapsed - INTRO_GIF_DURATION);
        } else if (elapsed < INTRO_GIF_DURATION + INTRO_TEAM_DURATION + INTRO_TEXT_DURATION) {
            drawIntroLogoPhase(g2, elapsed - INTRO_GIF_DURATION - INTRO_TEAM_DURATION);
        } else {
            transitionToMenu();
        }
    }
    
    private void drawIntroGifPhase(Graphics2D g2, long elapsed) {
        if (introGif != null) {
            int gifWidth = introGif.getIconWidth();
            int gifHeight = introGif.getIconHeight();
            float scale = Math.max((float)gp.screenWidth / gifWidth, (float)gp.screenHeight / gifHeight);
            int scaledWidth = (int)(gifWidth * scale);
            int scaledHeight = (int)(gifHeight * scale);
            int x = (gp.screenWidth - scaledWidth) / 2;
            int y = (gp.screenHeight - scaledHeight) / 2;
            g2.drawImage(introGif.getImage(), x, y, scaledWidth, scaledHeight, null);
        } else {
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, Math.max(36, gp.screenHeight / 12)));
            drawCenteredText(g2, "INTRO GIF", gp.screenHeight / 2);
        }
        drawSkipMessage(g2, elapsed);
    }
    
    private void drawIntroTeamPhase(Graphics2D g2, long elapsed) {
        introAlpha = calculateFadeAlpha(elapsed, INTRO_TEAM_DURATION);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, introAlpha));
        
        // Titre
        g2.setColor(new Color(150, 150, 150));
        g2.setFont(new Font("Arial", Font.PLAIN, Math.max(20, gp.screenHeight / 24)));
        drawCenteredText(g2, "Developed by", gp.screenHeight / 3 - 20);
        
        // Noms des membres
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, Math.max(22, gp.screenHeight / 22)));
        int startY = gp.screenHeight / 3 + 30;
        int spacing = Math.max(35, gp.screenHeight / 15);
        
        for (int i = 0; i < teamMembers.length; i++) {
            int yPos = startY + (i * spacing);
            int textWidth = getTextWidth(g2, teamMembers[i]);
            int centerX = gp.screenWidth / 2;
            g2.drawString(teamMembers[i], centerX - textWidth / 2, yPos);
            g2.setColor(LIGHT_BLUE);
            g2.fillRect(centerX - textWidth / 2 - 30, yPos - 8, 20, 2);
            g2.setColor(Color.WHITE);
        }
        
        drawSkipMessage(g2, elapsed);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }
    
    private void drawIntroLogoPhase(Graphics2D g2, long elapsed) {
        introAlpha = calculateFadeAlpha(elapsed, INTRO_TEXT_DURATION);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, introAlpha));
        
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, Math.max(48, gp.screenHeight / 8)));
        drawCenteredText(g2, "EPITECH", gp.screenHeight / 2 - 30);
        
        g2.setFont(new Font("Arial", Font.PLAIN, Math.max(24, gp.screenHeight / 18)));
        drawCenteredText(g2, "Game Studio", gp.screenHeight / 2 + 30);
        
        // Effet de brillance
        if (elapsed % 2000 < 1000) {
            float pulse = (float)Math.sin(elapsed * Math.PI / 1000);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, introAlpha * 0.15f * Math.abs(pulse)));
            g2.setColor(LIGHT_BLUE);
            g2.fillOval(gp.screenWidth / 2 - 150, gp.screenHeight / 2 - 120, 300, 200);
        }
        
        drawSkipMessage(g2, elapsed);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }
    
    private void drawSkipMessage(Graphics2D g2, long elapsed) {
        if (elapsed % 1000 < 700) {
            g2.setFont(new Font("Arial", Font.PLAIN, Math.max(16, gp.screenHeight / 28)));
            g2.setColor(SEMI_TRANSPARENT_GRAY);
            drawCenteredText(g2, "Press ENTER to skip", gp.screenHeight - 50);
        }
    }
    
    private void transitionToMenu() {
        GamePanel.gameState = GamePanel.menuState;
        gp.tileM.selectRandomMenuGif();
        gp.sound.setFile(Sound.MENU_MUSIC);
        gp.sound.loop();
    }
    
    private void drawLoadingScreen(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        if (loadingStartTime == 0) loadingStartTime = System.currentTimeMillis();
        long elapsed = System.currentTimeMillis() - loadingStartTime;
        
        loadingProgress = Math.min(1.0f, elapsed / 2000.0f);
        loadingDots = (int)((elapsed / 300) % 4);
        
        // Titre avec points animés
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, Math.max(36, gp.screenHeight / 12)));
        String title = loadingText + ".".repeat(loadingDots);
        drawCenteredText(g2, title, gp.screenHeight / 2 - 50);
        
        // Barre de progression
        int barWidth = gp.screenWidth / 2;
        int barHeight = 30;
        int barX = gp.screenWidth / 2 - barWidth / 2;
        int barY = gp.screenHeight / 2 + 20;
        
        g2.setColor(new Color(100, 100, 100));
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(barX, barY, barWidth, barHeight);
        
        int fillWidth = (int)((barWidth - 6) * loadingProgress);
        g2.setColor(new Color(50, 150, 255));
        g2.fillRect(barX + 3, barY + 3, fillWidth, barHeight - 6);
        
        g2.setColor(new Color(100, 200, 255, 100));
        g2.fillRect(barX + 3, barY + 3, fillWidth, barHeight / 3);
        
        // Pourcentage
        g2.setFont(new Font("Arial", Font.PLAIN, Math.max(18, gp.screenHeight / 24)));
        g2.setColor(Color.WHITE);
        drawCenteredText(g2, (int)(loadingProgress * 100) + "%", barY + barHeight + 40);
        
        if (loadingProgress >= 1.0f) {
            loadingStartTime = 0;
            loadingProgress = 0f;
        }
    }
    
    public void startLoading(String text) {
        loadingText = text;
        loadingStartTime = 0;
        loadingProgress = 0f;
        GamePanel.gameState = GamePanel.loadingState;
    }
    
    public boolean isLoadingComplete() {
        return loadingProgress >= 1.0f;
    }

    public void ChooseEnemy(Graphics2D g2) {
        g2.setFont(getLargeFont());

        if (gp.gameAction.getSelectedEnemies().getPV() <= 0) {
            g2.setColor(Color.RED);
            String text = gp.gameAction.getSelectedEnemies().getName() + " is already dead";
            drawCenteredText(g2, text, gp.screenHeight / 4);
        }
        if (gp.gameAction.getSelected().getPV() <= 0) {
            g2.setColor(Color.red);

            String text;
            int textLength;
            int x;

            text = gp.gameAction.getSelected().getName() + " is already dead";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth/2 - textLength/2;
            int y = gp.screenHeight / 4;

            g2.drawString(text, x, y);
        }

        g2.setColor(Color.ORANGE);
        String text = gp.gameAction.getSelectedEnemies().getName() + " will takes damage";
        drawCenteredText(g2, text, gp.screenHeight / 6);
    }

    public void DisplayAttack (Graphics2D g2) {
        // Log supprimé
        
        if (GameAction.asSelectedAttack) {
            ChooseEnemy(g2);
            return;
        }

        // Afficher le message indiquant qu'on sélectionne une attaque
        g2.setFont(getMediumFont());
        g2.setColor(Color.YELLOW);
        String header = "Select an attack for " + gp.gameAction.getSelected().getName();
        drawCenteredText(g2, header, gp.screenHeight / 8);
        // Log supprimé

        g2.setFont(getSmallFont());
        g2.setColor(Color.WHITE);
        int x = gp.screenWidth / 20;
        int y = (int)(gp.screenHeight * 0.92);

        for (int i = 0; i < gp.gameAction.getSelected().getAttack().size(); i++) {
            Attack attack = gp.gameAction.getSelected().getAttack().get(i);
            String text = attack.getName() + " : " + attack.getDamage() + " dmg";
            int textLength = getTextWidth(g2, text);
            
            boolean selected = (i == GameAction.indexAttack);
            makeButton(g2, x - 4, y - 15, textLength + 8, 20, selected);
            g2.drawString(text, x, y);
            x += gp.screenWidth / 5;
        }
    }

    public void drawPlayerTurn(Graphics2D g2) {
        g2.setFont(getSmallFont());
        g2.setColor(Color.WHITE);
        
        String text = "It's your turn to play";
        int y = gp.screenHeight / 20;
        int textLength = getTextWidth(g2, text);
        int x = gp.screenWidth/2 - textLength/2;
        
        makeButton(g2, x - 4, y - 15, textLength + 8, 20, false);
        g2.drawString(text, x, y);

       
        
        if (GameAction.asSelectedAllies) {
            
            DisplayAttack(g2);
        } else {
            // Pas encore d'allié sélectionné : afficher qui est en train de sélectionner
            text = "You're currently using : " + gp.gameAction.getSelected().getName();
            y = gp.screenHeight / 8;
            textLength = getTextWidth(g2, text);
            x = gp.screenWidth/2 - textLength/2;
            
            makeButton(g2, x - 4, y - 15, textLength + 8, 20, true);
            g2.drawString(text, x, y);
        }
    }

    public void TestIa(Graphics2D g2) {
        g2.setFont(getLargeFont());
        g2.setColor(Color.ORANGE);
        drawCenteredText(g2, "It's ia's turn to play", gp.screenHeight / 10);
    }

    public void HomeMenu(Graphics2D g2) {
        g2.setFont(getLargeFont());
        g2.setColor(Color.WHITE);

        drawCenteredText(g2, "Welcome in Timeless Dungeon", gp.screenHeight / 4);
        drawMenuOption(g2, "Play", gp.screenHeight / 2, 0);
        drawMenuOption(g2, "Team Manager", (gp.screenHeight * 5) / 8, 1);
        drawMenuOption(g2, "Settings", (gp.screenHeight * 3) / 4, 2);
        drawMenuOption(g2, "Quit", (gp.screenHeight * 7) / 8, 3);
    }
    
    private void drawMenuOption(Graphics2D g2, String text, int y, int optionIndex) {
        int x = getCenterX(g2, text);
        g2.drawString(text, x, y);
        if (commandNum == optionIndex) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void DungeonSelectionMenu(Graphics2D g2) {
        g2.setFont(getLargeFont());
        g2.setColor(Color.WHITE);
        drawCenteredText(g2, "Select a dungeon", gp.screenHeight / 8);

        if (forestWorld != null && iceWorld != null && desertWorld != null && mountainWorld != null) {
            drawVisualDungeonSelection(g2);
        } else {
            drawTextDungeonSelection(g2);
        }
    }

    private void drawVisualDungeonSelection(Graphics2D g2) {
        if (commandNum >= 4) {
            // Afficher seulement le bouton Back au centre
            g2.setFont(getLargeFont());
            g2.setColor(Color.WHITE);
            String backText = "Back";
            int backTextWidth = g2.getFontMetrics().stringWidth(backText);
            int backX = gp.screenWidth/2 - backTextWidth/2;
            int backY = gp.screenHeight / 2;
            g2.drawString(backText, backX, backY);
            g2.drawString(">", backX - gp.tileSize, backY);
            
            g2.setFont(getSmallFont());
            g2.setColor(new Color(200, 200, 200));
            String instructions = "Press ENTER to go back - ↑ to return to dungeons";
            int instrWidth = g2.getFontMetrics().stringWidth(instructions);
            g2.drawString(instructions, gp.screenWidth/2 - instrWidth/2, gp.screenHeight - 20);
            return;
        }

        int centerX = gp.screenWidth / 2;
        int centerY = gp.screenHeight / 3;
        
        // Taille des worlds
        int selectedSize = gp.screenWidth / 4;
        int selectedHeight = (int)(selectedSize * 1.2);
        int sideSize = (int)(selectedSize * 0.7);
        int sideHeight = (int)(sideSize * 1.2);
        int farSize = (int)(selectedSize * 0.5);
        int farHeight = (int)(farSize * 1.2);

        BufferedImage[] worlds = {forestWorld, iceWorld, desertWorld, mountainWorld};
        String[] worldNames = {"Forest of Dispair", "Ice Dungeon", "Desert Hill", "Mountain"};
        
        // Dessiner les 5 worlds visibles avec leurs positions et tailles
        int spacing = gp.screenWidth / 14;
        int[] offsets = {
            -selectedSize/2 - spacing - sideSize/2 - spacing - farSize/2,
            -selectedSize/2 - spacing - sideSize/2,
            0,
            selectedSize/2 + spacing + sideSize/2,
            selectedSize/2 + spacing + sideSize/2 + spacing + farSize/2
        };
        int[] sizes = {farSize, sideSize, selectedSize, sideSize, farSize};
        int[] heights = {farHeight, sideHeight, selectedHeight, sideHeight, farHeight};
        float[] alphas = {0.3f, 0.6f, 1.0f, 0.6f, 0.3f};
        
        for (int i = 0; i < 5; i++) {
            int worldIndex = (commandNum + i - 2 + 4) % 4;
            int x = centerX + offsets[i] - sizes[i]/2;
            int y = centerY - heights[i]/2;
            
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphas[i]));
            g2.drawImage(worlds[worldIndex], x, y, sizes[i], heights[i], null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            
            // Bordure pour le world sélectionné
            if (i == 2) {
                g2.setColor(new Color(255, 255, 255, 200));
                g2.setStroke(new BasicStroke(5));
                g2.drawRoundRect(x - 8, y - 8, sizes[i] + 16, heights[i] + 16, 20, 20);
                g2.setColor(new Color(255, 255, 255, 80));
                g2.setStroke(new BasicStroke(3));
                g2.drawRoundRect(x - 12, y - 12, sizes[i] + 24, heights[i] + 24, 20, 20);
            }
        }
        
        // Noms des worlds
        g2.setFont(new Font("Arial", Font.BOLD, Math.max(20, gp.screenHeight / 25)));
        g2.setColor(Color.WHITE);
        drawCenteredText(g2, worldNames[commandNum], centerY + selectedHeight / 2 + 40);
        
        g2.setFont(getSmallFont());
        g2.setColor(new Color(150, 150, 150));
        int leftIndex = (commandNum - 1 + 4) % 4;
        int rightIndex = (commandNum + 1) % 4;
        int leftX = centerX + offsets[1];
        int rightX = centerX + offsets[3];
        g2.drawString(worldNames[leftIndex], leftX - getTextWidth(g2, worldNames[leftIndex])/2, 
                     centerY + sideHeight / 2 + 30);
        g2.drawString(worldNames[rightIndex], rightX - getTextWidth(g2, worldNames[rightIndex])/2, 
                     centerY + sideHeight / 2 + 30);
        
        // Flèches de navigation
        g2.setFont(new Font("Arial", Font.BOLD, 40));
        g2.setColor(new Color(255, 255, 255, 150));
        g2.drawString("‹", 30, centerY);
        g2.drawString("›", gp.screenWidth - 50, centerY);

        // Bouton Back en bas
        g2.setFont(getLargeFont());
        g2.setColor(Color.WHITE);
        String backText = "Back";
        int backTextWidth = g2.getFontMetrics().stringWidth(backText);
        int backX = gp.screenWidth/2 - backTextWidth/2;
        int backY = gp.screenHeight - gp.screenHeight / 8;
        g2.drawString(backText, backX, backY);
        
        // Instructions de navigation
        g2.setFont(getSmallFont());
        g2.setColor(new Color(200, 200, 200));
        String instructions = "← → to swipe between dungeons - ↓ for Back - ENTER to select";
        int instrWidth = g2.getFontMetrics().stringWidth(instructions);
        g2.drawString(instructions, gp.screenWidth/2 - instrWidth/2, gp.screenHeight - 20);
    }

    private void drawTextDungeonSelection(Graphics2D g2) {
        // Version textuelle en fallback
        String text;
        int textLength;
        int x;
        int y;

        // First dungeon
        text = "Forest of Dispair";
        textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth/2 - textLength/2;
        y = gp.screenHeight / 3;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        // Second dungeon
        text = "Ice Dungeon";
        textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth/2 - textLength/2;
        y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        // Back
        text = "Back";
        textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth/2 - textLength/2;
        y = (gp.screenHeight * 2) / 3;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void OptionMenu(Graphics2D g2) {
        if (brightness < 1.0f) {
            g2.setColor(new Color(0, 0, 0, (int)((1.0f - brightness) * 200)));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        }
        
        g2.setFont(getLargeFont());
        g2.setColor(Color.WHITE);
        drawCenteredText(g2, "Settings", gp.screenHeight / 8);
        
        // Calculer les espacements adaptatifs
        OptionSpacing spacing = calculateOptionSpacing();
        int y = spacing.startY;
        
        // Volume et Brightness
        g2.setFont(new Font("Arial", Font.PLAIN, Math.max(20, gp.screenHeight / 25)));
        y = drawOptionWithBar(g2, "Volume", y, 0, spacing.startY);
        drawVolumeBar(g2, gp.screenWidth / 2, y - 15, gp.screenWidth / 3, 20);
        
        y += spacing.spacing;
        y = drawOptionWithBar(g2, "Brightness", y, 1, y);
        drawBrightnessBar(g2, gp.screenWidth / 2, y - 15, gp.screenWidth / 3, 20);
        
        // Séparateur
        y += spacing.separatorSpacing;
        g2.setColor(new Color(100, 100, 100));
        g2.drawLine(gp.screenWidth / 4, y, gp.screenWidth * 3 / 4, y);
        g2.setColor(Color.WHITE);
        
        // Résolutions
        y += spacing.resolutionSpacing;
        g2.setFont(new Font("Arial", Font.BOLD, Math.max(18, gp.screenHeight / 28)));
        drawCenteredText(g2, "Resolution", y);
        
        g2.setFont(new Font("Arial", Font.PLAIN, Math.max(18, gp.screenHeight / 28)));
        y = drawResolutionOption(g2, "960x528p", y + spacing.resSpacing, 2);
        y = drawResolutionOption(g2, "768x432p", y + spacing.resSpacing, 3);
        y = drawResolutionOption(g2, "1280x720p", y + spacing.resSpacing, 4);
        
        // Back
        g2.setFont(getLargeFont());
        y = gp.screenHeight - Math.max(50, gp.screenHeight / 10);
        int x = getCenterX(g2, "Back");
        g2.drawString("Back", x, y);
        if (optionNum == 5) g2.drawString(">", x - gp.tileSize, y);
        
        // Instructions
        g2.setFont(getSmallFont());
        g2.setColor(SEMI_TRANSPARENT_GRAY);
        drawCenteredText(g2, "← → to adjust | ↑ ↓ to navigate | ENTER to select", gp.screenHeight - 20);
    }
    
    private class OptionSpacing {
        int startY, spacing, separatorSpacing, resolutionSpacing, resSpacing;
    }
    
    private OptionSpacing calculateOptionSpacing() {
        OptionSpacing s = new OptionSpacing();
        if (gp.screenHeight >= 700) {
            s.startY = 150; s.spacing = 85; s.separatorSpacing = 55;
            s.resolutionSpacing = 50; s.resSpacing = 55;
        } else if (gp.screenHeight >= 500) {
            s.startY = gp.screenHeight / 5; s.spacing = gp.screenHeight / 9;
            s.separatorSpacing = gp.screenHeight / 14; s.resolutionSpacing = gp.screenHeight / 16;
            s.resSpacing = gp.screenHeight / 13;
        } else {
            s.startY = gp.screenHeight / 4; s.spacing = gp.screenHeight / 8;
            s.separatorSpacing = 30; s.resolutionSpacing = 25; s.resSpacing = 35;
        }
        return s;
    }
    
    private int drawOptionWithBar(Graphics2D g2, String text, int y, int optionIndex, int actualY) {
        int x = gp.screenWidth / 4;
        g2.drawString(text, x, actualY);
        if (optionNum == optionIndex) g2.drawString(">", x - 30, actualY);
        return actualY;
    }
    
    private int drawResolutionOption(Graphics2D g2, String text, int y, int optionIndex) {
        int x = getCenterX(g2, text);
        g2.drawString(text, x, y);
        if (optionNum == optionIndex) g2.drawString(">", x - 30, y);
        return y;
    }
    
    private void drawVolumeBar(Graphics2D g2, int x, int y, int width, int height) {
        drawProgressBar(g2, x, y, width, height, volume, new Color(50, 150, 255));
    }
    
    private void drawBrightnessBar(Graphics2D g2, int x, int y, int width, int height) {
        float normalizedBrightness = (brightness - 0.3f) / 0.7f;
        drawProgressBar(g2, x, y, width, height, normalizedBrightness, new Color(255, 255, 100));
    }
    
    private void drawProgressBar(Graphics2D g2, int x, int y, int width, int height, 
                                 float progress, Color fillColor) {
        g2.setColor(Color.WHITE);
        g2.drawRect(x, y, width, height);
        
        int fillWidth = (int)(width * progress);
        g2.setColor(fillColor);
        g2.fillRect(x + 2, y + 2, fillWidth - 4, height - 4);
        
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.PLAIN, 14));
        g2.drawString((int)(progress * 100) + "%", x + width + 10, y + 15);
    }

    public void drawTeamManager(Graphics2D g2) {
        g2.setFont(getLargeFont());
        g2.setColor(Color.WHITE);
        drawCenteredText(g2, "Team Manager", gp.screenHeight / 10);
        
        // Liste des héros disponibles
        String[] heroes = {"Knight", "Archer", "Wizard", "Musketeer", "Swordsman", "Enchantress"};
        
        // Afficher les 3 slots de l'équipe à gauche
        int slotStartY = gp.screenHeight / 4;
        int slotSpacing = gp.screenHeight / 6;
        int slotX = gp.screenWidth / 6;
        g2.setFont(getMediumFont());
        
        for (int i = 0; i < 3; i++) {
            int y = slotStartY + (i * slotSpacing);
            
            // Bordure du slot
            if (teamManagerSlot == i) {
                g2.setColor(LIGHT_BLUE);
                g2.setStroke(new BasicStroke(3));
            } else {
                g2.setColor(Color.GRAY);
                g2.setStroke(new BasicStroke(1));
            }
            g2.drawRect(slotX - 10, y - 30, 250, 45);
            
            // Contenu du slot
            g2.setColor(Color.WHITE);
            String slotText = "Slot " + (i + 1) + ": ";
            
            // Récupérer le héros actuel de la team du joueur
            if (gp.playerTeam != null && i < gp.playerTeam.getTeamSize()) {
                slotText += gp.playerTeam.getCharacter(i).getName();
            } else {
                slotText += "Empty";
            }
            
            g2.drawString(slotText, slotX, y);
        }
        
        // Afficher la liste des héros à droite
        int listX = gp.screenWidth * 2 / 3;
        int listStartY = gp.screenHeight / 4;
        g2.setFont(getMediumFont());
        g2.setColor(Color.WHITE);
        g2.drawString("Available Heroes:", listX - 20, listStartY - 40);
        
        g2.setFont(getSmallFont());
        for (int j = 0; j < heroes.length; j++) {
            int heroY = listStartY + (j * 40);
            if (heroSelectionIndex == j) {
                g2.setColor(LIGHT_BLUE);
                g2.fillRect(listX - 10, heroY - 25, 180, 35);
                g2.setColor(Color.BLACK);
                g2.drawString("► " + heroes[j], listX, heroY);
            } else {
                g2.setColor(Color.WHITE);
                g2.drawString(heroes[j], listX + 20, heroY);
            }
        }
        
        // Instructions
        g2.setColor(Color.GRAY);
        g2.setFont(new Font("Arial", Font.PLAIN, 12));
        drawCenteredText(g2, "UP/DOWN: Navigate slots | LEFT/RIGHT: Select hero | ENTER: Confirm | ESC: Back", 
                        gp.screenHeight * 9 / 10);
    }

    public void WinScreen(Graphics2D g2) {
        gp.sound.stop();
        gp.sound.setFile(Sound.VICTORY_MUSIC);
        gp.sound.play();
        drawGameEndScreen(g2, new Color(0, 100, 0, 200), "VICTORY!", Color.YELLOW, 
                  "You have defeated all enemies!");
    }

    public void LoseScreen(Graphics2D g2) {
        gp.sound.stop();
        gp.sound.setFile(Sound.LOSE_MUSIC);
        gp.sound.play();
        drawGameEndScreen(g2, new Color(100, 0, 0, 200), "DEFEAT", Color.RED, 
                  "Your team has been defeated...");
    }
    
    private void drawGameEndScreen(Graphics2D g2, Color bgColor, String title, 
                                   Color titleColor, String message) {
        g2.setColor(bgColor);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        g2.setFont(new Font("Arial", Font.BOLD, Math.max(60, gp.screenHeight / 8)));
        g2.setColor(titleColor);
        drawCenteredText(g2, title, gp.screenHeight / 3);
        
        g2.setFont(getLargeFont());
        g2.setColor(Color.WHITE);
        drawCenteredText(g2, message, gp.screenHeight / 2);
        
        g2.setFont(getSmallFont());
        drawCenteredText(g2, "Press ENTER to return to menu", (gp.screenHeight * 2) / 3);
    }
}