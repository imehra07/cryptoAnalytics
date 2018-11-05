package au.com.crypto.repository;


import au.com.crypto.model.CurrencyDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface CurrencyRepository extends MongoRepository<CurrencyDocument, String> {
     @Query(value = "{ 'currency': {'$eq' : ?0 } }")
     List<CurrencyDocument> findByCurrencyName(String currencyName);

     @Query(value = "{ 'currency': {'$eq' : ?0 } }")
     List<CurrencyDocument> findByCurrencyAndDate(String currencyName);
}



