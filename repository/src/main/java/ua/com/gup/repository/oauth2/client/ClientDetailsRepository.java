package ua.com.gup.repository.oauth2.client;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.mongo.composition.domain.oauth2.client.ClientDetail;

/**
 * @author Victor Dvorak
 * Repository for the oauth2_client  entity.
 **/

public interface ClientDetailsRepository extends MongoRepository<ClientDetail, String> {
     ClientDetail findByClientId(String clientId);
}