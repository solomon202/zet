package  com.example.solit.repository;

//вытаскивает с базы 
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.solit.entity.Role;

@Repository
@Profile("dao")
public interface RoleRepository extends CrudRepository<Role, Long> {
}
