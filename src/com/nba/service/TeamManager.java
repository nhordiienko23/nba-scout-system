package com.nba.service;

import com.nba.exception.StaffNotFoundException;
import com.nba.model.Player;
import com.nba.model.Staff;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamManager {
    private Map<Integer, Staff> team;

    public TeamManager() {
        this.team = new HashMap<>();
    }

    public void addStaff(Staff staff) {
        team.put(staff.getId(), staff);
    }

    private void validateStaffExists(int id) {
        if (!team.containsKey(id)) {
            throw new StaffNotFoundException(id);
        }
    }

    public Staff getStaffById(int id) {
        validateStaffExists(id);
        return team.get(id);
    }

    public boolean removeStaff(int id) {
        validateStaffExists(id);
        team.remove(id);
        return true;
    }

    public List<Staff> getAllStaff() {
        return new ArrayList<>(team.values());
    }

    public List<Player> getHighestRatingStaff() {
        List<Player> topRanked = new ArrayList<>();
        int highestRank = -1;
        for (Staff staffCurrent : team.values()) {
            if (staffCurrent instanceof Player) {
                Player player = (Player) staffCurrent;
                int rating = player.getRating();
                if (rating > highestRank) {
                    highestRank = rating;
                    topRanked.clear();
                    topRanked.add(player);
                } else if (rating == highestRank) {
                    topRanked.add(player);
                }
            }
        }
        return topRanked;
    }

    public List<Staff> getHighestPaidStaff() {
        List<Staff> topEarned = new ArrayList<>();
        double highestSalary = -1;
        for (Staff staffCurrent : team.values()) {
            double currentTotal = staffCurrent.calculateTotalSalary();
            if (currentTotal > highestSalary) {

                highestSalary = currentTotal;
                topEarned.clear();
                topEarned.add(staffCurrent);
            } else if (currentTotal == highestSalary) {
                topEarned.add(staffCurrent);
            }
        }
        return topEarned;
    }

    public void saveTeamToFile(String filename){
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename))){
            objectOutputStream.writeObject(team);
            System.out.println("Team data saved successfully to " + filename);
        }catch (IOException e){
            System.err.println("Error saving team: " + e.getMessage());
        }
    }
    public  void loadTeamFromFile(String filename){
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename))){
            team = (Map<Integer, Staff>) objectInputStream.readObject();
            System.out.println("Team data loaded successfully from " + filename);
        }catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading team: " + e.getMessage());
        }
    }

}
