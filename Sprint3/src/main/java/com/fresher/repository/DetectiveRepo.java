package com.fresher.repository;

import com.fresher.core.Rank;
import com.fresher.model.Detective;

import java.util.Optional;
import java.util.Set;

public interface DetectiveRepo extends AbstractRepo<Detective> {

    Set<Detective> findAll();

    Optional<Detective> findByBadgeNumber(String badgeNumber);

    Set<Detective> findbyRank(Rank rank);

    default Optional<Detective> findByIdWithPersonDetails(Long id) {
        return Optional.empty();
    }
}