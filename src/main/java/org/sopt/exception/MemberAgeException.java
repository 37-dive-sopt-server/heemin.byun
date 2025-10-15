package org.sopt.exception;

public class MemberAgeException extends MemberException{
    public MemberAgeException(int age) {
        super("회원가입은 만 20세 이상만 가능합니다. (현재 나이: " + age + "세)");
    }
}
