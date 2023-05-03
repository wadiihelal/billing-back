package com.epac.billingService.Repositories;
import com.epac.billingService.Entities.Invoice;
import com.epac.billingService.Entities.Ordre;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface InvoiceRepository extends MongoRepository<Invoice,String> {
 Invoice getInvoiceByIdInvoice(String idInvoice);
 @Aggregation(pipeline = {
         "{ '$group': { '_id': { 'id': '$siren' }, 'client': { '$first': '$siren' } } }",
         "{ '$project': { '_id': 0 } }"
 })
 List<HashMap> getClientList();
 @Aggregation(pipeline = {
         "{ '$group': { '_id': { 'id': '$siren' }, 'client': { '$first': '$siren' }, 'nb': { '$push': 1 } } }",
         " { '$project': { '_id': 0, 'nb': { '$size': '$nb' }, 'client': 1 } }"
 })
 List<HashMap>getInvoicesByAllClient();
}
