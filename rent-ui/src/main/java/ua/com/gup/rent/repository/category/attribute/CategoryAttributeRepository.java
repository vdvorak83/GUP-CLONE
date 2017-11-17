package ua.com.gup.rent.repository.category.attribute;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.rent.model.mongo.category.attribute.CategoryAttribute;
import ua.com.gup.rent.repository.abstracted.generic.GenericRepository;

import java.util.Optional;

/**
 * Repository for the category attribute entity.
 */

public interface CategoryAttributeRepository extends MongoRepository<CategoryAttribute, String>, GenericRepository<CategoryAttribute, String> {

    Optional<CategoryAttribute> findOneByCode(int code);

}
