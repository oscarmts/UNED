package es.uned.portalreuniones.repository.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uned.portalreuniones.model.room.TechResource;

@Repository
public interface TechResourceRepository extends JpaRepository<TechResource, Long> {

}
