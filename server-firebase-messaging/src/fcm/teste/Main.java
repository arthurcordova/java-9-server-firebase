package fcm.teste;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

import java.io.IOException;
import java.net.URI;

public class Main {

    public static void main(String args[]){
        try {
            fcm();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void fcm() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        /**
         * to: device token
         * priority: level of priority
         */
        String jsonMessage = "{ \"to\": \"TOKEN DEVICE\", \"priority\": \"high\", \"notification\": { \"title\": \"Java 9 Teste\", \"body\": \"Message from java 9 server.\" } }";
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://fcm.googleapis.com/fcm/send"))
                .header("Content-type","application/json")
                .header("Authorization", "key=CHAVE DO FIREBASE")
                .POST(HttpRequest.BodyProcessor.fromString(jsonMessage))
                .build();

        HttpResponse response = client.send(request, HttpResponse.BodyHandler.asString());
        System.out.println("Response code: " + response.statusCode());
        System.out.println("Response body: " + response.body().toString());
    }
}
