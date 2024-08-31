package com.zhych.model;

import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.List;

public class ConversationHistory {
    public final List<Message> history = new ArrayList<>();

    public void addMessage(String role, String content, int windowSize) {
        history.add(new Message(role, content));
        if (history.size() >= windowSize * 2) {
            history.subList(0, history.size() - windowSize * 2).clear();
        }
    }

    public String toJson() {
        return JSON.toJSONString(history);
    }

    public static class Message {
        private String role;
        private String content;

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }

        // 必须提供无参构造函数以供 FastJSON 反序列化
        public Message() {}

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
