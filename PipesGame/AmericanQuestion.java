package test;


public class AmericanQuestion {

	String[] patterns={"Singleton","Strategy","Class Adapter","Object Adapter","Decorator","Observer","Facade","Command","Factory"};
	
	public String q1(){
		// which design pattern do we see here?
		// new Thread(()->System.out.println("hello world!")).start();
		return patterns[1];
	}
	public String q2(){
		// which design pattern does InputStreamReader applies?
		// new BufferedReader(new InputStreamReader(System.in));
		return patterns[3];
	}
	public String q3(){
		// which design pattern do we see here?
		// Model m=Model.getInstance();
		return patterns[0];
	}	
	public String q4(){
		// which design pattern do we see here?
		// Model m=new MyModel();
		return patterns[6];
	}	
}
