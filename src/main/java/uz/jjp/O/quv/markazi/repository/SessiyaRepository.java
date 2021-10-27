package uz.jjp.O.quv.markazi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.jjp.O.quv.markazi.entity.Oquvchi;
import uz.jjp.O.quv.markazi.entity.Sessiya;
import uz.jjp.O.quv.markazi.entity.Tolov;

import java.util.List;

@Repository
public interface SessiyaRepository extends JpaRepository<Sessiya,Long> {

public List<Sessiya> getAllByOquvchiIdAndAktivIsTrue(Long id);

}
