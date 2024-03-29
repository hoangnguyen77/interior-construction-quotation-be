package com.swp.spring.interiorconstructionquotation.dao;

import com.swp.spring.interiorconstructionquotation.entity.QuotationHeader;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "quotation-header")
public interface IQuotationHeaderRepository extends JpaRepository<QuotationHeader, Integer> {
    @Modifying
    @Transactional
    @Query(value = "insert into quotation_header(header_id, customer_id, construction_id) values (?1, ?2, ?3)", nativeQuery = true)
    void createQuotationHeader(int id, int customerID, int constructionID);
    QuotationHeader findByHeaderId(int id);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM quotation_header WHERE header_id = ?1", nativeQuery = true)
    void deleteByHeaderId(int headerId);
    @Query("SELECT COUNT(DISTINCT qh) FROM QuotationHeader qh JOIN qh.list ql WHERE ql.status.statusId = ?1")
    int countByQuotationListStatusId( int statusId);
    @Query("SELECT COUNT(DISTINCT qh) FROM QuotationHeader qh JOIN qh.list ql WHERE ql.status.statusId = 2 OR ql.status.statusId = 3")
    int countByQuotationListStatusIdTwoOrThree();
}
