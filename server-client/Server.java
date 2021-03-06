import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
public class Server {
	public static int numVMs = 0;
	public static String ip;
	public static String time;
	//private static ServerSocket server;
	public static  Object[][] data = new Object[50][3];
	public static String[] columnHeaders = new String[] {
            "Number", "I.P", "Up Time"};
	public static void dashboard() {
		JFrame dashboard = new JFrame("Dashboard");
    	dashboard.setVisible(true);	
		dashboard.setTitle("Dashboard Information");
		dashboard.setBounds((960 - 250), (540 - 250), 500, 500);
		JTable table = new JTable(data, columnHeaders); 
		dashboard.add(new JScrollPane(table));
		int c = numVMs;
		
		if (c != 0) {
			data[c - 1][0] = (c);
			data[c - 1][1] = ip;
			data[c - 1][2] = time;
		}
		dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dashboard.pack();
	}
	public static void main(String[] args)throws IOException { 
		dashboard();
		System.out.println("Server Running");
		ServerSocket server = new ServerSocket(9090);
		try ( 	 
			    Socket clientSocket = server.accept();
			    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			){
			out.println("welcome");
			ip = in.readLine();
			time = in.readLine();

			numVMs++;
			System.out.println(ip);
			System.out.println(time);
			
		}
		
		finally {
			server.close();
		}
		}
        

	

}
