package eu.vamdc.hitran;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	/* variables */
	String xsamsFile;

	/* panels */
	private JPanel pan = new JPanel();

	/* menus */
	private JMenuItem jmiExit; // Exit of FILE menu

	/* buttons */
	private JButton xsamsSource;

	private void BuildMenu() {
		// menu bar
		JMenuBar jmb = new JMenuBar();
		// FILE
		JMenu jmFile = new JMenu("File");
		jmiExit = new JMenuItem("Exit");
		jmiExit.addActionListener(this);
		jmFile.add(jmiExit);
		
		jmb.add(jmFile);
		setJMenuBar(jmb); // set menu bar
	}
	
	private void BuildWindow() {
		xsamsSource = new JButton("Browse...");
		xsamsSource.setToolTipText("Choose the XSAMS you want to convert");
		pan.add(xsamsSource);
		this.setContentPane(pan);
		xsamsSource.addActionListener(this);
	}
	
	/**
	 * Build main window of application
	 */
	
	public MainWindow() {
		super("Xsams2Hitran"); // main window

		BuildMenu();
		BuildWindow();
	}

	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == jmiExit) {
			System.exit(0);
			return;
		}
		
		if (evt.getSource() == xsamsSource) {
			loadXSAMSFile();
			return;
		}
	}
	
	/**
	 * Load XSAMS File into memory andd make conversion
	 */
	
	public void loadXSAMSFile() {

		// choose the file
		JFileChooser dialog = new JFileChooser();

		dialog.setSize(400, 300);
		dialog.setFileSelectionMode(JFileChooser.FILES_ONLY); // files only
		dialog.setDialogTitle("Choose XSAMS file");
		dialog.setFileFilter(new FileNameExtensionFilter("XSAMS files", "xsams"));

		Container parent = getParent();
		int choice = dialog.showDialog(parent, "Select"); // Dialog, Select
		if (choice == JFileChooser.APPROVE_OPTION) {
			xsamsFile = dialog.getSelectedFile().getAbsolutePath();
			System.out.println("Source file: " + xsamsFile);
			ConvertXsams2Hitran file = new ConvertXsams2Hitran();
			file.convertXSAMS(xsamsFile);
		}
	}
}
