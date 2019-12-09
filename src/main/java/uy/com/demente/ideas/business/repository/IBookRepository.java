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
	 * @param title
	 * @return
	 */
	public List<Book> findByTitle(String title);

	/**
	 * Query generada con @NamedQuery
	 * 
	 * @param author
	 * @return
	 */
	public Book findByAuthor(String author);

	/**
	 * Ejemplo con Spring Data Query Methods
	 * 
	 * @param genre
	 * @param publisher
	 * @return
	 */
	@Query("SELECT b FROM Book WHERE b.genre =:genre AND b.publisher =:publisher")
	public List<Book> find(@Param("genre") String genre, @Param("publisher") Date publisher);
}
