package UI;

import Database.Handler;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class Menu  implements ActionListener {
      public static JFrame frame;
      private JButton add, query;
      private Point addButtonPoint;
      public static JPanel middlePanel, containerPanel;
      public static JScrollPane middlePanelScrollPane;
      public static ArrayList<Manga> currentMangaList = new ArrayList<Manga>();

      public Menu () {
            frame = new JFrame ("Manga Database");
            frame.setLocationRelativeTo(null);
            frame.setSize(910, 900);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.getContentPane().setLayout(new GridLayout());
            frame.setResizable(false);

            JSplitPane splitPane = new JSplitPane();
            frame.getContentPane().add(splitPane);
            splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
            splitPane.setDividerLocation(100);
            splitPane.setSize(new Dimension(910, 900));
            splitPane.setPreferredSize(new Dimension(910, 900));

            JPanel topPanel = new JPanel();
            topPanel.setBackground(Color.BLACK);

            JLabel title = new JLabel();
            title.setText("Manga Database");
            title.setSize(500, 150);
            title.setLocation(0, 0);
            title.setForeground(Color.WHITE);
            title.setFont(new Font("Arial", Font.BOLD, 40));
            title.setVisible(true);
            topPanel.add(title);

            query = new JButton("QUERY");
            query.setForeground(Color.BLACK);
            query.setBackground(Color.WHITE);
            query.setSize(50, 50);
            query.addActionListener(this);
            query.setVisible(true);
            topPanel.add(query);

            splitPane.setTopComponent(topPanel);

            containerPanel = new JPanel();
            splitPane.setBottomComponent(containerPanel);
            containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

            middlePanel = new JPanel();
            middlePanel.setLayout(new FlowLayout());

            middlePanelScrollPane = new JScrollPane(middlePanel, 22, 31);
            middlePanelScrollPane.getVerticalScrollBar().setUnitIncrement(16);
            middlePanel.setPreferredSize(new Dimension(900, 900));
            //middlePanel.setSize(new Dimension(900, 900));
            containerPanel.add(middlePanelScrollPane);

            add = new JButton();
            add.setSize(210, 200);
            addButtonPoint = new Point(15, 15);
            add.setLocation(addButtonPoint);

            try {
                  Image addimg = ImageIO.read(getClass().getResource("../addButton.png"));
                  add.setIcon(new ImageIcon(addimg));
            }
            catch (Exception e) {
                  e.printStackTrace();
            }

            add.addActionListener(this);
            add.setVisible(true);

            middlePanel.add(add);
            
            Handler.readExistingData();

            frame.setVisible(true);
            frame.pack();
      }

      public void moveAddButton (int pos_x, int pos_y) {
            addButtonPoint.x += pos_x;
            addButtonPoint.y += pos_y;

            if (addButtonPoint.x >= 536) {
                  addButtonPoint.x = 15;
                  addButtonPoint.y += 210;
            }

            if (addButtonPoint.y > 815) {
                  middlePanel.setPreferredSize(new Dimension(900, addButtonPoint.y + 250));
            }
            add.setLocation(addButtonPoint);
            SwingUtilities.updateComponentTreeUI(frame);
      }

      @Override
      public void actionPerformed(ActionEvent e) {
            if (e.getSource() == add) {
                  System.out.println("Add Manga Window Pressed");
                  AddManga addManga = new AddManga("Add a New Manga!");
                  moveAddButton(260, 0);

            }
            else if (e.getSource() == query) {
                  System.out.println("Query Window Pressed");
                  QueryUI queryUI = new QueryUI();
            }
      }
}
