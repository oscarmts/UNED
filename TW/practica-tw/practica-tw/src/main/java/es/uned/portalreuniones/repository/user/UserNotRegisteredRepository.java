package es.uned.portalreuniones.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.uned.portalreuniones.model.user.UserNotRegistered;

@Repository
public interface UserNotRegisteredRepository extends JpaRepository<UserNotRegistered, Long> {

	@Query("SELECT t FROM UserNotRegistered t WHERE t.event.id = :idEvent")
	List<UserNotRegistered> findByIdEvent(@Param("idEvent") Long idEvent);
}
