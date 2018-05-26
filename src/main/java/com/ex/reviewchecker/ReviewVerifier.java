package com.ex.reviewchecker;

import org.springframework.stereotype.Service;

@Service
public class ReviewVerifier {
    public ReviewDto verify(ReviewDto reviewDto) {
        reviewDto.setAccepted(true);
        reviewDto.setVerified(true);
        return reviewDto;
    }
}
