import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.GridBagConstraints;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.CardLayout;
import java.awt.Desktop.Action;

import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Pair_GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txt_NameEnter;
	private JButton btn_AddName;
	private JButton btn_GeneratePairs;
	private JPanel panel;
	private JTextArea Output;
	private JScrollPane scroll;
	private Pairings pair_list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pair_GUI frame = new Pair_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Pair_GUI() {
		pair_list = new Pairings();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x_start = (int) screenSize.getWidth()/5;
		int y_start = (int) screenSize.getHeight()/5;
		int frameWidth = (int) screenSize.getWidth()/2;
		int frameHeight = (int) screenSize.getHeight()*2/3;
		
		setBounds(x_start, y_start, frameWidth, frameHeight);
		
		setTitle("Accountability Pairs Generator");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		txt_NameEnter = new JTextField();
		txt_NameEnter.setFont(new Font("Calibri", Font.PLAIN, 20));
		txt_NameEnter.setText("Enter a name");
		GridBagConstraints gbc_txt_NameEnter = new GridBagConstraints();
		gbc_txt_NameEnter.insets = new Insets(0, 0, 5, 0);
		gbc_txt_NameEnter.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_NameEnter.gridx = 0;
		gbc_txt_NameEnter.gridy = 0;
		contentPane.add(txt_NameEnter, gbc_txt_NameEnter);
		txt_NameEnter.setColumns(10);
		// add action for enter press
		AbstractAction action = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// get name text
				String name = txt_NameEnter.getText();
				txt_NameEnter.setText("");
				
				// add name to list
				pair_list.addName(name);
				
				// output
				Output.setText(name + " added");
			}
		};
		txt_NameEnter.addActionListener(action);
		
		btn_AddName = new JButton("Add Name");
		btn_AddName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// get name text
				String name = txt_NameEnter.getText();
				txt_NameEnter.setText("");
				
				// add name to list
				pair_list.addName(name);
				
				// output
				Output.setText(name + " added");
			}
		});
		btn_AddName.setFont(new Font("Calibri", Font.BOLD, 20));
		GridBagConstraints gbc_btn_AddName = new GridBagConstraints();
		gbc_btn_AddName.insets = new Insets(0, 0, 5, 0);
		gbc_btn_AddName.gridx = 0;
		gbc_btn_AddName.gridy = 1;
		contentPane.add(btn_AddName, gbc_btn_AddName);
		
		btn_GeneratePairs = new JButton("Generate Pairs");
		btn_GeneratePairs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pair_list.makePairs();
				Output.setText(pair_list.printCycles());
			}
		});
		btn_GeneratePairs.setFont(new Font("Calibri", Font.BOLD, 20));
		GridBagConstraints gbc_btn_GeneratePairs = new GridBagConstraints();
		gbc_btn_GeneratePairs.insets = new Insets(0, 0, 5, 0);
		gbc_btn_GeneratePairs.gridx = 0;
		gbc_btn_GeneratePairs.gridy = 2;
		contentPane.add(btn_GeneratePairs, gbc_btn_GeneratePairs);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 3;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new CardLayout(0, 0));
		
		Output = new JTextArea();
		Output.setEditable(false);
		Output.setFont(new Font("Calibri", Font.PLAIN, 20));
		
		scroll = new JScrollPane(Output);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		panel.add(scroll);
	}
}
