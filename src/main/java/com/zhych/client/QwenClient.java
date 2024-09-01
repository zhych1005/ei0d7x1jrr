package com.zhych.client;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.zhych.model.ConversationHistory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Javadoc qwen客户端
 */
public class QwenClient {

    protected static LinkedList<Message> messageHistory = new LinkedList<>();

    private static final Generation gen = new Generation();

    // 生成 Qwen 响应
    public static String generateResponse(List<ConversationHistory.Message> messages, String apiKey, boolean includeSystemPrompt, String modelName) throws ApiException, NoApiKeyException, InputRequiredException {
        // 创建生成参数并调用 API
        GenerationParam param = createGenerationParam(messages, apiKey, includeSystemPrompt, modelName);
        GenerationResult result = gen.call(param);
        // 获取模型响应
        return result.getOutput().getChoices().get(0).getMessage().getContent();
    }

    private static GenerationParam createGenerationParam(List<ConversationHistory.Message> history, String apiKey, boolean includeSystemPrompt, String modelName) {
        List<Message> messages = new ArrayList<>(messageHistory); // 使用滚动窗口内的消息
        // 判断对话历史是否为空
        history.forEach(item -> {
            Message message = new Message();
            message.setRole(item.getRole());
            message.setContent(item.getContent());
            messages.add(message);
        });
        GenerationParam paramBuilder = GenerationParam.builder()
                .model(modelName)
                .messages(messages)
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .apiKey(apiKey)
                .topK(50)
                .temperature(0.1f)
                .topP(0.8)
                .seed(1234)
                .build();

        if (includeSystemPrompt) {
            // 添加系统提示词消息
            Message systemMessage = Message.builder()
                    .role(Role.SYSTEM.getValue())
                    .content("You are a helpful assistant.")
                    .build();
            // 系统提示词作为对话历史的第一条消息
            List<Message> messagesWithSystemPrompt = new ArrayList<>(messageHistory);
            messagesWithSystemPrompt.add(0, systemMessage);
            paramBuilder.setMessages(messagesWithSystemPrompt);
        }
        return paramBuilder;
    }
}