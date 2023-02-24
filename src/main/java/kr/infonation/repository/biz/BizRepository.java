package kr.infonation.repository.biz;

import kr.infonation.domain.biz.Biz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BizRepository extends JpaRepository<Biz, Long> {
}
