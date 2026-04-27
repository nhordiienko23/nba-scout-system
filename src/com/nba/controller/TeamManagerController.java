package com.nba.controller;

import com.nba.dto.StaffDto;
import com.nba.model.*;
import com.nba.service.TeamManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Team Management System", description = "Endpoints for managing players and coaches")
public class TeamManagerController {

    private final TeamManager teamManager;

    public TeamManagerController(TeamManager teamManager) {
        this.teamManager = teamManager;
    }

    private PageRequest createPageRequest(int page, int size) {
        return PageRequest.of(page, size);
    }

    @PostMapping("/staff/add")
    @Operation(summary = "Add new staff")
    public String addStaff(@RequestBody StaffDto dto) {
        teamManager.addStaff(teamManager.convertToStaff(dto));
        return "Staff " + dto.name + " added successfully!";
    }

    @PutMapping("/staff/{id}")
    @Operation(summary = "Update staff fully")
    public String updateStaff(@PathVariable int id, @RequestBody StaffDto dto) {
        teamManager.updateStaff(id, dto);
        return "Staff with ID " + id + " updated successfully!";
    }

    @PatchMapping("/staff/{id}")
    @Operation(summary = "Patch staff partially")
    public String patchStaff(@PathVariable int id, @RequestBody StaffDto dto) {
        teamManager.patchStaff(id, dto);
        return "Staff with ID " + id + " updated successfully!";
    }

    @GetMapping("/staff/{id}")
    @Operation(summary = "Get staff by ID")
    public Staff getStaffById(@PathVariable int id) {
        return teamManager.getStaffById(id);
    }

    @DeleteMapping("/staff/{id}")
    @Operation(summary = "Delete staff")
    public String deleteStaffById(@PathVariable int id) {
        teamManager.removeStaff(id);
        return "Staff with ID " + id + " was successfully removed.";
    }

    @GetMapping("/staff")
    @Operation(summary = "Get all staff with pagination")
    public Page<Staff> getAllStaff(
            @Parameter(description = "Page number (0-indexed)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Items per page") @RequestParam(defaultValue = "20") int size) {
        return teamManager.getAllStaff(createPageRequest(page, size));
    }

    @GetMapping("/players")
    @Operation(summary = "Get all players with pagination")
    public Page<Player> getAllPlayers(
            @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Items per page") @RequestParam(defaultValue = "20") int size) {
        return teamManager.getPlayers(createPageRequest(page, size));
    }

    @GetMapping("/coaches")
    @Operation(summary = "Get all coaches with pagination")
    public Page<Coach> getAllCoaches(
            @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Items per page") @RequestParam(defaultValue = "20") int size) {
        return teamManager.getCoaches(createPageRequest(page, size));
    }

    @GetMapping("/highest-paid")
    @Operation(summary = "Get highest paid staff with pagination")
    public Page<Staff> getHighestPaid(
            @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Items per page") @RequestParam(defaultValue = "20") int size) {
        return teamManager.getHighestPaidStaff(createPageRequest(page, size));
    }

    @GetMapping("/highest-rating")
    @Operation(summary = "Get highest rated players with pagination")
    public Page<Player> getHighestRatingPlayers(
            @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Items per page") @RequestParam(defaultValue = "20") int size) {
        return teamManager.getHighestRatingPlayers(createPageRequest(page, size));
    }

    @GetMapping("/players/bonus")
    @Operation(summary = "Get players by bonus with pagination")
    public Page<Player> getPlayersByBonus(@RequestParam double minBonus,
                                          @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
                                          @Parameter(description = "Items per page") @RequestParam(defaultValue = "20") int size) {
        return teamManager.getPlayersByBonus(minBonus, createPageRequest(page, size));
    }

    @GetMapping("/staff/base-salary")
    @Operation(summary = "Get staff by base salary with pagination")
    public Page<Staff> getStaffByBaseSalary(@RequestParam double minBaseSalary,
                                            @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
                                            @Parameter(description = "Items per page") @RequestParam(defaultValue = "20") int size) {
        return teamManager.getStaffByBaseSalary(minBaseSalary, createPageRequest(page, size));
    }

    @GetMapping("/staff/name")
    @Operation(summary = "Search staff by name with pagination")
    public Page<Staff> getByName(@RequestParam String name,
                                 @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
                                 @Parameter(description = "Items per page") @RequestParam(defaultValue = "20") int size) {
        return teamManager.getByName(name, createPageRequest(page, size));
    }

    @GetMapping("/coaches/experience-year")
    @Operation(summary = "Get coaches by experience with pagination")
    public Page<Coach> getCoachesByExperienceYear(@RequestParam int minExperienceYear,
                                                  @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
                                                  @Parameter(description = "Items per page") @RequestParam(defaultValue = "20") int size) {
        return teamManager.getCoachByExperienceYears(minExperienceYear, createPageRequest(page, size));
    }

    @GetMapping("/coaches/championship-won")
    @Operation(summary = "Get coaches by championships with pagination")
    public Page<Coach> getCoachesByChampionshipWon(@RequestParam int minChampionshipWon,
                                                   @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
                                                   @Parameter(description = "Items per page") @RequestParam(defaultValue = "20") int size) {
        return teamManager.getCoachesByChampionshipWon(minChampionshipWon, createPageRequest(page, size));
    }

    @GetMapping("/players/positions")
    @Operation(summary = "Get players by positions with pagination")
    public Page<Player> getPlayersByPosition(@RequestParam Position[] position,
                                             @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
                                             @Parameter(description = "Items per page") @RequestParam(defaultValue = "20") int size) {
        return teamManager.getPlayersByPositions(position, createPageRequest(page, size));
    }
}