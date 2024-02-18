package mogodb.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mogodb.common.MgRangeOperate;
import mogodb.util.JsonStrToMap;
import org.bson.Document;

import com.casia.isiteam.mogodb.common.MgDBSort;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

/**
 * mongoDB 查询方法 封装类
 * @author zhangqingzhou
 *
 */
public class MgSearchClient {

	private MongoClient mongoClient;
	/**
	 *  操作的数据库
	 */
	private String dbName;
	/**
	 * 要操作的 表/集合
	 */
	private String table;

	/**
	 * 查询条件
	 */
	private  BasicDBObject query;

	/**
	 * 排序 规则
	 */
	private BasicDBObject sort;

	/**
	 * 查询条件 名字个数
	 */
	private Long total = 0L;

	/**
	 * 起始索引
	 */
	private int start = 0;
	/**
	 * 返回条数
	 */
	private int limit = 10;

	/**
	 * 查询返回结果
	 */
	private List<Map<String,Object>> reposeList;

	/**
	 *
	 * @param dbName  数据库名
	 * @param table   表明/集合名
	 */
	public MgSearchClient(String dbName, String table){
		if(dbName == null || table == null){
			throw new IllegalArgumentException("dbName/tableName val must not null !!!");
		}
		this.dbName = dbName;
		this.table = table;
		this.mongoClient = MongoClientUtil.getMongoClient();
		reset();

	}

	/**
	 * 重置 结果
	 */
	public void reset(){
		this.query = new BasicDBObject();
		this.sort = null;
		this.total = 0L;
		this.start = 0;
		this.limit = 10;
		this.reposeList = null;
	}
	/**
	 *  配置 limit 用于分页 查询
	 * @param start
	 */
	public void setStart(int start){
		if(start < 0){
			throw new IllegalArgumentException("start must >= 0");
		}
		this.start = start;
	}
	/**
	 * 返回 条数
	 * @param limit
	 */
	public void setLimit(int limit){
		if(limit < 0){
			throw new IllegalArgumentException("limit must >= 0");
		}
		this.limit = limit;
	}


	/**
	 * 基础查询 类似于
	 *  where name = "zhang"
	 * @param filedName
	 * @param value
	 */
	public void addPrimaryQuery(String filedName, Object value){

		if(filedName != null && value != null){
			this.query.put(filedName, value);
		}

	}
	/**
	 *  范围 查询
	 * @param filedName
	 * @param startVal
	 * @param startOperate
	 * @param endVal
	 * @param endOperate
	 */
	public void addRangeQuery(String filedName, Object startVal, MgRangeOperate startOperate, Object endVal, MgRangeOperate endOperate ){

		if(filedName == null || filedName.equals("") || (startVal == null && endVal == null)){
			return;
		}
		HashMap<String, Object> range = new HashMap<String, Object>();
		if(startVal != null){
			range.put(startOperate.getVal(), startVal);
		}
		if(endVal != null){
			range.put(endOperate.getVal(), endVal);
		}
		this.query.put(filedName, range);
	}
	/**
	 *  in 查询
	 * @param fieldName
	 * @param values
	 */
	public void addInQuery(String fieldName, BasicDBList values){

		if(fieldName == null || values == null || values.size() == 0){
			return;
		}
		HashMap<String, Object> inQuery = new HashMap<String, Object>();
		inQuery.put(MgRangeOperate.IN.getVal(), values);
		this.query.put(fieldName, inQuery);
	}
	/**
	 *  not in 查询
	 * @param fieldName
	 * @param values
	 */
	public void addNotInQuery(String fieldName, BasicDBList values){

		if(fieldName == null || values == null || values.size() == 0){
			return;
		}
		HashMap<String, Object> inQuery = new HashMap<String, Object>();
		inQuery.put(MgRangeOperate.NIN.getVal(), values);
		this.query.put(fieldName, inQuery);
	}

	/**
	 * 模糊查询 类似于 like
	 * @param fieldName
	 * @param value
	 */
	public void addLikeQuery(String fieldName, Object value){
		if(fieldName == null || value == null ){
			return;
		}

		HashMap<String, Object> likeQuery = new HashMap<String, Object>();
		likeQuery.put(MgRangeOperate.REGEX.getVal(), value);

		this.query.put(fieldName, likeQuery);

	}

	public void addSort(String fieldName, MgDBSort sort){
		if(fieldName == null || sort == null ){
			return;
		}
		BasicDBObject sortIt = new BasicDBObject();
		sortIt.put(fieldName, sort.getVal());
		this.sort = sortIt;
	}

	public void execte(){
		MongoCollection<Document> collection = this.mongoClient.getDatabase(this.dbName).getCollection(this.table);
		this.total = collection.count(this.query);
		FindIterable<Document> iterable = null;
		// 全部
		if(this.query == null){
			iterable = collection.find();
		}else{
			// 不为空 ，则 追加条件
			iterable = collection.find(this.query);
		}
		// 增加排序条件
		if(this.sort != null){
			iterable.sort(this.sort);
		}
		// 增加分页 限定
		if(this.start == 0){
			iterable.limit(limit);
		}else{
			iterable.limit(limit).skip(this.start);
			//iterable.limit(paramInt)
		}
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		MongoCursor<Document> cursor = iterable.iterator();
		while (cursor.hasNext()) {
			Document user = cursor.next();
			String jsonString = user.toJson();
			Map<String, Object> jsonStrToMap = JsonStrToMap.jsonStrToMap(jsonString);
			list.add(jsonStrToMap);
		}
		this.reposeList = list;
	}

	/**
	 * 获取 总命中数
	 * @return
	 */
	public Long getTotal(){

		return this.total;
	}
	/**
	 * 获取查询结果
	 * @return
	 */
	public List<Map<String, Object>>  getReposeList(){
		if(this.reposeList == null){
			return null;
		}
		return this.reposeList;
	}
}
