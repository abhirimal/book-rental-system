package com.abhiyan.bookrentalsystem.repository;

import com.abhiyan.bookrentalsystem.enums.RentType;
import com.abhiyan.bookrentalsystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction,Integer> {

//    @Query(nativeQuery = true, value="select * from transaction" +
//            "inner join member on transaction.member_id=member.id" +
//            "inner join tbl_book on transaction.book_id=tbl_book.id" +
//            "where rent_type=RENT")
//    List<Transaction> findRentTransaction();

    List<Transaction> findAllByRentType(RentType rentType);

    Transaction findTransactionByCode(String code);

    @Query(nativeQuery = true, value="select code from transaction where rent_type='RENT'")
    List<String> allCode();

    @Query(nativeQuery = true, value="SELECT * from transaction where rent_type='RENT'" +
            "and member_id=?1 ")
    List<Transaction> findTransactionListByMemberId(Integer id);

    @Query(nativeQuery = true, value="SELECT * from transaction where member_id=?1 ")
    Transaction findTransactionByMemberId(Integer id);

    @Query(nativeQuery = true, value="SELECT * from transaction where rent_type='RETURN'" +
            "and member_id=?1 ")
    List<Transaction> findReturnedBookTransactionListByMemberId(Integer id);
}
