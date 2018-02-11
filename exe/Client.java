package exe;

import java.io.IOException;

public class Client {
	public static Launcher launcher = null;

	public static void main(String[] args) throws InterruptedException, IOException {
		new ModeManager();
		
		while(launcher == null) {
			Thread.sleep(1000);
		}
		
		launcher.launch();
	}
}
