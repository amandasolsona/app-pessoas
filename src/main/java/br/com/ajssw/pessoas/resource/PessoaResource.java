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

import br.com.ajssw.pessoas.model.Pessoa;
import br.com.ajssw.pessoas.service.PessoaService;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> getAllPessoas() {
		List<Pessoa> pessoas = pessoaService.readPessoas();
		if(pessoas.size() == 0)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(pessoas);
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa) {
		Pessoa retornoPessoa = pessoaService.criar(pessoa);
		if(retornoPessoa == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(retornoPessoa);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Pessoa>> getById(@PathVariable Long id) {
		Optional<Pessoa> pessoa = pessoaService.getPessoaId(id);
		if(pessoa.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(pessoa);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		Optional<Pessoa> optPessoa = pessoaService.getPessoaId(id);
		if(optPessoa.isEmpty())
			return ResponseEntity.notFound().build();
		pessoa.setId(id);
		Pessoa updatePessoa = pessoaService.update(pessoa);
		return ResponseEntity.ok(updatePessoa);
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@PathVariable Long id) {
		pessoaService.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
