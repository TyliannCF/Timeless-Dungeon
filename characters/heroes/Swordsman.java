package characters.heroes;

import characters.Attack;
import characters.Character;

public class Swordsman extends Character {
    
    public Swordsman() {
        super(
            "Aragon", "Swordsman", 6,
            110, 10, 12, 9, 25,
            "assets/Swordsman/Swordsman_spritelist.png", 128, 128
        );
        Attack attack1 = new Attack("Blade Slash", 14);
        Attack attack2 = new Attack("Heavy Strike", 24);

        addAttack(attack1);
        addAttack(attack2);
        configureAnimations();
    }
    
    private void configureAnimations() {
        // Positions
        setAnimationPosition("idle", 0, 0, 8);
        setAnimationPosition("walk", 1, 0, 8);
        setAnimationPosition("run", 2, 0, 8);
        setAnimationPosition("jump", 3, 0, 8);
        setAnimationPosition("attack1", 4, 0, 6);
        setAnimationPosition("attack2", 5, 0, 3);
        setAnimationPosition("attack3", 6, 0, 4);
        setAnimationPosition("attack4", 7, 0, 3);
        setAnimationPosition("hurt", 8, 0, 3);
        setAnimationPosition("death", 9, 0, 3);
        
        setAnimationDelay("idle", 110);
        setAnimationDelay("walk", 105);
        setAnimationDelay("run", 95);
        setAnimationDelay("jump", 100);
        setAnimationDelay("attack1", 105);
        setAnimationDelay("attack2", 140);
        setAnimationDelay("attack3", 115);
        setAnimationDelay("attack4", 120);   
        setAnimationDelay("hurt", 130);
        setAnimationDelay("death", 170);



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
