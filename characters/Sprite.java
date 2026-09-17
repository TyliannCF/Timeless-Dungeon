package characters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.HashMap;
import java.util.Map;
import java.awt.Point;

public abstract class Sprite {
    
   
    private BufferedImage spritesheet;
    private String spritesheetPath;
    private int frameWidth, frameHeight;
    
   
    private Map<String, BufferedImage> additionalSpritesheets;
    private Map<String, Integer> spritesheetFrameWidths;
    private Map<String, Integer> spritesheetFrameHeights;
    private Map<String, String> animationToSpritesheetMap;
    
    
    private Map<String, Point> animationGridPositions;
    private Map<String, BufferedImage[]> frameCache;
    
    
    private String status;
    private int currentFrame, frameCount, frameDelay;
    private long lastFrameTime;
    private boolean isAnimating;
    private boolean returnToIdleAfterAnimation;
    
    // Configuration des animations
    private Map<String, Integer> animationFrameCounts;
    private Map<String, Integer> animationDelays;
   
     
    
    public Sprite(String spritesheetPath, int frameWidth, int frameHeight) {
       
        this.status = "idle";
        this.currentFrame = 0;
        this.frameDelay = 100;
        this.lastFrameTime = System.currentTimeMillis();
        this.isAnimating = false;
        this.returnToIdleAfterAnimation = false;
        
        
        this.spritesheetPath = spritesheetPath;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        
        // Initialiser les Maps
        this.animationFrameCounts = new HashMap<>();
        this.animationDelays = new HashMap<>();
        this.animationGridPositions = new HashMap<>();
        this.frameCache = new HashMap<>();
        this.additionalSpritesheets = new HashMap<>();
        this.spritesheetFrameWidths = new HashMap<>();
        this.spritesheetFrameHeights = new HashMap<>();
        this.animationToSpritesheetMap = new HashMap<>();
        
        loadSpritesheet();
    }
    
    private void loadSpritesheet() {
        try {
            this.spritesheet = ImageIO.read(new File(spritesheetPath));
            System.out.println("Spritesheet chargée: " + spritesheetPath);
        } catch (IOException e) {
            System.err.println("Erreur chargement spritesheet: " + spritesheetPath);
            e.printStackTrace();
        }
    }
    
    
   
    protected void loadAdditionalSpritesheet(String name, String path, int frameWidth, int frameHeight) {
        try {
            BufferedImage sheet = ImageIO.read(new File(path));
            additionalSpritesheets.put(name, sheet);
            spritesheetFrameWidths.put(name, frameWidth);
            spritesheetFrameHeights.put(name, frameHeight);
            System.out.println("Spritesheet additionnelle chargée: " + name + " (" + path + ")");
        } catch (IOException e) {
            System.err.println("Erreur chargement spritesheet " + name + ": " + path);
            e.printStackTrace();
        }
    }
    
    
    
    
    protected void setAnimationPosition(String animationName, int row, int col, int frameCount) {
        String key = animationName.toLowerCase();
        animationGridPositions.put(key, new Point(col, row));
        animationFrameCounts.put(key, frameCount);
    }
    
    
    protected void setAnimationPositionInSheet(String animationName, String sheetName, int row, int col, int frameCount) {
        String key = animationName.toLowerCase();
        animationGridPositions.put(key, new Point(col, row));
        animationFrameCounts.put(key, frameCount);
        animationToSpritesheetMap.put(key, sheetName);
    }
    
   
    public BufferedImage getFrame(String animationName, int frameIndex) {
        
        BufferedImage[] cachedFrames = frameCache.get(animationName);
        
        if (cachedFrames != null && frameIndex < cachedFrames.length && cachedFrames[frameIndex] != null) {
            return cachedFrames[frameIndex];
        }
        
        
        String sheetName = animationToSpritesheetMap.get(animationName);
        BufferedImage currentSheet;
        int currentFrameWidth;
        int currentFrameHeight;
        
        if (sheetName != null && additionalSpritesheets.containsKey(sheetName)) {
            
            currentSheet = additionalSpritesheets.get(sheetName);
            currentFrameWidth = spritesheetFrameWidths.get(sheetName);
            currentFrameHeight = spritesheetFrameHeights.get(sheetName);
        } else {
            
            currentSheet = spritesheet;
            currentFrameWidth = frameWidth;
            currentFrameHeight = frameHeight;
        }
        
        if (currentSheet == null) {
            return null;
        }
        
       
        Point pos = animationGridPositions.get(animationName);
        if (pos == null) {
            return null;
        }
        
        int x = (pos.x + frameIndex) * currentFrameWidth;
        int y = pos.y * currentFrameHeight;
        
       
        if (x + currentFrameWidth > currentSheet.getWidth() || y + currentFrameHeight > currentSheet.getHeight()) {
            return null;
        }
        
        BufferedImage frame = currentSheet.getSubimage(x, y, currentFrameWidth, currentFrameHeight);
        
       
        if (cachedFrames == null) {
            int totalFrames = animationFrameCounts.getOrDefault(animationName, 1);
            cachedFrames = new BufferedImage[totalFrames];
            frameCache.put(animationName, cachedFrames);
        }
        if (frameIndex < cachedFrames.length) {
            cachedFrames[frameIndex] = frame;
        }
        
        return frame;
    }
    
    
    
    // Définir délai d'animation
    public void setAnimationDelay(String animationName, int delay) {
        animationDelays.put(animationName.toLowerCase(), delay);
    }
    
    // Changer animation courante 
    public void setStatus(String status) {
        String lowerStatus = status.toLowerCase();
        
        // Essayer d'abord avec le nom exact
        if (!animationFrameCounts.containsKey(lowerStatus)) {
            // Si "attack" n'existe pas, essayer "attack1"
            if (lowerStatus.equals("attack")) {
                if (animationFrameCounts.containsKey("attack1")) {
                    lowerStatus = "attack1";
                }
            }
            // Si "dead" n'existe pas, essayer "death"
            if (lowerStatus.equals("dead")) {
                if (animationFrameCounts.containsKey("death")) {
                    lowerStatus = "death";
                }
            }
        }
        
        // Vérifier si l'animation existe
        if (animationFrameCounts.containsKey(lowerStatus)) {
            this.status = lowerStatus;
            this.frameCount = animationFrameCounts.get(lowerStatus);
            this.frameDelay = animationDelays.getOrDefault(lowerStatus, 100);
        } else {
            System.out.println("Animation inconnue: " + status + ", fallback sur idle");
            this.status = "idle";
            this.frameCount = animationFrameCounts.getOrDefault("idle", 1);
            this.frameDelay = animationDelays.getOrDefault("idle", 100);
        }
        
        playAnimation();
    }
    
    
    public void playAnimation() {
        this.isAnimating = true;
        this.currentFrame = 0;
        this.lastFrameTime = System.currentTimeMillis();
    }
    
    
    public void stopAnimation() {
        this.isAnimating = false;
        this.currentFrame = 0;
    }
    
    // Mettre à jour frame courante (basé sur temps écoulé)
    public void updateAnimation() {
        if (!isAnimating) return;
        
        long currentTime = System.currentTimeMillis();
        
        if (currentTime - lastFrameTime >= frameDelay) {
            currentFrame++;
            
            // Gestion fin d'animation
            if (currentFrame >= frameCount) {
                if (status.equals("death")) {
                    // Death: bloquer sur dernière frame
                    currentFrame = frameCount - 1;
                    isAnimating = false;
                } else if (returnToIdleAfterAnimation) {
                    // Retour automatique à idle après l'animation
                    setStatus("idle");
                    playAnimation();
                    returnToIdleAfterAnimation = false;
                } else {
                    // Autres: boucler
                    currentFrame = 0;
                }
            }
            
            lastFrameTime = currentTime;
        }
    }
    
    
    public void setAnimationSpeed(int delayMs) {
        this.frameDelay = delayMs;
    }
    
    public String getStatus() {
        return status;
    }
    
    public int getCurrentFrame() {
        return currentFrame;
    }
    
    public int getFrameCount() {
        return frameCount;
    }
    
    public boolean isAnimating() {
        return isAnimating;
    }
    
    
    public int getAnimationFrameCount(String animationName) {
        return animationFrameCounts.getOrDefault(animationName.toLowerCase(), 1);
    }
    
    public int getAnimationDelay(String animationName) {
        return animationDelays.getOrDefault(animationName.toLowerCase(), frameDelay);
    }
    
    // (ILANNE) Jouer une animation une seule fois puis retourner à idle
    public void playAnimationOnce(String animationName) {
        setStatus(animationName);
        playAnimation();
        returnToIdleAfterAnimation = true;
    }
    
    public String getSpritesheetPath() {
        return spritesheetPath;
    }
    
    public int getFrameWidth() {
        return frameWidth;
    }
    
    public int getFrameHeight() {
        return frameHeight;
    }
}