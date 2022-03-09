package es.uned.portalreuniones.model.room;

import javax.persistence.Entity;
import javax.persistence.Table;

import es.uned.portalreuniones.model.common.EntityIdentifiable;

/**
 * Entity de la tabla TECH_RESOURCE
 * 
 * @author omontes
 *
 */
@Entity
@Table(name = "TECH_RESOURCE")
public class TechResource extends EntityIdentifiable {

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
