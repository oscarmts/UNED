package es.uned.portalreuniones.repository.room;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.uned.portalreuniones.model.room.RoomSchedule;

@Repository
public interface RoomScheduleRepository extends JpaRepository<RoomSchedule, Long> {

	@Query("SELECT t FROM RoomSchedule t where t.room.id = :idRoom")
	List<RoomSchedule> findByIdRoom(@Param("idRoom") Long idRoom);
}
