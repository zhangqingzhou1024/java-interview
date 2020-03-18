package mogodb.search;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * 负责 获取 客户端
 *
 * @author zhangqingzhou
 */
public class MongoClientUtil {
    static final String ServerAddress = "47.104.247.12";
    static final int PORT = 27017;


    public static MongoClient getMongoClient() {
        MongoClient mongoClient = null;
        try {
            // 连接到 mongodb 服务
            mongoClient = new MongoClient(ServerAddress, PORT);
            System.out.println("Connect to mongodb successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return mongoClient;
    }

    /**
     * @param mongoClient
     * @param dbName      ���ݿ���
     * @return
     */
    public MongoDatabase getMongoDataBase(MongoClient mongoClient, String dbName) {
        MongoDatabase mongoDataBase = null;
        if (dbName == null || dbName.equals("")) {
            return null;
        }
        try {
            if (mongoClient != null) {
                // ���ӵ����ݿ�
                mongoDataBase = mongoClient.getDatabase(dbName);
                System.out.println("Connect to DataBase successfully");
            } else {
                throw new RuntimeException("MongoClient���ܹ�Ϊ��");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mongoDataBase;
    }

    public void closeMongoClient(MongoDatabase mongoDataBase, MongoClient mongoClient) {
        if (mongoDataBase != null) {
            mongoDataBase = null;
        }
        if (mongoClient != null) {
            mongoClient.close();
        }
        System.out.println("CloseMongoClient successfully");

    }
}
