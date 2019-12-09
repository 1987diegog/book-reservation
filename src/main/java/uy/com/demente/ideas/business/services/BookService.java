package uy.com.demente.ideas.business.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uy.com.demente.ideas.business.repository.IBookRepository;
import uy.com.demente.ideas.model.Book;

@Service
@Transactional(readOnly = true)
// readOnly = true -> todos los metodos que no esten anotados con @Transactional 
// seran tratados como una transaccion solo en modo de lectrua
public class BookService {

	private final IBookRepository bookRepository;

	public BookService(IBookRepository bookRepository) {
		this.bookRepository = bookRepository;
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
	 * @param book
	 * @return
	 */
	@Transactional
	public Book create(Book book) {
		return this.bookRepository.save(book);
	}

	/**
	 * 
	 * @param book
	 * @return
	 */
	@Transactional
	public Book update(Book book) {
		return this.bookRepository.save(book);
	}

	/**
	 * 
	 * @param book
	 */
	@Transactional
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

	/**
	 * 
	 * @param author
	 * @return
	 */
	public Book findById(Long id) {

		Optional<Book> optional = this.bookRepository.findById(id);
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
	public List<Book> findAll() {
		return this.bookRepository.findAll();
	}
}
