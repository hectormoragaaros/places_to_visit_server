package org.hectormoraga.placestovisit.repository;

import org.hectormoraga.placestovisit.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
