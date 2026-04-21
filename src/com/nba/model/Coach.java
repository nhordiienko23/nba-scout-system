package com.nba.model;

import com.nba.exception.InvalidStaffDataException;

public class Coach extends Staff {
    private int experienceYears;
    private int championshipsWon;

    public Coach(String name, double baseSalary, int experienceYears, int championshipsWon) {
        super(name, baseSalary);
        validateExperienceYear(experienceYears);
        this.experienceYears = experienceYears;
        validateChampionshipsWon(championshipsWon);
        this.championshipsWon = championshipsWon;
    }

    private void validateChampionshipsWon(int championshipsWon) {
        if (championshipsWon < 0) {
            throw new InvalidStaffDataException("Championships Won must be positive number or null");
        }
        if (championshipsWon < this.championshipsWon) {
            throw new InvalidStaffDataException("Championships Won cannot decrease!");
        }
    }

    private void validateExperienceYear(int experienceYears) {
        if (experienceYears <= 0) {
            throw new InvalidStaffDataException("Experience year must be positive number");
        }
        if (experienceYears < this.experienceYears) {
            throw new InvalidStaffDataException("Experience year cannot decrease!");
        }
    }

    // The coach receives 5% for each year of experience + a fixed reward for each ring (championship)
    @Override
    public double calculateBonus() {
        double expBonus = getBaseSalary() * (experienceYears * 0.05);
        double ringBonus = championshipsWon * 50000;
        return expBonus + ringBonus;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Role: Coach | Exp: %d yrs | Rings: %d | Bonus: $%,.0f",
                experienceYears, championshipsWon, calculateBonus());
    }

    public int getChampionshipsWon() {
        return championshipsWon;
    }

    public void setChampionshipsWon(int championshipsWon) {
        validateChampionshipsWon(championshipsWon);
        this.championshipsWon = championshipsWon;
    }

    public void setExperienceYears(int experienceYears) {
        validateExperienceYear(experienceYears);
        this.experienceYears = experienceYears;
    }

    public int getExperienceYears() {
        return experienceYears;
    }
}
