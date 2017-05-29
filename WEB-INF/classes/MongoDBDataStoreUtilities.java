import java.util.HashMap;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoException;
import com.mongodb.AggregationOutput;

public class MongoDBDataStoreUtilities{
	static DBCollection myReviews;
	public static void getConnection(){
		MongoClient mongo;
		mongo=new MongoClient("localhost", 27017);
		DB db=mongo.getDB("CustomerReviews");
		myReviews=db.getCollection("Reviews");
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

	public static void getFiveMostLikedProducts(PrintWriter pw) throws MongoException, IOException{
		getConnection();

		DBObject match=new BasicDBObject("$match",new BasicDBObject("reviewRating","5"));

		DBObject groupFields=new BasicDBObject("_id",0);
		groupFields.put("_id","$productName");
		groupFields.put("count",new BasicDBObject("$sum",1));
		DBObject group=new BasicDBObject("$group",groupFields);

		DBObject projectFields=new BasicDBObject("_id",0);
		projectFields.put("product Name","$_id");
		projectFields.put("Number of rating 5","$count");
		DBObject project=new BasicDBObject("$project",projectFields);

		DBObject sort=new BasicDBObject();
		sort.put("Number of rating 5",-1);
		DBObject orderby=new BasicDBObject();
		orderby=new BasicDBObject("$sort",sort);
		DBObject limit=new BasicDBObject();
		limit=new BasicDBObject("$limit",5);

		AggregationOutput aggregate=myReviews.aggregate(match, group, project, orderby, limit);
		for (DBObject result: aggregate.results()){
			BasicDBObject bobj=(BasicDBObject)result;
			//lists.put(bobj.getString("product Name"), bobj.getInt("Number of rating 5"));
			pw.print("<tr><td>"+bobj.getString("product Name")+"</td><td>"+ bobj.getInt("Number of rating 5")+"</td></tr>");
		}

	}


	public static void getTopFiveZipcodes(PrintWriter pw) throws MongoException, IOException{
		getConnection();

		DBObject groupFields=new BasicDBObject("_id",0);
		groupFields.put("_id","$retailerZip");
		groupFields.put("count",new BasicDBObject("$sum",1));
		DBObject group=new BasicDBObject("$group",groupFields);

		DBObject projectFields=new BasicDBObject("_id",0);
		projectFields.put("Zip Code","$_id");
		projectFields.put("Number of product sold","$count");
		DBObject project=new BasicDBObject("$project",projectFields);

		DBObject sort=new BasicDBObject();
		sort.put("Number of product sold",-1);
		DBObject orderby=new BasicDBObject();
		orderby=new BasicDBObject("$sort",sort);
		DBObject limit=new BasicDBObject();
		limit=new BasicDBObject("$limit",5);

		AggregationOutput aggregate=myReviews.aggregate(group, project, orderby, limit);
		for (DBObject result: aggregate.results()){
			BasicDBObject bobj=(BasicDBObject)result;
			//lists.put(bobj.getString("product Name"), bobj.getInt("Number of rating 5"));
			pw.print("<tr><td>"+bobj.getString("Zip Code")+"</td><td>"+ bobj.getInt("Number of product sold")+"</td></tr>");
		}
	}

	public static void getTopFiveProducts(PrintWriter pw) throws MongoException, IOException{
		getConnection();

		DBObject groupFields=new BasicDBObject("_id",0);
		groupFields.put("_id","$productName");
		groupFields.put("count",new BasicDBObject("$sum",1));
		DBObject group=new BasicDBObject("$group",groupFields);

		DBObject projectFields=new BasicDBObject("_id",0);
		projectFields.put("Product Name","$_id");
		projectFields.put("Number of product sold","$count");
		DBObject project=new BasicDBObject("$project",projectFields);

		DBObject sort=new BasicDBObject();
		sort.put("Number of product sold",-1);
		DBObject orderby=new BasicDBObject();
		orderby=new BasicDBObject("$sort",sort);
		DBObject limit=new BasicDBObject();
		limit=new BasicDBObject("$limit",5);

		AggregationOutput aggregate=myReviews.aggregate(group, project, orderby, limit);
		for (DBObject result: aggregate.results()){
			BasicDBObject bobj=(BasicDBObject)result;
			//lists.put(bobj.getString("product Name"), bobj.getInt("Number of rating 5"));
			pw.print("<tr><td>"+bobj.getString("Product Name")+"</td><td>"+ bobj.getInt("Number of product sold")+"</td></tr>");
		}
	}
}