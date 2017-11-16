package ua.com.gup.rent.repository.category;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.rent.model.mongo.category.Category;

import java.util.List;
import java.util.Optional;

/**
 * Repository for the category entity.
 */
public interface CategoryRepository extends MongoRepository<Category, String> {
    Optional<Category> findOneByCode(Integer code);
    Optional<List<Category>> findByCodeInOrderByCodeAsc(List<Integer> codes);
}