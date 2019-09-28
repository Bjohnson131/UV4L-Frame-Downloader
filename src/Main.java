
public class Main {

	public static int intervalMs = 0;
	public static boolean running = true;
	public static boolean tempRun = false;
	public static int numPics = 0;
	public static String ip = "localhost";
	public static String port = "9090";

	public static void main(String[] args) {
		// The first argument should be how often you want to run  the algorithm
		int currentProc =0;
		while(currentProc < args.length) {
			if(currentProc == 0) {
				intervalMs = Integer.parseInt(args[0]);
				currentProc++;
				continue;
			}
			if(args[currentProc].equals("-t")){
				tempRun = true;
				currentProc++;
				numPics = Integer.parseInt(args[currentProc]);
				currentProc++;
			}
			if(args[currentProc].equals("-ip")){
				currentProc++;
				ip = args[currentProc];
				currentProc++;
			}
			if(args[currentProc].equals("-p")){
				currentProc++;
				port = args[currentProc];
				currentProc++;
			}
			
			
		}

		if(intervalMs<1) {
		
			System.out.println("No input. Please input the capture interval in Ms");
			return;
		}else {
			Thread t = new Thread(new mainThread());
			t.start();
			while(t.isAlive()) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}
