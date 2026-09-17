package characters.bosses;

import characters.Character;
import characters.Attack;

public class Hellhound3 extends Character {
    
    public Hellhound3() {
        super(
            "Hellhound Phase 3", "Boss", 30,
            200, 30, 20, 25, 0,
            "assets/Hellhound_3/Hellhound_3_Spritelist.png", 128, 128
        );
        
        Attack attack1 = new Attack("Apocalypse Bite", 40);
        Attack attack2 = new Attack("Infernal Fury", 55);
        addAttack(attack1);
        addAttack(attack2);
        
        configureAnimations();
    }
    
    private void configureAnimations() {
        // Positions dans la spritesheet (row, col, frameCount)
        setAnimationPosition("idle", 0, 0, 6);      
        setAnimationPosition("idle_2", 1, 0, 7);    
        setAnimationPosition("walk", 2, 0, 9);      
        setAnimationPosition("run", 3, 0, 6);       
        setAnimationPosition("jump", 4, 0, 10);      
        setAnimationPosition("attack1", 5, 0, 5);   
        setAnimationPosition("attack2", 6, 0, 4);   
        setAnimationPosition("attack3", 7, 0, 3);   
        setAnimationPosition("hurt", 8, 0, 3);      
        setAnimationPosition("death", 9, 0, 5);    
        
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
