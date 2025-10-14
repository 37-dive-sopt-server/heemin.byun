package org.sopt.repository;

import org.sopt.domain.Member;

import java.util.*;

public class MemoryMemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();

    public Member save(Member member) {
        store.put(member.getId(), member);
        return member;
    }


    public Optional<Member> findById(Long id) {
        Member m = store.get(id);
        // 삭제된 회원은 조회되지 않도록 필터링
        if (m == null || m.isDeleted()) return Optional.empty();
        return Optional.of(m);
    }

    public List<Member> findAll() {
        List<Member> result = new ArrayList<>();
        for (Member m : store.values()) {
            if (!m.isDeleted()) result.add(m);
        }
        return result;
    }

    // 이메일 중복 체크용
    public Optional<Member> findActiveByEmail(String email) {
        return store.values().stream()
                .filter(m -> !m.isDeleted())
                .filter(m -> m.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public boolean existsActiveByEmail(String email) {
        return findActiveByEmail(email).isPresent();
    }

    public boolean softDelete(Long id) {
        Member m = store.get(id);
        if (m == null || m.isDeleted()) return false;
        m.setDeleted(true);
        return true;
    }
}