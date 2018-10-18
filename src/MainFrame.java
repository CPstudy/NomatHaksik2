import javax.swing.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

class Nomat extends JFrame implements Runnable, ActionListener {
	
	public static final int WIDTH = 550;
	public static final int HEIGHT = 420;
	public static final int FRAME_MARGIN = 6;
	
	int x = 0;
	int y = 0;
	
	JImageView imgBG;
	JPanel panelTitle;
	JPanel panelDate;
	JPanel panelMenu;
	JPanel panelCon;
	JButton btnClose;
	JButton btnMinimize;
	JButton btnLink;
	JLabel lblDayOfWeek;
	JLabel lblDate;
	JLabel lblTitle;
	JLabel[] lblCon = new JLabel[4];
	JLabel[] lblMenu = new JLabel[4];
	JTextArea txtMenu;
	JScrollPane scrollPane;
	
	String[] menu = {"Á¤½Ä", "ÀÏÇ°¾ç½Ä", "ÇÁ¸®¹Ì¾ö", "½ºÆä¼È", "Áß½Ä", "¼®½Ä"};

	ArrayList<String> list = new ArrayList<>();
	ArrayList<String> list2 = new ArrayList<>();
	
	Nomat() {
		super.setTitle("³ë¸À ÇÐ½Ä");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		int posX = (int) (width / 2) - (WIDTH / 2);
		int posY = (int) (height / 2) - (HEIGHT / 2);
		
		setLayout(null);
		setBounds(posX, posY, WIDTH, HEIGHT);
		//setUndecorated(true);
		//setBackground(new Color(0, 0, 0, 0));
		setIconImage(new ImageIcon(getClass().getResource("img/icon.png")).getImage());
		
		panelTitle = new JPanel();
		panelTitle.setLayout(null);
		panelTitle.setBounds(0, 0, WIDTH, 30);
		panelTitle.setBackground(new Color(0, 0, 0, 0));
		panelTitle.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
			}
		});
		//add(panelTitle);
		
		txtMenu = new JTextArea();
		
		scrollPane = new JScrollPane(txtMenu);
		scrollPane.setBounds(0, 50, WIDTH, HEIGHT - 50);
		add(scrollPane);
		
		lblTitle = new JLabel("³ë¸À ÇÐ½Ä");
		lblTitle.setBounds(FRAME_MARGIN + 8, (30 / 2) - 14, 100, 30);
		lblTitle.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		lblTitle.setForeground(new Color(130, 130, 130));
		panelTitle.add(lblTitle);
		
		btnClose = new JButton();
		btnClose.setBounds(WIDTH - 33, 3, 30, 30);
		btnClose.setIcon(new ImageIcon(getClass().getResource("img/btn_close.png")));
		btnClose.setBackground(new Color(0, 0, 0, 0));
		btnClose.addActionListener(this);
		panelTitle.add(btnClose);
		
		btnMinimize = new JButton();
		btnMinimize.setBounds(WIDTH - 63, 3, 30, 30);
		btnMinimize.setIcon(new ImageIcon(getClass().getResource("img/btn_minimize.png")));
		btnMinimize.setBackground(new Color(0, 0, 0, 0));
		btnMinimize.addActionListener(this);
		panelTitle.add(btnMinimize);
		
		btnLink = new JButton();
		btnLink.setBounds(WIDTH - 93, 3, 30, 30);
		btnLink.setIcon(new ImageIcon(getClass().getResource("img/btn_link.png")));
		btnLink.setBackground(new Color(0, 0, 0, 0));
		btnLink.addActionListener(this);
		panelTitle.add(btnLink);
		
		panelDate = new JPanel();
		panelDate.setLayout(null);
		panelDate.setBounds(0, 40, 120, HEIGHT - 60);
		panelDate.setBackground(new Color(255, 80, 80, 0));
		add(panelDate);
		
		lblDayOfWeek = new JLabel("ÀÏ", SwingConstants.CENTER);
		lblDayOfWeek.setBounds(0, 35, 120, 30);
		lblDayOfWeek.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		lblDayOfWeek.setForeground(new Color(255, 255, 255));
		panelDate.add(lblDayOfWeek);
		
		lblDate = new JLabel("12/31", SwingConstants.CENTER);
		lblDate.setBounds(0, lblDayOfWeek.getY() + 10, 120, 70);
		lblDate.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		lblDate.setForeground(new Color(255, 255, 255));
		panelDate.add(lblDate);
		
		panelCon = new JPanel();
		panelCon.setLayout(new GridLayout(4, 1, 5, 5));
		panelCon.setBounds(panelDate.getX() + 120, panelDate.getY(), 70, HEIGHT - 60);
		panelCon.setBackground(new Color(255, 80, 80, 0));
		add(panelCon);
		
		for(int i = 0; i < lblCon.length; i++) {
			lblCon[i] = new JLabel("Å×½ºÆ®", SwingConstants.LEFT);
			lblCon[i].setSize(70, 30);
			lblCon[i].setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
			lblCon[i].setText(menu[i]);
			panelCon.add(lblCon[i]);
		}
		
		panelMenu = new JPanel();
		panelMenu.setLayout(new GridLayout(4, 1, 5, 5));
		panelMenu.setBounds(panelCon.getX() + 80, panelDate.getY(), 330, HEIGHT - 60);
		panelMenu.setBackground(new Color(255, 80, 80, 0));
		add(panelMenu);
		
		for(int i = 0; i < lblMenu.length; i++) {
			lblMenu[i] = new JLabel("Å×½ºÆ®", SwingConstants.LEFT);
			lblMenu[i].setSize(100, 30);
			lblMenu[i].setFont(new Font("±Ã¼­Ã¼", Font.PLAIN, 13));
			//panelMenu.add(lblMenu[i]);
		}
		
		Thread thread = new Thread(this);
		thread.start();
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void printMenu(String... args) {
		for(int i = 0; i < args.length; i++) {
			if(i == 0) txtMenu.append("***** Áß»ý°ü *****\n");
			if(i == 4) txtMenu.append("\n***** ½ÅÀ¯°ü *****\n");
			txtMenu.append("[");
			txtMenu.append(menu[i]);
			txtMenu.append("]");
			if(i < args.length) txtMenu.append("\n");
			txtMenu.append(args[i]);
			if(i < args.length) txtMenu.append("\n");
		}
	}
	
	public void getMenu() {
		try {
			Document doc = Jsoup.connect("http://m.sungkyul.ac.kr/life/sub03_01.do").get();
			Elements contents = doc.select("div#rstr_stu table.sk_table tbody tr div");
			
			int count = 0;
			
			for(Element element : contents) {
				System.out.println("[" + count + "] " + element.toString());
				list.add(element.text());
				count++;
			}
			
			contents = doc.select("div#rstr_sam table.sk_table tbody tr div");
			for(Element element : contents) {
				System.out.println("[" + count + "] " + element.toString());
				list2.add(element.text());
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String[] str = {"ÀÏ", "¿ù", "È­", "¼ö", "¸ñ", "±Ý", "Åä"};
		
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		
		lblDayOfWeek.setText(str[dayOfWeek - 1]);
		lblDate.setText(month + "/" + date);
		
		int n = (dayOfWeek - 2) * 8;
		int nn = (dayOfWeek - 2) * 4;
		printMenu(list.get(n), list.get(n + 2), list.get(n + 4), list.get(n + 6), list2.get(nn), list2.get(nn + 2));
		
		repaint();
	}

	@Override
	public void run() {
		getMenu();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnClose) {
			System.exit(0);
		} else if(e.getSource() == btnMinimize) {
			setState(JFrame.ICONIFIED);
		} else if(e.getSource() == btnLink) {
			try {
		        Desktop.getDesktop().browse(new URL("https://www.sungkyul.ac.kr/mbs/skukr/jsp/restaurant/restaurant.jsp?configIdx=1&id=skukr_050701000000").toURI());
		    } catch (Exception e1) {
		        e1.printStackTrace();
		    }
		}
	}
}

public class MainFrame {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			//UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		new Nomat();
	}
}
