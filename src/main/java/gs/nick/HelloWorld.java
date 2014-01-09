package gs.nick;

import static spark.Spark.*;
import spark.*;

public class HelloWorld {

	public static void main(String[] args) {
		
		setPort(9090);
		
		get(new Route("/hello") {
			@Override
			public Object handle(Request req, Response res) {
				return "Hello World! <br>SessionID: " + req.session().id();
			}
		});
		
		get( new JsonTransformerRoute("/json") {
			@Override
			public Object handle(Request req, Response res) {
				MyData d = new MyData();
				
				d.setName("Nick");
				d.setMsg(req.session().id());
				d.setAge(29);
				
				res.header("content-type", "application/json");
				
				return d;
			}
		});
		
		get( new JsonTransformerRoute("/info") {
			@Override
			public Object handle( Request req, Response res ) {
				return req.queryMap();
			}
		});

	}

}
