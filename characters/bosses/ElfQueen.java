package characters.bosses;

import characters.Character;
import characters.Attack;

public class ElfQueen extends Character {

    public ElfQueen() {
        super(
            "Elf Queen", "Boss", 18,
            180, 14, 9, 14, 40,
            "assets/ElfQueen/Queen_Spritelist.png", 192, 192
        );
        
        Attack attack1 = new Attack("Mystic Strike", 18);
        Attack attack2 = new Attack("Arcane Blast", 25);
        addAttack(attack1);
        addAttack(attack2);
        
        configureAnimations();
    }
    
    private void configureAnimations() {
        setAnimationPosition("idle", 0, 0, 7);
        setAnimationPosition("dialogue", 1, 0, 8);
        setAnimationPosition("walk", 2, 0, 12);
        setAnimationPosition("attack", 3, 0, 4);
        setAnimationPosition("special", 4, 0, 4);
        setAnimationPosition("defend", 5, 0, 3);
        setAnimationPosition("charge1", 6, 0, 6);
        setAnimationPosition("charge2", 7, 0, 12);
       
        
        setAnimationDelay("idle", 110);
        setAnimationDelay("walk", 75);
        setAnimationDelay("run", 65);
        setAnimationDelay("attack", 85);
        setAnimationDelay("hurt", 125);
        setAnimationDelay("death", 155);
    }
    
}
