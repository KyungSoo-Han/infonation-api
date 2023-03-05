package kr.infonation.repository.cust;

import kr.infonation.domain.cust.Destination;
import kr.infonation.domain.cust.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

    @Query("select d from Destination d where d.biz.id = : bizId and d.customer.id = :customerId")
    List<Destination> findDestination(Long bizId, Long customerId);
}
