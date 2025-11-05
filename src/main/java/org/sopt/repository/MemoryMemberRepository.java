package org.sopt.repository;

import org.sopt.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
/*
@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();

    @Override
    public Member save(Member member) {
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member m = store.get(id);
        // 삭제된 회원은 조회되지 않도록 필터링
        if (m == null || m.isDeleted()) return Optional.empty();
        return Optional.of(m);
    }

    @Override
    public List<Member> findAll() {
        List<Member> result = new ArrayList<>();
        for (Member m : store.values()) {
            if (!m.isDeleted()) result.add(m);
        }
        return result;
    }

    @Override
    // 이메일 중복 체크용
    public Optional<Member> findActiveByEmail(String email) {
        return store.values().stream()
                .filter(m -> !m.isDeleted())
                .filter(m -> m.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public boolean existsActiveByEmail(String email) {
        return findActiveByEmail(email).isPresent();
    }

    @Override
    public boolean softDelete(Long id) {
        Member m = store.get(id);
        if (m == null || m.isDeleted()) return false;
        m.setDeleted(true);
        return true;
    }
}

 */