package org.sopt.exception;

public class DuplicateEmailException extends MemberException {
    public DuplicateEmailException(String email) {
        super("이미 사용 중인 이메일입니다: " + email);
    }
}
