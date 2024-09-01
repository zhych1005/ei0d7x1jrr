package com.zhych.conversation;

import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.zhych.client.QwenClient;
import com.zhych.context.BaseContextWindowManager;
import org.apache.commons.lang3.StringUtils;

/**
 * Javadoc QwenContextWindowManager
 */
public class QwenContextWindowManager extends BaseContextWindowManager {

    protected boolean includeSystemPrompt;

    public QwenContextWindowManager(int windowSize, boolean includeSystemPrompt) {
        super(windowSize);
        this.includeSystemPrompt = includeSystemPrompt;
    }

    // 生成 Qwen 响应
    public String call(String userInput) throws ApiException, NoApiKeyException, InputRequiredException {
        if (StringUtils.isBlank(apiKey)) {
            throw new IllegalStateException("API key is not set.");
        }
        addUserMessage(userInput);
        String response = QwenClient.generateResponse(conversationHistory.history, apiKey, includeSystemPrompt, modelName);
        addAssistantMessage(response);
        return response;
    }
}