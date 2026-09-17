package characters;


public class Attack {
    
    private int cible;      
    private int damage;     
    private String name;    
    private int manaCost;   
    
    
    public Attack(String name, int damage) {
        this.name = name;
        this.damage = damage;
        this.cible = -1;
        this.manaCost = 0;
    }
    
    
    public Attack(String name, int damage, int manaCost, int cible) {
        this.name = name;
        this.damage = damage;
        this.manaCost = manaCost;
        this.cible = cible;
    }
    
    
    public int getCible() {
        return cible;
    }
    
   
    public int getDamage() {
        return damage;
    }
    
   
    public String getName() {
        return name;
    }
    
    
    public int getManaCost() {
        return manaCost;
    }
    
    
    
    
    public void setCible(int cible) {
        this.cible = cible;
    }
    
   
    public void setDamage(int damage) {
        this.damage = damage;
    }
    
    
    public void setName(String name) {
        this.name = name;
    }
    
    
    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }
    
    
    @Override
    public String toString() {
        String info = name + " - Dégâts: " + damage;
        if (manaCost > 0) {
            info += " (Coût: " + manaCost + " mana)";
        }
        return info;
    }
}
