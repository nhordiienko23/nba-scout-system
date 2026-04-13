package com.nba.main;

import com.nba.exception.InvalidStaffDataException;
import com.nba.exception.StaffNotFoundException;
import com.nba.model.*;
import com.nba.service.TeamManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        TeamManager teamManager = getTeamManager();
        System.out.println("=== Chicago Bulls Roster ===");
        for (Staff member : teamManager.getAllStaff()) {
            System.out.println(member);
        }

        System.out.println("\n--- Financial Analysis ---");
        List<Staff> stars = teamManager.getHighestPaidStaff();
        if (!stars.isEmpty()) {
            System.out.println("Top Earners of the Team (" + stars.size() + " members found):");
            for (Staff star : stars) {
                System.out.printf(" - %-15s | Compensation: $%,.0f\n",
                        star.getName(), star.calculateTotalSalary());
            }
        }

        System.out.println("\n--- Top Ranked Players (The GOATs) ---");
        List<Player> topRanked = teamManager.getHighestRatingStaff();

        if (!topRanked.isEmpty()) {
            int bestRating = topRanked.get(0).getRating();
            System.out.println("Top Rating in the Team: [" + bestRating + "] (" + topRanked.size() + " players found)");

            for (Player player : topRanked) {
                // %-10s выведет список позиций типа [SF, PF]
                System.out.printf(" - %-15s | Positions: %-12s | Rating: %d\n",
                        player.getName(), player.getPositions(), player.getRating());
            }
        }
        System.out.println("\n--- Testing Exception ---");
        try {
            System.out.println(teamManager.getStaffById(10));
        } catch (StaffNotFoundException e) {
            System.out.println("Search Failed: " + e.getMessage());
        }
        try {
            teamManager.removeStaff(99);
        } catch (StaffNotFoundException e) {
            System.out.println("Action Failed: " + e.getMessage());
        }
        System.out.println("\n--- Testing Data Validation (Fail-Fast) ---");
        testingDataValidation();

        System.out.println("\n--- Testing data writing to file ---");
        String file = "nba_team.dat";

        teamManager.saveTeamToFile(file);

        System.out.println("\n--- Testing reading data from File ---");
        TeamManager loadedManager = new TeamManager();
        loadedManager.loadTeamFromFile(file);
        loadedManager.getAllStaff().forEach(System.out::println);


    }

    public static void testingDataValidation(){
        try {
            System.out.println("Trying to create player with rating 150...");
            Player brokenPlayer = new Player("Fake Star", 500000, 150, Position.C);
        } catch (InvalidStaffDataException e) {
            System.out.println("Validation Caught: " + e.getMessage());
        }

        try {
            System.out.println("\nTrying to create coach with negative salary...");
            Coach brokenCoach = new Coach("Bad Coach", -1000, 5, 0);
        } catch (InvalidStaffDataException e) {
            System.out.println("Validation Caught: " + e.getMessage());
        }

        try {
            System.out.println("\nTrying to create player without any position...");
            Player noPosPlayer = new Player("Ghost Player", 300000, 75); // Массив позиций будет пуст
        } catch (InvalidStaffDataException e) {
            System.out.println("Validation Caught: " + e.getMessage());
        }
    }

    private static TeamManager getTeamManager() {
        Player jordan = new Player("Michael Jordan", 1000000, 99, Position.SG, Position.SF);
        Player pippen = new Player("Scottie Pippen", 700000, 92, Position.SF, Position.PF);
        Player rodman = new Player("Dennis Rodman", 500000, 88, Position.PF, Position.C);

        // Пример игрока с тремя позициями
        Player leBron = new Player("LeBron James", 900000, 98, Position.SF, Position.PF, Position.PG);

        Player edwards = new Player("Anthony Edwards", 1000000, 98, Position.SG);

        Coach coach = new Coach("Phil Jackson", 20000, 20, 11);
        TeamManager teamManager = new TeamManager();
        teamManager.addStaff(jordan);
        teamManager.addStaff(pippen);
        teamManager.addStaff(rodman);
        teamManager.addStaff(leBron);
        teamManager.addStaff(edwards);
        teamManager.addStaff(coach);
        return teamManager;
    }
}
