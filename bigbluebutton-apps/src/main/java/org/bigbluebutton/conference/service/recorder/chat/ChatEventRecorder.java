package org.bigbluebutton.conference.service.recorder.chat;

import java.util.HashMap;
import java.util.Hashtable;

import org.bigbluebutton.conference.BigBlueButtonUtils;
import org.bigbluebutton.conference.service.chat.IChatRoomListener;
import org.bigbluebutton.conference.service.recorder.RecorderApplication;
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;

public class ChatEventRecorder implements IChatRoomListener {
	private static Logger log = Red5LoggerFactory.getLogger( ChatEventRecorder.class, "bigbluebutton" );
	
	private final RecorderApplication recorder;
	private final String session;
	
	String name = "RECORDER:CHAT";
	
	public ChatEventRecorder(String session, RecorderApplication recorder){
		this.recorder = recorder;
		this.session = session;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public void newChatMessage(String message) {
		recorder.record(session, getMapAttributes(message));		
	}
	
	private HashMap<String,String> getMapAttributes(String message){
		HashMap<String,String> map = new HashMap<String, String>();
		String[] chat_attribs = message.trim().split("\\|",-1);
		map.put("timestamp", Long.toString(System.currentTimeMillis()));
		map.put("module", "chat");
		map.put("event", "new_message");
		map.put("user", chat_attribs[1]);
		map.put("text", chat_attribs[0]);
		map.put("language", chat_attribs[4]);
		map.put("color", chat_attribs[2]);
		
		return map;
	}
	
	/*
	 * <font color="#0"><b>[markos - 12:06:38 PM]</b> heyyyyy </font><br/>
	 * 
	 * */
	private String parseChatToJSON(String message){
		String json="{ ";
		String[] chat_attribs=message.trim().split("\\|",-1);
		
		json+="\"module\":\"chat\", ";
		json+="\"event\":\"new_message\", ";
		json+="\"user\":\""+chat_attribs[1]+"\", ";
		json+="\"text\":\""+chat_attribs[0]+"\", ";
		json+="\"language\":\""+chat_attribs[4]+"\", ";
		json+="\"color\":\""+chat_attribs[2]+"\" }";
		
		return json;
	}
	
	/********************************
	 *  Testing performance XML over the playback client
	 *  chat message format: <message>|<user>|<color>|<time>|<language>
	 * ****************************/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String parseChatToXML(String message){
		String[] chat_attribs=message.trim().split("\\|",-1);
		
		Hashtable keyvalues=new Hashtable();
		keyvalues.put("event", "new_message");
		keyvalues.put("message", chat_attribs[0]);
		keyvalues.put("user", chat_attribs[1]);
		keyvalues.put("color", chat_attribs[2]);
		keyvalues.put("language", chat_attribs[4]);
		
		String xmlstr=BigBlueButtonUtils.parseEventsToXML("chat", keyvalues);
		return xmlstr;
	}

}