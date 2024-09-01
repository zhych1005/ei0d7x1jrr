package com.zhych.context;

import com.zhych.conversation.OpenAIContextWindowManager;
import com.zhych.conversation.QwenContextWindowManager;

/**
 * Javadoc 上下文窗口管理器工厂类
 */
public class ContextWindowManagerFactory {
    /**
     * 根据模型类型、窗口大小、API 密钥和是否包括系统提示词来创建上下文窗口管理器。
     * @param windowSize             上下文窗口大小
     * @param apiKey                 API 密钥
     * @param includeSystemPrompt    是否包括系统提示词
     * @return 创建的上下文窗口管理器实例
     */
    public static QwenContextWindowManager createQwenManager(int windowSize, String apiKey, boolean includeSystemPrompt, String modelName) {
        QwenContextWindowManager qwenManager = new QwenContextWindowManager(windowSize, includeSystemPrompt);
        qwenManager.setApiKey(apiKey);
        qwenManager.setModelName(modelName);
        return qwenManager;
    }

    public static OpenAIContextWindowManager createOpenAIManager(int windowSize, String apiKey, boolean includeSystemPrompt) {
        OpenAIContextWindowManager openAIManager = new OpenAIContextWindowManager(windowSize, includeSystemPrompt);
        openAIManager.setApiKey(apiKey);
        return openAIManager;
    }
}