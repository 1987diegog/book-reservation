package uy.com.demente.ideas.business.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javassist.NotFoundException;
import uy.com.demente.ideas.business.repository.IPersonRepository;
import uy.com.demente.ideas.model.Person;
import uy.com.demente.ideas.view.resources.dto.PersonDTO;
import uy.com.demente.ideas.view.resources.factory.BOFactory;
import uy.com.demente.ideas.view.resources.factory.DTOFactory;

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
	public PersonDTO create(PersonDTO personDTO) {
		Person person = BOFactory.getPerson(personDTO);
		return DTOFactory.getPerson(this.personRepository.save(person));
	}

	/**
	 * 
	 * @param email
	 * @param person
	 * @return
	 * @throws NotFoundException
	 */
	@Transactional
	public PersonDTO update(String email, PersonDTO personDTO) throws NotFoundException {

		if (personRepository.findByEmail(email) != null) {
			Person person = BOFactory.getPerson(personDTO);
			return DTOFactory.getPerson(personRepository.save(person));
		} else {
			throw new NotFoundException("No se encuentra la persona");
		}
	}

	/**
	 * 
	 * @param person
	 * @throws NotFoundException
	 */
	@Transactional
	public void delete(Long id) throws NotFoundException {

		Optional<Person> optional = this.personRepository.findById(id);
		if (optional.isPresent()) {
			this.personRepository.delete(optional.get());
		} else {
			throw new NotFoundException("No se encuentra la persona");
		}
	}

	/**
	 * 
	 * @param email
	 * @return
	 * @throws NotFoundException
	 */
	public PersonDTO findByEmail(String email) throws NotFoundException {

		Person person = this.personRepository.findByEmail(email);
		if (person != null) {
			PersonDTO response = DTOFactory.getPerson(person);
			return response;
		} else {
			throw new NotFoundException("No se encuentra la persona");
		}
	}

	/**
	 * 
	 * @param author
	 * @return
	 */
	public PersonDTO findById(Long id) {

		Optional<Person> optional = this.personRepository.findById(id);
		if (optional.isPresent()) {
			return DTOFactory.getPerson(optional.get());
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @return
	 */
	public List<PersonDTO> findAll() {
		return DTOFactory.getListPerson(this.personRepository.findAll());
	}
}
