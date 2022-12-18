package com.example.testproject.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ShortUrlResponseDTO {

    private String orgUrl;

    private String shortUrl;
}
