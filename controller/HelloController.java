package com.mincheol.fullstack.controller;

import com.mincheol.fullstack.domain.ResultVO;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    // @RequestMapping이 모든 HTTP 메서드를 허용해준다.
    @RequestMapping("/hello")
    public String hello() {
        return "Hello test";
    }

    // @GetMapping은 GET 메서드만 허용한다.
    @GetMapping("/hello2")
    public String hello2() {
        return "Hello GET test";
    }

    // GET은 request할 때, Header만 존재하고 Body가 없다.
    // 따라서 Query Parameter를 사용하여 값을 넘겨준다.
    // URL 뒤 ? 다음에 key=value&key=value... 이런식으로 key=value를 &로 연결해서 데이터를 보낸다.
    @GetMapping("/hello3/1")
    public String hello3_1(@RequestParam("name") String name) {
        return "Hello " + name;
    }

    // GET 메서드와 URI Parameter를 사용하는 방법이다.
    // name이라는 key에 gildong이라는 value를 보내기 위해 /gildong으로 보내고 /{name}으로 받는다.
    @GetMapping("/hello3/2/{name}")
    public String hello3_2(@PathVariable String name) {
        // URI Parameter는 @PathVariable Annotation을 사용하여 받는다.
        return "Hello " + name;
    }

    // Post는 body에 데이터를 담아서 보낼 수 있어서 보통 body로 데이터를 보낸다.
    // 그렇다고 Query Parameter를 사용하여 데이터를 보낼 수 없는 것은 아니다.
    @PostMapping("/hello3/3")
    public String hello3_3(@RequestParam("name") String name) {
        return "Hello " + name;
    }

    // input 박스에 값을 넣고 submit 버튼을 누르면 POST 방식으로 전송되고 input에 담긴 데이터들은 x-www-form-urlencoded 방식으로 body에 담겨 전송된다.
    @PostMapping("/hello4")
    public String hello4(@RequestParam String name) {
        return "Hello " + name;
    }

    // JSON 객체를 반환하도록 한다.
    @PostMapping("/hello5")
    public ResultVO hello5(@RequestParam("name") String name) {
        ResultVO result = new ResultVO(0, name);
        // return 타입이 ResultVO 객체라는 것에 주목하자.
        // 객체를 반환하면 Spring Framework의 Jackson Mapper 라이브러리가 자바 객체를 JSON으로 변환해주기 때문에 단순히 자바 객체만 반환하면 된다.
        return result;
    }

    // JSON으로 데이터를 보내는 경우이다.
    @PostMapping("/hello6")
    // request의 body를 받아서 그것을 ResultVO 객체로 변환하라는 의미이다.
    public ResultVO hello6(@RequestBody ResultVO result) {
        // 따라서 body 부분에 JSON 데이터를 보내면 자바 객체로 변환된다.
        // 이것도 위에서 언급한 Jackson Mapper가 수행해준다.
        return result;
    }
}