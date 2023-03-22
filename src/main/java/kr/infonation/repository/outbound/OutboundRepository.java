package kr.infonation.repository.outbound;


import kr.infonation.domain.outbound.Outbound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OutboundRepository extends JpaRepository<Outbound, Long> {
    @Modifying
    @Query("delete from Outbound i where i.outboundNo = :outboundNo")
    void deleteByOutboundNo(@Param("outboundNo") String outboundNo);
}
