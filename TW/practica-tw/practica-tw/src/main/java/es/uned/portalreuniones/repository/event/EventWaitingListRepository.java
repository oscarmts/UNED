package es.uned.portalreuniones.repository.event;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.uned.portalreuniones.model.event.EventWaitingList;

@Repository
public interface EventWaitingListRepository extends JpaRepository<EventWaitingList, Long> {

	@Query("SELECT t FROM EventWaitingList t where t.event.id = :idEvent")
	List<EventWaitingList> findByIdEvent(@Param("idEvent") Long idEvent);

	@Transactional
	@Modifying
	@Query("DELETE EventWaitingList t where t.event.id = :idEvent AND t.user.id=:idUser")
	void deleteByIdEventAndIdUser(@Param("idEvent") Long idEvent, @Param("idUser") Long idUser);
}
