package characters.heroes;

import characters.Attack;
import characters.Character;

public class Enchantress extends Character {
    
    public Enchantress() {
        super(
            "Lyria", "Enchantress", 1,
            100, 10, 5, 8, 50,
            "assets/Enchantress/Enchantress_spritelist.png", 128, 128
        );
        Attack attack1 = new Attack("Magic Touch", 18);
        Attack attack2 = new Attack("Mystic Burst", 30);

        addAttack(attack1);
        addAttack(attack2);
        configureAnimations();
    }
    
    private void configureAnimations() {
        // Positions dans la spritesheet (row, col, frameCount)
        setAnimationPosition("idle", 0, 0, 5);
        setAnimationPosition("walk", 1, 0, 8);
        setAnimationPosition("run", 2, 0, 8);
        setAnimationPosition("jump", 3, 0, 8);
        setAnimationPosition("attack1", 4, 0, 6);
        setAnimationPosition("attack2", 5, 0, 3);
        setAnimationPosition("attack3", 6, 0, 3);
        setAnimationPosition("attack4", 7, 0, 10);
        setAnimationPosition("hurt", 8, 0, 2);
        setAnimationPosition("death", 9, 0, 5);
        
        // Délais
        setAnimationDelay("idle", 120);
        setAnimationDelay("walk", 110);
        setAnimationDelay("run", 90);
        setAnimationDelay("jump", 100);
        setAnimationDelay("attack1", 90);
        setAnimationDelay("attack2", 85);
        setAnimationDelay("attack3", 85);
        setAnimationDelay("attack4", 80);
        setAnimationDelay("hurt", 120);
        setAnimationDelay("death", 150);


        setAnimationPositionInSheet("hang", "bonus", 0, 0, 5);
        setAnimationPositionInSheet("pull_up", "bonus", 1, 0, 5);
        setAnimationPositionInSheet("climb", "bonus", 2, 0, 6);
        setAnimationPositionInSheet("roll", "bonus", 3, 0, 7);
        setAnimationPositionInSheet("elixir", "bonus", 4, 0, 8);
        setAnimationPositionInSheet("take", "bonus", 5, 0, 5);
        setAnimationPositionInSheet("special_attack1", "bonus", 6, 0, 7);
        setAnimationPositionInSheet("special_attack2", "bonus", 7, 0, 6);
        setAnimationPositionInSheet("special_attack3", "bonus", 8, 0, 8);
        setAnimationPositionInSheet("rest", "bonus", 9, 0, 3);

        setAnimationDelay("special_attack1", 120);
        setAnimationDelay("special_attack2", 140);
        setAnimationDelay("special_attack3", 130);
        setAnimationDelay("roll", 80);
        setAnimationDelay("take", 120);
        setAnimationDelay("hang", 150);
        setAnimationDelay("pull_up", 130);
        setAnimationDelay("rest", 250);
        setAnimationDelay("elixir", 110);
    }
}
