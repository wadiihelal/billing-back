package com.epac.billingService.Repositories;

import com.epac.billingService.Entities.DatabaseSequence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;


@Repository
public interface DataBaseSequenceeRespository extends MongoRepository<DatabaseSequence,Integer> {
    DatabaseSequence  findById(String id);

}
