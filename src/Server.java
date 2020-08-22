/*
 Multithreaded version of User and Student Client/Server programme
*/
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.*;
import java.awt.*;
import javax.swing.*;


public class Server extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Text area for displaying contents
	private JTextArea jta = new JTextArea();
	ServerSocket serverSocket;
	
	public static void main(String[] args) {
		new Server();
	}
 
	public Server() {
		// Place text area on the frame
		setLayout(new BorderLayout());
		add(new JScrollPane(jta), BorderLayout.CENTER);
		setTitle("Server");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true); // It is necessary to show the frame here!
		try {
			// Create a server socket
			serverSocket = new ServerSocket(8000);
			jta.append("Server started at " + new Date() + '\n');

			while (true) {
				Socket s1=serverSocket.accept();
				myClient c = new myClient(s1);
				c.start();
			}
		}	
		catch(IOException ex) {
			System.err.println(ex);
		}
	} // End Server Construct
	
	private class myClient extends Thread {
		private SQLController database;
		
		//The socket the client is connected through
		private Socket socket;
		//The ip address of the client
		@SuppressWarnings("unused")
		private InetAddress address;
		//The input and output streams to the client
		private DataInputStream inputFromClient;
		private DataOutputStream outputToClient; 
		
		// The Constructor for the client
		public myClient(Socket socket) throws IOException {

			// Declare & Initialise input/output streams
			this.socket = socket;
	        this.inputFromClient = new DataInputStream(socket.getInputStream());
	        this.outputToClient = new DataOutputStream(socket.getOutputStream());
	        
	        this.address = socket.getInetAddress();   
		    this.database = new SQLController();
		    database.run();
		}
		/*
		 * The method that runs when the thread starts
		 */
		public void run() {
			try {				
				while (true) {

	                // Receive input from client
	                requestHandler(inputFromClient.readUTF());
	            }
			} catch (Exception e) {
				System.err.println(e + " on " + socket);
			}
		}
		
		public void requestHandler(String request) throws SQLException, IOException {
	        String requestType = request.split("-")[0];
	        String requestData = request.split("-")[1];

	        switch (requestType) {
	            case "login": {
	                try {
	    		        String foundUser = database.findUser(requestData);
	    		        	                    
	                    if (foundUser != null) {
	                    	// Send user back to the client
		    		        outputToClient.writeUTF(foundUser);
		    		        jta.append("Login ID received from client: " + requestData + "/" + foundUser + new Date() +'\n');
	                    } else {
	                        outputToClient.writeBoolean(false);
	                        jta.append("No User Found");
	                    }
	                } catch (SQLException ex) {
	                    System.out.println("Error finding User! \n " + ex.getMessage());
	                }
	                break;
	            }

	            case "getAllStudents": {
	            	jta.append("Searching for students...\n");
	                ArrayList<Student> students = database.getStudents();
	                jta.append("[" + students.size() + "] students returned." + new Date() + "\n");
	                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	                oos.writeUnshared(students);
	                break;
	            }

	            case "searchStudents": {
	            	jta.append("Searching for student...");
	                ArrayList<Student> students = database.getSearchStudent(requestData);
	                jta.append("[" + students.size() + "] students returned." + new Date() + "\n");
	                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	                oos.writeUnshared(students);
	                break;
	            }
	            case "logout": {
	                // close socket and interrupt thread
	            	jta.append("Logout - User logged out"+ new Date() + "\n");
	            	try {
	            		socket.close();
		                this.interrupt();
	            	} catch (SocketException ex) {
	            		System.out.println(ex);
	            	}
	                
	                
	                break;
	            }
	        }
	    }
	}
}

