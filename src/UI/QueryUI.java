package UI;

import Database.Handler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueryUI implements ActionListener {

      private JFrame frame;
      private JButton submit;

      private JToggleButton getMangaName, getMangaAuthorFirstName, getMangaAuthorLastName, getMangaIMGUI;
      private JToggleButton getTotalVolume, getCurrentVolume, getTotalChapter, getCurrentChapter;
      private JToggleButton getStatus, getFullDatabase;

      public static boolean isMangaNameSelected = false, isMangaAuthorFirstNameSelected = false, isMangaAuthorLastNameSelected = false, isMangaIMGUISelected = false;
      public static boolean isTotalVolumeSelected = false, isCurrentVolumeSelected = false, isTotalChapterSelected = false, isCurrentChapterSelected = false;
      public static boolean isStatusSelected = false, isFullDatabaseSelected = false;

      public QueryUI() {
            frame = new JFrame();
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setSize(900, 900);
            //remove this default close operation later on.
            //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setBackground(Color.WHITE);
            panel.setPreferredSize(new Dimension(900, 900));
            panel.setLayout(null);

            JLabel heading = new JLabel("Query Manga!");
            heading.setSize(500, 150);
            heading.setLocation(250, 15);
            heading.setVisible(true);
            heading.setForeground(Color.BLACK);
            heading.setFont(new Font("Arial", Font.BOLD, 45));
            panel.add(heading);

            JLabel queryMangaName = new JLabel("Query Manga Name? ");
            queryMangaName.setSize(300, 50);
            queryMangaName.setLocation(15, 165);
            queryMangaName.setVisible(true);
            queryMangaName.setForeground(Color.BLACK);
            queryMangaName.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(queryMangaName);

            getMangaName = new JToggleButton("NO");
            getMangaName.setSize(300, 50);
            getMangaName.setLocation(360, 165);
            getMangaName.setVisible(true);
            getMangaName.setForeground(Color.BLACK);
            getMangaName.setFont(new Font("Arial", Font.PLAIN, 15));
            getMangaName.addActionListener(this);
            panel.add(getMangaName);

            JLabel queryMangaAuthorFirstName = new JLabel("Query Manga Author First Name? ");
            queryMangaAuthorFirstName.setSize(300, 50);
            queryMangaAuthorFirstName.setLocation(15, 225);
            queryMangaAuthorFirstName.setVisible(true);
            queryMangaAuthorFirstName.setForeground(Color.BLACK);
            queryMangaAuthorFirstName.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(queryMangaAuthorFirstName);

            getMangaAuthorFirstName = new JToggleButton("NO");
            getMangaAuthorFirstName.setSize(300, 50);
            getMangaAuthorFirstName.setLocation(360, 225);
            getMangaAuthorFirstName.setVisible(true);
            getMangaAuthorFirstName.setForeground(Color.BLACK);
            getMangaAuthorFirstName.setFont(new Font("Arial", Font.PLAIN, 15));
            getMangaAuthorFirstName.addActionListener(this);
            panel.add(getMangaAuthorFirstName);

            JLabel queryMangaAuthorLastName = new JLabel("Query Manga Author Last Name? ");
            queryMangaAuthorLastName.setSize(300, 50);
            queryMangaAuthorLastName.setLocation(15, 285);
            queryMangaAuthorLastName.setVisible(true);
            queryMangaAuthorLastName.setForeground(Color.BLACK);
            queryMangaAuthorLastName.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(queryMangaAuthorLastName);

            getMangaAuthorLastName = new JToggleButton("NO");
            getMangaAuthorLastName.setSize(300, 50);
            getMangaAuthorLastName.setLocation(360, 285);
            getMangaAuthorLastName.setVisible(true);
            getMangaAuthorLastName.setForeground(Color.BLACK);
            getMangaAuthorLastName.setFont(new Font("Arial", Font.PLAIN, 15));
            getMangaAuthorLastName.addActionListener(this);
            panel.add(getMangaAuthorLastName);

            JLabel queryMangaIMGURL = new JLabel("Query Manga Image URL? ");
            queryMangaIMGURL.setSize(300, 50);
            queryMangaIMGURL.setLocation(15, 345);
            queryMangaIMGURL.setVisible(true);
            queryMangaIMGURL.setForeground(Color.BLACK);
            queryMangaIMGURL.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(queryMangaIMGURL);

            getMangaIMGUI = new JToggleButton("NO");
            getMangaIMGUI.setSize(300, 50);
            getMangaIMGUI.setLocation(360, 345);
            getMangaIMGUI.setVisible(true);
            getMangaIMGUI.setForeground(Color.BLACK);
            getMangaIMGUI.setFont(new Font("Arial", Font.PLAIN, 15));
            getMangaIMGUI.addActionListener(this);
            panel.add(getMangaIMGUI);

            JLabel queryTotalVolume = new JLabel("Query Total Volume? ");
            queryTotalVolume.setSize(300, 50);
            queryTotalVolume.setLocation(15, 405);
            queryTotalVolume.setVisible(true);
            queryTotalVolume.setForeground(Color.BLACK);
            queryTotalVolume.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(queryTotalVolume);

            getTotalVolume = new JToggleButton("NO");
            getTotalVolume.setSize(300, 50);
            getTotalVolume.setLocation(360, 405);
            getTotalVolume.setVisible(true);
            getTotalVolume.setForeground(Color.BLACK);
            getTotalVolume.setFont(new Font("Arial", Font.PLAIN, 15));
            getTotalVolume.addActionListener(this);
            panel.add(getTotalVolume);

            JLabel queryCurrentVolume = new JLabel("Query Current Volume? ");
            queryCurrentVolume.setSize(300, 50);
            queryCurrentVolume.setLocation(15, 465);
            queryCurrentVolume.setVisible(true);
            queryCurrentVolume.setForeground(Color.BLACK);
            queryCurrentVolume.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(queryCurrentVolume);

            getCurrentVolume = new JToggleButton("NO");
            getCurrentVolume.setSize(300, 50);
            getCurrentVolume.setLocation(360, 465);
            getCurrentVolume.setVisible(true);
            getCurrentVolume.setForeground(Color.BLACK);
            getCurrentVolume.setFont(new Font("Arial", Font.PLAIN, 15));
            getCurrentVolume.addActionListener(this);
            panel.add(getCurrentVolume);

            JLabel queryTotalChapter = new JLabel("Query Total Chapter? ");
            queryTotalChapter.setSize(300, 50);
            queryTotalChapter.setLocation(15, 525);
            queryTotalChapter.setVisible(true);
            queryTotalChapter.setForeground(Color.BLACK);
            queryTotalChapter.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(queryTotalChapter);

            getTotalChapter = new JToggleButton("NO");
            getTotalChapter.setSize(300, 50);
            getTotalChapter.setLocation(360, 525);
            getTotalChapter.setVisible(true);
            getTotalChapter.setForeground(Color.BLACK);
            getTotalChapter.setFont(new Font("Arial", Font.PLAIN, 15));
            getTotalChapter.addActionListener(this);
            panel.add(getTotalChapter);

            JLabel queryCurrentChapter = new JLabel("Query Current Chapter? ");
            queryCurrentChapter.setSize(300, 50);
            queryCurrentChapter.setLocation(15, 585);
            queryCurrentChapter.setVisible(true);
            queryCurrentChapter.setForeground(Color.BLACK);
            queryCurrentChapter.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(queryCurrentChapter);

            getCurrentChapter = new JToggleButton("NO");
            getCurrentChapter.setSize(300, 50);
            getCurrentChapter.setLocation(360, 585);
            getCurrentChapter.setVisible(true);
            getCurrentChapter.setForeground(Color.BLACK);
            getCurrentChapter.setFont(new Font("Arial", Font.PLAIN, 15));
            getCurrentChapter.addActionListener(this);
            panel.add(getCurrentChapter);

            JLabel queryStatus = new JLabel("Query Manga Status? ");
            queryStatus.setSize(300, 50);
            queryStatus.setLocation(15, 645);
            queryStatus.setVisible(true);
            queryStatus.setForeground(Color.BLACK);
            queryStatus.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(queryStatus);

            getStatus = new JToggleButton("NO");
            getStatus.setSize(300, 50);
            getStatus.setLocation(360, 645);
            getStatus.setVisible(true);
            getStatus.setForeground(Color.BLACK);
            getStatus.setFont(new Font("Arial", Font.PLAIN, 15));
            getStatus.addActionListener(this);
            panel.add(getStatus);

            JLabel queryFullDatabase = new JLabel("Get all mangas listed in Database? ");
            queryFullDatabase.setSize(300, 50);
            queryFullDatabase.setLocation(15, 705);
            queryFullDatabase.setVisible(true);
            queryFullDatabase.setForeground(Color.BLACK);
            queryFullDatabase.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(queryFullDatabase);

            getFullDatabase = new JToggleButton("NO");
            getFullDatabase.setSize(300, 50);
            getFullDatabase.setLocation(360, 705);
            getFullDatabase.setVisible(true);
            getFullDatabase.setForeground(Color.BLACK);
            getFullDatabase.setFont(new Font("Arial", Font.PLAIN, 15));
            getFullDatabase.addActionListener(this);
            panel.add(getFullDatabase);

            submit = new JButton("Submit");
            submit.setSize(300, 75);
            submit.setLocation(235, 765);
            submit.setVisible(true);
            submit.setForeground(Color.BLACK);
            submit.setFont(new Font("Arial", Font.BOLD, 25));
            submit.addActionListener(this);
            panel.add(submit);

            frame.add(panel);
            frame.setVisible(true);
            frame.pack();
      }

      @Override
      public void actionPerformed(ActionEvent e) {
            if (e.getSource() == submit) {
                  // DO QUERY SQL SHIT
            	
            	Handler.queryManga();

                  frame.setVisible(false);
                  frame.dispose();
            }
            else if (e.getSource() == getMangaName) {
                  AbstractButton abstractButton = (AbstractButton) e.getSource();
                  isMangaNameSelected = abstractButton.getModel().isSelected();

                  if (isMangaNameSelected) getMangaName.setText("YES");
                  else getMangaName.setText("NO");
            }
            else if (e.getSource() == getMangaAuthorFirstName) {
                  AbstractButton abstractButton = (AbstractButton) e.getSource();
                  isMangaAuthorFirstNameSelected = abstractButton.getModel().isSelected();

                  if (isMangaAuthorFirstNameSelected) getMangaAuthorFirstName.setText("YES");
                  else getMangaAuthorFirstName.setText("NO");
            }
            else if (e.getSource() == getMangaAuthorLastName) {
                  AbstractButton abstractButton = (AbstractButton) e.getSource();
                  isMangaAuthorLastNameSelected = abstractButton.getModel().isSelected();

                  if (isMangaAuthorLastNameSelected) getMangaAuthorLastName.setText("YES");
                  else getMangaAuthorLastName.setText("NO");
            }
            else if (e.getSource() == getMangaIMGUI) {
                  AbstractButton abstractButton = (AbstractButton) e.getSource();
                  isMangaIMGUISelected = abstractButton.getModel().isSelected();

                  if (isMangaIMGUISelected) getMangaIMGUI.setText("YES");
                  else getMangaIMGUI.setText("NO");
            }
            else if (e.getSource() == getTotalVolume) {
                  AbstractButton abstractButton = (AbstractButton) e.getSource();
                  isTotalVolumeSelected = abstractButton.getModel().isSelected();

                  if (isTotalVolumeSelected) getTotalVolume.setText("YES");
                  else getTotalVolume.setText("NO");
            }
            else if (e.getSource() == getCurrentVolume) {
                  AbstractButton abstractButton = (AbstractButton) e.getSource();
                  isCurrentVolumeSelected = abstractButton.getModel().isSelected();

                  if (isCurrentVolumeSelected) getCurrentVolume.setText("YES");
                  else getCurrentVolume.setText("NO");
            }
            else if (e.getSource() == getTotalChapter) {
                  AbstractButton abstractButton = (AbstractButton) e.getSource();
                  isTotalChapterSelected = abstractButton.getModel().isSelected();

                  if (isTotalChapterSelected) getTotalChapter.setText("YES");
                  else getTotalChapter.setText("NO");
            }
            else if (e.getSource() == getCurrentChapter) {
                  AbstractButton abstractButton = (AbstractButton) e.getSource();
                  isCurrentChapterSelected = abstractButton.getModel().isSelected();

                  if (isCurrentChapterSelected) getCurrentChapter.setText("YES");
                  else getCurrentChapter.setText("NO");
            }
            else if (e.getSource() == getStatus) {
                  AbstractButton abstractButton = (AbstractButton) e.getSource();
                  isStatusSelected = abstractButton.getModel().isSelected();

                  if (isStatusSelected) getStatus.setText("YES");
                  else getStatus.setText("NO");
            }
            else if (e.getSource() == getFullDatabase) {
                  AbstractButton abstractButton = (AbstractButton) e.getSource();
                  isFullDatabaseSelected = abstractButton.getModel().isSelected();

                  if (isFullDatabaseSelected) getFullDatabase.setText("YES");
                  else getFullDatabase.setText("NO");
            }
      }

}
