package kr.infonation.repository.cust;

import kr.infonation.domain.cust.Destination;
import kr.infonation.domain.cust.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
}
