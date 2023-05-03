package com.epac.billingService.Repositories;

import com.epac.billingService.Entities.ProductionPart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductionPartRepository extends MongoRepository<ProductionPart,Long> {




}

