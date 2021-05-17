package UI;

import Database.Handler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MangaWindow implements ActionListener {

      JButton delete, update; // Maybe also a View Button Later on.
      JPanel panel;
      JPanel whatInputed;
      public String path;

      public MangaWindow (String path_to_img, int pos_x, int pos_y, JPanel frame, JFrame mainF) {
            whatInputed = frame;
            path = path_to_img;
            panel = new JPanel();
            panel.setLayout(null);
            panel.setPreferredSize(new Dimension(210, 290));
            panel.setVisible(true);

            JLabel label = new JLabel();
            label.setLocation(pos_x, pos_y);
            label.setSize(210, 265);
            label.setVisible(true);
            try {
                  ImageIcon imageIcon = new ImageIcon(path_to_img);
                  Image image = new ImageIcon(path_to_img).getImage();

                  if (image.getWidth(null) == -1) {
                        ImageIcon ir = new ImageIcon("C:\\Users\\prith\\eclipse-workspace\\Test\\src\\UI\\noImage.png");
                        label.setIcon(ir);
                  }
                  else {
                        label.setIcon(imageIcon);
                  }
                  label.setVisible(true);
                  System.out.println("I Went HERE?");
            }
            catch (Exception e) {
                  //e.printStackTrace();
                  System.out.println("NO IMAGE?");
                  ImageIcon imageIcon = new ImageIcon("C:\\Users\\prith\\eclipse-workspace\\Test\\src\\UI\\noImage.png");
                  label.setIcon(imageIcon);
                  label.setVisible(true);
            }
            panel.add(label);

            delete = new JButton("Delete");
            delete.setSize(new Dimension(110, 15));
            delete.setLocation(0, 275);
            delete.setFont(new Font("Arial", Font.ITALIC, 15));
            delete.setForeground(Color.RED);
            delete.addActionListener(this);
            panel.add(delete);

            update = new JButton ("Update " + path_to_img);
            update.setSize(new Dimension(110, 15));
            update.setLocation(110, 275);
            update.setFont(new Font("Arial", Font.ITALIC, 15));
            update.setForeground(Color.BLUE);
            update.addActionListener(this);
            panel.add(update);
            panel.setVisible(true);

            frame.add(panel);
            SwingUtilities.updateComponentTreeUI(mainF);
      }

      @Override
      public void actionPerformed(ActionEvent e) {
            if (e.getSource() == delete) {
                  // DELETE
                  System.out.println("Delete the manga");
                  
                  Manga toDelete = new Manga();
                  boolean found = false;
                  
                  for (Manga manga: Menu.currentMangaList) {
                        if (manga.mangaImageURL.equals(path)) {
                        	toDelete = manga;
                              Menu.currentMangaList.remove(manga);
                              found = true;
                              break;
                        }
                  }
                  
                  Handler.deleteFromDatabase(toDelete);
                  
                  panel.setVisible(false);
                  panel.removeAll();
            }
            else if (e.getSource() == update) {
                  // UPDATE
                  System.out.println("update the manga " + path);

                  Manga toUpdate = new Manga();
                  boolean found = false;
                  System.out.println("SIZE B4 UPDATE: " + Menu.currentMangaList.size());
                  for (Manga manga: Menu.currentMangaList) {
                        if (manga.mangaImageURL.equals(path)) {
                        	toUpdate = manga;
                              Menu.currentMangaList.remove(manga);
                              found = true;
                              break;
                        }
                  }
                  String look_for = "Update " + path;
                  for (Component c: whatInputed.getComponents()) {
                        if (c instanceof JPanel) {
                              for (Component d: ((JPanel) c).getComponents()) {
                                    if (d instanceof JButton && ((JButton) d).getText().equals(look_for)){
                                          c.setVisible(false);
                                          ((JPanel)c).removeAll();
                                    }
                              }
                        }
                  }

                  System.out.println("SIZE AF UPDATE (SHOULD BE 1 SMALL): " + Menu.currentMangaList.size());

                  if (found) {
                        AddManga addManga = new AddManga("Update Manga!", toUpdate);
                  }
                  else {
                        System.out.println("Could not find the manga specified by that path");
                  }
            }
            // VIEW?
      }
}
