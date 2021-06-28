package utn.ParcialesLabV.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utn.ParcialesLabV.model.Cumpleanito;

public interface CumpleanitosRepository extends JpaRepository<Cumpleanito, Integer> {
    @Query (value = "SELECT * FROM personas INNER JOIN cumpleanitos ON persona.id= cumplenanitos.id_invitado", nativeQuery = true )
    Page getAllInvitados(Pageable pageable);
}
