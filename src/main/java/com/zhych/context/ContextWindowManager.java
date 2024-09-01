package com.zhych.context;

/**
 * Javadoc ContextWindowManager
 */
public interface ContextWindowManager {
    void addUserMessage(String message);
    void addAssistantMessage(String message);
    void addSystemMessage(String message);
    String getConversationHistoryAsJson();
    void setApiKey(String apiKey);
    void setModelName(String modelName);
}