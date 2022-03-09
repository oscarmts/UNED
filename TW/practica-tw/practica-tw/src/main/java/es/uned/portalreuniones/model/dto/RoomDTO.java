package es.uned.portalreuniones.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * DTO para la pantalla de nueva sala
 * 
 * @author omontes
 *
 */
public class RoomDTO {

	@NotEmpty
	@NotBlank
	private String name;

	@NotEmpty
	@NotBlank
	private String capacity;

	@NotEmpty
	private int[] techResources;

	@NotEmpty
	private int[] hours;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public int[] getTechResources() {
		return techResources;
	}

	public void setTechResources(int[] techResources) {
		this.techResources = techResources;
	}

	public int[] getHours() {
		return hours;
	}

	public void setHours(int[] hours) {
		this.hours = hours;
	}

}
