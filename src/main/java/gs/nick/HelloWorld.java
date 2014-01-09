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
		
		get(new Route("/json") {
			@Override
			public Object handle(Request req, Response res) {
				MyData d = new MyData();
				d.setName("Nick");
				d.setCar(req.session().id());
				d.setAge(29);
				return d;
			}
		});

	}

}

class MyData {
	private String name;
	private String car;
	private int age;
	public void setName(String n) {
		name = n;
	}
	public String getName() {
		return name;
	}
	public void setCar(String c) {
		car = c;
	}
	public String getCar() {
		return car;
	}
	public void setAge(int a) {
		age=a;
	}
	public int getAge() {
		return age;
	}
}