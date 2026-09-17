package characters.enemies;

import characters.Character;
import characters.Attack;

public class Dark_Elf_1 extends Character {

    public Dark_Elf_1() {
        super(
            "Dark Elf 1", "Enemy", 12,
            80, 12, 6, 12, 0,
            "assets/Dark_Elf_1/Elf_1_Spritelist.png", 128, 128
        );
        
        Attack attack1 = new Attack("Dark Arrow", 15);
        Attack attack2 = new Attack("Shadow Strike", 20);
        addAttack(attack1);
        addAttack(attack2);
        
        configureAnimations();
    }
    
    private void configureAnimations() {
        setAnimationPosition("idle", 0, 0, 21);
        setAnimationPosition("walk", 1, 0, 12);
        setAnimationPosition("attack", 2, 0, 11);
        setAnimationPosition("hurt", 3, 0, 3);
        setAnimationPosition("death", 4, 0, 4);
       
        
        setAnimationDelay("idle", 120);
        setAnimationDelay("walk", 80);
        setAnimationDelay("run", 70);
        setAnimationDelay("attack", 90);
        setAnimationDelay("hurt", 130);
        setAnimationDelay("death", 160);
    }
    
}
