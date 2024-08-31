package com.zhych.conversation;

import com.zhych.context.BaseContextWindowManager;

public class OpenAIContextWindowManager extends BaseContextWindowManager {

    protected boolean includeSystemPrompt;
    public OpenAIContextWindowManager(int windowSize, boolean includeSystemPrompt) {
        super(windowSize);
        this.includeSystemPrompt = includeSystemPrompt;
    }
    // 可以根据需要添加 OpenAI 特定的实现
}