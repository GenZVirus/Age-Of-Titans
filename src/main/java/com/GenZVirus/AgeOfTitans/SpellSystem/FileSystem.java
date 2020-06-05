package com.GenZVirus.AgeOfTitans.SpellSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.common.collect.Lists;

public class FileSystem {
	
	// This class is the custom player data storing system
	
	private static File file;

	// Calling the constructor will check if the file exits and if not it will create the file
	
	public FileSystem() {
		try {
		      FileSystem.file = new File("world/ageoftitans/playerdata.txt");
		      File parent = file.getParentFile();
		      if (!parent.exists() && !parent.mkdirs()) {
		          throw new IllegalStateException("Couldn't create dir: " + parent);
		      }
		      if (file.createNewFile()) {
		        System.out.println("File created: " + file.getName() + " with path: " + file.getPath());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		  }
	
	// Edits the file with the data provided
	
	public static void editFile(String PlayerName, int slot1ID, int slot2ID, int slot3ID, int slot4ID) {
		ArrayList<String> lines = new ArrayList<String>();
		String line = null;
		try
        {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null)
            {
                if (line.contains(PlayerName))
                    line = PlayerName + " " + slot1ID + " " + slot2ID + " " + slot3ID + " " + slot4ID;
                lines.add(line);
            }
            fr.close();
            br.close();
            
            FileWriter fw = new FileWriter(file);
            BufferedWriter out = new BufferedWriter(fw);
            for(String s : lines) {
            	s = s + "\n";
                out.write(s);
            }
            out.flush();
            out.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
	}
	
	// Checks if the player exists, if it does it reads the file, else it writes it into the file with the data provided
	
	public static List<Integer> readOrWrite(String UniquePlayerName, int slot1ID, int slot2ID, int slot3ID, int slot4ID) {
		try {
			Scanner myReader = new Scanner(file);
			boolean found = false;
			while(myReader.hasNext()) {
				String check = myReader.next();
				if(check.contentEquals(UniquePlayerName)) {
					found = true;
					break;
				}
			}
			myReader.close();
			if(found) {
				return readFile(UniquePlayerName);
			} else {
				writeFile(UniquePlayerName, slot1ID, slot2ID, slot3ID, slot4ID);
				return readFile(UniquePlayerName);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return Lists.newArrayList();
	}
	
	// Reads the file searching for the player requested
	
	public static List<Integer> readFile(String UniquePlayerName) {
		
		List<Integer> slotList = Lists.newArrayList();
		int slot1 = 0;
		int slot2 = 0;
		int slot3 = 0;
		int slot4 = 0;
		try {
			Scanner myReader = new Scanner(file);
			while(myReader.hasNext()) {
				String uniquePlayerName = myReader.next();
				if(uniquePlayerName.contentEquals(UniquePlayerName)) {
					slot1 = Integer.parseInt(myReader.next());
					slot2 = Integer.parseInt(myReader.next());
					slot3 = Integer.parseInt(myReader.next());
					slot4 = Integer.parseInt(myReader.next());
				} else {
					myReader.hasNext();
					myReader.hasNext();
					myReader.hasNext();
					myReader.hasNext();
				}
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		slotList.add(slot1);
		slotList.add(slot2);
		slotList.add(slot3);
		slotList.add(slot4);
		
		return slotList;
		
	}
	
	// Writes to the file the data provided
	
	public static void writeFile(String UniquePlayerName, int slot1ID, int slot2ID, int slot3ID, int slot4ID) {
		
		try {
			FileWriter myWriter = new FileWriter(file.getPath(), true);
			myWriter.append(UniquePlayerName + " " + slot1ID + " " + slot2ID + " " + slot3ID + " " + slot4ID + "\n");
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
	}
	
}
