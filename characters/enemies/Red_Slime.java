package characters.enemies;

import characters.Character;
import characters.Attack;

public class Red_Slime extends Character {

    public Red_Slime() {
        super(
            "Red Slime", "Enemy", 8,
            30, 5, 2, 6, 0,
            "assets/Red_Slime/Red_Slime_Spritelist.png", 128, 128
        );
        
        Attack attack1 = new Attack("Fire Splash", 7);
        Attack attack2 = new Attack("Burning Bounce", 10);
        addAttack(attack1);
        addAttack(attack2);
        
        configureAnimations();
    }
    
    private void configureAnimations() {
        setAnimationPosition("idle", 0, 0, 8);
        setAnimationPosition("walk", 1, 0, 8);
        setAnimationPosition("run", 2, 0, 7);
        setAnimationPosition("run_attack", 3, 0, 10);
        setAnimationPosition("jump", 4, 0, 13);
        setAnimationPosition("attack1", 5, 0, 4);
        setAnimationPosition("attack2", 6, 0, 4);
        setAnimationPosition("attack3", 7, 0, 5);
        setAnimationPosition("hurt", 8, 0, 6);
        setAnimationPosition("death", 9, 0, 3);
        
        setAnimationDelay("idle", 150);
        setAnimationDelay("walk", 120);
        setAnimationDelay("attack1", 130);
        setAnimationDelay("attack2", 140);
        setAnimationDelay("hurt", 160);
        setAnimationDelay("death", 200);
    }
    
}
