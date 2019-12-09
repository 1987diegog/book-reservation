package uy.com.demente.ideas.business.services;

import org.springframework.stereotype.Service;

import uy.com.demente.ideas.business.repository.IBookRepository;
import uy.com.demente.ideas.model.Book;

@Service
public class BookService {

	private final IBookRepository bookRepository;

	public BookService(IBookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	/**
	 * 
	 * @param book
	 * @return
	 */
	public Book create(Book book) {
		return this.bookRepository.save(book);
	}

	/**
	 * 
	 * @param book
	 * @return
	 */
	public Book update(Book book) {
		return this.bookRepository.save(book);
	}

	/**
	 * 
	 * @param book
	 */
	public void delete(Book book) {
		this.bookRepository.delete(book);
	}

	/**
	 * 
	 * @param author
	 * @return
	 */
	public Book findByAuthor(String author) {
		return this.bookRepository.findByAuthor(author);
	}
}
