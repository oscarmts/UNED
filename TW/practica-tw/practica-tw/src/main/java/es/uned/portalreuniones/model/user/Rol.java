package es.uned.portalreuniones.model.user;

import javax.persistence.Entity;

import es.uned.portalreuniones.model.common.EntityIdentifiable;

/**
 * Entity de la tabla ROL
 * 
 * @author omontes
 *
 */
@Entity
public class Rol extends EntityIdentifiable {

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
