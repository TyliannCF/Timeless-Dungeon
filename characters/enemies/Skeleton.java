package characters.enemies;

import characters.Character;
import characters.Attack;

public class Skeleton extends Character {
    
    public Skeleton() {
        super(
            "Skeleton", "Enemy", 8,
            50, 7, 4, 6, 0,
            "assets/Skeleton/Skeleton_spritelist.png", 128, 128
        );
        
        Attack attack1 = new Attack("Bone Strike", 10);
        Attack attack2 = new Attack("Skull Bash", 14);
        addAttack(attack1);
        addAttack(attack2);
        
        configureAnimations();
    }
    
    private void configureAnimations() {
        setAnimationPosition("idle", 0, 0, 7);
        setAnimationPosition("walk", 1, 0, 8);
        setAnimationPosition("run", 2, 0, 7);
        setAnimationPosition("jump", 3, 0, 10);
        setAnimationPosition("attack1", 4, 0, 7);
        setAnimationPosition("attack2", 5, 0, 4);
        setAnimationPosition("attack3", 6, 0, 7);
        setAnimationPosition("special_attack", 7, 0, 5);
        setAnimationPosition("hurt", 8, 0, 3);
        setAnimationPosition("death", 9, 0, 3);
        
        setAnimationDelay("idle", 150);
        setAnimationDelay("walk", 120);
        setAnimationDelay("run", 100);
        setAnimationDelay("attack1", 110);
        setAnimationDelay("attack2", 130);
        setAnimationDelay("hurt", 160);
        setAnimationDelay("death", 200);
    }
    
}
