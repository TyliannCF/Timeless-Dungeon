package characters.enemies;

import characters.Attack;
import characters.Character;

public class Mimic_1 extends Character {

    public Mimic_1() {
        super(
            "Mimic 1", "Enemy", 10,
            60, 8, 5, 7, 0,
            "assets/Mimic_1/Mimic_1_Spritelist.png", 128, 128
        );

        Attack attack1 = new Attack("Bite", 10);
        Attack attack2 = new Attack("Chomp", 16);

        addAttack(attack1);
        addAttack(attack2);
        
        configureAnimations();
    }
    
    private void configureAnimations() {
        setAnimationPosition("idle", 0, 0, 1);
        setAnimationPosition("special", 1, 0, 10);
        setAnimationPosition("walk", 2, 0, 11);
        setAnimationPosition("attack", 3, 0, 7);
        setAnimationPosition("hurt", 4, 0, 5);
        setAnimationPosition("dead", 5, 0, 1);
        
        setAnimationDelay("idle", 150);
        setAnimationDelay("walk", 120);
        setAnimationDelay("run", 100);
        setAnimationDelay("attack1", 110);
        setAnimationDelay("attack2", 130);
        setAnimationDelay("hurt", 160);
        setAnimationDelay("death", 200);
    }

}