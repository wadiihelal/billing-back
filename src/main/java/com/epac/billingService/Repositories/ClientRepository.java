package com.epac.billingService.Repositories;

import com.epac.billingService.Entities.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository

public interface ClientRepository extends MongoRepository<Client,String>
{
 Client findByAccountName(String name);
 Client findClientBySiren(String siren);
 Client deleteBySiren(String siren);
 Client findByClientId(Long clientId);
}
