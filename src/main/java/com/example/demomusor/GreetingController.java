package com.example.demomusor;

import com.example.demomusor.domain.Message;
import com.example.demomusor.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/greeting")
public class GreetingController {

    @Autowired
    private MessageRepository messageRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping
    public String main (Model model) {
        /*Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        System.out.println(messages);*/
        return "main";
    }

    @PostMapping("add")
    public String add(@RequestParam String text,
                      @RequestParam String tag,
                      @RequestParam("file") MultipartFile file,
                      Model model
                      ) throws IOException {
        //сохранение сообщения
        Message message = new Message(text, tag);

        if (file != null) {
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            message.setFilename(resultFilename);
        }
        messageRepository.save(message);

        //вывод сообщений
        Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);

        System.out.println(messages);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter,
                      Model model) {

        //вывод сообщений
        //Integer id = Integer.valueOf(filter);
        List<Message> messages = messageRepository.findByTag(filter);
        //Optional<Message> message = messageRepository.findById(id);
        model.addAttribute("messages", messages);

        //System.out.println(messages);

        return "main";
    }

    @GetMapping("/index")
    private String getIndex() {
        return "index";
    }

}