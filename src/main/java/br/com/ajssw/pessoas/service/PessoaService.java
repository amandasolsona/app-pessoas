package br.com.ajssw.pessoas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ajssw.pessoas.model.Pessoa;
import br.com.ajssw.pessoas.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa criar(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	public List<Pessoa> readPessoas() {
		return pessoaRepository.findAll();
	}
	
	public Optional<Pessoa> getPessoaId(Long id) {
		return pessoaRepository.findById(id);
	}
	
	public Pessoa update(Pessoa pessoa) {
		Optional<Pessoa> findPessoa = pessoaRepository.findById(pessoa.getId());
		if(findPessoa.isPresent()) {
			Pessoa updatePessoa = findPessoa.get();
			updatePessoa.setNome(pessoa.getNome());
			updatePessoa.setDocumento(pessoa.getDocumento());
			return pessoaRepository.save(updatePessoa);
		}	
		return pessoa;
	}
	
	public void deletar(Long id) {
		pessoaRepository.deleteById(id);
	}
}
