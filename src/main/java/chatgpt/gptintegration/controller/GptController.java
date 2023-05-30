package chatgpt.gptintegration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import chatgpt.gptintegration.dto.Request;
import chatgpt.gptintegration.dto.Response;

@RestController
@RequestMapping("/bot")
public class GptController {

    @Value("${openai.model}")
    private String model;

    @Value("${open.api.url}")
    private String apiURL;

    @Autowired
    private RestTemplate template;

    @GetMapping("/chat")
    public String chat(@RequestParam("prompt") String prompt) {
        Request request = new Request(model, prompt);

        Response response = template.postForObject(apiURL, request, Response.class);
        return response.getChoices().get(0).getMessage().getContent();

    }
}
