package uy.com.demente.ideas.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uy.com.demente.ideas.model.Person;

public interface IPersonRepository extends JpaRepository<Person, Long> {

	/**
	 * Query generada de forma dinamica utilizando Spring y el nombre reservado
	 * findBy
	 * 
	 * @param title
	 * @return
	 */
	public List<Person> findByName(String name);

	/**
	 * Query generada con @NamedQuery
	 * 
	 * @param email
	 * @return
	 */
	public Person findByEmail(String email);

	/**
	 * Ejemplo con Spring Data Query Methods
	 * 
	 * @param genre
	 * @param publisher
	 * @return
	 */
	@Query("SELECT p FROM Person p WHERE p.age =:age AND p.name =:name")
	public List<Person> find(@Param("age") int age, @Param("name") String name);
}
