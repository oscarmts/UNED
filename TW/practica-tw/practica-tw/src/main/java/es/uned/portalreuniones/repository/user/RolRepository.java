package es.uned.portalreuniones.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.uned.portalreuniones.model.user.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

	@Query("SELECT t FROM Rol t where t.id != 0")
	List<Rol> findAll();
}
