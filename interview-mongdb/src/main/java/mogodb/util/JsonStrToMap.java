package mogodb.util;


import java.util.HashMap;
import java.util.Map;

import com.mongodb.util.JSON;
public class JsonStrToMap {
	


	    /**
	     * json �ַ���ת��Ϊmap��ʽ
	     * @param jsonString
	     * @return
	     */

	    @SuppressWarnings("unchecked")
		public static Map<String, Object> jsonStrToMap(String jsonString) {
	        Object parseObj = JSON.parse(jsonString); // �����л� ��json ת��Ϊ����
	        Map<String, Object> map = (HashMap<String, Object>) parseObj; // �Ѷ���ת��Ϊmap
	        return map;
	    }

}
