package au.com.crypto.flyway;


import au.com.crypto.model.CurrencyDocument;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;

import org.apache.commons.io.IOUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;


@ChangeLog
public class MongoBeeDatabaseChangeLogs {


    @ChangeSet(order = "001", id = "initialCurrencyLoad", author = "imehra")
    public void initialCurrencyLoad(MongoTemplate mongoTemplate) {
        InputStream in = null;
        try {
            in = this.getClass().getResourceAsStream( "/test-data.json" );
            String json = IOUtils.toString(in, Charset.defaultCharset());
            ObjectMapper mapper = new ObjectMapper();
            CurrencyDocument[] list = mapper.readValue(json,CurrencyDocument[].class);

            for(CurrencyDocument obj : list) {
                mongoTemplate.insert(obj);
            }
        } catch (IOException e) {
            IOUtils.closeQuietly(in);
            throw new RuntimeException("Failed to load currency data");
        }
    }


}
