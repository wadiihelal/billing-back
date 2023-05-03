package com.epac.billingService.Repositories;
import com.epac.billingService.Entities.Ordre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdreRepository extends MongoRepository<Ordre,Long> {
   Ordre findOrderByIdOrder(Long id);
   Ordre findOrderByOrderNum(Long idnum);
   Ordre deleteByOrderNum(String idnum);
   Ordre deleteByIdOrder(Long id);

}
