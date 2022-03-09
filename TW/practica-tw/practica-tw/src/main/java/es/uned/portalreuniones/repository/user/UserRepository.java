package es.uned.portalreuniones.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.uned.portalreuniones.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT t FROM User t where t.rol.id != 0")
	List<User> findAll();

	@Query("SELECT t FROM User t where t.nickName = :nickName")
	User findByNickName(@Param("nickName") String nickName);

	@Query("SELECT t FROM User t where t.nickName = :nickName and t.password = :password")
	List<User> findByNickNameAndPassword(@Param("nickName") String nickName, @Param("password") String password);
}
