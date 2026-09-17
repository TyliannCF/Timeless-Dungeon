package characters.heroes;

import characters.Attack;
import characters.Character;

public class Archer extends Character {
    
    public Archer() {
        super(
            "Legolas", "Archer", 4,
            90, 11, 6, 12, 35,
            "assets/Archer/Archer_spritelist.png", 128, 128
        );

        Attack attack1 = new Attack("Quick Shot", 15);
        Attack attack2 = new Attack("Power Arrow", 25);

        addAttack(attack1);
        addAttack(attack2);
        configureAnimations();
    }
    
    private void configureAnimations() {
        // Positions
        setAnimationPosition("idle", 0, 0, 6);
        setAnimationPosition("walk", 1, 0, 8);
        setAnimationPosition("run", 2, 0, 8);
        setAnimationPosition("jump", 3, 0, 9);
        setAnimationPosition("attack1", 4, 0, 4);
        setAnimationPosition("attack2", 5, 0, 14);
        setAnimationPosition("attack3", 6, 0, 13);
        setAnimationPosition("attack4", 7, 0, 4);
        setAnimationPosition("hurt", 8, 0, 3);
        setAnimationPosition("death", 9, 0, 3);
        
        setAnimationDelay("idle", 120);
        setAnimationDelay("walk", 105);
        setAnimationDelay("run", 95);
        setAnimationDelay("jump", 100);
        setAnimationDelay("attack1", 130);
        setAnimationDelay("attack2", 80);    
        setAnimationDelay("attack3", 85);    
        setAnimationDelay("attack4", 320);   
        setAnimationDelay("hurt", 130);
        setAnimationDelay("death", 170);

    }
}
