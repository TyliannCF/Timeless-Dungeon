package characters.enemies;

import characters.Attack;
import characters.Character;

public class Mimic_3 extends Character {

    public Mimic_3() {
        super(
            "Mimic 3", "Enemy", 12,
            80, 10, 6, 9, 0,
            "assets/Mimic_3/Mimic_3_Spritelist.png", 128, 128
        );

        Attack attack1 = new Attack("Bite", 14);
        Attack attack2 = new Attack("Chomp", 20);

        addAttack(attack1);
        addAttack(attack2);
        
        configureAnimations();
    }
    
    private void configureAnimations() {
        setAnimationPosition("idle", 0, 0, 1);
        setAnimationPosition("special", 1, 0, 5);
        setAnimationPosition("walk", 2, 0, 6);
        setAnimationPosition("attack", 3, 0, 7);
        setAnimationPosition("hurt", 4, 0, 4);
        setAnimationPosition("dead", 5, 0, 2);
      
        
        setAnimationDelay("idle", 130);
        setAnimationDelay("walk", 90);
        setAnimationDelay("run", 80);
        setAnimationDelay("attack1", 100);
        setAnimationDelay("attack2", 120);
        setAnimationDelay("hurt", 140);
        setAnimationDelay("death", 170);
    }

}