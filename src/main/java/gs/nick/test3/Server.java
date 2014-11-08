package gs.nick.test3;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import spark.Spark;


public class Server {
	
	private List<Integer> oServerList;

	public static void main(String[] args) {
		new Server();
	}

	Server() {
		this.setPort();
		this.registerRoutes();
		this.oServerList = new ArrayList<Integer>();
		System.out.println("Begin");
	}

	private void registerRoutes() {
		
		Spark.get("/", (req, res) -> {
			System.out.println("the / route is happening");
			return "<h1>Hello, World!</h1>";
		});
		
		Spark.get("/test", (req, res) -> {
			System.out.println("the /test route is happening");
			res.header("x-run-with", "java eight");
			return "<h1>test</h1>";
		});
		
		Spark.get("/add", (req, res) -> {
			int num = 0;
			String msg;
			if (req.queryParams("num") != null) {
				num = Integer.parseInt(req.queryParams("num"));
				oServerList.add(num);
				System.out.println("Adding: " + num);
				msg = "Added";
			} else {
				msg = "Please provide a ?num=23 in query parameter";
			}
			return "<h1>" + msg + "</h1>";
		});
		
		Spark.get("/list", (req, res) -> {
			res.type("application/json");
			ObjectMapper oMap = new ObjectMapper();
			String result = "{\"msg\": \"Error\"}";
			try {
				result = oMap.writeValueAsString(oServerList);
			} catch (IOException ex) {
				Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
			}
			return result;
		});
	}

	private void setPort() {
		String sPort = System.getenv("port");
		int iPort = 7171;
		if (sPort != null && !sPort.equals("")) {
			iPort = Integer.parseInt(sPort);
		}
		Spark.setPort(iPort);
		System.out.println("PORT IS " + iPort);
	}
	
	public void filterList(List<Integer> list, java.util.function.Predicate<Integer> tester) {
		
	}

}
