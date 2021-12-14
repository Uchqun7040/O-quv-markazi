package uz.jjp.O.quv.markazi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.jjp.O.quv.markazi.entity.Fan;
import uz.jjp.O.quv.markazi.entity.Guruh;
import uz.jjp.O.quv.markazi.entity.Sessiya;

import java.util.List;

@Repository
public interface GuruhRepository extends JpaRepository<Guruh,Long> {
    Page<Guruh> findAllByIdOrNomContainsIgnoreCaseOrOqituvchi_IsmContainsIgnoreCaseOrOqituvchi_FamiliyaContainsIgnoreCaseOrFan_NomContainsIgnoreCaseOrOquvchiSonOrNarxOrInfoContainsIgnoreCaseOrderByIdDesc(Long id, String nom, String oqism, String oqfam, String fnom, int oqson, int narx, String info, Pageable pageable);
    List<Guruh> findAllByAktiv(Boolean aktiv);
    Page<Guruh> findAllByOrderByIdDesc(Pageable pageable);
    Guruh findByFan_Nom(String fan);
    List<Guruh> findAllByOqituvchi_IdAndAktivIsTrueOrderByIdDesc(Long id);
}
