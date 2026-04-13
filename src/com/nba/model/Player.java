package com.nba.model;

import com.nba.exception.InvalidStaffDataException;

import javax.management.InvalidAttributeValueException;

public class Player extends Staff {
    private int rating;
    private String position;

    public Player(String name, double baseSalary, String position, int rating) {
        super(name, baseSalary);
        validatePosition(position);
        this.position = position;
        validateRating(rating);
        this.rating = rating;
    }

    private void validatePosition(String position) {
        if (position == null || position.isEmpty()) {
            throw new InvalidStaffDataException("Position can't be null or empty");
        }
    }

    private void validateRating(int rating) {
        if (rating < 0 || rating > 100) {
            throw new InvalidStaffDataException("Rating must be between 0 and 100. Provided: " + rating);
        }
    }

    public int getRating() {
        return rating;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        validatePosition(position);
        this.position = position;
    }

    public void setRating(int rating) {
        validateRating(rating);
        this.rating = rating;
    }

    //If the rating is > 90, the bonus for player is 20% of the salary.
    @Override
    public double calculateBonus() {
        if (rating > 90) {
            return getBaseSalary() * 0.2;
        }
        return 0;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Pos: %s | Rating: %d | Bonus: $%,.0f", position, rating, calculateBonus());
    }

}
