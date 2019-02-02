package MongoDB;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
class MongodbConfig extends AbstractMongoConfiguration {
    @Override
    public MongoClient mongoClient() {
//                                         mongodb://localahost:27017
        return new MongoClient(
                new MongoClientURI(
                        "mongodb://localhost:27017"
                ));
    }

    @Override
    protected String getDatabaseName() {
        return "admin2";
    }
}
