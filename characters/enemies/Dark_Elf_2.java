package characters.enemies;

import characters.Character;
import characters.Attack;

public class Dark_Elf_2 extends Character {

    public Dark_Elf_2() {
        super(
            "Dark Elf 2", "Enemy", 14,
            90, 14, 8, 14, 0,
            "assets/Dark_Elf_2/Elf_2_Spritelist.png", 128, 128
        );
        
        Attack attack1 = new Attack("Dark Blade", 18);
        Attack attack2 = new Attack("Cursed Slash", 24);
        addAttack(attack1);
        addAttack(attack2);
        
        configureAnimations();
    }
    
    private void configureAnimations() {
        setAnimationPosition("idle", 0, 0, 5);
        setAnimationPosition("walk", 1, 0, 12);
        setAnimationPosition("attack", 2, 0, 4);
        setAnimationPosition("hurt", 3, 0, 4);
        setAnimationPosition("death", 4, 0, 5);
       
        
        setAnimationDelay("idle", 120);
        setAnimationDelay("walk", 80);
        setAnimationDelay("run", 70);
        setAnimationDelay("attack", 90);
        setAnimationDelay("hurt", 130);
        setAnimationDelay("death", 160);
    }
    
}
