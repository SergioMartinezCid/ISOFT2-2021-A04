package es.uclm.esi.isoft2.a04.Persistance;

public class ConnectionTester {
	
	public static void main (String [] args) {
		
		try {
			
			Broker broker = new Broker();
			
			broker.getConnection();
			
			
		}catch(Exception e) {
			System.out.print(e);
		}
	}

}
