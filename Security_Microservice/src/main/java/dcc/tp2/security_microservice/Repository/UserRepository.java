package dcc.tp2.security_microservice.Repository;

import dcc.tp2.security_microservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Méthode pour trouver un utilisateur par son nom d'utilisateur
    User findByUsername(String username);
}
