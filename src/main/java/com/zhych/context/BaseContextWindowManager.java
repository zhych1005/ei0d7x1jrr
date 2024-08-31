package com.zhych.context;

import com.zhych.model.ConversationHistory;

public abstract class BaseContextWindowManager implements ContextWindowManager {
    public final ConversationHistory conversationHistory = new ConversationHistory();
    protected String apiKey;
    protected int windowSize;
    protected String modelName;

    protected BaseContextWindowManager(int windowSize) {
        this.windowSize = windowSize;
    }

    @Override
    public void addUserMessage(String message) {
        conversationHistory.addMessage("user", message, windowSize);
    }

    @Override
    public void addAssistantMessage(String message) {
        conversationHistory.addMessage("assistant", message, windowSize);
    }

    @Override
    public void addSystemMessage(String message) {
        conversationHistory.addMessage("system", message, windowSize);
    }

    @Override
    public String getConversationHistoryAsJson() {
        return conversationHistory.toJson();
    }

    @Override
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
