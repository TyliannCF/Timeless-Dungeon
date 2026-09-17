package characters.bosses;

import characters.Character;
import characters.Attack;

public class ElfKing extends Character {

    public ElfKing() {
        super(
            "Elf King", "Boss", 20,
            200, 15, 10, 15, 50,
            "assets/ElfKing/King_Spritelist.png", 192, 192
        );
        
        Attack attack1 = new Attack("Royal Slash", 20);
        Attack attack2 = new Attack("Divine Judgment", 30);
        addAttack(attack1);
        addAttack(attack2);
        
        configureAnimations();
    }
    
    private void configureAnimations() {
        setAnimationPosition("idle", 0, 0, 7);
        setAnimationPosition("dialogue", 1, 0, 7);
        setAnimationPosition("walk", 2, 0, 12);
        setAnimationPosition("special", 3, 0, 4);
        setAnimationPosition("attack", 4, 0, 5);
        setAnimationPosition("defend", 5, 0, 3);
       
        
        setAnimationDelay("idle", 100);
        setAnimationDelay("walk", 70);
        setAnimationDelay("run", 60);
        setAnimationDelay("attack", 80);
        setAnimationDelay("hurt", 120);
        setAnimationDelay("death", 150);
    }
    
}
