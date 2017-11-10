package ua.com.gup.rent.repository.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.gup.rent.model.mongo.RentObject;
import ua.com.gup.rent.repository.RentObjectRepository;

import javax.annotation.PostConstruct;

@Repository
public class RentObjectRepositoryImpl extends GenericRepositoryImpl<RentObject, String> implements RentObjectRepository {

    public RentObjectRepositoryImpl() {
        super(RentObject.class);
    }

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(RentObject.class)) {
            mongoTemplate.createCollection(RentObject.class);
        }
    }

    @Override
    public void deleteById(String rentObjectId) {
        Query deleteQuery = new Query(Criteria.where("id").is(rentObjectId));
        mongoTemplate.remove(deleteQuery, RentObject.class);
    }

    @Override
    public boolean isOwner(String rentObjectId, String userId) {
        Query exists = new Query(Criteria.where("id").is(rentObjectId).and("ownerId").is(userId));
        return mongoTemplate.exists(exists, RentObject.class);
    }
}
