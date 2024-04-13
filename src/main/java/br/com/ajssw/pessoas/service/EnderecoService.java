package br.com.ajssw.pessoas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ajssw.pessoas.model.Endereco;
import br.com.ajssw.pessoas.model.Pessoa;
import br.com.ajssw.pessoas.repository.EnderecoRepository;
import br.com.ajssw.pessoas.repository.PessoaRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Endereco save(Endereco endereco) {
		Optional<Pessoa> findPessoa = pessoaRepository.findById(endereco.getPessoa().getId());
		if(findPessoa.isEmpty()) {
			System.out.println("Pessoa nao encontrada!");
			return null;
		} else {
			endereco.setPessoa(findPessoa.get());
			return enderecoRepository.save(endereco);
		}
	}
	
	public List<Endereco> findAll() {
		return enderecoRepository.findAll();
	}
	
	public Endereco update(Endereco endereco) {
		Optional<Endereco> findEndereco = enderecoRepository.findById(endereco.getId());
		if(findEndereco.isPresent()) {
			Endereco updateEndereco = findEndereco.get();
			updateEndereco.setTipoLogradouro(endereco.getTipoLogradouro());
			updateEndereco.setLogradouro(endereco.getLogradouro());
			updateEndereco.setCep(endereco.getCep());
			updateEndereco.setCidade(endereco.getCidade());
			updateEndereco.setUf(endereco.getUf());
			updateEndereco.setPessoa(endereco.getPessoa());
			return enderecoRepository.save(updateEndereco);
		}	
		return endereco;
	}
	
	public void delete(Long id) {
		enderecoRepository.deleteById(id);
	}
	
	public Optional<Endereco> getEnderecoId(Long id) {
		return enderecoRepository.findById(id);
	}
}
