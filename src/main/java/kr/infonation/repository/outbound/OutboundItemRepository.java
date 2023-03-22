package kr.infonation.repository.outbound;

import kr.infonation.domain.outbound.OutboundItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OutboundItemRepository extends JpaRepository<OutboundItem, Long> {

    @Query("select max(u.outboundSeq) from OutboundItem u where u.outbound.outboundNo = :outboundNo ")
    Integer getOutboundMaxSeq(@Param("outboundNo") String OutboundNo);

    @Query("select ii from OutboundItem ii where  ii.outbound.biz.id = :bizId and  ii.outbound.outboundNo = :outboundNo")
    Optional<OutboundItem> findByOutboundNo(@Param("bizId") Long bizId, @Param("outboundNo") String outboundNo);

    @Modifying
    @Query("delete from OutboundItem i where i.outbound.outboundNo = :outboundNo")
    void deleteByOutboundNo(@Param("outboundNo") String outboundNo);
}
