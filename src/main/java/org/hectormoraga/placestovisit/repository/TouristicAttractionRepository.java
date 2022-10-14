package org.hectormoraga.placestovisit.repository;

import org.hectormoraga.placestovisit.entity.TouristicAttraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristicAttractionRepository extends JpaRepository<TouristicAttraction, Integer> {
}
