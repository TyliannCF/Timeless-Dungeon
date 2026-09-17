package characters;

import java.util.ArrayList;
import java.util.List;


public class Team {
    private List<Character> characters;
    private String typePlayer;
    private int id;

    private static final int MAX_TEAM_SIZE = 4;
    
    
    public Team(String typePlayer, int id) {
        this.characters = new ArrayList<>();
        this.typePlayer = typePlayer;
        this.id = id;
    }

    public List<Character> getCharacters() {
        return new ArrayList<>(characters);
    }


    public String getTypePlayer() {
        return typePlayer;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public boolean addCharacter(Character character) {
        if (characters.size() < MAX_TEAM_SIZE) {
            characters.add(character);
            System.out.println(character.getName() + " a rejoint l'équipe !");
            return true;
        } else {
            System.out.println("L'équipe est déjà complète (max " + MAX_TEAM_SIZE + " personnages).");
            return false;
        }
    }

    public boolean removeCharacter(int index) {
        if (index >= 0 && index < characters.size()) {
            Character removed = characters.remove(index);
            System.out.println(removed.getName() + " a quitté l'équipe.");
            return true;
        }
        return false;
    }


    public Character getCharacter(int index) {
        if (index >= 0 && index < characters.size()) {
            return characters.get(index);
        }
        return null;
    }

    public int getTeamSize() {
        return characters.size();
    }
    
    public boolean isFull() {
        return characters.size() >= MAX_TEAM_SIZE;
    }
    
    
    public boolean hasAliveCharacters() {
        for (Character character : characters) {
            if (character.isAlive()) {
                return true;
            }
        }
        return false;
    }
    
    
    public List<Character> getAliveCharacters() {
        List<Character> alive = new ArrayList<>();
        for (Character character : characters) {
            if (character.isAlive()) {
                alive.add(character);
            }
        }
        return alive;
    }
    
    
    public void healTeam(int healAmount) {
        for (Character character : characters) {
            if (character.isAlive()) {
                character.heal(healAmount);
            }
        }
        System.out.println("L'équipe a été soignée de " + healAmount + " PV !");
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Équipe ").append(typePlayer).append(" (ID: ").append(id).append(") ===\n");
        sb.append("Membres (").append(characters.size()).append("/").append(MAX_TEAM_SIZE).append("):\n");
        for (int i = 0; i < characters.size(); i++) {
            sb.append(i + 1).append(". ").append(characters.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
}
