package es.uned.portalreuniones.repository.event;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.uned.portalreuniones.model.event.EventEnroll;

@Repository
public interface EventEnrollRepository extends JpaRepository<EventEnroll, Long> {

	@Query("SELECT t FROM EventEnroll t where t.user.id = :idUser AND t.event.configured = true AND t.event.finished = false")
	List<EventEnroll> findByIdUser(@Param("idUser") Long idUser);

	@Query("SELECT t FROM EventEnroll t where t.event.id = :idEvent")
	List<EventEnroll> findByIdEvent(@Param("idEvent") Long idEvent);

	@Transactional
	@Modifying
	@Query("DELETE EventEnroll t where t.event.id = :idEvent AND t.user.id=:idUser")
	void deleteByIdEventAndIdUser(@Param("idEvent") Long idEvent, @Param("idUser") Long idUser);
}
