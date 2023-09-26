package com.shorting.urlshorting.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.shorting.urlshorting.module.UrlShorting;
import com.shorting.urlshorting.repository.UrlRepository;
import io.grpc.internal.*;
@Service
public class UrlServiceImpl implements UrlService {
	 private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCode.png";
@Autowired
UrlRepository urlRepository;


	@Override
	public String saveUrl(String url) {
		String Id= UUID.randomUUID().toString().substring(0, 5);
	   UrlShorting urlShorting = new UrlShorting();
	   urlShorting.setId(Id);
	   urlShorting.setUrl(url);
	   urlRepository.save(urlShorting);
	   return Id;
	}

	@Override
	public ResponseEntity<String> getUrl(String Id) throws URISyntaxException {
		if(urlRepository.existsById(Id)) {
			UrlShorting urlShorting = urlRepository.findById(Id).get();
			 String url = urlShorting.getUrl();
			 URI externalUri = new URI(url);
		        HttpHeaders httpHeaders = new HttpHeaders();
		        httpHeaders.setLocation(externalUri);

		        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
			
		}else {
			return ResponseEntity.badRequest().body("no data found");
		}
		
	}

	@Override
	public  Object generateQrCode(String url) throws WriterException, IOException {
	      Object path=  UrlServiceImpl.generateQRCodeImage(url,250,250,QR_CODE_IMAGE_PATH);
	        File file = new File(path.toString());
	        final HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.IMAGE_JPEG);
	        InputStream finput = null;
	            finput = new FileInputStream(file);
	            return new ResponseEntity<byte[]>(IoUtils.toByteArray(finput),headers,HttpStatus.ACCEPTED);
	        
	       
	}
public  static Object  generateQRCodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
		return path;

    }

@Override
public Object downlaodQrCode(String id) throws WriterException, IOException {
	if(urlRepository.existsById(id)) {
		UrlShorting urlShorting = urlRepository.findById(id).get();
		 String url = urlShorting.getUrl();
		 Object path=  UrlServiceImpl.generateQRCodeImage(url,250,250,QR_CODE_IMAGE_PATH);
	      File file = new File(path.toString());
	      final HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(MediaType.IMAGE_JPEG);
	      InputStream finput = null;
	          finput = new FileInputStream(file);
	          return new ResponseEntity<byte[]>(IOUtils.toByteArray(finput),headers,HttpStatus.CREATED);
	}else {
		return ResponseEntity.badRequest().body("no data found");
	}
	 
      
}
 



}
