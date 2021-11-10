package uz.jjp.O.quv.markazi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.jjp.O.quv.markazi.entity.Fan;
import uz.jjp.O.quv.markazi.entity.Guruh;

import java.util.List;

@Repository
public interface GuruhRepository extends JpaRepository<Guruh,Long> {
    List<Guruh> findAllByIdOrNomContainsIgnoreCaseOrOqituvchi_IsmContainsIgnoreCaseOrOqituvchi_FamiliyaContainsIgnoreCaseOrFan_NomContainsIgnoreCaseOrOquvchiSonOrNarxOrInfoContainsIgnoreCase(Long id,String nom,String oqism,String oqfam,String fnom,int oqson,int narx, String info);
    List<Guruh> findAllByAktiv(Boolean aktiv);
}
