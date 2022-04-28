package br.com.meli.dhprojetointegrador.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.meli.dhprojetointegrador.entity.BatchStock;

@Repository
public interface BatchStockRepository extends JpaRepository<BatchStock, Long> {
  @Query(nativeQuery = true, value = "SELECT b.* FROM batch_stock b join inbound_order i on i.order_number = b.order_number WHERE i.section_id =?1 and b.due_date >?2")
  public List<BatchStock> findBySectionId(Long sectionId, LocalDate dueDate);

  @Query("FROM BatchStock b WHERE b.products.id = :product AND b.dueDate <= :maxdueDate")
  List<BatchStock> findBatchStockByProducts(@Param("product") Long productId, @Param("maxdueDate") LocalDate maxdueDate,
      Sort sort);
}
