package uy.com.demente.ideas.business.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uy.com.demente.ideas.model.Book;

public interface IBookRepository extends JpaRepository<Book, Long> {

	/**
	 * Query generada de forma dinamica utilizando Spring y el nombre reservado
	 * findBy
	 * 
	 * @param author
	 * @return
	 */
	public List<Book> findByAuthor(String author);

	/**
	 * Query generada con @NamedQuery
	 * 
	 * @param title
	 * @return
	 */
	public Book findByTitle(String title);

	/**
	 * Ejemplo con Spring Data Query Methods
	 * 
	 * @param genre
	 * @param publisher
	 * @return
	 */
	@Query("SELECT b FROM Book b WHERE b.genre =:genre AND b.publisher =:publisher")
	public List<Book> find(@Param("genre") String genre, @Param("publisher") Date publisher);
}
