package com.mincheol.fullstack.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

// @Data는 Setter, Getter를 자동으로 만들어준다.
// @NoArgsConstructor는 디폴트 생성자를 만들어준다.
// @AllArgsConstructor는 모든 파라미터를 가진 생성자를 만들어준다.

public class ResultVO {
    // insert, update 시에는 쿼리 결과만을 리턴할 필요가 있으므로 ResultVO 객체를 추가한다.
    private int code;
    private String message;
    // Control + F12를 눌러 File Structure를 확인해보자.
    // 생성자: ResultVO(int, String), ResultVO()
    // Setter: setMesssage(String), setCode(int)
    // Getter: getMesssage(), getCode()
    // 기타: toString()
    // 위와 같은 함수들이 자동으로 생성되었음을 알 수 있다.
}