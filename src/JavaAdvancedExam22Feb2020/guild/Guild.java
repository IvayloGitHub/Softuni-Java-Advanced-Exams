package JavaAdvancedExam22Feb2020.guild;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Guild {
    public String name;
    public int capacity;
    public List<Player> roster;

    public Guild(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.roster = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Player> getRoster() {
        return roster;
    }

    public void addPlayer(Player player) {
        if (this.capacity > roster.size()) {
            roster.add(player);
        }
    }

    public boolean removePlayer(String name) {
        return roster.removeIf(p -> p.getName().equals(name));
    }

    public void promotePlayer(String name) {
        roster.stream().filter(p -> p.getName().equals(name)).findFirst().ifPresent(player -> player.setRank("Member"));
    }

    public void demotePlayer(String name) {
        roster.stream().filter(p -> p.getName().equals(name)).findFirst().ifPresent(player -> player.setRank("Trial"));

    }

    public Player[] kickPlayersByClass(String clazz) {
        List<Player> filtered = roster.stream().filter(player -> player.getClazz().equals(clazz))
                .collect(Collectors.toList());
        for (Player player : filtered) {
            roster.remove(player);
        }
        Player[] array = new Player[filtered.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = filtered.get(i);
        }
        return array;
    }

    public int count() {
        return roster.size();
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Players in the guild: %s:", getName()));
        sb.append(System.lineSeparator());
        for (Player player : roster) {
            sb.append(player.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
