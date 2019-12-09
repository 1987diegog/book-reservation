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
public class BOFactory {

	/////////////////////////////////////////////////////////////
	///////////////////////// PERSONS ///////////////////////////
	/////////////////////////////////////////////////////////////

	public static List<Person> getListPerson(List<PersonDTO> listPersonDTO) {

		List<Person> listPerson = null;

		if (listPersonDTO != null) {
			listPerson = listPersonDTO.stream().map(BOFactory::getPerson)
					.collect(Collectors.toCollection(ArrayList::new));
		}

		return listPerson;
	}

	public static Person getPerson(PersonDTO personDTO) {

		Person person = null;

		if (personDTO != null) {
			person = new Person();
			BeanUtils.copyProperties(personDTO, person);
		}

		return person;
	}

	/////////////////////////////////////////////////////////////
	////////////////////////// BOOKS ////////////////////////////
	/////////////////////////////////////////////////////////////

	public static List<Book> getListBooks(List<BookDTO> listBookDTO) {

		List<Book> listBook = null;

		if (listBookDTO != null) {
			listBook = listBookDTO.stream().map(BOFactory::getBook).collect(Collectors.toCollection(ArrayList::new));
		}

		return listBook;
	}

	public static Book getBook(BookDTO bookDTO) {

		Book book = null;

		if (bookDTO != null) {
			book = new Book();
			BeanUtils.copyProperties(bookDTO, book);
		}

		return book;
	}

}
