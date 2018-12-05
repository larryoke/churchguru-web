package com.laotek.churchguru.web.server.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController {

	private String metaHttp = "<meta http-equiv=\"refresh\" content=\"0; url=/%s\" />";

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String publicHome() {
		return String.format(metaHttp, "admin.htm");
	}

	@RequestMapping(value = "/upcoming", method = RequestMethod.GET)
	public String upcoming() {
		return "publicHome";
	}

	@RequestMapping(value = "/mobile", method = RequestMethod.GET)
	@ResponseBody
	public String mobile() {
		return String.format(metaHttp, "mobi.htm");
	}

	@RequestMapping(value = "/cancelAndCloseIOSBrowserForPaypalMobile", method = RequestMethod.GET)
	@ResponseBody
	public String cancelAndCloseIOSBrowserForPaypalMobile() {
		return String.format(metaHttp, "cancelAndCloseIOSBrowserForPaypalMobile.htm");
	}

	@RequestMapping(value = "/thanksAndCloseIOSBrowserForPaypalMobile", method = RequestMethod.GET)
	@ResponseBody
	public String thanksAndCloseIOSBrowserForPaypalMobile() {
		return String.format(metaHttp, "thanksAndCloseIOSBrowserForPaypalMobile.htm");
	}

	@RequestMapping(value = "/cancelAndCloseAndroidBrowserForPaypalMobile", method = RequestMethod.GET)
	@ResponseBody
	public String cancelAndCloseAndroidBrowserForPaypalMobile() {
		return String.format(metaHttp, "cancelAndCloseAndroidBrowserForPaypalMobile.htm");
	}

	@RequestMapping(value = "/thanksAndCloseAndroidBrowserForPaypalMobile", method = RequestMethod.GET)
	@ResponseBody
	public String thanksAndCloseAndroidBrowserForPaypalMobile() {
		return String.format(metaHttp, "thanksAndCloseAndroidBrowserForPaypalMobile.htm");
	}

	@RequestMapping(value = "/privacyPolicy", method = RequestMethod.GET)
	@ResponseBody
	public String privacyPolicy() {
		return String.format(metaHttp, "privacyPolicy.htm");
	}

	@RequestMapping(value = "/communicator", method = RequestMethod.GET)
	@ResponseBody
	public String communicator() {
		return String.format(metaHttp, "admin.htm");
	}

	@RequestMapping(value = "/help", method = RequestMethod.GET)
	public String help() {
		return "helpContents";
	}

	@RequestMapping(value = "/videos", method = RequestMethod.GET)
	public String videos() {
		return "helpContents";
	}

	@RequestMapping(value = "/errorCode500", method = RequestMethod.GET)
	public String errorCode500() {
		return "errorCode500";
	}

	@RequestMapping(value = "/googlea4c0ba495bf9176f.html", method = RequestMethod.GET)
	@ResponseBody
	public String google() {
		return "google-site-verification: googlea4c0ba495bf9176f.html";
	}

	@RequestMapping(value = "/help/{helpfile}", method = RequestMethod.GET)
	public String help(@PathVariable String helpfile) {
		return helpfile;
	}
}
