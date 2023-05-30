package dasniko.keycloak.authenticator.gateway;

import java.net.http.HttpClient;
import java.util.Map;

// import com.squareup.okhttp.MediaType;
// import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.*;

import software.amazon.awssdk.core.Response;
import software.amazon.awssdk.services.sns.SnsClient;

import java.io.*;
// import okhttp3.*;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
public class CustomSmsService implements SmsService {

	private static final SnsClient sns = SnsClient.create();

	private final String senderId;

	CustomSmsService(Map<String, String> config) {
		senderId = config.get("senderId");
	}

	/*
	 * @Override
	 * public void send(String phoneNumber, String message) {
	 * Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
	 * messageAttributes.put("AWS.SNS.SMS.SenderID",
	 * MessageAttributeValue.builder().stringValue(senderId).dataType("String").
	 * build());
	 * messageAttributes.put("AWS.SNS.SMS.SMSType",
	 * MessageAttributeValue.builder().stringValue("Transactional").dataType(
	 * "String").build());
	 * 
	 * sns.publish(builder -> builder
	 * .message(message)
	 * .phoneNumber(phoneNumber)
	 * .messageAttributes(messageAttributes));
	 * }
	 */

	@Override
	public void send(String phoneNumber, String message) {
		OkHttpClient client = new OkHttpClient().newBuilder()
      .build();
    MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
    RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
      .addFormDataPart("receptor","09171245402")
      .addFormDataPart("template","test6")
      .addFormDataPart("type","1")
      .addFormDataPart("param1","5591")
      .addFormDataPart("param2","WADQG/ctsu+")
      .build();
    Request request = new Request.Builder()
      .url("https://api.ghasedak.me/v2/verification/send/simple")
      .method("POST", body)
      .addHeader("content-type", "application/x-www-form-urlencoded")
      .addHeader("apikey", "236e4d1074db2b32a81dd266325db6ee12e8afbe4db6490ebdf957f1d974ed57")
      .addHeader("cache-control", "no-cache")
      .build();
    Response response = client.newCall(request).execute();
    System.out.println(response.body().string());
	}

}
