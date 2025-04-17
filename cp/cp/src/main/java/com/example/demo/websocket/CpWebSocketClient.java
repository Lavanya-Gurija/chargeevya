package com.example.demo.websocket;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.stereotype.Component;

@Component
public class CpWebSocketClient extends WebSocketClient {
    public CpWebSocketClient() throws URISyntaxException {
        super(new URI("ws://localhost:8080/ws/cp")); // CMS WebSocket endpoint
        connect();
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("Connected to CMS");
        send("{\"type\":\"status\", \"value\":\"CP online\"}");
    }

   

	public void onMessage(String message) {
        System.out.println("Received from CMS: " + message);
    }

    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Disconnected from CMS");
    }

    public void onError(Exception ex) {
        ex.printStackTrace();
    }
}

