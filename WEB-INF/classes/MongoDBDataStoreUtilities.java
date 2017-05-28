import java.util.HashMap;
import java.util.ArrayList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.MongoException;

public class MongoDBDataStoreUtilities{
	static DBCollection myReviews;
	public static void getConnection(){
		MongoClient mongo;
		mongo=new MongoClient("localhost", 27017);
		DB db=mongo.getDB("CustomerReviews");
		myReviews=db.getCollection("myReviews");
	}

	public static HashMap<String, ArrayList<Review>> selectReview() throws MongoException{
		
		getConnection();
		
		HashMap<String, ArrayList<Review>> reviewHashMap=new HashMap<String, ArrayList<Review>>();
		DBCursor cursor=myReviews.find();
		while(cursor.hasNext()){
			BasicDBObject obj=(BasicDBObject)cursor.next();
			if (!reviewHashMap.containsKey(obj.getString("productName"))){
				ArrayList<Review> arr=new ArrayList<Review>();
				reviewHashMap.put(obj.getString("productName"),arr);
			}
			ArrayList<Review> listReview=reviewHashMap.get(obj.getString("productName"));
			Review review=new Review(obj.getString("productName"),obj.getString("userName"),obj.getString("retailerZip"),obj.getString("productType"), obj.getString("productMaker"), obj.getString("reviewRating"),obj.getString("reviewDate"),obj.getString("reviewText")); 
			listReview.add(review);
		}
		return reviewHashMap;
	}

	public static void insertReview(String productName,String userName, String retailerZip, String productType, String productMaker, String reviewRating,String reviewDate,String reviewText) throws MongoException{
		getConnection();
		BasicDBObject doc=new BasicDBObject("title", "myReviews").append("productName",productName).
									append("userName",userName).append("retailerZip",retailerZip).
									append("productType",productType).append("productMaker",productMaker).
									append("reviewRating",reviewRating).
									append("reviewDate",reviewDate).append("reviewText",reviewText);
		myReviews.insert(doc);
	}
/*
	public static HashMap<String, Integer> getFiveMostLikedProducts() throws MongoException{
		getConnection();

	}*/
}