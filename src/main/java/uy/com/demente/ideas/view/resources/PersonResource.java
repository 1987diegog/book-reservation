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
import uy.com.demente.ideas.business.services.PersonService;
import uy.com.demente.ideas.view.resources.dto.PersonDTO;

/**
 * @author diego.gonzalezdurand
 */
// @RestController -> se indica que nuestra clase debera ser tratada como 
// un servicio web.
@RestController
// @RequestMapping -> Habilitamos que sea consumido por otras aplicaciones
@RequestMapping("/api/persons")
@Api(tags = "Person")
public class PersonResource {

	private final PersonService personService;

	public PersonResource(PersonService personService) {
		this.personService = personService;
	}

	@PostMapping
	@ApiOperation(value = "Crea una nueva Persona", notes = "Servicio para crear una nueva persona")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Persona creada correctamente"),
			@ApiResponse(code = 400, message = "Solicitud invalida") })
	public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO personDTO) {
		System.out.println("Llegue: Crear Persona");
		PersonDTO response = personService.create(personDTO);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{email}")
	@ApiOperation(value = "Actualiza una Persona", notes = "Servicio para actualizar un persona")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Persona actualizada correctamente"),
			@ApiResponse(code = 404, message = "Persona no encontrada") })
	public ResponseEntity<PersonDTO> update(@PathVariable("email") String email, PersonDTO personDTO) {
		System.out.println("Llegue: Update Persona");
		PersonDTO response = null;
		if (personService.findByEmail(email) != null) {
			response = personService.update(personDTO);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Elimina una Persona", notes = "Servicio para eliminar una persona")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Persona eliminada correctamente"),
			@ApiResponse(code = 404, message = "Persona no encontrada") })
	public ResponseEntity<String> remove(@PathVariable("id") Long id) {
		System.out.println("Llegue: Remove Persona");
		PersonDTO personDTO = personService.findById(id);
		if (personDTO != null) {
			this.personService.delete(personDTO);
			return new ResponseEntity<>("Se borro la Persona con id: \" + id", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No se encontro la Persona con id: " + id, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	@ApiOperation(value = "Listar Personas", notes = "Servicio para listar todas las personas")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Personas encontradas"),
			@ApiResponse(code = 404, message = "Personas no encontradas") })
	public ResponseEntity<List<PersonDTO>> findAll() {

		System.out.println("Llegue: listar Personas");
		List<PersonDTO> listPersonsDTO = this.personService.findAll();
		return ResponseEntity.ok(listPersonsDTO);
	}
}
