public class ChessClock {

	private int timeInSec = 0;
	private Timer timer;

	public CheckClock(int time){

		this.timeInSec = time;
	}
	public void start(){

		this.timer = new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e){

				updateBoard("Time Remaining: " + this.timeInSec-- + "sec");
			}
		});
	}
	public void updateBoard(JFrame board, String message) {

		JLabel timeString = new JLabel()
		timeString.setText(message);
		board.add(timeString)
	}
}
	