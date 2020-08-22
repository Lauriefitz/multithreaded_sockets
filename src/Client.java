import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Client extends javax.swing.JFrame {
	
	
	private static final long serialVersionUID = 1L;
	
	// Declaring GUI components
	private JTextField jtf = new JTextField();
	private JTextField jtfSearch;
	private JTextArea jta = new JTextArea();
	private javax.swing.JPanel p;
	private javax.swing.JPanel p3;
	private javax.swing.JTable jTable;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel2s;
	private javax.swing.JLabel jLabel3s;
	private javax.swing.JLabel jLabel4s;
	private javax.swing.JLabel jLabel5s;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JButton logoutButton;
	private javax.swing.JButton loginButton;
	private javax.swing.JButton resetButton;
	private javax.swing.JButton buttonSearch;
	private javax.swing.JButton buttonNext;
	private javax.swing.JButton buttonPrevious;
	private ImageIcon img;
	
	ArrayList<Student> students = new ArrayList<>();
    
	// IO streams
	private DataOutputStream toServer;
	private DataInputStream fromServer;
	private Socket socket;
  
	// Table Column headers
	String col[] = { "SID", "Stud ID", "First Name", "Surname" };
	private final DefaultTableModel tableModel = new DefaultTableModel(col, 0){
		private static final long serialVersionUID = 1L;
		
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
			
		}
		
	};
		
	public static void main(String[] args) {
		new Client();
	}
	
	public Client() {
		mainView();
		visiblity(true);
		try {
			// Create a socket to connect to the server
			socket = new Socket("localhost", 8000);
	  
			// Create an input stream to receive data from the server
			fromServer = new DataInputStream(socket.getInputStream());	  
	  
			// Create an output stream to send data to the server
			toServer = new DataOutputStream(socket.getOutputStream());
			}
		catch (IOException ex) {
			jta.append(ex.toString() + '\n');
		}
	}	
	
	private void mainView() {
		// Panel p to hold the label and text field
		p = new JPanel();
		p3 = new JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel2s = new javax.swing.JLabel();
		jLabel3s = new javax.swing.JLabel();
		jLabel4s = new javax.swing.JLabel();
		jLabel5s = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jtf = new javax.swing.JTextField(); 
		jtfSearch = new javax.swing.JTextField(); 
		logoutButton = new javax.swing.JButton();
		loginButton = new javax.swing.JButton();
		resetButton = new javax.swing.JButton();
		buttonSearch = new javax.swing.JButton();
		buttonNext = new javax.swing.JButton();
		buttonPrevious = new javax.swing.JButton();
		jTable = new javax.swing.JTable();
		img = new ImageIcon("src/wit-logo.png");
	   
		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24));
		jLabel1.setForeground(new java.awt.Color(49, 80, 166));
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("Login ID: ");
	   
		jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14));
		jLabel2.setForeground(new java.awt.Color(49, 80, 166));
		jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		jLabel2.setText("SID: ");
	   
	    jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14));
	    jLabel3.setForeground(new java.awt.Color(49, 80, 166));
	    jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
	    jLabel3.setText("Student ID: ");
	    
	    jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14));
	    jLabel4.setForeground(new java.awt.Color(49, 80, 166));
	    jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
	    jLabel4.setText("First Name: ");
	    
	    jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14));
	    jLabel5.setForeground(new java.awt.Color(49, 80, 166));
	    jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
	    jLabel5.setText("Surname: ");
	    
		jLabel2s.setFont(new java.awt.Font("Tahoma", 1, 14));
		jLabel2s.setForeground(new java.awt.Color(0, 0, 0));
		jLabel2s.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		jLabel2s.setText("");
	   
	    jLabel3s.setFont(new java.awt.Font("Tahoma", 1, 14));
	    jLabel3s.setForeground(new java.awt.Color(0, 0, 0));
	    jLabel3s.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
	    jLabel3s.setText("");
	    
	    jLabel4s.setFont(new java.awt.Font("Tahoma", 1, 14));
	    jLabel4s.setForeground(new java.awt.Color(0, 0, 0));
	    jLabel4s.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
	    jLabel4s.setText("");
	    
	    jLabel5s.setFont(new java.awt.Font("Tahoma", 1, 14));
	    jLabel5s.setForeground(new java.awt.Color(0, 0, 0));
	    jLabel5s.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
	    jLabel5s.setText("");
	    
	    jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14));
	    jLabel6.setForeground(new java.awt.Color(49, 80, 166));
	    jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
	    jLabel6.setText("Search by Surname: ");
	   
	    logoutButton.setBackground(new java.awt.Color(0, 0, 255));
	    logoutButton.setFont(new java.awt.Font("Dialog", 1, 14));
	    logoutButton.setForeground(new java.awt.Color(255, 255, 255));
	    logoutButton.setText("Logout");
	    logoutButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
	    logoutButton.setBorderPainted(false);
	    logoutButton.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonLogoutActionPerformed();
				//System.out.println("Logout");
			}
		});
	    //logoutButton.setVisible(!vis);
	    
	    loginButton.setBackground(new java.awt.Color(0, 0, 255));
	    loginButton.setFont(new java.awt.Font("Dialog", 1, 14));
	    loginButton.setForeground(new java.awt.Color(255, 255, 255));
	    loginButton.setText("Login");
	    loginButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
	    loginButton.setBorderPainted(false);	    
	    loginButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonLoginActionPerformed();
			}
		});
	    //loginButton.setVisible(true);
	    
	    resetButton.setBackground(new java.awt.Color(232, 32, 37));
	    resetButton.setFont(new java.awt.Font("Dialog", 1, 14));
	    resetButton.setForeground(new java.awt.Color(255, 255, 255));
	    resetButton.setText("Reset");
	    resetButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
	    resetButton.setBorderPainted(false);	    
	    resetButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonResetActionPerformed();
			}
		});
	    
	    buttonNext.setBackground(new java.awt.Color(150, 168, 141));
	    buttonNext.setFont(new java.awt.Font("Dialog", 1, 14));
	    buttonNext.setForeground(new java.awt.Color(255, 255, 255));
	    buttonNext.setText("Next Student");
	    buttonNext.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
	    buttonNext.setBorderPainted(false);	    
	    buttonNext.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonNextActionPerformed();
			}
		});
	    
	    buttonPrevious.setBackground(new java.awt.Color(204, 213, 169));
	    buttonPrevious.setFont(new java.awt.Font("Dialog", 1, 14));
	    buttonPrevious.setForeground(new java.awt.Color(255, 255, 255));
	    buttonPrevious.setText("Previous Student");
	    buttonPrevious.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
	    buttonPrevious.setBorderPainted(false);	    
	    buttonPrevious.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonPreviousActionPerformed();
			}
		});
	    
	    // Adding components to a border layout
	    p.setLayout(new BorderLayout());
	    p.setBackground(new java.awt.Color(255, 255, 255));
	    p.add(jLabel1, BorderLayout.WEST);
	    //p.add(logoutButton, BorderLayout.PAGE_END);
	    p.add(loginButton, BorderLayout.EAST);
	    p.add(jtf, BorderLayout.CENTER);
	    
	    jtf.setHorizontalAlignment(JTextField.RIGHT);
	    jtf.setToolTipText("Enter User ID here...");
	    
		jTable.setAutoCreateRowSorter(true);
		jTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
		jTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		jTable.setSelectionBackground(new java.awt.Color(255, 127, 127));
		jTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		jTable.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				jTableStudentsMousePressed();
			}
		});				
		
	    
	    buttonSearch.setText("Search Students");
	    buttonSearch.setBackground(new java.awt.Color(0, 50, 255));
	    buttonSearch.setFont(new java.awt.Font("Dialog", 1, 14));
	    buttonSearch.setForeground(new java.awt.Color(255, 255, 255));
	    buttonSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
	    buttonSearch.setBorderPainted(false);
	    buttonSearch.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonSearchStudentsActionPerformed();
			}
		});
	    
	    p3.setLayout(new BorderLayout(20, 20));
	    // Creating new grid layout within a border layout, to align text labels and fields, and buttons
	    JLabel jLabelSpace = new JLabel();
	    jLabelSpace.setText("");
	    JPanel buttonsPanel = new JPanel();
	    buttonsPanel.setLayout(new GridLayout(0, 2, 5, 10));

	    buttonsPanel.add(jLabel2);
	    buttonsPanel.add(jLabel2s);
	    buttonsPanel.add(jLabel3);
	    buttonsPanel.add(jLabel3s);
	    buttonsPanel.add(jLabel4);
	    buttonsPanel.add(jLabel4s);
	    buttonsPanel.add(jLabel5);
	    buttonsPanel.add(jLabel5s);
	    buttonsPanel.add(jLabel6);
	    buttonsPanel.add(jtfSearch);
    
	    buttonsPanel.add(buttonSearch);
	    buttonsPanel.add(jLabelSpace);
	    buttonsPanel.add(buttonPrevious);
	    buttonsPanel.add(buttonNext);
	    buttonsPanel.add(resetButton);
	    buttonsPanel.add(logoutButton);
	    
	    buttonsPanel.setVisible(true);
	    
	    // Scrollbar for table
	    JScrollPane scrollPane = new JScrollPane(jTable);
	    
	    p3.add(buttonsPanel, BorderLayout.WEST);
	    p3.add(scrollPane, BorderLayout.CENTER);
		
	    
		setLayout(new BorderLayout());
		add(p, BorderLayout.NORTH);
		add(p3, BorderLayout.CENTER);
	
		setTitle("Client");
		setIconImage(img.getImage());
		getContentPane().setBackground( new java.awt.Color(255, 255, 255) );
		setSize(600, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true); // It is necessary to show the frame here!
		
	}
	
	private void visiblity(Boolean vis) {
		p.setVisible(true);
		loginButton.setVisible(vis);
		logoutButton.setVisible(!vis);
		p3.setVisible(!vis);
		jTable.setVisible(!vis);
	}
	
		
	private void jButtonLogoutActionPerformed(){
		//Closes socket and then closes gui
		try {
			toServer.writeUTF("logout-" + null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		System.exit(1);
	}
	
		
	private void jButtonLoginActionPerformed() {
				
		try {
			// Get the value from the text field
			String loginID = (jtf.getText().trim());
			// Send the value to the server
			toServer.writeUTF("login-" + loginID);
			toServer.flush();
			// Get result from the server
			String loginIDserver = fromServer.readUTF();
			// If the result is empty or the user isn't found, show pop up
			if (loginIDserver.isEmpty() || loginIDserver.contentEquals("No User Found!") ){
				JOptionPane.showMessageDialog(null, "Login ID " + loginID + " not found. Try again!");
				System.out.println("Login ID non existant.");
			}
			// Else enter program, display all gui components
			else {
				System.out.println("Login ID received from the server is "
        	          + loginIDserver + '\n');
				jLabel1.setText("Welcome " + loginIDserver);
				jtf.setVisible(false);
				loginButton.setVisible(false);    
				logoutButton.setVisible(true);
				p3.setVisible(true);
				jTable.setVisible(true);
				buttonNext.setVisible(true);
				buttonPrevious.setVisible(true);				
				try {
					// Getting data from DB and mapping to table
					getAllStudents();
					mapToTable(students);
				} catch (IOException | ClassNotFoundException ex) {
					System.out.println(ex);
				}
					
				
			}
					
		}
		catch (IOException ex) {
			System.err.println(ex);
				
		}
			
	}
	
	private void jButtonResetActionPerformed() {
		try {
			jtfSearch.setText("");
			jLabel2s.setText("");
			jLabel3s.setText("");
			jLabel4s.setText("");
			jLabel5s.setText("");
			getAllStudents();
			mapToTable(students);
		} catch (IOException | ClassNotFoundException ex) {
			System.out.println(ex);
		}
	}
	
	private void jTableStudentsMousePressed() {
		//System.out.println(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
		String stuid = jTable.getValueAt(jTable.getSelectedRow(), 0).toString();
		for (Student student : students) {
			if (student.getSID().equals(stuid)) {

				jLabel2s.setText(student.getSID());
				jLabel3s.setText(student.getStudID());
				jLabel4s.setText(student.getFirstName());
				jLabel5s.setText(student.getSurname());
			}
		}
	}
	
	private void jButtonPreviousActionPerformed() {
		if (jTable.getSelectedRow() == 0) {
			JOptionPane.showMessageDialog(null, "No Previous student!");
		}
		else {
			int rowNumber = jTable.getSelectedRow() - 1;
			jTable.setRowSelectionInterval(rowNumber, rowNumber);
			setStudentDetails();
		}
	}
	
	private void jButtonNextActionPerformed() {
		if ((jTable.getSelectedRow()+1) == jTable.getRowCount()) {
			JOptionPane.showMessageDialog(null, "No Next student!");
		}
		else {
			int rowNumber = jTable.getSelectedRow() + 1;
			jTable.setRowSelectionInterval(rowNumber, rowNumber);
			setStudentDetails();
		}
	}
	
	private void setStudentDetails() {
		for (Student student : students) {
			if (student.getSID().equals(jTable.getValueAt(jTable.getSelectedRow(), 0).toString())) {

				jLabel2s.setText(student.getSID());
				jLabel3s.setText(student.getStudID());
				jLabel4s.setText(student.getFirstName());
				jLabel5s.setText(student.getSurname());
			}
		}
	}
	
	private void jButtonSearchStudentsActionPerformed() {
		String surname = jtfSearch.getText();
		// If field is empty or contains a number, show pop up
		if(surname.isEmpty() || surname.matches(".*\\d+.*")) {
			JOptionPane.showMessageDialog(null, "Surname field error! Is it empty? Does it contain a number?");
			System.out.println("Surname field error! Is it empty? Does it contain a number?");
		}else {
			try {
				getOneStudent(surname);				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}
		
	// map values from  database to table
	private void mapToTable(ArrayList<Student> students) {
		tableModel.setRowCount(0); // reset rows to avoid duplicates
		jTable.setModel(tableModel);
		for (Student student : students) {
			Object[] data = { student.getSID(), student.getStudID(), student.getFirstName(), student.getSurname() };
			tableModel.addRow(data);
			//System.out.println(data);
		}
		//Sort rows by first element in the column (SID)
		jTable.getRowSorter().toggleSortOrder(0);
		//Setting the highlight on the first row of data
		jTable.setRowSelectionInterval(0, 0);
		setStudentDetails();
	}
  
	@SuppressWarnings("unchecked")
	private ArrayList<Student> getAllStudents() throws IOException, ClassNotFoundException {
		toServer.writeUTF("getAllStudents-" + null);
		// IO for objects
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		students = (ArrayList<Student>) ois.readUnshared();
		return students;
	}
	
	@SuppressWarnings("unchecked")
	private ArrayList<Student> getOneStudent(String surname) throws IOException, ClassNotFoundException {
		toServer.writeUTF("searchStudents-" + surname);
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		students = (ArrayList<Student>) ois.readUnshared();
		if(students.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Can't find a student with that surname!");
		}else {
			mapToTable(students);
		}
		return students;
	}

}