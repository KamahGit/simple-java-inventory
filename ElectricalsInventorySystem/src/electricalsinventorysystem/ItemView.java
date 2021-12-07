package electricalsinventorysystem;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.AddResult;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ItemView extends JFrame{

	

	/**
	 * Publicly declare swing components
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtName, txtQty, txtID;
	private JTable tblItems;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private JLabel lblName, lblQty, lblID;
	private JButton btnAdd, btnUpdate, btnDel;
	private static final int EDIT_MODE = 1;
	private static final int CREATE_MODE = 0;
	JPanel panelForm = new JPanel();
	JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemView window = new ItemView();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ItemView() {
		setResizable(false);
		setMinimumSize(new Dimension(500, 500));
		getContentPane().setSize(new Dimension(450, 450));
		initialize();
		loadItemTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(535,527);
		this.setTitle("View Inventory");
		getContentPane().setLayout(null);
		
		
		panelForm.setLocation(10, 11);
		panelForm.setSize(464,154);
		panelForm.setBorder(new TitledBorder(null, "Item Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panelForm);
		panelForm.setLayout(null);
		
		lblName = new JLabel("Item Description");
		lblName.setBounds(10, 54, 112, 20);
		panelForm.add(lblName);
		
		
		txtName = new JTextField();
		txtName.setBounds(131, 54, 283, 20);
		panelForm.add(txtName);
		txtName.setColumns(10);
		
		lblQty = new JLabel("Item Quantity");
		lblQty.setBounds(10, 85, 112, 20);
		panelForm.add(lblQty);
		
		txtQty = new JTextField();
		txtQty.setColumns(10);
		txtQty.setBounds(131, 85, 57, 20);
		panelForm.add(txtQty);
		
		btnAdd = new JButton("Add");
		buttonGroup.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveItem();
			}
		});
		btnAdd.setBounds(10, 120, 112, 23);
		panelForm.add(btnAdd);
		
		btnDel = new JButton("Delete");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delItem();
			}
		});
		btnDel.setEnabled(false);
		buttonGroup.add(btnDel);
		btnDel.setBounds(306, 120, 112, 23);
		panelForm.add(btnDel);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		buttonGroup.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateItem();
			}
		});
		btnUpdate.setBounds(166, 120, 105, 23);
		panelForm.add(btnUpdate);
		
		lblID = new JLabel("Item Quantity");
		lblID.setBounds(10, 23, 112, 20);
		panelForm.add(lblID);
		
		txtID = new JTextField();
		txtID.setColumns(10);
		txtID.setBounds(131, 23, 57, 20);
		panelForm.add(txtID);
		
//		JPanel panelTbl = new JPanel();
//		panelTbl.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
//		panelTbl.setBounds(10, 176, 492, 293);
//		getContentPane().add(panelTbl);
//		panelTbl.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 176, 492, 293);
		getContentPane().add(scrollPane);
		
		
		tblItems = new JTable();
		tblItems.setModel(new DefaultTableModel(
		new String[] {"ID", "Description", "Qty", "Created", "Modified"},1));
		tblItems.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(tblItems);
		
		tblItems.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				changeMode(EDIT_MODE);				
			}
		});
		
		
		}
	

	/**
	 * Changes form from Edit mode to Create mode
	 * and vice versa
	 * @param form_mode
	 */
	public void changeMode(int form_mode) {
		if (form_mode == 1) {
			btnAdd.setEnabled(false);
			btnDel.setEnabled(true);
			btnUpdate.setEnabled(true);
		}
		else if(form_mode == 0){
			btnAdd.setEnabled(true);
			btnDel.setEnabled(false);
			btnUpdate.setEnabled(false);
		}
	}
	
	
	public void loadItemTable () {
		
		ResultSet resultSet;
		DefaultTableModel tblItemsModel = (DefaultTableModel) tblItems.getModel();
		tblItemsModel.setRowCount(0);
		
		try {
			
			Connection dbConnection = DBConnection.getConnection();
			Statement qStatement = dbConnection.createStatement();
			resultSet = qStatement.executeQuery("SELECT * from item");
			
			while (resultSet.next()) {
				String[] row = new String[] {
						resultSet.getString("item_id"),
						resultSet.getString("item_desc"),
						resultSet.getString("item_qty"),
						resultSet.getDate("item_created_at").toString(),
						resultSet.getDate("item_updated_at").toString()
				};
				tblItemsModel.addRow(row);
			}
			qStatement.close();
			dbConnection.close();
		} 
		catch(Exception e) {e.printStackTrace();}
	}
	
	public void saveItem() {
		// TODO: Add BODY
	}
	
	public void delItem() {
		// TODO: Add BODY
	}
	
	public void updateItem() {
		// TODO: Add BODY
	}
}
