package es.uned.portalreuniones.repository.event;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.uned.portalreuniones.model.event.EventPreferenceDateSchedule;

@Repository
public interface EventPreferenceDateScheduleRepository extends JpaRepository<EventPreferenceDateSchedule, Long> {

	@Query("SELECT t FROM EventPreferenceDateSchedule t where t.event.id = :idEvent ORDER BY date desc")
	List<EventPreferenceDateSchedule> findByIdEvent(@Param("idEvent") Long idEvent);
}
