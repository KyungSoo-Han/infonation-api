package kr.infonation.repository.inbound;

import kr.infonation.domain.inbound.InboundItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Repository
public interface InboundItemRepository extends JpaRepository<InboundItem, Long> {

    @Query("select max(u.inboundSeq) from InboundItem u where u.inbound.inboundNo = :inboundNo ")
    Integer getInboundMaxSeq(@Param("inboundNo") String inboundNo);

    @Query("select ii from InboundItem ii where ii.inbound.biz.id = :bizId and ii.inbound.inboundNo = :inboundNo")
    Optional<InboundItem> findByInboundNo(@Param("bizId") Long bizId, @Param("inboundNo") String inboundNo);
    @Modifying
    @Query("delete from InboundItem i where i.inbound.inboundNo = :inboundNo")
    void deleteByInboundNo(@Param("inboundNo") String inboundNo);
}
