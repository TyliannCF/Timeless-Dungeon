package dungeon;

import characters.Character;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;


public class Dungeon {
    private String name;
    private Room currentRoom;
    private Room nextRoom;
    private Room previousRoom;
    private int id;
    private boolean status;
    private List<Room> room;
    private List<Character> enemy;
    private List<Character> allies;
    protected int currentRoomIndex;


    //there are the Getters
    public String getName(){
        return name;
    }

    public Room getCurrentRoom(){
        return currentRoom;
    }

    public Room getNextRoom(){
        return nextRoom;
    }

    public Room getPreviousRoom(){
        return previousRoom;
    }

    public int getId(){
        return id;
    }

    public boolean getStatus(){
        return status;
    }

    public List<Room> getRoom(){
        return new ArrayList<>(room);
    }
    
    public void updatePlayerTeam(List<Character> playerTeam) {
        for (Room r : room) {
            r.getPlayer().clear();
            r.getPlayer().addAll(playerTeam);
            // Réinitialiser le statut des personnages
            for (Character character : r.getPlayer()) {
                character.setStatus("idle");
            }
        }
    }

    public List<Character> getEnemy(){
        return enemy;
    }

    public List<Character> getAllies(){
        if (allies == null) {
            allies = new ArrayList<Character>();
        }
        return allies;
    }

    public Dungeon(String name, int id, List<Room> room, Room currentRoom, Room nextRoom, Room previousRoom, boolean status){
        this.name = name;
        this.id = id;
        this.room = room;
        this.currentRoom = currentRoom;
        this.nextRoom = nextRoom;
        this.previousRoom = previousRoom;
        this.status = status;
        this.currentRoomIndex = 0;
    }


    public void update(){
        currentRoom.update();
    }

    public void addRoom(Room newRoom){
        room.add(newRoom);
    }

    protected void updateRoomNavigation(int index) {
        if (index < 0 || index >= room.size()) {
            return;
        }

        this.currentRoomIndex = index;
        this.currentRoom = room.get(index);


        if (index < room.size() - 1) {
            this.nextRoom = room.get(index + 1);
        } else {
            this.nextRoom = null;
        }

        if (index > 0) {
            this.previousRoom = room.get(index - 1);
        } else {
            this.previousRoom = null;
        }
    }


    public boolean moveToNextRoom() {
        if (currentRoomIndex < room.size() - 1) {
            currentRoomIndex++;
            updateRoomNavigation(currentRoomIndex);
            System.out.println("Going towards: " + room.get(currentRoomIndex).getName());
            return true;
        }
        return false;
    }

    public boolean moveToPreviousRoom() {
        if (currentRoomIndex > 0) {
            currentRoomIndex--;
            updateRoomNavigation(currentRoomIndex);
            System.out.println("Returns to: " + room.get(currentRoomIndex).getName());
            return true;
        }
        return false;
    }

    public void draw(Graphics2D g2, main.GamePanel gp){
            //wizard.draw(g2, 0, 100, 2, false);
            //archer.draw(g2, 125, 100, 2, false);
            //enchantress.draw(g2,250,100, 2, false);
            //knight.draw(g2,500, 100, 2, true);
        currentRoom.draw(g2, gp);
    }

    public int getCurrentRoomIndex() {
        return currentRoomIndex;
    }

}