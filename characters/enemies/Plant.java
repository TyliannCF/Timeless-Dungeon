package characters.enemies;

import characters.Character;
import characters.Attack;

public class Plant extends Character {
    
    public Plant() {
        super(
            "Plant", "Enemy", 12,
            45, 6, 3, 7, 0,
            "assets/Plent/Plent_spritelist.png", 128, 128
        );
        
        Attack attack1 = new Attack("Vine Whip", 9);
        Attack attack2 = new Attack("Poison Spore", 13);
        addAttack(attack1);
        addAttack(attack2);
        
        configureAnimations();
    }
    
    private void configureAnimations() {
        setAnimationPosition("idle", 0, 0, 5);
        setAnimationPosition("walk", 1, 0, 9);
        setAnimationPosition("attack1", 2, 0, 6);
        setAnimationPosition("attack2", 3, 0, 5);
        setAnimationPosition("attack3", 4, 0, 8);
        setAnimationPosition("poison", 5, 0, 7);
        setAnimationPosition("disguise", 6, 0, 11);
        setAnimationPosition("attack_disguise", 7, 0, 7);
        setAnimationPosition("hurt", 10, 0, 3);
        setAnimationPosition("death", 11, 0, 2);
        
        setAnimationDelay("idle", 140);
        setAnimationDelay("walk", 100);
        setAnimationDelay("attack1", 110);
        setAnimationDelay("attack2", 130);
        setAnimationDelay("attack3", 95);
        setAnimationDelay("hurt", 150);
        setAnimationDelay("death", 200);
        setAnimationDelay("attack_disguise", 105);
        setAnimationDelay("cloud_poison", 100);
        setAnimationDelay("poison", 110);
        setAnimationDelay("disguise", 95);
    }
}
