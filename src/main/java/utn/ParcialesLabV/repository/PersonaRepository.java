package utn.ParcialesLabV.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.ParcialesLabV.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
}
