package be.iccbxl.pid.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Long> {
	Show findBySlug(String slug);
	Show findByTitle(String title);
	List<Show> findByLocation(Location location);
}
