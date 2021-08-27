package be.iccbxl.pid.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByLastname(String lastname);

	User findById(long id);	
	User findByLogin(String login);
	User findByEmail(String email);
}