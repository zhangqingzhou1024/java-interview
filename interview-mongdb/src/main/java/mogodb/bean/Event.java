package mogodb.bean;

import java.util.Map;

import org.bson.Document;

/**
 * 
 * @author zhangqingzhou
 *
 */
public class Event extends Document{
	
	
	public Event() {
		
	}


	public Event(String id, String name, Integer user_id, String content,
			String insert_time) {
		
		this.put("id", id);
		this.put("_id", id);
		this.put("user_id", user_id);
		this.put("content", content);
		this.put("insert_time", insert_time);
		
	}


	
	
	
	
	
}
