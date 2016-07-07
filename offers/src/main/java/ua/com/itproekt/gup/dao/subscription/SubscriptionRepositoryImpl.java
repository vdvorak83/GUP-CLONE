package ua.com.itproekt.gup.dao.subscription;

import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.itproekt.gup.model.subscription.Subscription;
import ua.com.itproekt.gup.util.MongoTemplateOperations;

@Repository
public class SubscriptionRepositoryImpl implements SubscriptionRepository {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public void create(Subscription subscription) {
        mongoTemplate.insert(subscription);
    }

    @Override
    public Subscription find(String subscriptionId) {
        Query query = new Query(Criteria.where("id").is(subscriptionId));
        return mongoTemplate.findOne(query, Subscription.class);
    }

    @Override
    public Subscription findAndUpdate(Subscription subscription) {
        return MongoTemplateOperations.updateFieldsAndReturnUpdatedObj(subscription);
    }

    @Override
    public int delete(String subscriptionId) {
        Query query = new Query(Criteria.where("id").is(subscriptionId));
        WriteResult result = mongoTemplate.remove(query, Subscription.class);
        return result.getN();
    }
}