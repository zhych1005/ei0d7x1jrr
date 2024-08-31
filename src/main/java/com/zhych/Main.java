package com.zhych;

import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.zhych.context.ContextWindowManagerFactory;
import com.zhych.conversation.QwenContextWindowManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int windowSize = 2;
        String modelName = "qwen-plus";
        String apiKey = "sk-cb99fe091e51421988efb9fb6e738e55";

        // 创建上下文管理器
        QwenContextWindowManager qwenManager = ContextWindowManagerFactory.createQwenManager(windowSize, apiKey, false, modelName);

        try (Scanner scanner = new Scanner(System.in)) {
            String userInput;
            System.out.println("Enter 'exit' to quit.");

            while (true) {
                System.out.print("You: ");
                userInput = scanner.nextLine();

                if ("exit".equalsIgnoreCase(userInput)) {
                    break;
                }

                // 生成 Qwen 响应
                String response = qwenManager.call(userInput);

                // 输出 Qwen 的响应
                System.out.println("Qwen: " + response);

                // 输出更新后的对话历史
                String updatedConversationJson = qwenManager.getConversationHistoryAsJson();
                System.out.println("Updated Conversation History (JSON): " + updatedConversationJson);
            }
        } catch (ApiException | NoApiKeyException | InputRequiredException e) {
            e.printStackTrace();
        }
    }
}
