package org.sopt.repository;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Primary
public class FileMemberRepository implements MemberRepository {

    private final Path path;
    private final Map<Long, Member> store = new HashMap<>();

    // 파일 포맷: id|name|birthdate(yyyy-MM-dd)|email|gender|isDeleted
    private static final String SEP = "\\|";
    private static final String JOIN = "|";

    public FileMemberRepository() {
        this("members.csv"); // 프로젝트 루트에 저장
    }

    public FileMemberRepository(String fileName) {
        this.path = Paths.get(fileName);
        loadFromFile();
    }

    @Override
    public synchronized Member save(Member member) {
        store.put(member.getId(), member);
        persist();
        return member;
    }

    @Override
    public synchronized Optional<Member> findById(Long id) {
        Member m = store.get(id);
        if (m == null || m.isDeleted()) return Optional.empty();
        return Optional.of(m);
    }

    @Override
    public synchronized List<Member> findAll() {
        return store.values().stream()
                .filter(m -> !m.isDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public synchronized Optional<Member> findActiveByEmail(String email) {
        return store.values().stream()
                .filter(m -> !m.isDeleted())
                .filter(m -> m.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public synchronized boolean existsActiveByEmail(String email) {
        return findActiveByEmail(email).isPresent();
    }

    @Override
    public synchronized boolean softDelete(Long id) {
        Member m = store.get(id);
        if (m == null || m.isDeleted()) return false;
        m.setDeleted(true);
        persist();
        return true;
    }

    // ---------- 내부 유틸 ----------

    private void loadFromFile() {
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
                return;
            }
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (String line : lines) {
                if (line.trim().isEmpty()) continue;
                String[] cols = line.split(SEP, -1);
                //6칼럼
                if (cols.length < 6) continue;

                Long id = Long.parseLong(cols[0]);
                String name = cols[1];
                LocalDate birth = LocalDate.parse(cols[2]); // yyyy-MM-dd
                String email = cols[3];
                Gender gender = Gender.valueOf(cols[4]);
                boolean deleted = Boolean.parseBoolean(cols[5]);

                Member m = new Member(id, name, birth, email, gender);
                m.setDeleted(deleted);
                store.put(id, m);
            }
        } catch (IOException e) {
            throw new UncheckedIOException("회원 파일을 읽는 중 오류가 발생했습니다.", e);
        }
    }

    private void persist() {
        try (BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Member m : store.values()) {
                String line = String.join(JOIN,
                        m.getId().toString(),
                        safe(m.getName()),
                        m.getBirthdate().toString(),
                        safe(m.getEmail()),
                        m.getGender().name(),
                        Boolean.toString(m.isDeleted())
                );
                bw.write(line);
                bw.newLine();
            }
        } catch (NoSuchFileException e) {
            try {
                Files.createFile(path);
                persist();
            } catch (IOException ioe) {
                throw new UncheckedIOException(ioe);
            }
        } catch (IOException e) {
            throw new UncheckedIOException("회원 파일을 저장하는 중 오류가 발생했습니다.", e);
        }
    }

    private String safe(String s) {
        return s == null ? "" : s.replace(JOIN, " "); // 구분자 보호
    }
}