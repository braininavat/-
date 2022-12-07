package com.example.testproject.controller;

import com.example.testproject.dto.MemberDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {

    @PostMapping(value = "/hello")
    public String posthello(){
        return "hello post";
    }



    @PostMapping(value = "/member")
    public String postMember(@RequestBody Map<String,Object> postData) {
        StringBuilder sb = new StringBuilder();
        postData.entrySet().forEach(map ->
        {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });
        return sb.toString();
    }
    //Json 형태로 전달가능
    //RequestBody 를 붙이지 않으면 Null이 들어감

    @PostMapping(value = "/member2")
    public String postMemberDTO(@RequestBody MemberDTO memberDTO){
        return memberDTO.toString();
    }
    //Json으로 Post시 멤버 순서 상관 없고 전달받는 key값이 없으면 Null, DTO에 대응하지 않는 key은 무시

}
