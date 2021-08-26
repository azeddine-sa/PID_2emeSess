package be.iccbxl.pid.model;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ShowRepository extends PagingAndSortingRepository<Show, Long> {
	Show findBySlug(String slug);
	Show findByTitle(String title);
	List<Show> findByLocation(Location location);

	@Query("SELECT s FROM Show s WHERE CONCAT(s.title,' ', s.price) LIKE %?1%")
	public Page<Show> search(String keyword, Pageable pageable);
}
