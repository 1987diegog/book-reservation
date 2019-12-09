package uy.com.demente.ideas.view.resources.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author diego.gonzalezdurand
 *
 */
public class BookDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idBook;
	private String title;
	private String author;
	private Date publisher;
	private String genre;

	private PersonDTO person;

	public Long getIdBook() {
		return idBook;
	}

	public void setIdBook(Long idBook) {
		this.idBook = idBook;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPublisher() {
		return publisher;
	}

	public void setPublisher(Date publisher) {
		this.publisher = publisher;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public PersonDTO getPerson() {
		return person;
	}

	public void setPerson(PersonDTO person) {
		this.person = person;
	}
}
