package UI;

public class Manga {
	public int ID;
      public String mangaName;
      public String mangaImageURL;
      public String authorFirstName;
      public String authorLastName;
      public int totalVolume;
      public int currentVolume;
      public int currentChapter;
      public int totalChapter;
      public boolean status;

      public Manga () {
      	ID = 0;
      	
            mangaName = "Enter the name";
            mangaImageURL = "C:\\Users\\prith\\eclipse-workspace\\Test\\img\\";
            authorFirstName = "FirstName";
            authorLastName = "LastName";

            totalVolume = 0;
            currentVolume = 0;
            currentChapter = 0;
            totalChapter = 0;

            status = false;
      }

      public boolean equals (Manga other) {
            return this.mangaImageURL.equals(other.mangaImageURL);
      }

      public String getMangaName () {
            return this.mangaName;
      }
}
