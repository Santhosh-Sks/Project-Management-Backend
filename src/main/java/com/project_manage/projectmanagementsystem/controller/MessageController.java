package com.project_manage.projectmanagementsystem.controller;


import com.project_manage.projectmanagementsystem.model.Chat;
import com.project_manage.projectmanagementsystem.model.Message;
import com.project_manage.projectmanagementsystem.model.User;
import com.project_manage.projectmanagementsystem.request.CreateMessageRequest;
import com.project_manage.projectmanagementsystem.service.MessageService;
import com.project_manage.projectmanagementsystem.service.ProjectService;
import com.project_manage.projectmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;


    @PostMapping("/send")
    public ResponseEntity<Message>sendMessage(@RequestBody CreateMessageRequest request)
        throws Exception{
        User user = userService.findUserById(request.getSenderId());
        if(user==null){
            throw new Exception("User not found with if"+request.getSenderId());
        }
        Chat chats = projectService.getProjectById(request.getProjectId()).getChat();
        if(chats==null){
            throw new Exception("Chats not found");
        }
        Message sentMessage = messageService.sendMessage(request.getSenderId(),
                request.getProjectId(),request.getContent());
        return ResponseEntity.ok(sentMessage);
    }

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>>getMessageByChatId(@PathVariable Long projectId)
        throws Exception{
        List<Message> message = messageService.getMessageByProjectId(projectId);
        return ResponseEntity.ok(message);
    }

}
