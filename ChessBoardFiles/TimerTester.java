public class TimerTester{

	public static void main(String[] args) {
		
		Timer time = new Timer(60);
		time.start();
		try{

			Thread.sleep(10000);
		}catch (Exception e) {
			
		}
		System.out.println("Wait");
		time.wait(10000);
	}
}