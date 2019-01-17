package guru.springframework.repositories;

import guru.springframework.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    Optional<Recipe> findByDescription(String description);
}
