package gs.nick;

import static spark.Spark.*;
import spark.*;

public class HelloWorld {

	public static void main(String[] args) {

		setPort(9090);

		get(new Route("/") {
			@Override
			public Object handle(Request req, Response res) {
				return "<ol><li>/hello</li><li>/json</li><li>/info</li>";
			}
		});

		get(new Route("/hello") {
			@Override
			public Object handle(Request req, Response res) {
				return "Hello World! <br>SessionID: " + req.session().id();
			}
		});

		get(new JsonTransformerRoute("/json") {
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

		get(new JsonTransformerRoute("/info") {
			@Override
			public Object handle(Request req, Response res) {
				return req.queryMap();
			}
		});

		get(new Route("/fbaas") {
			@Override
			public Object handle(Request req, Response res) {
				QueryParamsMap query = req.queryMap();
				int start, stop;
				if( req.queryParams("start") == null || req.queryParams("stop") == null ) {
					return "must define start and stop";
				}
				start = new Integer( req.queryParams("start"));
				stop = new Integer( req.queryParams("stop"));
				String result = "";
				if (start < stop) {
					while (start < stop) {
						result = result + this.fizzBuzz(start) + " ";
						start++;
					}
				} else {
					result = "condition not met: start < stop";
				}
				return result;
			}

			private String fizzBuzz(int i) {
				if (i % 5 == 0 && i % 3 == 0) {
					return "FizzBuzz";
				} else if (i % 3 == 0) {
					return "Fizz";
				} else if (i % 5 == 0) {
					return "Buzz";
				}
				Integer in = new Integer(i);
				return in.toString();
			}
		});

	}

}
