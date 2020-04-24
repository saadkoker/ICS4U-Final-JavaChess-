public class Timer extends Thread{

	private int timeInSec = 0;

	public Timer(int seconds){

		this.timeInSec = seconds;
	}
		public void run(){
			while(true){
				try{

				Thread.sleep(999);
				System.out.println(this.timeInSec--);

				}catch (Exception e) {
				
				}
			}
	}
	public void pause(){

		try{

		this.wait();

		}catch (Exception e) {

		}
	}
}