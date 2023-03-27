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
    public List<SelectDto> selectTableData(String gbn, Long bizId, Long parentId, Long codeId, String codeName) throws CustomException {

        List<SelectDto> selectDto ;

        System.out.println("gbn = " + gbn);
        System.out.println("bizId = " + bizId);
        System.out.println("parentId = " + parentId);
        System.out.println("codeId = " + codeId);
        System.out.println("codeName = " + codeName);
        //System.out.println("gbn==\"customer\" = " + gbn == "customer");
        //System.out.println("gbn = " + gbn.equals("customer"));

        if (gbn.equals("customer")) {
            selectDto = em.createQuery("select new kr.infonation.common.dto.SelectDto( c.id, c.name) " +
                            "from Customer c where c.biz.id = :bizId and (:codeId is null or c.id = :codeId) and c.name like CONCAT('%', COALESCE(:codeName,''), '%')")
                    .setParameter("bizId", bizId)
                    .setParameter("codeId", codeId)
                    .setParameter("codeName", codeName)
                    .getResultList();
        }
        else if (gbn.equals("supplier")){
            selectDto = em.createQuery("select new kr.infonation.common.dto.SelectDto( c.id, c.name) " +
                            "from Supplier c where c.biz.id = :bizId and c.customer.id = :parentId and (  c.id = :codeId or :codeId is null ) and c.name like CONCAT('%', COALESCE(:codeName,''), '%')")
                    .setParameter("bizId", bizId)
                    .setParameter("parentId", parentId)
                    .setParameter("codeId", codeId)
                    .setParameter("codeName", codeName)
                    .getResultList();

            System.out.println("selectDto = " + selectDto);
        }
        else if (gbn.equals("destination")){
            selectDto = em.createQuery("select new kr.infonation.common.dto.SelectDto( c.id, c.name) " +
                            "from Destination c where c.biz.id = :bizId and c.customer.id = :parentId and (  c.id = :codeId or :codeId is null ) and c.name like CONCAT('%', COALESCE(:codeName,''), '%')")
                    .setParameter("bizId", bizId)
                    .setParameter("parentId", parentId)
                    .setParameter("codeId", codeId)
                    .setParameter("codeName", codeName)
                    .getResultList();

            System.out.println("selectDto = " + selectDto);
        }
        else if (gbn.equals("item")) {
            selectDto = em.createQuery("select new kr.infonation.common.dto.SelectDto( c.id, c.name) " +
                            "from Item c where c.biz.id = :bizId and c.customer.id = :parentId and (:codeId is null or c.id = :codeId) and c.name like CONCAT('%', :codeName, '%')")
                    .setParameter("bizId", bizId)
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
