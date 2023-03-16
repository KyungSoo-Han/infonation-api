package kr.infonation.repository.inbound;

import kr.infonation.domain.inbound.Inbound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface  InboundRepository extends JpaRepository<Inbound, Long> {
}