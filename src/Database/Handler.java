package Database;

import UI.Manga;
import UI.MangaWindow;
import UI.Menu;
import UI.QueryUI;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class Handler {
	
	public static final String path = "NAME_GOES_HERE";
	public static final String userName = "USER_NAME_GOES_HERE";
	public static final String password = "PASSWORD_GOES_HERE"; 
	public static int incremental = 0;
	
	public static Connection connection;
	
	public static void initializeConnection () {
		try {
			connection = DriverManager.getConnection(path, userName, password);
		}
		catch (Exception e) {
			System.out.println("Could not establish connection to the data server.");
			e.printStackTrace();
		}
	}
	
	//TODO
	public static void readExistingData () {
		// read existing data from the server and add it to the manga UI;
		initializeConnection();
		
		String sqlStatement = "SELECT * FROM current_manga";
		
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sqlStatement);
			
			while (result.next()) {
				Manga tempManga = new Manga();
				
				tempManga.mangaName = result.getString("manga_name");
				tempManga.authorFirstName = result.getString("author_first_name");
				tempManga.authorLastName = result.getString("author_last_name");
				tempManga.mangaImageURL = result.getString("manga_image_url");
				tempManga.totalVolume = Integer.parseInt(result.getString("total_volume"));
				tempManga.totalChapter = Integer.parseInt(result.getString("total_chapter"));
				tempManga.currentChapter = Integer.parseInt(result.getString("current_chapter"));
				tempManga.currentVolume = Integer.parseInt(result.getString("current_volume"));
				tempManga.status = (result.getString("status").equals("1") ? true : false);
				
				MangaWindow mangaWindow = new MangaWindow(
	                                tempManga.mangaImageURL, 15, 15, Menu.middlePanel, Menu.frame
	                  );
				
				Menu.currentMangaList.add(tempManga);
			}
			
		}
		catch (Exception e) {
			System.out.println("Problem trying to read Existing Data from the server.");
		}
	}
	
	//TODO
      public static void writeToDatabase (Manga manga) {
            // Write a new manga
      	initializeConnection();
      	
      	String endQuote = "', '";
      	String sqlStatement = "insert into current_manga "
      				  + "(manga_name, author_first_name, author_last_name, manga_image_url, total_volume, "
      				  + "total_chapter, current_volume, current_chapter, status)" + " values ('"
      				  + manga.mangaName + endQuote + manga.authorFirstName + endQuote + manga.authorLastName + endQuote 
      				  + manga.mangaImageURL + endQuote + manga.totalVolume + endQuote + manga.totalChapter + endQuote 
      				  + manga.currentVolume + endQuote + manga.currentChapter + endQuote + (manga.status == true ? 1 : 0)+ "')";
      	
      	
      	System.out.println(sqlStatement);
      	
      	try {
      		Statement statement = connection.createStatement();
      		statement.executeUpdate(sqlStatement);
      		System.out.println("Successfully inserted into the Database");
      	}
      	catch (Exception e) {
      		System.out.println("Problem trying to write to database");
      		e.printStackTrace();
      	}
      }

      //TODO
      public static void updateToDatabase (Manga manga) {
            // Update existing manga from the database
      	initializeConnection();
      	
      	String sqlStatement = "update current_manga set manga_name='" + manga.mangaName + "', author_first_name='" + manga.authorFirstName
      				  + "', author_last_name='" + manga.authorLastName + "', manga_image_url='" + manga.mangaImageURL + "', " 
      				  + "total_volume='" + manga.totalVolume + "', total_chapter='" + manga.totalChapter + "', current_volume='" 
      				  + manga.currentVolume + "', current_chapter='" + manga.currentChapter + "', status='" + (manga.status == true ? 1 : 0)
      				  + "' WHERE manga_image_url=" + "'" + manga.mangaImageURL + "'";
      	System.out.println(sqlStatement);
      	try {
      		Statement statement = connection.createStatement();
      		statement.executeUpdate(sqlStatement);
      		System.out.println("Successfully inserted into the database");
      	}
      	catch (Exception e) {
      		System.out.println("Problem trying to update to database");
      		e.printStackTrace();
      	}
      }

      //TODO
      public static void deleteFromDatabase (Manga manga) {
            // Delete existing manga from the database
      	initializeConnection();
      	
      	String endQuote = "', '";
      	String sqlStatement1 = "delete from current_manga where manga_name = '" + manga.mangaName + "'";
      	String sqlStatement2 = "insert into deleted_manga "
				  + "(manga_name, author_first_name, author_last_name, manga_image_url, total_volume, "
				  + "total_chapter, current_volume, current_chapter, status)" + " values ('"
				  + manga.mangaName + endQuote + manga.authorFirstName + endQuote + manga.authorLastName + endQuote 
				  + manga.mangaImageURL + endQuote + manga.totalVolume + endQuote + manga.totalChapter + endQuote 
				  + manga.currentVolume + endQuote + manga.currentChapter + endQuote + (manga.status == true ? 1 : 0) + "')";
      	
      	try {
      		Statement statement = connection.createStatement();
      		int rowsAffected = statement.executeUpdate(sqlStatement1);
      		System.out.println("Successfully deleted from the database");
      		statement.executeUpdate(sqlStatement2);
      		System.out.println("Successfully inserted into Database of deleted_manga");
      	}
      	catch (Exception e) {
      		System.out.println("Problem trying to delete to database");
      		e.printStackTrace();
      	}
      	
      }
	
	
      public static boolean isAllCurrentQueryOptionSelected () {
            return (QueryUI.isMangaNameSelected && QueryUI.isMangaAuthorFirstNameSelected && QueryUI.isMangaAuthorLastNameSelected &&
                   QueryUI.isMangaIMGUISelected && QueryUI.isTotalVolumeSelected && QueryUI.isTotalChapterSelected &&
                   QueryUI.isCurrentChapterSelected && QueryUI.isCurrentVolumeSelected && QueryUI.isStatusSelected &&
                    (!QueryUI.isFullDatabaseSelected));
      }

      public static boolean isAllMangaQueryOptionSelected () {
            return (QueryUI.isMangaNameSelected && QueryUI.isMangaAuthorFirstNameSelected && QueryUI.isMangaAuthorLastNameSelected &&
                    QueryUI.isMangaIMGUISelected && QueryUI.isTotalVolumeSelected && QueryUI.isTotalChapterSelected &&
                    QueryUI.isCurrentChapterSelected && QueryUI.isCurrentVolumeSelected && QueryUI.isStatusSelected &&
                    QueryUI.isFullDatabaseSelected);
      }
	
	public static Manga getSpecifiedManga (String path) {
		System.out.println("getSpecifiedManga (" + path + ")");
		initializeConnection();
		Manga manga = new Manga();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM current_manga WHERE manga_image_url = '" + path + "'");
			
			manga.mangaName = result.getString("manga_name");
			manga.authorFirstName = result.getString("author_first_name");
			manga.authorLastName = result.getString("author_last_name");
			manga.mangaImageURL = result.getString("manga_image_url");
			manga.totalVolume = Integer.parseInt(result.getString("total_volume"));
			manga.totalChapter = Integer.parseInt(result.getString("total_chapter"));
			manga.currentChapter = Integer.parseInt(result.getString("current_chapter"));
			manga.currentVolume = Integer.parseInt(result.getString("current_volume"));
			
			if (result.getString("status").equals("1")) {
				manga.status = true;
			}
			else {
				manga.status = false;
			}
			
		}
		catch (Exception e) {
			manga = null;
			System.out.println("Error during query execution");
			e.printStackTrace();
		}
		
		return manga;
	}
	
	/*
	public Vector<String> getResultSetInformation (ResultSet result) {
		// CHECK to see if ResultSet's reference is not leaked or smt
		Vector<String> temporary = new Vector<String>
	}
	
	*/
	
	public static void queryManga () {
		String file_name = "", executionStatement = "";
		Vector<Vector<String>> contents = new Vector<Vector<String>>();
		initializeConnection();
		
		if (isAllCurrentQueryOptionSelected()) {
			// SELECT * FROM current_manga
			System.out.println("isAllCurrentQueryOptionSelected");
			file_name = "CurrentMangaList" + incremental;
			executionStatement = "SELECT * FROM current_manga";
			
			try {
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(executionStatement);
				
				while (result.next()) {
					Vector<String> temporary = new Vector<String> ();
					
					temporary.add("Manga Name = " + result.getString("manga_name"));
					temporary.add("Author First Name = " + result.getString("author_first_name"));
					temporary.add("Author Last Name = " + result.getString("author_last_name"));
					temporary.add("Manga Image URL = " + result.getString("manga_image_url"));
					temporary.add("Total Volume = " + result.getString("total_volume"));
					temporary.add("Total Chapter = " + result.getString("total_chapter"));
					temporary.add("Current Volume = " + result.getString("current_volume"));
					temporary.add("Current Chapter = " + result.getString("current_chapter"));
					temporary.add("Status = " + result.getString("status"));
					contents.add(temporary);
				}
				
			}
			catch (Exception e) {
				System.out.println("Problem with isAllCurrentQueryOptionSelected() ");
				e.printStackTrace();
			}
			
		}
		else if (isAllMangaQueryOptionSelected()) {
			// SELECT * FROM current_manga
			// SELECT * FROM deleted_manga
			file_name = "CurrentAndDeletedMangaList" + incremental;
			System.out.println("isAllMangaQueryOptionSelected");
			executionStatement = "SELECT * FROM current_manga";
			
			try {
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(executionStatement);
				
				while (result.next()) {
					Vector<String> temporary = new Vector<String> ();
					
					temporary.add("Manga Name = " + result.getString("manga_name"));
					temporary.add("Author First Name = " + result.getString("author_first_name"));
					temporary.add("Author Last Name = " + result.getString("author_last_name"));
					temporary.add("Manga Image URL = " + result.getString("manga_image_url"));
					temporary.add("Total Volume = " + result.getString("total_volume"));
					temporary.add("Total Chapter = " + result.getString("total_chapter"));
					temporary.add("Current Volume = " + result.getString("current_volume"));
					temporary.add("Current Chapter = " + result.getString("current_chapter"));
					temporary.add("Status = " + result.getString("status"));
					contents.add(temporary);
				}
				
				executionStatement = "SELECT * FROM deleted_manga";
				statement = connection.createStatement();
				result = statement.executeQuery(executionStatement);
				
				while (result.next()) {
					Vector<String> temporary = new Vector<String> ();
					
					temporary.add("Deleted Manga");
					temporary.add("Manga Name = " + result.getString("manga_name"));
					temporary.add("Author First Name = " + result.getString("author_first_name"));
					temporary.add("Author Last Name = " + result.getString("author_last_name"));
					temporary.add("Manga Image URL = " + result.getString("manga_image_url"));
					temporary.add("Total Volume = " + result.getString("total_volume"));
					temporary.add("Total Chapter = " + result.getString("total_chapter"));
					temporary.add("Current Volume = " + result.getString("current_volume"));
					temporary.add("Current Chapter = " + result.getString("current_chapter"));
					temporary.add("Status = " + result.getString("status"));
					contents.add(temporary);
				}
				
			}
			catch (Exception e) {
				System.out.println("Problem with isAllCurrentQueryOptionSelected() ");
				e.printStackTrace();
			}
			
		}
		else {
			file_name = "CustomSpecificationMangaList" + incremental;
			System.out.println("ELSE LOL");
			
			executionStatement = "SELECT ";
			
			if (QueryUI.isMangaNameSelected) executionStatement += "manga_name";
			if (QueryUI.isMangaAuthorFirstNameSelected) executionStatement += ", author_first_name";
			if (QueryUI.isMangaAuthorLastNameSelected) executionStatement += ", author_last_name";
			if (QueryUI.isMangaIMGUISelected) executionStatement += ", manga_image_url";
			if (QueryUI.isTotalVolumeSelected) executionStatement += ", total_volume";
			if (QueryUI.isTotalChapterSelected) executionStatement += ", total_chapter";
			if (QueryUI.isCurrentChapterSelected) executionStatement += ", current_chapter";
			if (QueryUI.isCurrentVolumeSelected) executionStatement += ", current_volume";
			if (QueryUI.isStatusSelected) executionStatement += ", status";
	
			executionStatement += " FROM current_manga";
			
			try {
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(executionStatement);
				
				while (result.next()) {
					Vector<String> temporary = new Vector<String>();
					
					if (QueryUI.isMangaNameSelected) temporary.add("Manga Name = " + result.getString("manga_name"));
					if (QueryUI.isMangaAuthorFirstNameSelected) temporary.add("Author First Name = " + result.getString("author_first_name"));
					if (QueryUI.isMangaAuthorLastNameSelected) temporary.add("Author Last Name = " + result.getString("author_last_name"));
					if (QueryUI.isMangaIMGUISelected) temporary.add("Manga Image URL = " + result.getString("manga_image_url"));
					if (QueryUI.isTotalVolumeSelected)temporary.add("Total Volume = " + result.getString("total_volume"));
					if (QueryUI.isTotalChapterSelected) temporary.add("Total Chapter = " + result.getString("total_chapter"));
					if (QueryUI.isCurrentChapterSelected) temporary.add("Current Chapter = " + result.getString("current_chapter"));
					if (QueryUI.isCurrentVolumeSelected) temporary.add("Current Volume = " + result.getString("current_volume"));
					if (QueryUI.isStatusSelected) temporary.add("Status = " + result.getString("status"));
					
					contents.add(temporary);
				}
				
			}
			catch (Exception e){
				System.out.println("Problem with the else statement in the query");
				e.printStackTrace();
			}
		}
		
		++incremental;
		
		createFile(contents, file_name);
	}
	
	public static void createFile (Vector<Vector<String>> content, String file_name) {
		if (content == null) {
			return;
		}
		try {
			PrintWriter writer = new PrintWriter(new FileOutputStream(file_name, true));
			
			for (Vector<String> container: content) {
				for (String word: container) {
					writer.println(word);
				}
				writer.println("");
			}
			writer.println("");
			writer.close();
			
			System.out.println("Successfully wrote to file: " + file_name);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
