package org.sopt.domain;

import java.util.Locale;


public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE");

    private final String code;
    Gender(String code) {
        this.code = code;
    }

    public static Gender from(String input) {
        String s = input.toString().trim().toUpperCase(Locale.ROOT);
        if (input == null) throw new IllegalArgumentException("성별을 입력해주세요.");
        try {
            return Gender.valueOf(input.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("성별은 MALE 또는 FEMALE 만 가능합니다.");
        }
    }
}
