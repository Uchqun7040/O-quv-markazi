package uz.jjp.O.quv.markazi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.jjp.O.quv.markazi.entity.Oqituvchi;

@Repository
public interface OqituvchiRepository extends JpaRepository<Oqituvchi,Long> {
}
