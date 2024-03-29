package kr.infonation.repository.center;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.infonation.dto.center.CenterDto;
import kr.infonation.dto.center.QCenterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.infonation.domain.biz.QBiz.biz;
import static kr.infonation.domain.center.QCenter.center;

@Repository
@RequiredArgsConstructor
public class CenterQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<CenterDto> findCenter(){

       List<CenterDto> centerdto = queryFactory
               .select(new QCenterDto( center.id, center.name, center.address, center.biz.id, center.biz.name))
                .from(center)
                .leftJoin(center.biz, biz)
                .fetch();
        return centerdto ;
    }


}
