package kr.infonation.repository.outbound;


import kr.infonation.domain.outbound.Outbound;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutboundRepository extends JpaRepository<Outbound, Long> {

}
