package co.edu.usbbog.appausa.appausaws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbbog.appausa.appausaws.model.Partida;
import co.edu.usbbog.appausa.appausaws.model.PartidaPK;

public interface PartidaRepository extends JpaRepository<Partida,PartidaPK>{

}
