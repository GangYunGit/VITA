package com.ssafy.vitauser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class AWSConfig {
	
	/**
	 * Key는 중요정보이기 때문에 properties 파일에 저장한 뒤 가져와 사용하는 방법이 좋습니다.
	 */
	private String iamAccessKey = "AKIASGA7T2WZ4OTATJM7"; // IAM Access Key
	private String iamSecretKey = "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"; // IAM Secret Key
	private String region = "ap-northeast-2"; // Bucket Region 
	
	@Bean
	public AmazonS3Client amazonS3Client() {
		BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(iamAccessKey, iamSecretKey);
		return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                                                             .withRegion(region)
                                                             .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                                                             .build();
	}

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(2000000000);
		return multipartResolver;
	}
}