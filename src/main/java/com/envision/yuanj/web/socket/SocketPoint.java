package com.envision.yuanj.web.socket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@ServerEndpoint(value = "/websocket")
@Component
public class SocketPoint {

	private static int onlineCount = 0;

	private static CopyOnWriteArraySet<SocketPoint> clients = new CopyOnWriteArraySet<>();
	private Session session;
	//用来记录sessionId和该session进行绑定
	public static Map<String,Session> map = new HashMap<String, Session>();

//	@OnOpen
//	public void onOpen(Session session) {
//		this.session = session;
//		SocketPoint.clients.add(this);
//	}
	@OnOpen
	public void onOpen(Session session,@PathParam("nickname") String nickname) {
		this.session = session;
		map.put(session.getId(), session);
		clients.add(this);     //加入set中
		System.out.println("有新连接加入！当前在线人数为" + clients.size());
		this.session.getAsyncRemote().sendText("上线成功（频道号是"+session.getId()+"）");
	}

	@OnClose
	public void onClose() {
		SocketPoint.clients.remove(this);
	}

//	@OnMessage
//	public void onMessage(Session session, String message) {
//		for (SocketPoint socketPoint : clients) {
//			try {
//				socketPoint.sendMessage(message);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	@OnMessage
	public void onMessage(String message, Session session,@PathParam("nickname") String nickname) {
		ObjectMapper objectMapper = new ObjectMapper();
		SocketMsg socketMsg;
		try {
			socketMsg = objectMapper.readValue(message, SocketMsg.class);
			if(socketMsg.getType() == 1){
				nickname=socketMsg.getFromUser();
				//单聊.需要找到发送者和接受者.
				socketMsg.setFromUser(session.getId());//发送者.
				Session fromSession = map.get(socketMsg.getFromUser());
				Session toSession = map.get(socketMsg.getToUser());
				//发送给接受者.
				if(toSession != null){
					//发送给发送者.
					fromSession.getAsyncRemote().sendText(session.getId()+"："+socketMsg.getMsg());
					toSession.getAsyncRemote().sendText(session.getId()+"："+socketMsg.getMsg());
				}else{
					//发送给发送者.
					fromSession.getAsyncRemote().sendText("系统消息：对方不在线或者您输入的频道号不对");
				}
			}else{
				//群发消息
				for (SocketPoint socketPoint : clients) {
					try {
						socketPoint.sendMessage(session.getId()+"："+socketMsg.getMsg());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@OnError
	public void onError(Session session, Throwable error) {

	}

	public void sendMessage(String msg) throws IOException {
		this.session.getBasicRemote().sendText(msg);
	}

}
