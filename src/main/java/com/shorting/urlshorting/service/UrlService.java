package com.shorting.urlshorting.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;

import com.google.zxing.WriterException;

public interface UrlService {
 public String saveUrl(String url);
 public ResponseEntity<String>getUrl(String Id) throws URISyntaxException;
 public Object generateQrCode(String url) throws WriterException, IOException;
 public Object downlaodQrCode(String url) throws FileNotFoundException, WriterException, IOException; 
}
