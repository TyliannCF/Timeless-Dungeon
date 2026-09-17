package characters.enemies;

import characters.Attack;
import characters.Character;

public class Demon_1 extends Character {

    public Demon_1() {
        super(
            "Demon 1", "Enemy", 13,
            90, 11, 7, 10, 0,
            "assets/Demon_1/Demon_1_Spritelist.png", 128, 128
        );

        Attack attack1 = new Attack("Claw Strike", 14);
        Attack attack2 = new Attack("Dark Fire", 20);

        addAttack(attack1);
        addAttack(attack2);
        
        configureAnimations();
    }
    
    private void configureAnimations() {
        setAnimationPosition("idle", 0, 0, 6);
        setAnimationPosition("walk", 1, 0, 5);
        setAnimationPosition("run", 2, 0, 9);
        setAnimationPosition("hurt", 3, 0, 4);
        setAnimationPosition("death", 4, 0, 3);
        setAnimationPosition("charge", 5, 0, 6);
        
        setAnimationDelay("idle", 120);
        setAnimationDelay("walk", 80);
        setAnimationDelay("run", 70);
        setAnimationDelay("attack1", 90);
        setAnimationDelay("attack2", 110);
        setAnimationDelay("hurt", 130);
        setAnimationDelay("death", 160);
    }

}