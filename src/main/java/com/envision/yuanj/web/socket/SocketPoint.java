package com.envision.yuanj.web.socket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

@ServerEndpoint(value = "/websocket")
@Component
public class SocketPoint {

	private static int onlineCount = 0;

	private static CopyOnWriteArraySet<SocketPoint> clients = new CopyOnWriteArraySet<>();
	private Session session;

	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		SocketPoint.clients.add(this);

	}

	@OnClose
	public void onClose() {
		SocketPoint.clients.remove(this);
	}

	@OnMessage
	public void onMessage(Session session, String message) {
		for (SocketPoint socketPoint : clients) {
			try {
				socketPoint.sendMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@OnError
	public void onError(Session session, Throwable error) {

	}

	public void sendMessage(String msg) throws IOException {
		this.session.getBasicRemote().sendText(msg);
	}

}
