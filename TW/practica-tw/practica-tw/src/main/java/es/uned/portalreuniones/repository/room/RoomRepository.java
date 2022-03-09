package es.uned.portalreuniones.repository.room;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.uned.portalreuniones.model.room.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

	@Query("SELECT r FROM Room r, Event e WHERE r.id = e.room.id AND r.id = :idRoom AND e.date = :date AND e.hour = :hour AND e.id != :idEvent AND e.finished=FALSE")
	List<Room> findByDateHour(@Param("idRoom") Long idRoom, @Param("idEvent") Long idEvent, @Param("date") Date date,
			@Param("hour") Integer hour);

	@Query("SELECT r FROM Room r WHERE r.name = :name")
	Room findByName(@Param("name") String name);

}
