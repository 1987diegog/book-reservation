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
import javassist.NotFoundException;
import uy.com.demente.ideas.business.services.BookService;
import uy.com.demente.ideas.view.resources.dto.BookDTO;

/**
 * @author diego.gonzalezdurand
 */
// @RestController -> se indica que nuestra clase debera ser tratada como 
// un servicio web.
@RestController
// @RequestMapping -> Habilitamos que sea consumido por otras aplicaciones
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

		System.out.println("Llegue: Crear Book");
		BookDTO response = bookService.create(bookDTO);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{title}")
	@ApiOperation(value = "Actualiza un Book", notes = "Servicio para actualizar un book")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Book actualizado correctamente"),
			@ApiResponse(code = 404, message = "Book no encontrado") })
	public ResponseEntity<BookDTO> update(@PathVariable("title") String title, BookDTO bookDTO) {

		try {
			System.out.println("Llegue: Update Book");
			BookDTO response = bookService.update(title, bookDTO);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Elimina un Book", notes = "Servicio para eliminar un book")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Book eliminado correctamente"),
			@ApiResponse(code = 404, message = "Book no encontrado") })
	public ResponseEntity<String> remove(@PathVariable("id") Long id) {

		try {
			System.out.println("Llegue: Remove Book");
			this.bookService.delete(id);
			return new ResponseEntity<>("Se borro el Book con id: \" + id", HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>("No se encontro el Book con id: " + id, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	@ApiOperation(value = "Listar Books", notes = "Servicio para listar todos los books")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Books encontrados"),
			@ApiResponse(code = 404, message = "Books no encontrados") })
	public ResponseEntity<List<BookDTO>> findAll() {

		System.out.println("Llegue: listar Books");
		List<BookDTO> listBooksDTO = this.bookService.findAll();
		return ResponseEntity.ok(listBooksDTO);
	}
}
