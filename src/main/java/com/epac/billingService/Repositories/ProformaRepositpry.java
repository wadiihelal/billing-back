package com.epac.billingService.Repositories;

import com.epac.billingService.Entities.Invoice;
import com.epac.billingService.Entities.Proforma;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProformaRepositpry extends MongoRepository<Proforma,String> {
    Proforma getProformaByIdInvoice(String idInvoice);

}
