package characters.enemies;

import characters.Character;

public class FireSpiritSpritesheet extends Character {
    
    public FireSpiritSpritesheet() {
        super(
            "Fire Spirit", "Enemy", 10,
            60, 8, 3, 10, 50,
            "assets/Fire_Spirit/Fire_Spirit_spritelist.png", 128, 128
        );
        
        configureAnimations();
    }
    
    private void configureAnimations() {
        // Positions
        setAnimationPosition("idle", 0, 0, 6);
        setAnimationPosition("walk", 1, 0, 7);
        setAnimationPosition("run", 2, 0, 7);
        setAnimationPosition("idle2", 3, 0, 6);
        setAnimationPosition("shot", 4, 0, 8);
        setAnimationPosition("attack", 5, 0, 14);
        setAnimationPosition("explosion", 6, 0,11);
        setAnimationPosition("flame", 7, 0, 13);
        setAnimationPosition("hurt", 8, 0, 3);
        setAnimationPosition("death", 9, 0, 5);
        
        // Délais d'animation
        setAnimationDelay("idle", 100);
        setAnimationDelay("walk", 110);
        setAnimationDelay("run", 100);
        setAnimationDelay("jump", 110);
        setAnimationDelay("attack1", 90);   // Explosion
        setAnimationDelay("attack2", 85);   // Flame
        setAnimationDelay("attack3", 95);   // Shot
        setAnimationDelay("attack4", 100);  // Attack
        setAnimationDelay("hurt", 130);
        setAnimationDelay("death", 150);
    }
}
