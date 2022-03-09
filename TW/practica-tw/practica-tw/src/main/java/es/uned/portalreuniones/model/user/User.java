package es.uned.portalreuniones.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import es.uned.portalreuniones.model.common.EntityIdentifiable;

/**
 * Entity de la tabla USER
 * 
 * @author omontes
 *
 */
@Entity
public class User extends EntityIdentifiable {

	private String name;

	private String surname;

	@Column(name = "NICKNAME")
	private String nickName;

	private String password;

	private String email;

	@OneToOne
	@JoinColumn(name = "ROL_ID", nullable = false)
	private Rol rol;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFullName() {
		StringBuffer sbFullName = new StringBuffer();
		if (name != null) {
			sbFullName.append(name);
		}
		if (surname != null) {
			sbFullName.append(" ").append(surname);
		}
		return sbFullName.toString();
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return rol.getId().equals(2l);
	}

	public boolean isJP() {
		return rol.getId().equals(1l);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nickName == null) ? 0 : nickName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((rol == null) ? 0 : rol.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		User other = (User) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + super.getId() + ", name=" + name + ", surname=" + surname + ", nickName=" + nickName + "]";
	}

}
