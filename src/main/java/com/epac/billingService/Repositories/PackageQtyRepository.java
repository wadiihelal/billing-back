package com.epac.billingService.Repositories;

import com.epac.billingService.Entities.Ordre;
import com.epac.billingService.Entities.PackageQty;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository

public interface PackageQtyRepository extends MongoRepository<PackageQty,String> {
}
