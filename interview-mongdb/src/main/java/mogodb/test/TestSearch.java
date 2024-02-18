package mogodb.test;

import com.casia.isiteam.mogodb.common.MgDBSort;
import com.mongodb.BasicDBList;
import mogodb.common.MgRangeOperate;
import mogodb.search.MgSearchClient;

import java.util.List;
import java.util.Map;

public class TestSearch {

	public static void main(String[] args) {
		
		MgSearchClient client = new MgSearchClient("event", "event20180701");
		//client.addPrimaryQuery("_id", "9");
		client.addSort("_id", MgDBSort.DESC);
		client.addRangeQuery("_id", "5", MgRangeOperate.GTE, "81", MgRangeOperate.LT);
		BasicDBList values = new BasicDBList();  
	    values.add("6");  
	    values.add("7");  
	    
		client.addInQuery("_id", values);
		client.addLikeQuery("content", "�й�");
		client.setLimit(3);
		client.execte();
		
		Long total = client.getTotal();
		List<Map<String, Object>> reposeList = client.getReposeList();
		System.out.println("total:"+total);
		System.out.println(reposeList);
	}
}
