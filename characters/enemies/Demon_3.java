package characters.enemies;

import characters.Character;
import characters.Attack;

public class Demon_3 extends Character {

    public Demon_3() {
        super(
            "Demon 3", "Enemy", 15,
            110, 13, 9, 12, 0,
            "assets/Demon_3/Demon_3_Spritelist.png", 128, 128
        );
        
        Attack attack1 = new Attack("Infernal Strike", 18);
        Attack attack2 = new Attack("Soul Ripper", 24);
        addAttack(attack1);
        addAttack(attack2);
        
        configureAnimations();
    }
    
    private void configureAnimations() {
        setAnimationPosition("idle", 0, 0, 6);
        setAnimationPosition("walk", 1, 0, 12);
        setAnimationPosition("attack", 2, 0, 5);
        setAnimationPosition("hurt", 3, 0, 3);
        setAnimationPosition("death", 4, 0, 5);
        
        setAnimationDelay("idle", 100);
        setAnimationDelay("walk", 60);
        setAnimationDelay("run", 50);
        setAnimationDelay("attack1", 70);
        setAnimationDelay("attack2", 90);
        setAnimationDelay("hurt", 110);
        setAnimationDelay("death", 140);
    }

}