package kr.infonation.repository.item;

import kr.infonation.domain.item.ItemStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface ItemStockRepository extends JpaRepository<ItemStock, Long> {
}
