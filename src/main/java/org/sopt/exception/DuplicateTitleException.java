package org.sopt.exception;

public class DuplicateTitleException extends RuntimeException {
    public DuplicateTitleException(String title) {
        super("이미 존재하는 제목입니다: " + title);
    }
}