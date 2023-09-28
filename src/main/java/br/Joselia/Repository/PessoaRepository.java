package br.Joselia.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.Joselia.entity.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
