package characters.minibosses;

import characters.Character;
import characters.Attack;

public class Dragon1 extends Character {
    
    public Dragon1() {
        super(
            "Dragon 1", "Mini-Boss", 20,
            100, 18, 12, 15, 0,
            "assets/Dragon_1/Dragon_1_Spritelist.png", 128, 128
        );
        
        Attack attack1 = new Attack("Dragon Claw", 22);
        Attack attack2 = new Attack("Fire Breath", 35);
        addAttack(attack1);
        addAttack(attack2);
        
        configureAnimations();
    }
    
    private void configureAnimations() {
        setAnimationPosition("idle", 0, 0, 7);
        setAnimationPosition("walk", 1, 0, 12);
        setAnimationPosition("take_off", 2, 0, 7);
        setAnimationPosition("flight", 3, 0, 12);
        setAnimationPosition("special", 4, 0, 13);
        setAnimationPosition("landing", 5, 0, 5);
        setAnimationPosition("attack1", 6, 0, 4);
        setAnimationPosition("attack2", 7, 0, 10);
        setAnimationPosition("hurt", 8, 0, 4);
        setAnimationPosition("death", 9, 0, 3);
        
        
        setAnimationDelay("idle", 150);
        setAnimationDelay("walk", 110);
        setAnimationDelay("run", 85);
        setAnimationDelay("attack1", 100);
        setAnimationDelay("attack2", 105);
        setAnimationDelay("attack3", 110);
        setAnimationDelay("hurt", 140);
        setAnimationDelay("death", 220);
        setAnimationDelay("flight", 120);
        setAnimationDelay("landing", 130);
        setAnimationDelay("rise", 125);
        setAnimationDelay("special", 95);
    }
}
