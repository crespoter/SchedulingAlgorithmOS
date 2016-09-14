import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
public class MainWindow {
	
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnRun = new JButton("RUN ALL");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String[] args = {};
				AssignmentOSIntegrated.main(args);
				
			}
		});
		btnRun.setBounds(165, 103, 97, 25);
		frame.getContentPane().add(btnRun);
		
		Button FCFS = new Button("FCFS");
		FCFS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String[] args = {};
				FirstComeFirstServe.main(args);
			}
		});
		FCFS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		FCFS.setBounds(23, 30, 79, 24);
		frame.getContentPane().add(FCFS);
		
		Button Preemptive_Priority = new Button("Pre..Prio");
		Preemptive_Priority.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] args={};
				priorityPreemtive.main(args);
				
			}
		});
		Preemptive_Priority.setBounds(23, 178, 79, 24);
		frame.getContentPane().add(Preemptive_Priority);
		
		Button SRJF = new Button("SRJF");
		SRJF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] args={};
				preemptive.main(args);
			}
		});
		SRJF.setBounds(322, 178, 79, 24);
		frame.getContentPane().add(SRJF);
		
		Button RR = new Button("RR");
		RR.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] args={};
				roundRobin.main(args);
			}
		});
		RR.setBounds(311, 30, 79, 24);
		frame.getContentPane().add(RR);
		
		Button SJF = new Button("SJF");
		SJF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] args={};
				nonpreemptive.main(args);
			}
		});
		SJF.setBounds(175, 30, 79, 24);
		frame.getContentPane().add(SJF);
		
		Button NonPreemptivePriority = new Button("NonPre-Prio");
		NonPreemptivePriority.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] args={};
				priorityNonPreemptive.main(args);
			}
		});
		NonPreemptivePriority.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		NonPreemptivePriority.setBounds(175, 178, 79, 24);
		frame.getContentPane().add(NonPreemptivePriority);
	}
}
