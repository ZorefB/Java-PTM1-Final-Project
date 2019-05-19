package test;

import server_side.*;


public class TestSetter {


	static Server s;

	public static void runServer(int port) {

		s=new MySerialServer();
		try {
			s.start(port, new MyClientHandler(new FileCacheManager<Searchable<Position>, String>(), new SolverSearcher<Position>(new BestFirstSearch<Position>())));
		}catch (Exception e) { e.printStackTrace();}
	}

	public static void stopServer() {
		// put the code here that stops your server
		// for example:
		s.stop();
	}

}
