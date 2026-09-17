package characters.enemies;

import characters.Character;
import characters.Attack;

public class Demon_2 extends Character {

    public Demon_2() {
        super(
            "Demon 2", "Enemy", 14,
            100, 12, 8, 11, 0,
            "assets/Demon_2/Demon_2_Spritelist.png", 128, 128
        );
        
        Attack attack1 = new Attack("Demon Claw", 16);
        Attack attack2 = new Attack("Hellfire Blast", 22);
        addAttack(attack1);
        addAttack(attack2);
        
        configureAnimations();
    }
    
    private void configureAnimations() {
        setAnimationPosition("idle", 0, 0, 6);
        setAnimationPosition("walk", 1, 0, 12);
        setAnimationPosition("attack", 2, 0, 5);
        setAnimationPosition("hurt", 3, 0, 3);
        setAnimationPosition("death", 4, 0, 3);
        
        setAnimationDelay("idle", 110);
        setAnimationDelay("walk", 70);
        setAnimationDelay("run", 60);
        setAnimationDelay("attack1", 80);
        setAnimationDelay("attack2", 100);
        setAnimationDelay("hurt", 120);
        setAnimationDelay("death", 150);
    }

}