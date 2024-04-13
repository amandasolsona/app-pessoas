package br.com.ajssw.pessoas.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.ajssw.pessoas.model.Endereco;
import br.com.ajssw.pessoas.service.EnderecoService;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoResource {

	@Autowired
	private EnderecoService enderecoService;
	
	@PostMapping
	public ResponseEntity<Endereco> save(@RequestBody Endereco endereco) {
		Endereco newEndereco = enderecoService.save(endereco);
		if(newEndereco == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(newEndereco);
	}
	
	@GetMapping
	public ResponseEntity<List<Endereco>> findAll() {
		List<Endereco> enderecos = enderecoService.findAll();
		if(enderecos == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(enderecos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Endereco>> getById(@PathVariable Long id) {
		Optional<Endereco> endereco = enderecoService.getEnderecoId(id);
		if(endereco.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(endereco);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Endereco> update(@PathVariable Long id, @RequestBody Endereco endereco) {
		Optional<Endereco> optEndereco = enderecoService.getEnderecoId(id);
		if(optEndereco.isEmpty())
			return ResponseEntity.notFound().build();
		endereco.setId(id);
		Endereco updEndereco = enderecoService.update(endereco);
		return ResponseEntity.ok(updEndereco);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		enderecoService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
