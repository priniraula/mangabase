package UI;

import Database.Handler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddManga implements ActionListener {
      private JFrame frame;
      private JButton submit;
      private Manga manga = new Manga();
      private JToggleButton statusToggleButton;
      private boolean statusSelected;
      private JTextField mangaName, mangaAuthorFirstName, mangaAuthorLastName, mangaIMGURL;
      private JTextField totalVolume, currentVolume, currentChapter, totalChapter;
      public static boolean isUpdating = false;

      public AddManga (String title) {
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

            JLabel heading = new JLabel(title);
            heading.setSize(500, 150);
            heading.setLocation(250, 15);
            heading.setVisible(true);
            heading.setForeground(Color.BLACK);
            heading.setFont(new Font("Arial", Font.BOLD, 45));
            panel.add(heading);

            JLabel askMangaName = new JLabel("Enter the name of the manga: ");
            askMangaName.setSize(300, 50);
            askMangaName.setLocation(15, 165);
            askMangaName.setVisible(true);
            askMangaName.setForeground(Color.BLACK);
            askMangaName.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(askMangaName);

            mangaName = new JTextField(manga.mangaName, 16);
            mangaName.setSize(300, 50);
            mangaName.setLocation(360, 165);
            mangaName.setVisible(true);
            mangaName.setForeground(Color.BLACK);
            mangaName.setFont(new Font("Arial", Font.PLAIN, 15));
            panel.add(mangaName);

            JLabel askMangaAuthorFirstName = new JLabel("Enter the first name of the author: ");
            askMangaAuthorFirstName.setSize(300, 50);
            askMangaAuthorFirstName.setLocation(15, 225);
            askMangaAuthorFirstName.setVisible(true);
            askMangaAuthorFirstName.setForeground(Color.BLACK);
            askMangaAuthorFirstName.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(askMangaAuthorFirstName);

            mangaAuthorFirstName = new JTextField(manga.authorFirstName, 16);
            mangaAuthorFirstName.setSize(300, 50);
            mangaAuthorFirstName.setLocation(360, 225);
            mangaAuthorFirstName.setVisible(true);
            mangaAuthorFirstName.setForeground(Color.BLACK);
            mangaAuthorFirstName.setFont(new Font("Arial", Font.PLAIN, 15));
            panel.add(mangaAuthorFirstName);

            JLabel askMangaAuthorLastName = new JLabel("Enter the last name of the author: ");
            askMangaAuthorLastName.setSize(300, 50);
            askMangaAuthorLastName.setLocation(15, 285);
            askMangaAuthorLastName.setVisible(true);
            askMangaAuthorLastName.setForeground(Color.BLACK);
            askMangaAuthorLastName.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(askMangaAuthorLastName);

            mangaAuthorLastName = new JTextField(manga.authorLastName, 16);
            mangaAuthorLastName.setSize(300, 50);
            mangaAuthorLastName.setLocation(360, 285);
            mangaAuthorLastName.setVisible(true);
            mangaAuthorLastName.setForeground(Color.BLACK);
            mangaAuthorLastName.setFont(new Font("Arial", Font.PLAIN, 15));
            panel.add(mangaAuthorLastName);

            JLabel askMangaIMGURL = new JLabel("Enter the path to image: ");
            askMangaIMGURL.setSize(300, 50);
            askMangaIMGURL.setLocation(15, 345);
            askMangaIMGURL.setVisible(true);
            askMangaIMGURL.setForeground(Color.BLACK);
            askMangaIMGURL.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(askMangaIMGURL);

            mangaIMGURL = new JTextField(manga.mangaImageURL, 16);
            mangaIMGURL.setSize(300, 50);
            mangaIMGURL.setLocation(360, 345);
            mangaIMGURL.setVisible(true);
            mangaIMGURL.setForeground(Color.BLACK);
            mangaIMGURL.setFont(new Font("Arial", Font.PLAIN, 15));
            panel.add(mangaIMGURL);

            JLabel askTotalVolume = new JLabel("Enter the total Volume Count (INT ONLY): ");
            askTotalVolume.setSize(300, 50);
            askTotalVolume.setLocation(15, 405);
            askTotalVolume.setVisible(true);
            askTotalVolume.setForeground(Color.BLACK);
            askTotalVolume.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(askTotalVolume);

            totalVolume = new JTextField(Integer.toString(manga.totalVolume), 16);
            totalVolume.setSize(300, 50);
            totalVolume.setLocation(360, 405);
            totalVolume.setVisible(true);
            totalVolume.setForeground(Color.BLACK);
            totalVolume.setFont(new Font("Arial", Font.PLAIN, 15));
            panel.add(totalVolume);

            JLabel askCurrentVolume = new JLabel("Enter the current Volume Count (INT ONLY): ");
            askCurrentVolume.setSize(300, 50);
            askCurrentVolume.setLocation(15, 465);
            askCurrentVolume.setVisible(true);
            askCurrentVolume.setForeground(Color.BLACK);
            askCurrentVolume.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(askCurrentVolume);

            currentVolume = new JTextField(Integer.toString(manga.currentVolume), 16);
            currentVolume.setSize(300, 50);
            currentVolume.setLocation(360, 465);
            currentVolume.setVisible(true);
            currentVolume.setForeground(Color.BLACK);
            currentVolume.setFont(new Font("Arial", Font.PLAIN, 15));
            panel.add(currentVolume);

            JLabel askCurrentChapter = new JLabel("Enter the current chapter Count (INT ONLY): ");
            askCurrentChapter.setSize(300, 50);
            askCurrentChapter.setLocation(15, 525);
            askCurrentChapter.setVisible(true);
            askCurrentChapter.setForeground(Color.BLACK);
            askCurrentChapter.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(askCurrentChapter);

            currentChapter = new JTextField(Integer.toString(manga.currentChapter), 16);
            currentChapter.setSize(300, 50);
            currentChapter.setLocation(360, 525);
            currentChapter.setVisible(true);
            currentChapter.setForeground(Color.BLACK);
            currentChapter.setFont(new Font("Arial", Font.PLAIN, 15));
            panel.add(currentChapter);

            JLabel askTotalChapter = new JLabel("Enter the total chapters Count (INT ONLY): ");
            askTotalChapter.setSize(300, 50);
            askTotalChapter.setLocation(15, 585);
            askTotalChapter.setVisible(true);
            askTotalChapter.setForeground(Color.BLACK);
            askTotalChapter.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(askTotalChapter);

            totalChapter = new JTextField(Integer.toString(manga.totalChapter), 16);
            totalChapter.setSize(300, 50);
            totalChapter.setLocation(360, 585);
            totalChapter.setVisible(true);
            totalChapter.setForeground(Color.BLACK);
            totalChapter.setFont(new Font("Arial", Font.PLAIN, 15));
            panel.add(totalChapter);

            JLabel askStatus = new JLabel("Is the manga ongoing?: ");
            askStatus.setSize(300, 50);
            askStatus.setLocation(15, 645);
            askStatus.setVisible(true);
            askStatus.setForeground(Color.BLACK);
            askStatus.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(askStatus);

            statusToggleButton = new JToggleButton("Ongoing?");
            statusToggleButton.setSize(300, 50);
            statusToggleButton.setLocation(360, 645);
            statusToggleButton.setVisible(true);
            statusToggleButton.setForeground(Color.BLACK);
            statusToggleButton.setFont(new Font("Arial", Font.PLAIN, 15));
            statusToggleButton.addActionListener(this);
            panel.add(statusToggleButton);

            submit = new JButton("Submit");
            submit.setSize(300, 75);
            submit.setLocation(235, 705);
            submit.setVisible(true);
            submit.setForeground(Color.BLACK);
            submit.setFont(new Font("Arial", Font.BOLD, 25));
            submit.addActionListener(this);
            panel.add(submit);

            frame.add(panel);
            frame.setVisible(true);
            frame.pack();
      }

      AddManga(String title, Manga existingManga) {
      	isUpdating = true;
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

            JLabel heading = new JLabel(title);
            heading.setSize(500, 150);
            heading.setLocation(250, 15);
            heading.setVisible(true);
            heading.setForeground(Color.BLACK);
            heading.setFont(new Font("Arial", Font.BOLD, 45));
            panel.add(heading);

            JLabel askMangaName = new JLabel("Enter the name of the manga: ");
            askMangaName.setSize(300, 50);
            askMangaName.setLocation(15, 165);
            askMangaName.setVisible(true);
            askMangaName.setForeground(Color.BLACK);
            askMangaName.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(askMangaName);

            mangaName = new JTextField(existingManga.mangaName, 16);
            mangaName.setSize(300, 50);
            mangaName.setLocation(360, 165);
            mangaName.setVisible(true);
            mangaName.setForeground(Color.BLACK);
            mangaName.setFont(new Font("Arial", Font.PLAIN, 15));
            panel.add(mangaName);

            JLabel askMangaAuthorFirstName = new JLabel("Enter the first name of the author: ");
            askMangaAuthorFirstName.setSize(300, 50);
            askMangaAuthorFirstName.setLocation(15, 225);
            askMangaAuthorFirstName.setVisible(true);
            askMangaAuthorFirstName.setForeground(Color.BLACK);
            askMangaAuthorFirstName.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(askMangaAuthorFirstName);

            mangaAuthorFirstName = new JTextField(existingManga.authorFirstName, 16);
            mangaAuthorFirstName.setSize(300, 50);
            mangaAuthorFirstName.setLocation(360, 225);
            mangaAuthorFirstName.setVisible(true);
            mangaAuthorFirstName.setForeground(Color.BLACK);
            mangaAuthorFirstName.setFont(new Font("Arial", Font.PLAIN, 15));
            panel.add(mangaAuthorFirstName);

            JLabel askMangaAuthorLastName = new JLabel("Enter the last name of the author: ");
            askMangaAuthorLastName.setSize(300, 50);
            askMangaAuthorLastName.setLocation(15, 285);
            askMangaAuthorLastName.setVisible(true);
            askMangaAuthorLastName.setForeground(Color.BLACK);
            askMangaAuthorLastName.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(askMangaAuthorLastName);

            mangaAuthorLastName = new JTextField(existingManga.authorLastName, 16);
            mangaAuthorLastName.setSize(300, 50);
            mangaAuthorLastName.setLocation(360, 285);
            mangaAuthorLastName.setVisible(true);
            mangaAuthorLastName.setForeground(Color.BLACK);
            mangaAuthorLastName.setFont(new Font("Arial", Font.PLAIN, 15));
            panel.add(mangaAuthorLastName);

            JLabel askMangaIMGURL = new JLabel("Enter the path to image: ");
            askMangaIMGURL.setSize(300, 50);
            askMangaIMGURL.setLocation(15, 345);
            askMangaIMGURL.setVisible(true);
            askMangaIMGURL.setForeground(Color.BLACK);
            askMangaIMGURL.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(askMangaIMGURL);

            mangaIMGURL = new JTextField(existingManga.mangaImageURL, 16);
            mangaIMGURL.setSize(300, 50);
            mangaIMGURL.setLocation(360, 345);
            mangaIMGURL.setVisible(true);
            mangaIMGURL.setForeground(Color.BLACK);
            mangaIMGURL.setFont(new Font("Arial", Font.PLAIN, 15));
            panel.add(mangaIMGURL);

            JLabel askTotalVolume = new JLabel("Enter the total Volume Count (INT ONLY): ");
            askTotalVolume.setSize(300, 50);
            askTotalVolume.setLocation(15, 405);
            askTotalVolume.setVisible(true);
            askTotalVolume.setForeground(Color.BLACK);
            askTotalVolume.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(askTotalVolume);

            totalVolume = new JTextField(Integer.toString(existingManga.totalVolume), 16);
            totalVolume.setSize(300, 50);
            totalVolume.setLocation(360, 405);
            totalVolume.setVisible(true);
            totalVolume.setForeground(Color.BLACK);
            totalVolume.setFont(new Font("Arial", Font.PLAIN, 15));
            panel.add(totalVolume);

            JLabel askCurrentVolume = new JLabel("Enter the current Volume Count (INT ONLY): ");
            askCurrentVolume.setSize(300, 50);
            askCurrentVolume.setLocation(15, 465);
            askCurrentVolume.setVisible(true);
            askCurrentVolume.setForeground(Color.BLACK);
            askCurrentVolume.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(askCurrentVolume);

            currentVolume = new JTextField(Integer.toString(existingManga.currentVolume), 16);
            currentVolume.setSize(300, 50);
            currentVolume.setLocation(360, 465);
            currentVolume.setVisible(true);
            currentVolume.setForeground(Color.BLACK);
            currentVolume.setFont(new Font("Arial", Font.PLAIN, 15));
            panel.add(currentVolume);

            JLabel askCurrentChapter = new JLabel("Enter the current chapter Count (INT ONLY): ");
            askCurrentChapter.setSize(300, 50);
            askCurrentChapter.setLocation(15, 525);
            askCurrentChapter.setVisible(true);
            askCurrentChapter.setForeground(Color.BLACK);
            askCurrentChapter.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(askCurrentChapter);

            currentChapter = new JTextField(Integer.toString(existingManga.currentChapter), 16);
            currentChapter.setSize(300, 50);
            currentChapter.setLocation(360, 525);
            currentChapter.setVisible(true);
            currentChapter.setForeground(Color.BLACK);
            currentChapter.setFont(new Font("Arial", Font.PLAIN, 15));
            panel.add(currentChapter);

            JLabel askTotalChapter = new JLabel("Enter the total chapters Count (INT ONLY): ");
            askTotalChapter.setSize(300, 50);
            askTotalChapter.setLocation(15, 585);
            askTotalChapter.setVisible(true);
            askTotalChapter.setForeground(Color.BLACK);
            askTotalChapter.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(askTotalChapter);

            totalChapter = new JTextField(Integer.toString(existingManga.totalChapter), 16);
            totalChapter.setSize(300, 50);
            totalChapter.setLocation(360, 585);
            totalChapter.setVisible(true);
            totalChapter.setForeground(Color.BLACK);
            totalChapter.setFont(new Font("Arial", Font.PLAIN, 15));
            panel.add(totalChapter);

            JLabel askStatus = new JLabel("Is the manga ongoing?: ");
            askStatus.setSize(300, 50);
            askStatus.setLocation(15, 645);
            askStatus.setVisible(true);
            askStatus.setForeground(Color.BLACK);
            askStatus.setFont(new Font("Arial", Font.ITALIC, 15));
            panel.add(askStatus);

            statusToggleButton = new JToggleButton("Ongoing?");
            statusToggleButton.setSize(300, 50);
            statusToggleButton.setLocation(360, 645);
            statusToggleButton.setVisible(true);
            statusToggleButton.setForeground(Color.BLACK);
            statusToggleButton.setFont(new Font("Arial", Font.PLAIN, 15));
            statusToggleButton.addActionListener(this);
            panel.add(statusToggleButton);

            submit = new JButton("Submit");
            submit.setSize(300, 75);
            submit.setLocation(235, 705);
            submit.setVisible(true);
            submit.setForeground(Color.BLACK);
            submit.setFont(new Font("Arial", Font.BOLD, 25));
            submit.addActionListener(this);
            panel.add(submit);

            frame.add(panel);
            frame.setVisible(true);
            frame.pack();
      }

      public Manga getManga () {
            return this.manga;
      }

      @Override
      public void actionPerformed(ActionEvent e) {
            if (e.getSource() == statusToggleButton) {
                  AbstractButton abstractButton = (AbstractButton) e.getSource();
                  statusSelected = abstractButton.getModel().isSelected();

                  if (statusSelected) {
                        statusToggleButton.setText("Completed?");
                  }
                  else {
                        statusToggleButton.setText("Ongoing?");
                  }

            }
            else if (e.getSource() == submit) {
                  // Add checks to see if each text box has valid answers only.
                  // But only do that when you got time

                        manga.mangaName = mangaName.getText();
                        manga.authorFirstName = mangaAuthorFirstName.getText();
                        manga.authorLastName = mangaAuthorLastName.getText();
                        manga.mangaImageURL = mangaIMGURL.getText();
                        manga.totalVolume = Integer.parseInt(totalVolume.getText());
                        manga.currentVolume = Integer.parseInt(currentVolume.getText());
                        manga.totalChapter = Integer.parseInt(totalChapter.getText());
                        manga.currentChapter = Integer.parseInt(currentChapter.getText());
                        manga.status = statusSelected;

                        Menu.currentMangaList.add(manga);

                        MangaWindow mangaWindow = new MangaWindow(
                                manga.mangaImageURL, 15, 15, Menu.middlePanel, Menu.frame
                        );


                  SwingUtilities.updateComponentTreeUI(Menu.frame);
                  
                  if (!isUpdating) {
                  	Handler.writeToDatabase(manga);
                  }
                  else {
                  	Handler.updateToDatabase(manga);
                  	isUpdating = false;
                  }
                  frame.setVisible(false);
                  frame.dispose();
            }
      }
}
