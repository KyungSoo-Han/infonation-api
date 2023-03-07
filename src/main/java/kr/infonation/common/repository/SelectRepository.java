package kr.infonation.common.repository;

import kr.infonation.common.dto.SelectDto;
import kr.infonation.config.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SelectRepository {

    private final EntityManager em;
    public List<SelectDto> selectTableData(String gbn, Long parentId, Long codeId, String codeName) throws CustomException {

        List<SelectDto> selectDto ;

        System.out.println("gbn = " + gbn);
        System.out.println("gbn==\"customer\" = " + gbn == "customer");
        System.out.println("gbn = " + gbn.equals("customer"));

        if (gbn.equals("customer")) {
            selectDto = em.createQuery("select new kr.infonation.common.dto.SelectDto( c.id, c.name) " +
                            "from Customer c where (:codeId is null or c.id = :codeId) and c.name like CONCAT('%', :codeName, '%')")
                    .setParameter("codeId", codeId)
                    .setParameter("codeName", codeName)
                    .getResultList();
        }
        else if (gbn.equals("supplier")){
            selectDto = em.createQuery("select new kr.infonation.common.dto.SelectDto( c.id, c.name) " +
                            "from Supplier c where c.customer.id = :parentId and (:codeId is null or c.id = :codeId) and c.name like CONCAT('%', :codeName, '%')")
                    .setParameter("parentId", parentId)
                    .setParameter("codeId", codeId)
                    .setParameter("codeName", codeName)
                    .getResultList();
        }
        else if (gbn.equals("item")) {
            selectDto = em.createQuery("select new kr.infonation.common.dto.SelectDto( c.id, c.name) " +
                            "from Item c where c.customer.id = :parentId and (:codeId is null or c.id = :codeId) and c.name like CONCAT('%', :codeName, '%')")
                    .setParameter("parentId", parentId)
                    .setParameter("codeId", codeId)
                    .setParameter("codeName", codeName)
                    .getResultList();
        }
        else{
            throw new CustomException("구분값을 찾지 못했습니다.");
        }

        return selectDto;
    }
}
