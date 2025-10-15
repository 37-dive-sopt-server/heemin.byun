package org.sopt.repository;

import org.sopt.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    List<Member> findAll();
    Optional<Member> findActiveByEmail(String email);
    boolean existsActiveByEmail(String email);
    boolean softDelete(Long id);
}
