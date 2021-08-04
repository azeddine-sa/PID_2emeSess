package be.iccbxl.pid.model;

import org.springframework.data.repository.CrudRepository;

public interface TypeRepository extends CrudRepository<Type, Long> {
	Type findByType(String type);
	Optional<Type> findById(Long id);
}
