package characters.bosses;

import characters.Character;
import characters.Attack;

public class Hellhound2 extends Character {
    
    public Hellhound2() {
        super(
            "Hellhound Phase 2", "Boss", 28,
            175, 28, 17, 23, 0,
            "assets/Hellhound_2/Hellhound_2_Spritelist.png", 128, 128
        );
        
        Attack attack1 = new Attack("Magma Bite", 35);
        Attack attack2 = new Attack("Blazing Claw", 45);
        addAttack(attack1);
        addAttack(attack2);
        
        configureAnimations();
    }
    
    private void configureAnimations() {
        // Positions dans la spritesheet (row, col, frameCount)
        setAnimationPosition("idle", 0, 0, 6);      
        setAnimationPosition("idle_2", 1, 0, 6);    // Idle 2 est sur la ligne 1
        setAnimationPosition("walk", 2, 0, 9);      // Walk décalé à ligne 2
        setAnimationPosition("run", 3, 0, 6);       // Run décalé à ligne 3
        setAnimationPosition("jump", 4, 0, 10);      // Jump décalé à ligne 4
        setAnimationPosition("attack1", 5, 0, 6);   // Attack1 décalé à ligne 5
        setAnimationPosition("attack2", 6, 0, 3);   // Attack2 décalé à ligne 6
        setAnimationPosition("attack3", 7, 0, 4);   // Attack3 décalé à ligne 7
        setAnimationPosition("hurt", 8, 0, 3);      // Hurt décalé à ligne 9
        setAnimationPosition("death", 9, 0, 6);    // Death décalé à ligne 10
        
        // Délais d'animation
        setAnimationDelay("idle", 135);
        setAnimationDelay("idle_2", 150);
        setAnimationDelay("walk", 95);
        setAnimationDelay("run", 75);
        setAnimationDelay("attack1", 90);
        setAnimationDelay("attack2", 95);
        setAnimationDelay("attack3", 100);
        setAnimationDelay("attack4", 100);
        setAnimationDelay("jump", 100);
        setAnimationDelay("hurt", 125);
        setAnimationDelay("death", 200);
    }
}
