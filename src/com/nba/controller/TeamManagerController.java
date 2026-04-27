package com.nba.controller;

import com.nba.dto.StaffDto;
import com.nba.model.*;
import com.nba.service.TeamManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller to manage sports personnel (Players and Coaches).
 * All operations are delegated to the TeamManager service.
 */
@RestController
@RequestMapping("/api")
@Tag(name = "Team Management System", description = "Endpoints for managing players and coaches")
public class TeamManagerController {

    private final TeamManager teamManager;

    public TeamManagerController(TeamManager teamManager) {
        this.teamManager = teamManager;
    }

    @PostMapping("/staff/add")
    @Operation(summary = "Add new staff", description = "Creates a new player or coach entry in the system")
    public String addStaff(@RequestBody StaffDto dto) {
        Staff staff = teamManager.convertToStaff(dto);
        teamManager.addStaff(staff);
        return "Staff " + staff.getName() + " added successfully!";
    }

    @PutMapping("/staff/{id}")
    @Operation(summary = "Update staff fully", description = "Completely updates an existing staff member's attributes")
    public String updateStaff(@PathVariable int id, @RequestBody StaffDto dto) {
        teamManager.updateStaff(id, dto);
        return "Staff with ID " + id + " updated successfully!";
    }

    @PatchMapping("/staff/{id}")
    @Operation(summary = "Patch staff partially", description = "Updates specific attributes of a staff member")
    public String patchStaff(@PathVariable int id, @RequestBody StaffDto dto) {
        teamManager.patchStaff(id, dto);
        return "Staff with ID " + id + " updated successfully!";
    }

    @GetMapping("/staff/{id}")
    @Operation(summary = "Get staff by ID", description = "Retrieves a single staff member by their unique ID")
    public Staff getStaffById(@PathVariable int id) {
        return teamManager.getStaffById(id);
    }

    @DeleteMapping("/staff/{id}")
    @Operation(summary = "Delete staff", description = "Removes a staff member from the system by ID")
    public String deleteStaffById(@PathVariable int id) {
        teamManager.removeStaff(id);
        return "Staff with ID " + id + " was successfully removed.";
    }

    @GetMapping("/staff")
    @Operation(summary = "Get all staff", description = "Retrieves a list of all players and coaches")
    public List<Staff> getAllStaff() {
        return teamManager.getAllStaff();
    }

    @GetMapping("/players")
    @Operation(summary = "Get all players", description = "Retrieves a list of all players")
    public List<Player> getAllPlayers() {
        return teamManager.getPlayers();
    }

    @GetMapping("/coaches")
    @Operation(summary = "Get all coaches", description = "Retrieves a list of all coaches")
    public List<Coach> getAllCoaches() {
        return teamManager.getCoaches();
    }

    @GetMapping("/highest-paid")
    @Operation(summary = "Get highest paid staff", description = "Returns the staff members with the highest total salary")
    public List<Staff> getHighestPaid() {
        return teamManager.getHighestPaidStaff();
    }

    @GetMapping("/highest-rating")
    @Operation(summary = "Get highest rated players", description = "Returns the players with the highest rating")
    public List<Player> getHighestRatingPlayers() {
        return teamManager.getHighestRatingPlayers();
    }

    @GetMapping("/players/bonus")
    @Operation(summary = "Get players by bonus", description = "Returns players who earn a bonus above the specified minimum")
    public List<Player> getPlayersByBonus(@RequestParam double minBonus) {
        return teamManager.getPlayersByBonus(minBonus);
    }

    @GetMapping("/staff/base-salary")
    @Operation(summary = "Get staff by base salary", description = "Returns staff members with a base salary at or above the minimum")
    public List<Staff> getStaffByBaseSalary(@RequestParam double minBaseSalary) {
        return teamManager.getStaffByBaseSalary(minBaseSalary);
    }

    @GetMapping("/staff/name")
    @Operation(summary = "Search staff by name", description = "Searches for staff members whose name contains the query string")
    public List<Staff> getByName(@RequestParam String name) {
        return teamManager.getByName(name);
    }

    @GetMapping("/coaches/experience-year")
    @Operation(summary = "Get coaches by experience", description = "Returns coaches with at least the specified years of experience")
    public List<Coach> getCoachesByExperienceYear(@RequestParam int minExperienceYear) {
        return teamManager.getCoachByExperienceYears(minExperienceYear);
    }

    @GetMapping("/coaches/championship-won")
    @Operation(summary = "Get coaches by championships", description = "Returns coaches with at least the specified number of championships")
    public List<Coach> getCoachesByChampionshipWon(@RequestParam int minChampionshipWon) {
        return teamManager.getCoachesByChampionshipWon(minChampionshipWon);
    }

    @GetMapping("/players/positions")
    @Operation(summary = "Get players by positions", description = "Returns players who play in at least one of the specified positions")
    public List<Player> getPlayersByPosition(@RequestParam Position... position) {
        return teamManager.getPlayersByPositions(position);
    }
}