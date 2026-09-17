package characters.enemies;

import characters.Attack;
import characters.Character;

public class Mimic_2 extends Character {

    public Mimic_2() {
        super(
            "Mimic 2", "Enemy", 11,
            70, 9, 4, 8, 0,
            "assets/Mimic_2/Mimic_2_Spritelist.png", 128, 128
        );

        Attack attack1 = new Attack("Bite", 12);
        Attack attack2 = new Attack("Chomp", 18);

        addAttack(attack1);
        addAttack(attack2);
        
        configureAnimations();
    }
    
    private void configureAnimations() {
        setAnimationPosition("idle", 0, 0, 1);
        setAnimationPosition("special", 1, 0, 6);
        setAnimationPosition("walk", 2, 0, 9);
        setAnimationPosition("attack", 3, 0, 4);
        setAnimationPosition("hurt", 4, 0, 3);
        setAnimationPosition("dead", 5, 0, 4);
        
        setAnimationDelay("idle", 140);
        setAnimationDelay("walk", 100);
        setAnimationDelay("run", 90);
        setAnimationDelay("attack1", 110);
        setAnimationDelay("attack2", 130);
        setAnimationDelay("hurt", 150);
        setAnimationDelay("death", 180);
    }
    
}
