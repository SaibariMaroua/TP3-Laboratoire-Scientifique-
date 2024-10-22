package dcc.tp2.security_microservice.Service;

import dcc.tp2.security_microservice.Repository.UserRepository;
import dcc.tp2.security_microservice.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Primary
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // Injectez le repository

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username); // Implémentez cette méthode dans le repository
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return user; // Retourne l'objet User qui implémente UserDetails
    }
}
