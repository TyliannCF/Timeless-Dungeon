package characters.heroes;

import characters.Attack;
import characters.Character;

public class Musketeer extends Character {
    
    public Musketeer() {
        super(
            "Aramis", "Musketeer", 3,
            100, 12, 8, 10, 40,
            "assets/Musketeer/Musketeer_spritelist.png", 128, 128
        );
        Attack attack1 = new Attack("Quick Shot", 16);
        Attack attack2 = new Attack("Aimed Fire", 26);

        addAttack(attack1);
        addAttack(attack2);
        configureAnimations();
    }
    
    private void configureAnimations() {
        // Positions
        setAnimationPosition("idle", 0, 0, 5);
        setAnimationPosition("walk", 1, 0, 8);
        setAnimationPosition("run", 2, 0, 8);
        setAnimationPosition("jump", 3, 0, 7);
        setAnimationPosition("attack1", 4, 0, 5);
        setAnimationPosition("attack2", 5, 0, 4);
        setAnimationPosition("attack3", 6, 0, 6);
        setAnimationPosition("attack4", 7, 0, 5);
        setAnimationPosition("hurt", 8, 0, 2);
        setAnimationPosition("death", 9, 0, 4);
        
        setAnimationDelay("idle", 125);
        setAnimationDelay("walk", 105);
        setAnimationDelay("run", 95);
        setAnimationDelay("jump", 105);
        setAnimationDelay("attack1", 110);
        setAnimationDelay("attack2", 130);
        setAnimationDelay("attack3", 105);
        setAnimationDelay("attack4", 110);
        setAnimationDelay("hurt", 140);
        setAnimationDelay("death", 160);

    }
}
