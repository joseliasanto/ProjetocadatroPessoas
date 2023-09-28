package br.Joselia.controller;

import java.util.List;

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

import br.Joselia.Repository.PessoaRepository;
import br.Joselia.entity.Pessoa;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	PessoaRepository pessoaRepository;

	@GetMapping
	public List<Pessoa> listaPessoas() {
		return pessoaRepository.findAll();

	}

	@PostMapping
	public ResponseEntity<Pessoa> CadastrarPessoa(@RequestBody Pessoa pessoa) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaRepository.save(pessoa));

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa){
		return pessoaRepository.findById(id).map(objetoGravado -> {
			objetoGravado.setNome(pessoa.getNome());
			objetoGravado.setCpf(pessoa.getCpf());
			objetoGravado.setDataNascimento(pessoa.getDataNascimento());
			objetoGravado.setEndereco(pessoa.getEndereco());
			objetoGravado.setTelefone(pessoa.getTelefone());
			Pessoa pessoaAtualizado = pessoaRepository.save(objetoGravado);

			return ResponseEntity.ok().body(pessoaAtualizado);
		}).orElse(ResponseEntity.notFound().build());

	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>deletarPessoa(@PathVariable Long id){
		return pessoaRepository.findById(id).map(objetoGravado -> {
			pessoaRepository.deleteById(id);
			return ResponseEntity.noContent().<Void>build();
		}).orElse(ResponseEntity.notFound().build());
	}


		
	}

	

