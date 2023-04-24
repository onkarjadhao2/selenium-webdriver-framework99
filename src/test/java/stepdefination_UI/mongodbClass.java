package stepdefination_UI;

import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import org.testng.Assert;

public class mongodbClass {
	public static void main(String args[]) {

			// Connecting to the mongoDB instance
			MongoClient mongoClient = new MongoClient("localhost", 27017);

			// Selecting the database
			DB db = mongoClient.getDB("selenium");

			// Selecting the collection
			DBCollection dbCollection = db.getCollection("employee");

			// Setting search query with the required key-value pair
			BasicDBObject searchQuery = new BasicDBObject();
			
			//for the specific data
			//searchQuery.put("emp name", "Rahul Sharma");

			// DBCursor with the find query result
			DBCursor cursor = dbCollection.find();

			while(cursor.hasNext())
			{
			    int i=1;
			    System.out.println(cursor.next());
			    i++;
			}


	}
}
