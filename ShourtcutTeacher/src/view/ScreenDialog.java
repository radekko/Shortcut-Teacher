package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	
	public ScreenDialog(TaskFactory taskCreator){
		this.taskCreator = taskCreator;
		loadTask();
		initFrame();
		initComponents();
	}
	
	private void loadTask() {
		Task task = taskCreator.getNextTask();
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
		
		labelBefore = new JLabel("  =>  ", imageBefore, JLabel.CENTER);
		labelAfter = new JLabel("", imageAfter, JLabel.CENTER);
		
		panelJPG.add(labelBefore, BorderLayout.EAST);
		panelJPG.add(labelAfter, BorderLayout.CENTER);
		
		this.add(panelJPG, BorderLayout.CENTER);
	}
	
	private void initConfirmPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(2, 1));
		
		JPanel panel = new JPanel();
//		panel.setBorder(BORDER);

		checkButt = new JButton("Check");
		checkButt.addActionListener(this::checkAction);
		
		nextButt = new JButton("Next");
		nextButt.addActionListener(this::nextAction);
		
		panel.add(checkButt);
		panel.add(nextButt);
		
		JPanel panel2 = new JPanel();
//		panel2.setBorder(BORDER);
		resultLabel = new JLabel();
		resultLabel.setFont(resultLabel.getFont().deriveFont(20f));
		panel2.add(resultLabel, BorderLayout.SOUTH);
	
		mainPanel.add(panel, BorderLayout.NORTH);
		mainPanel.add(panel2, BorderLayout.CENTER);
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
		labelAfter.setIcon(imageAfter);
		resultLabel.setText("");
	}
}