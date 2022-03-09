package es.uned.portalreuniones.repository.event;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.uned.portalreuniones.model.event.EventPreferenceSchedule;
import es.uned.portalreuniones.model.user.User;

@Repository
public interface EventPreferenceScheduleRepository extends JpaRepository<EventPreferenceSchedule, Long> {

	@Query("SELECT t FROM EventPreferenceSchedule t where t.event.id = :idEvent and t.isOwnerHours = false")
	List<EventPreferenceSchedule> findByIdEvent(@Param("idEvent") Long idEvent);

	@Query("SELECT t.hour FROM EventPreferenceSchedule t where t.event.id = :idEvent and t.isOwnerHours = true")
	List<Integer> findHoursFromOwner(@Param("idEvent") Long idEvent);

	@Query("SELECT t.hour FROM EventPreferenceSchedule t where t.event.id = :idEvent and t.user.id = :idUser and t.isOwnerHours = false")
	List<Integer> findHoursFromUser(@Param("idEvent") Long idEvent, @Param("idUser") Long idUser);

	@Query("SELECT DISTINCT t.user FROM EventPreferenceSchedule t where t.event.id = :idEvent and t.isOwnerHours = false")
	List<User> findUsersWritePreferences(@Param("idEvent") Long idEvent);

	@Query("SELECT DISTINCT t.date FROM EventPreferenceSchedule t where t.event.id = :idEvent and t.user.id = :idUser and t.isOwnerHours = false")
	List<Date> findDateUserPreferences(@Param("idEvent") Long idEvent, @Param("idUser") Long idUser);

	@Query("SELECT t.hour FROM EventPreferenceSchedule t where t.event.id = :idEvent and t.date = :date")
	List<Integer> findHoursFromDateAndEvent(@Param("idEvent") Long idEvent, @Param("date") Date date);
}
