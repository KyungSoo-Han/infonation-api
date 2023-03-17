package kr.infonation.service.outbound;

import kr.infonation.domain.outbound.OutboundItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutboundItemRepository extends JpaRepository<OutboundItem, Long> {

    @Query("select max(u.outboundSeq) from OutboundItem u where u.outbound.outboundNo = :outboundNo ")
    Integer getOutboundMaxSeq(@Param("outboundNo") String OutboundNo);

    @Query("select ii from OutboundItem ii where ii.outbound.outboundNo = :outboundNo")
    List<OutboundItem> findByOutboundNo(@Param("outboundNo") String outboundNo);
}
