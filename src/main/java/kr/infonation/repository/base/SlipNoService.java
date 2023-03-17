package kr.infonation.repository.base;

import kr.infonation.config.CustomException;
import kr.infonation.domain.base.SlipNo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Service
public class SlipNoService {
    @PersistenceContext
    private final EntityManager em;
/*

    @Transactional
    public String getSlipNo(String slipGbn, String slipDate) throws CustomException {
        String slipNo = "";
        if(slipDate.length() != 8 )
            throw new CustomException("전표번호 생성중 날짜 타입이 바르지 않습니다.");
        //String yyyyMMdd = slipDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String yyyyMMdd = slipDate;

        List<SlipNo> result = em.createQuery("select s " +
                        "from SlipNo s " +
                        "where s.slipGbn = :slipGbn " +
                        "and s.slipDate = :slipDate  ", SlipNo.class)
                .setParameter("slipGbn", slipGbn)
                .setParameter("slipDate", yyyyMMdd)
                .getResultList();

        System.out.println(" ============================= ");

        SlipNo rtnData;
        if(result.isEmpty()){

            rtnData = new SlipNo(slipGbn, yyyyMMdd, 1);
            em.persist(rtnData);

            System.out.println("insertSlipData = " + rtnData);
        }
        else {

            rtnData = result.get(0);
            rtnData.updateCount();

            System.out.println("updateSlipData = " + rtnData);
        }
        //em.close();
        em.flush();

        slipNo = rtnData.getSlipGbn() + rtnData.getSlipDate() + String.format("%05d", rtnData.getCount());
        return slipNo;
    }
*/

    @Transactional
    public String generateSlipNo(String slipGbn, String slipDate) throws CustomException {
        if (slipDate == null) {
            throw new CustomException("전표번호 생성중 날짜 타입이 바르지 않습니다.");
        }

        List<SlipNo> result = em.createQuery("select s " +
                        "from SlipNo s " +
                        "where s.slipGbn = :slipGbn " +
                        "and s.slipDate = :slipDate  ", SlipNo.class)
                .setParameter("slipGbn", slipGbn)
                .setParameter("slipDate", slipDate)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE) // SELECT ... FOR UPDATE
                .getResultList();
        for (SlipNo slipNo : result) {
            System.out.println("slipNo = " + slipNo);
        }
        SlipNo slipNo;
        if (result.isEmpty()) {
            System.out.println("\"isEmpty\" = " + "isEmpty");
            slipNo = new SlipNo(slipGbn, slipDate, 1);
        } else {
            SlipNo existingSlipNo = result.get(0);
            System.out.println("existingSlipNo = " + existingSlipNo);
            existingSlipNo.updateCount();
            slipNo = existingSlipNo;
        }

        System.out.println("slipNo = " + slipNo);

        em.persist(slipNo);
        em.flush();

        String slipNoStr = slipNo.getSlipGbn() + slipNo.getSlipDate() + String.format("%05d", slipNo.getCount());
        return slipNoStr;
    }

}
