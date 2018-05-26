package com.ex.reviewchecker.messaging;

import java.io.IOException;

public interface Receiver {
    void receiveMessage(String jsonReviewDto) throws IOException, InterruptedException;
}
