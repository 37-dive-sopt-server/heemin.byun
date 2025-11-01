package org.sopt;

import org.sopt.controller.MemberController;
import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.sopt.exception.DuplicateEmailException;
import org.sopt.exception.MemberException;
import org.sopt.exception.MemberNotFoundException;
import org.sopt.repository.FileMemberRepository;
import org.sopt.repository.MemberRepository;
import org.sopt.repository.MemoryMemberRepository;
import org.sopt.service.MemberService;
import org.sopt.service.MemberServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
/*
public class Main {
    public static void main(String[] args) {

        MemberRepository memberRepository = new FileMemberRepository("members.csv");
        MemberService memberService = new MemberServiceImpl(memberRepository);
        MemberController memberController = new MemberController(memberService);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n✨ --- DIVE SOPT 회원 관리 서비스 --- ✨");
            System.out.println("---------------------------------");
            System.out.println("1️⃣. 회원 등록 ➕");
            System.out.println("2️⃣. ID로 회원 조회 🔍");
            System.out.println("3️⃣. 전체 회원 조회 📋");
            System.out.println("4️⃣. 회원 삭제 🗑️");
            System.out.println("5️⃣. 종료 🚪");
            System.out.println("---------------------------------");
            System.out.print("메뉴를 선택하세요: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("등록할 회원 이름을 입력하세요: ");
                    String name = scanner.nextLine();
                    if (name.trim().isEmpty()) {
                        System.out.println("⚠️ 이름을 입력해주세요.");
                        break;
                    }
                    System.out.print("등록할 생년월일을 입력하세요(Ex.2002.10.30: ");
                    String birthdate = scanner.nextLine();
                    if (birthdate.trim().isEmpty()) {
                        System.out.println("⚠️ 생년월일을 입력해주세요.");
                        break;
                    }
                    System.out.print("등록할 회원 이메일을 입력하세요: ");
                    String email = scanner.nextLine();
                    if (email.trim().isEmpty()) {
                        System.out.println("⚠️ 이메일을 입력해주세요.");
                        break;
                    }
                    System.out.print("등록할 성별을 입력하세요(MALE / FEMALE): ");
                    Gender gender;
                    try {
                        gender = Gender.from(scanner.nextLine());
                    } catch (IllegalArgumentException e) {
                        System.out.println("❌ " + e.getMessage());
                        break;
                    }
                    try {
                        Long createdId = memberController.createMember(name, birthdate, email, gender);
                        System.out.println("✅ 회원 등록 완료 (ID: " + createdId + ")");
                    } catch (MemberException e) {
                        System.out.println("❌ " + e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println("❌ " + e.getMessage());
                    }
                    break;
                case "2":
                    System.out.print("조회할 회원 ID를 입력하세요: ");
                    try {
                        Long id = Long.parseLong(scanner.nextLine());
                        Optional<Member> foundMember = memberController.findMemberById(id);
                        if (foundMember.isPresent()) {
                            Member m = foundMember.get();
                            System.out.println("✅ 조회된 회원");
                            System.out.println("   - ID=" + m.getId());
                            System.out.println("   - 이름=" + m.getName());
                            System.out.println("   - 생년월일=" + m.getBirthdate());
                            System.out.println("   - 이메일=" + m.getEmail());
                            System.out.println("   - 성별=" + m.getGender());
                        }
                    } catch (DuplicateEmailException e) {
                        System.out.println("⚠️ " + e.getMessage());
                    } catch (MemberNotFoundException e) {
                        System.out.println("⚠️ " + e.getMessage());
                    }
                    break;
                case "3":
                    List<Member> allMembers = memberController.getAllMembers();
                    if (allMembers.isEmpty()) {
                        System.out.println("ℹ️ 등록된 회원이 없습니다.");
                    }
                    else {
                        System.out.println("--- 📋 전체 회원 목록 📋 ---");
                        for (Member member : allMembers) {
                            System.out.println("👤 ID=" + member.getId() + ", 이름=" + member.getName());
                        }
                        System.out.println("--------------------------");
                    }
                    break;
                case "4": {
                    System.out.print("삭제할 회원 ID를 입력하세요: ");
                    try {
                        Long id = Long.parseLong(scanner.nextLine());
                        boolean deleted = memberController.deleteMember(id);
                        if (deleted) {
                            System.out.println("🗑️ 회원(ID=" + id + ")이 삭제(비활성화)되었습니다.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❌ 유효하지 않은 ID 형식입니다. 숫자를 입력해주세요.");
                    } catch (MemberNotFoundException e) {
                        System.out.println("⚠️ " + e.getMessage());
                    }
                    break;
                }
                case "5":
                    System.out.println("👋 서비스를 종료합니다. 안녕히 계세요!");
                    scanner.close();
                    return;
                default:
                    System.out.println("🚫 잘못된 메뉴 선택입니다. 다시 시도해주세요.");
            }
        }
    }
}

 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}