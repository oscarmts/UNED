package es.uned.portalreuniones.repository.room;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.uned.portalreuniones.model.room.RoomTechResource;

@Repository
public interface RoomTechResourceRepository extends JpaRepository<RoomTechResource, Long> {

	@Query("SELECT t FROM RoomTechResource t where t.room.id = :idRoom")
	List<RoomTechResource> findByIdRoom(@Param("idRoom") Long idRoom);
}
