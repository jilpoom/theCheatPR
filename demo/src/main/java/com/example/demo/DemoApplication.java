package com.example.demo;

import com.example.restservice.Greeting;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
@RestController
public class DemoApplication {

	private static final String template = "Hello, %s";
	private final AtomicLong counter = new AtomicLong();


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name){
		return String.format("<h1>Hello %s</h1>", name);
	}

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name){
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}


	// api 가져오기
	@GetMapping("/apis")
	public String apis(Model model) throws IOException {
		URL url = new URL("https://jsonplaceholder.typicode.com/");
		StringBuilder sb = new StringBuilder();
		InputStream in = url.openStream();
		BufferedReader bf = new BufferedReader(new InputStreamReader(in, "utf-8"));
		String line = "";
		while((line = bf.readLine()) != null){
			sb.append(line);
		}

		String data = sb.toString();


		return "<h1>API test</h1>";
	}

}
