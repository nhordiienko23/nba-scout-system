package com.nba.dto;

import com.nba.model.Position;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.List;

@Schema(description = "Data Transfer Object for creating or updating staff members")
public class StaffDto {

    @Schema(description = "Type of staff", example = "player", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Type is mandatory")
    public String type;

    @Schema(description = "Full name of the staff member", example = "LeBron James")
    public String name;

    @Schema(description = "Base salary in dollars", example = "50000.0")
    @Positive(message = "Base salary must be greater than zero")
    public double baseSalary;

    @Schema(description = "Player rating (0-100)", example = "95")
    @Min(0) @Max(100)
    public int rating;

    @Schema(description = "List of positions", example = "[\"PG\", \"SF\"]")
    public List<Position> positions;

    @Schema(description = "Years of coaching experience", example = "10")
    @Min(0)
    public int experienceYears;

    @Schema(description = "Number of championships won", example = "3")
    @Min(0)
    public int championshipsWon;

    @Override
    public String toString() {
        java.util.List<String> fields = new java.util.ArrayList<>();
        if (type != null && !type.isEmpty()) fields.add("type='" + type + "'");
        if (name != null && !name.isEmpty()) fields.add("name='" + name + "'");
        if (baseSalary != 0) fields.add("baseSalary=" + baseSalary);
        if (rating != 0) fields.add("rating=" + rating);
        if (positions != null && !positions.isEmpty()) fields.add("positions=" + positions);
        if (experienceYears != 0) fields.add("experienceYears=" + experienceYears);
        if (championshipsWon != 0) fields.add("championshipsWon=" + championshipsWon);
        return "StaffDto{" + String.join(", ", fields) + "}";
    }
}