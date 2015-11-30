import com.mongodb.*;


/**
 * Created by Administrator on 2015/11/23.
 */
public class QueryMongo {
    public static void main(String[] args) {
        Mongo mongo=new Mongo("10.0.165.13",30000);

        DB db=mongo.getDB("jzyx");
        DBCollection coll=db.getCollection("adTag_ciphertext");
        BasicDBObject find= (BasicDBObject) coll.findOne();
        System.out.println(find.toString());
    }
}
