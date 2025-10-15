package org.sopt.exception;

public class MemberNotFoundException extends MemberException {
    public MemberNotFoundException(Long memberId) {
        super("해당 ID의 회원을 찾을 수 없습니다. (ID: " + memberId + ")");
    }
}
