package uy.com.demente.ideas.business.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uy.com.demente.ideas.business.repository.IPersonRepository;
import uy.com.demente.ideas.model.Person;

@Service
@Transactional(readOnly = true)
// readOnly = true -> todos los metodos que no esten anotados con @Transactional 
// seran tratados como una transaccion solo en modo de lectrua
public class PersonService {

	private final IPersonRepository personRepository;

	public PersonService(IPersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	/**
	 * * @Transactional
	 * 
	 * Los metodos anotados con Transactional indican que seran transacciones y
	 * haran referencia al conjunto de operaciones que puedan afectar la base de
	 * datos, como por ejemplo inserts, updates o deletes (no se recomienda para
	 * consultas consultas de solo lectura). Estas operaciones se ejecutaran en
	 * bloque, es decir, se ejecutan todas con exito (commit) o no se ejecutara
	 * ninguna (rollback). Las transacciones empiezan y terminan a nivel de servicio
	 * y nunca a nivel de capa de datos, para indicar que una clase o metodo sera
	 * transaccional utilizamos a anotacion @Transactional.
	 * 
	 */

	/**
	 * 
	 * @param person
	 * @return
	 */
	@Transactional
	public Person create(Person person) {
		return this.personRepository.save(person);
	}

	/**
	 * 
	 * @param person
	 * @return
	 */
	@Transactional
	public Person update(Person person) {
		return this.personRepository.save(person);
	}

	/**
	 * 
	 * @param person
	 */
	@Transactional
	public void delete(Person person) {
		this.personRepository.delete(person);
	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	public Person findByEmail(String email) {
		return this.personRepository.findByEmail(email);
	}

	/**
	 * 
	 * @param author
	 * @return
	 */
	public Person findById(Long id) {

		Optional<Person> optional = this.personRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @return
	 */
	public List<Person> findAll() {
		return this.personRepository.findAll();
	}
}
