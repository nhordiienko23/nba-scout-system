package com.nba.repository;

import com.nba.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

    // Fetches all staff members with positions to solve N+1 problem for List-based queries
    @Query("SELECT s FROM Staff s LEFT JOIN FETCH TREAT(s AS Player).positions")
    List<Staff> findAllWithPositions(Sort sort);

    // Handles paginated requests, joined with positions to avoid N+1 issues
    @Query(value = "SELECT s FROM Staff s LEFT JOIN FETCH TREAT(s AS Player).positions",
            countQuery = "SELECT count(s) FROM Staff s")
    Page<Staff> findAll(Pageable pageable);
}