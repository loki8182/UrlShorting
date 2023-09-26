package com.shorting.urlshorting.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.shorting.urlshorting.service.UrlService;

@Controller
@RestController
public class UrlController {

	@Autowired
	UrlService urlService;
/*To generate the qr code for the url */
@PostMapping("/generateUrl")
           public Object generateQR(@RequestParam String url) throws WriterException, IOException {
			return urlService.generateQrCode(url);
}
/*To shorten the url 
 * it will generate the random uuid for the long url */
	@PostMapping("/shortenUrl")
	public String shortenUrl(@RequestParam String url) throws WriterException, IOException {
		return urlService.saveUrl(url);

	}
/* to reterview the url for the uuid from the database*/
	@GetMapping("{id}/url")
	   public ResponseEntity<String>getUrl(@PathVariable String id) throws URISyntaxException{
		return urlService.getUrl(id);
	}
/*To generate the qr code with the id which is used to save and reterview  the url from database*/
	@GetMapping("{id}/downlaodQRCode")
	 public Object downlaodQR(@PathVariable String id) throws FileNotFoundException, WriterException, IOException {
		return urlService.downlaodQrCode(id);
	}
}
