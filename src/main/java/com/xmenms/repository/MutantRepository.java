package com.xmenms.repository;

import com.xmenms.models.Mutant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MutantRepository extends CrudRepository<Mutant, Long> {

    List<Mutant> findByIsMutant(Boolean isMutant);

    List<Mutant>  findByandSequence(String andSequence);
}
