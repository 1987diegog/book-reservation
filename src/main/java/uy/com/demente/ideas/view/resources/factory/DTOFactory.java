package uy.com.demente.ideas.view.resources.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import uy.com.demente.ideas.model.Book;
import uy.com.demente.ideas.model.Person;
import uy.com.demente.ideas.view.resources.dto.BookDTO;
import uy.com.demente.ideas.view.resources.dto.PersonDTO;

/**
 * @author 1987diegog
 */
public class DTOFactory {

	/////////////////////////////////////////////////////////////
	///////////////////////// PERSONS ///////////////////////////
	/////////////////////////////////////////////////////////////

	public static List<PersonDTO> getListPerson(List<Person> listPerson) {

		List<PersonDTO> listPersonDTO = null;
		if (listPerson != null) {

			listPersonDTO = listPerson.stream().map(DTOFactory::getPerson)
					.collect(Collectors.toCollection(ArrayList::new));
		}

		return listPersonDTO;
	}

	public static PersonDTO getPerson(Person person) {

		PersonDTO personDTO = null;
		if (person != null) {
			personDTO = new PersonDTO();
			BeanUtils.copyProperties(person, personDTO);
		}

		return personDTO;
	}

	/////////////////////////////////////////////////////////////
	////////////////////////// BOOKS ////////////////////////////
	/////////////////////////////////////////////////////////////

	public static List<BookDTO> getListBooks(List<Book> listBook) {

		List<BookDTO> listBookDTO = null;
		if (listBook != null) {

			listBookDTO = listBook.stream().map(DTOFactory::getBook).collect(Collectors.toCollection(ArrayList::new));
		}

		return listBookDTO;
	}

	public static BookDTO getBook(Book book) {

		BookDTO bookDTO = null;

		if (book != null) {
			bookDTO = new BookDTO();
			BeanUtils.copyProperties(book, bookDTO);
		}

		return bookDTO;
	}

}
