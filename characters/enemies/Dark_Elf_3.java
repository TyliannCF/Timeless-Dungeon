package characters.enemies;

import characters.Character;
import characters.Attack;

public class Dark_Elf_3 extends Character {

    public Dark_Elf_3() {
        super(
            "Dark Elf 3", "Enemy", 15,
            100, 16, 9, 15, 0,
            "assets/Dark_Elf_3/Elf_3_Spritelist.png", 128, 128
        );
        
        Attack attack1 = new Attack("Void Strike", 20);
        Attack attack2 = new Attack("Shadow Burst", 26);
        addAttack(attack1);
        addAttack(attack2);
        
        configureAnimations();
    }
    
    private void configureAnimations() {
        setAnimationPosition("idle", 0, 0, 6);
        setAnimationPosition("walk", 1, 0, 12);
        setAnimationPosition("attack", 2, 0, 4);
        setAnimationPosition("hurt", 3, 0, 3);
        setAnimationPosition("death", 4, 0, 5);
       
        
        setAnimationDelay("idle", 110);
        setAnimationDelay("walk", 70);
        setAnimationDelay("run", 60);
        setAnimationDelay("attack", 80);
        setAnimationDelay("hurt", 120);
        setAnimationDelay("death", 150);
    }
    
}
