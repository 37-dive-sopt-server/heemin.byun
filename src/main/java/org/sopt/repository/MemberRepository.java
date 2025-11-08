package org.sopt.repository;

import org.sopt.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmailAndIsDeletedFalse(String email);
    boolean existsByEmailAndIsDeletedFalse(String email);
    Optional<Member> findByIdAndIsDeletedFalse(Long id);
    List<Member> findByIsDeletedFalse();

    @Modifying
    @Query("UPDATE Member m SET m.isDeleted = true WHERE m.id = :id")
    int softDeleteById(@Param("id") Long id);
}