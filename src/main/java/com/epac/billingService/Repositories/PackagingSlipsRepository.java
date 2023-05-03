package com.epac.billingService.Repositories;

import com.epac.billingService.Entities.PackageQty;
import com.epac.billingService.Entities.PackingSlips;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository

public interface PackagingSlipsRepository  extends MongoRepository<PackingSlips,Long>
{
}
