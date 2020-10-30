package co.edu.usbbog.appausa.appausaws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbbog.appausa.appausaws.model.Contrato;
import co.edu.usbbog.appausa.appausaws.model.ContratoPK;

public interface ContratoRepository extends JpaRepository<Contrato,ContratoPK> {

}
