package kr.infonation.repository.inbound;

import kr.infonation.domain.inbound.Inbound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface  InboundRepository extends JpaRepository<Inbound, Long> {
    @Modifying
    @Query("delete from Inbound i where i.inboundNo = :inboundNo")
    void deleteByInboundNo(@Param("inboundNo") String inboundNo);
}