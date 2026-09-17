package characters.enemies;

import characters.Attack;
import characters.Character;

public class Ally_Queen extends Character {

    public Ally_Queen() {
        super(
            "Ally Queen", "Ally", 16,
            120, 14, 10, 13, 0,
            "assets/Ally_Queen/Ally_Queen_Spritelist.png", 128, 128
        );

        Attack attack1 = new Attack("Royal Strike", 18);
        Attack attack2 = new Attack("Summon Power", 28);

        addAttack(attack1);
        addAttack(attack2);
        
        configureAnimations();
    }
    
    private void configureAnimations() {
        setAnimationPosition("summon_ally", 0, 0, 11);
        setAnimationPosition("idle", 1, 0, 6);
        setAnimationPosition("walk", 2, 0, 5);
        setAnimationPosition("attack", 3, 0, 6);
        setAnimationPosition("hurt", 4, 0, 3);
        setAnimationPosition("death", 5, 0, 5);
        
        setAnimationDelay("idle", 90);
        setAnimationDelay("walk", 50);
        setAnimationDelay("run", 40);
        setAnimationDelay("attack1", 60);
        setAnimationDelay("attack2", 80);
        setAnimationDelay("hurt", 100);
        setAnimationDelay("death", 130);
    }

}