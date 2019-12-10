package uy.com.demente.ideas.business.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javassist.NotFoundException;
import uy.com.demente.ideas.business.repository.IBookRepository;
import uy.com.demente.ideas.model.Book;
import uy.com.demente.ideas.view.resources.dto.BookDTO;
import uy.com.demente.ideas.view.resources.factory.BOFactory;
import uy.com.demente.ideas.view.resources.factory.DTOFactory;

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
	 * @param book
	 * @return
	 */
	@Transactional
	public BookDTO create(BookDTO bookDTO) {
		Book book = BOFactory.getBook(bookDTO);
		return DTOFactory.getBook(this.bookRepository.save(book));
	}

	/**
	 * 
	 * @param title
	 * @param book
	 * @return
	 * @throws NotFoundException
	 */
	@Transactional
	public BookDTO update(String title, BookDTO bookDTO) throws NotFoundException {

		if (bookRepository.findByTitle(title) != null) {
			Book book = BOFactory.getBook(bookDTO);
			return DTOFactory.getBook(bookRepository.save(book));
		} else {
			throw new NotFoundException("No se encuentra la booka");
		}
	}

	/**
	 * 
	 * @param book
	 * @throws NotFoundException
	 */
	@Transactional
	public void delete(Long id) throws NotFoundException {

		Optional<Book> optional = this.bookRepository.findById(id);
		if (optional.isPresent()) {
			this.bookRepository.delete(optional.get());
		} else {
			throw new NotFoundException("No se encuentra la booka");
		}
	}

	/**
	 * 
	 * @param author
	 * @return
	 */
	public BookDTO findById(Long id) {

		Optional<Book> optional = this.bookRepository.findById(id);
		if (optional.isPresent()) {
			return DTOFactory.getBook(optional.get());
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @return
	 */
	public List<BookDTO> findAll() {
		return DTOFactory.getListBooks(this.bookRepository.findAll());
	}
}
