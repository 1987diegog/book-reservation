package uy.com.demente.ideas.view.resources;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import uy.com.demente.ideas.business.services.BookService;
import uy.com.demente.ideas.model.Book;
import uy.com.demente.ideas.view.resources.dto.BookDTO;
import uy.com.demente.ideas.view.resources.factory.BOFactory;
import uy.com.demente.ideas.view.resources.factory.DTOFactory;

/**
 * @author diego.gonzalezdurand
 */
// @RestController -> se indica que nuestra clase debera ser tratada como 
// un servicio web.
@RestController
// @RequestMapping -> Habilitamos que sea consumido por otras aplicaciones, programas
@RequestMapping("/api/books")
@Api(tags = "Book")
public class BookResource {

	private final BookService bookService;

	public BookResource(BookService bookService) {
		this.bookService = bookService;
	}

	@PostMapping
	@ApiOperation(value = "Crea un nuevo Book", notes = "Servicio para crear un nuevo book")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Book creado correctamente"),
			@ApiResponse(code = 400, message = "Solicitud invalida") })
	public ResponseEntity<BookDTO> create(@RequestBody BookDTO bookDTO) {

		Book book = BOFactory.getBook(bookDTO);
		BookDTO response = DTOFactory.getBookDTO(bookService.create(book));
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{author}")
	@ApiOperation(value = "Actualiza un Book", notes = "Servicio para actualizar un book")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Book actualizado correctamente"),
			@ApiResponse(code = 404, message = "Book no encontrado") })
	public ResponseEntity<BookDTO> update(@PathVariable("author") String author, BookDTO bookDTO) {

		BookDTO response = null;
		if (bookService.findByAuthor(author) != null) {
			Book book = BOFactory.getBook(bookDTO);
			response = DTOFactory.getBookDTO(bookService.update(book));
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Elimina un Book", notes = "Servicio para eliminar un book")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Book eliminado correctamente"),
			@ApiResponse(code = 404, message = "Book no encontrado") })
	public ResponseEntity<String> remove(@PathVariable("id") Long id) {
		Book book = bookService.findById(id);
		if (book != null) {
			this.bookService.delete(book);
			return new ResponseEntity<>("Se borro el Book con id: \" + id", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No se encontro Book con id: " + id, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	@ApiOperation(value = "Listar Books", notes = "Servicio para listar todos los book")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Books encontrados"),
			@ApiResponse(code = 404, message = "Books no encontrados") })
	public ResponseEntity<List<BookDTO>> findAll() {
		List<Book> listBooks = this.bookService.findAll();
		List<BookDTO> listBooksDTO = DTOFactory.getListBooks(listBooks);
		return ResponseEntity.ok(listBooksDTO);
	}
}
