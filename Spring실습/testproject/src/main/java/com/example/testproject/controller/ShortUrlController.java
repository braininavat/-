package com.example.testproject.controller;


import com.example.testproject.data.dto.ShortUrlResponseDTO;
import com.example.testproject.service.ShortUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/short-url")
public class ShortUrlController {

    private final Logger LOGGER = LoggerFactory.getLogger(ShortUrlController.class);

    @Value("${testproject.short.url.id}")
    //application.properties 에 정의된 값을 불러온다
    private String CLIENT_ID;

    @Value("${testproject.short.url.secret}")
    private String CLIENT_SECRET;

    ShortUrlService shortUrlService;

    @Autowired
    ShortUrlController (ShortUrlService shortUrlService){
        this.shortUrlService = shortUrlService;
    }

    @PostMapping()
    public ShortUrlResponseDTO  generateShortUrl(String originalUrl){
        LOGGER.info("[generateShortUrl()] perform API. CLIENT_ID : {}, CLIENT_SECRET : {}", CLIENT_ID,CLIENT_SECRET);

        return shortUrlService.generateShortUrl(CLIENT_ID,CLIENT_SECRET,originalUrl);
    }

    @GetMapping()
    public ShortUrlResponseDTO getShortUrl(String originalUrl){
        ShortUrlResponseDTO shortUrlResponseDTO = new ShortUrlResponseDTO("qq123","qq123");

        return shortUrlService.getShortUrl(CLIENT_ID,CLIENT_SECRET,originalUrl);
    }

    @PutMapping("/")
    public ShortUrlResponseDTO updateShortUrl(String originalUrl){return null;}

    @DeleteMapping("/")
    public ResponseEntity<String> deleteShortUrl(String url){
        try{
            shortUrlService.deleteByShortUrl(url);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body("url 삭제완료");
    }


}
