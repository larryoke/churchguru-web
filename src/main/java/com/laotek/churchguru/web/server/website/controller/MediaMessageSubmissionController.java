package com.laotek.churchguru.web.server.website.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/estore")
public class MediaMessageSubmissionController {

    @RequestMapping(value = "/submit/message", method = RequestMethod.POST)
    @ResponseBody
    public String processMediaSubmission(HttpServletRequest request) {
	for (Enumeration<String> e = request.getAttributeNames(); e
		.hasMoreElements();) {
	    String name = e.nextElement();
	    System.out.println(name);
	    System.out.println(request.getParameter(name));
	}
	return "thanks";
    }
}
