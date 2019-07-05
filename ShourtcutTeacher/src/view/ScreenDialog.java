package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import main.Task;
import main.TaskFactory;

public class ScreenDialog extends JFrame {

	private static final LineBorder BORDER = new LineBorder(Color.BLACK,2);
	private TaskFactory taskCreator;
	private String currentShortcut;
	private Set<Integer> searchedKeys;
	private String description;

	private JLabel resultLabel;
	private JButton checkButt;
	private JButton nextButt;
	private ImageIcon imageBefore;
	private ImageIcon imageAfter;
	private JLabel labelBefore;
	private JLabel labelAfter;
	private JLabel labelDescription;
	private final Set<Integer> pressed = new HashSet<>();
	private static final long serialVersionUID = 1L;
	private JLabel betweenScreenLabel;
	
	public ScreenDialog(TaskFactory taskCreator){
		this.taskCreator = taskCreator;
		loadTask();
		initFrame();
		initComponents();
	}
	
	private void loadTask() {
		try {
			Task task = taskCreator.getNextTask();
			setFields(task);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this,
				        "Folder with tasks is empty. You should add some tasks to start application.");
			System.exit(0);
		} 
	}

	private void setFields(Task task) {
		imageBefore = task.getImageBefore();
		imageAfter = task.getImageAfter();
		currentShortcut = task.getShortcut().getKeysAsString();
		searchedKeys = task.getShortcut().getKeys();
		description = task.getShortcut().getDescription();
	}

	private void initFrame() {
		this.setTitle("Test");
		this.setSize(1000, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setFocusable(true);
		this.setVisible(true);
		
		addKeyListener(new KeyListener(){
            @Override
            public void keyPressed(KeyEvent e) {
            	if(isPressedLeftArrow(e))
            		checkResult();
            	else if(isPressedRightArrow(e))
            		nextTask();
            	else {
	            	pressed.add(e.getKeyCode());
	            	
	            	if(isPressedKeysFitToShortcut())
	            		nextTask();
            	}
            }

			private boolean isPressedKeysFitToShortcut() {
				return pressed.containsAll(searchedKeys);
			}

			private boolean isPressedRightArrow(KeyEvent e) {
				return e.getKeyCode() == KeyEvent.VK_RIGHT;
			}

			private boolean isPressedLeftArrow(KeyEvent e) {
				return e.getKeyCode() == KeyEvent.VK_LEFT;
			}

            @Override
            public void keyTyped(KeyEvent e) {}

			@Override
            public void keyReleased(KeyEvent e) {
            	 pressed.remove(e.getKeyCode());
            }
		});
	}

	private void initComponents(){
		initDescriptionPanel();
		initScreenPanel();
		initConfirmPanel();
	}
	
	private void initDescriptionPanel() {
		JPanel panel = new JPanel();
		labelDescription = new JLabel(description, JLabel.CENTER);
		panel.add(labelDescription, BorderLayout.NORTH);
		this.add(panel, BorderLayout.NORTH);
	}
	
	private void initScreenPanel() {
		JPanel panelJPG = new JPanel();
//		panelJPG.setBorder(BORDER);
		
		labelBefore = new JLabel(imageBefore, JLabel.CENTER);
		betweenScreenLabel = new JLabel("  =>  ");
		labelAfter = new JLabel("", imageAfter, JLabel.CENTER);
		
		panelJPG.add(labelBefore, BorderLayout.EAST);
		panelJPG.add(betweenScreenLabel, BorderLayout.CENTER);
		panelJPG.add(labelAfter, BorderLayout.WEST);
		
		this.add(panelJPG, BorderLayout.CENTER);
	}
	
	private void initConfirmPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(3, 1));
		
		JPanel infoPanel = new JPanel();
		JLabel labelDescription = new JLabel(new ImageIcon("gui_images/question.jpg"), JLabel.CENTER);
		labelDescription.setToolTipText("left arrow - check, right - next");
		infoPanel.add(labelDescription);
		
		JPanel buttonsPanel = new JPanel();
//		panel.setBorder(BORDER);

		checkButt = new JButton("Check");
		checkButt.addActionListener(this::checkAction);
		
		nextButt = new JButton("Next");
		nextButt.addActionListener(this::nextAction);
		
		buttonsPanel.add(checkButt);
		buttonsPanel.add(nextButt);
		
		JPanel resultPanel = new JPanel();
//		panel2.setBorder(BORDER);
		resultLabel = new JLabel();
		resultLabel.setFont(resultLabel.getFont().deriveFont(20f));
		resultPanel.add(resultLabel, BorderLayout.SOUTH);
	
		mainPanel.add(infoPanel, BorderLayout.NORTH);
		mainPanel.add(buttonsPanel, BorderLayout.CENTER);
		mainPanel.add(resultPanel, BorderLayout.SOUTH);
		this.add(mainPanel, BorderLayout.SOUTH);
	}
	
	private void checkAction(ActionEvent e) {
		checkResult();
		this.requestFocus();
	}

	private void nextAction(ActionEvent e) {
		nextTask();
		this.requestFocus();
	}
	
	private void checkResult() {
		resultLabel.setText(currentShortcut);
	}

	private void nextTask() {
		loadTask();
		labelDescription.setText(description);
		labelBefore.setIcon(imageBefore);
		
		if(isTwoScreensToOneTask())
			setFieldsForTwoScreenTask();
		else
			setFieldsForOneScreenTask();
		
		resultLabel.setText("");
	}

	private boolean isTwoScreensToOneTask() {
		return imageAfter.getIconHeight() > 0;
	}
	
	private void setFieldsForOneScreenTask() {
		labelAfter.setVisible(false);
		betweenScreenLabel.setVisible(false);
	}
	
	private void setFieldsForTwoScreenTask() {
		labelAfter.setVisible(true);
		betweenScreenLabel.setVisible(true);
		labelAfter.setIcon(imageAfter);
	}
}