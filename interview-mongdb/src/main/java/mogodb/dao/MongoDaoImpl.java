package mogodb.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.List;

/**
 * ʵ�ֽӿ�
 *
 * @author iiip
 */
public class MongoDaoImpl {

    /**
     * �������
     */
    public boolean insert(MongoDatabase db, String table, Document document) {
        MongoCollection<Document> collection = db.getCollection(table);
        collection.insertOne(document);
        long count = collection.count(document);

        System.out.println("count: " + count);
        if (count == 1) {
            System.out.println("�ĵ�����ɹ�");
            return true;
        } else {
            System.out.println("�ĵ�����ɹ�");
            return false;
        }

    }

    /**
     * �������
     * _id �����ظ�
     * insert many
     *
     * @param db
     * @param table
     * @param documents
     */
    public boolean insertList(MongoDatabase db, String table, List<Document> documents) {

        MongoCollection<Document> collection = db.getCollection(table);
        long preCount = collection.count();
        collection.insertMany(documents);
        long nowCount = collection.count();
        System.out.println("���������: " + (nowCount - preCount));
        if ((nowCount - preCount) == documents.size()) {
            System.out.println("�ĵ��������ɹ�");
            return true;
        } else {
            System.out.println("�ĵ�������ʧ��");
            return false;
        }

    }

    /**
     * ����ɾ��
     */
    public boolean delete(MongoDatabase db, String table, BasicDBObject document) {
        MongoCollection<Document> collection = db.getCollection(table);
        DeleteResult deleteManyResult = collection.deleteMany(document);
        long deletedCount = deleteManyResult.getDeletedCount();
        System.out.println("ɾ��������: " + deletedCount);
        if (deletedCount > 0) {
            System.out.println("�ĵ�ɾ������ɹ�");
            return true;
        } else {
            System.out.println("�ĵ�ɾ�����ʧ��");
            return false;
        }
    }

    /**
     * ɾ��һ��
     *
     * @param db
     * @param table
     * @param document
     */
    public boolean deleteOne(MongoDatabase db, String table, BasicDBObject document) {
        MongoCollection<Document> collection = db.getCollection(table);
        DeleteResult deleteOneResult = collection.deleteOne(document);
        long deletedCount = deleteOneResult.getDeletedCount();
        System.out.println("ɾ��������: " + deletedCount);
        if (deletedCount == 1) {
            System.out.println("�ĵ�ɾ��һ���ɹ�");
            return true;
        } else {
            System.out.println("�ĵ�ɾ��һ��ʧ��");
            return false;
        }
    }

    /**
     * ��������
     */
    public boolean update(MongoDatabase db, String table, BasicDBObject whereDoc, BasicDBObject updateDoc) {
        MongoCollection<Document> collection = db.getCollection(table);
        UpdateResult updateManyResult = collection.updateMany(whereDoc, new Document("$set", updateDoc));
        long modifiedCount = updateManyResult.getModifiedCount();
        System.out.println("�޸ĵ�����: " + modifiedCount);

        if (modifiedCount > 0) {
            System.out.println("�ĵ����¶���ɹ�");
            return true;
        } else {
            System.out.println("�ĵ�����ʧ��");
            return false;
        }
    }


    /**
     * ����һ��
     * update one Data
     *
     * @param db
     * @param table
     * @param whereDoc
     * @param updateDoc
     */
    public boolean updateOne(MongoDatabase db, String table, BasicDBObject whereDoc, BasicDBObject updateDoc) {
        MongoCollection<Document> collection = db.getCollection(table);
        UpdateResult updateOneResult = collection.updateOne(whereDoc, new Document("$set", updateDoc));
        long modifiedCount = updateOneResult.getModifiedCount();
        System.out.println("�޸ĵ�����: " + modifiedCount);
        if (modifiedCount == 1) {
            System.out.println("�ĵ�����һ���ɹ�");
            return true;
        } else {
            System.out.println("�ĵ�����ʧ��");
            return false;
        }
    }

    /**
     * ������
     * create collection
     *
     * @param db
     * @param table
     */
    public void createCol(MongoDatabase db, String table) {
        db.createCollection(table);
        System.out.println("���ϴ����ɹ�");
    }

    /**
     * ɾ����
     * drop a collection
     *
     * @param db
     * @param table
     */
    public void dropCol(MongoDatabase db, String table) {
        db.getCollection(table).drop();
        System.out.println("����ɾ���ɹ�");

    }

}