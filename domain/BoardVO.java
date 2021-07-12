package com.mincheol.fullstack.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
// @JsonInclude(JsonInclude.Include.NON_NULL)은 JSON을 만들때 property가 null이면 만들지 말라는 의미이다.
// @Data는 lombok 어노테이션으로, Setter 와 Getter를 자동으로 생성해준다.

public class BoardVO {
    // request, response 시 JSON으로 데이터를 주고 받는데 Java에는 JSON이라는 데이터 타입이 없다.
    // 따라서 JSON과 매핑할 수 있는 객체를 생성하는데 그것을 VO 혹은 DTO라고 한다.
    // VO는 Virtual Object, DTO는 Data Transfer Object라고 모두 데이터를 담기 위한 객체이다.
    // JSON과 Java 객체 간의 매핑은 Jackson Mapper라는 3rd-party 라이브러리가 수행해준다.

    private Integer id;
    private String title;
    private String content;
    private String created;
    private String updated;
}