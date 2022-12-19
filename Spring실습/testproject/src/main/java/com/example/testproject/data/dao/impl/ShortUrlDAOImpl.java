package com.example.testproject.data.dao.impl;

import com.example.testproject.data.dao.ShortUrlDAO;
import com.example.testproject.data.entity.ShortUrlEntity;
import com.example.testproject.data.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortUrlDAOImpl implements ShortUrlDAO {

    private final ShortUrlRepository shortUrlRepository;

    @Autowired
    ShortUrlDAOImpl(ShortUrlRepository shortUrlRepository){
        this.shortUrlRepository = shortUrlRepository;
    }

    @Override
    public ShortUrlEntity saveShortUrl(ShortUrlEntity shortUrl) {
        ShortUrlEntity foundShortUrl = shortUrlRepository.save(shortUrl);
        return foundShortUrl;
    }

    @Override
    public ShortUrlEntity getShortUrl(String originalUrl) {
        ShortUrlEntity foundShortUrl = shortUrlRepository.findByOrgUrl(originalUrl);
        return foundShortUrl;
    }

    @Override
    public ShortUrlEntity getOriginalUrl(String shortUrl) {
        ShortUrlEntity foundShortUrl = shortUrlRepository.findByUrl(shortUrl);
        return foundShortUrl;

    }

    @Override
    /** Naver APi를 호출하기때문에 업데이트 기능은 의미가 없다. */
    public ShortUrlEntity updateShortUrl(ShortUrlEntity newShortUrl) {

        ShortUrlEntity foundShortUrl = shortUrlRepository.findByOrgUrl(newShortUrl.getOrgUrl());

        foundShortUrl.setUrl(newShortUrl.getUrl());

        ShortUrlEntity savedShortUrl = shortUrlRepository.save(foundShortUrl);

        return savedShortUrl;
    }

    @Override
    public void deleteByShortUrl(String shortUrl) {
        ShortUrlEntity foundShortUrl = shortUrlRepository.findByUrl(shortUrl);
        shortUrlRepository.delete(foundShortUrl);
    }

    @Override
    public void deleteByOriginalUrl(String originalUrl) {
        ShortUrlEntity foundShortUrl = shortUrlRepository.findByOrgUrl(originalUrl);
        shortUrlRepository.delete(foundShortUrl);
    }
}
