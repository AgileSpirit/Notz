package com.agile.spirit.notz.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.services.UserServiceImpl;

public class DataGenerator {

  private final static Random random = new Random();

  public static User generateAdminUser(boolean generateNotes) {
    User admin = User.create();
    admin.setFirstName("Jeremy");
    admin.setLastName("Buget");
    admin.setEmail("admin@agile-spirit.fr");
    admin.setPassword("admin");
    if (generateNotes) {
      admin.setNotes(generateNotes(13));
    }
    return admin;
  }
  
  public static List<User> generateUsers(int rowsNumber) {
    List<User> generatedUsers = new ArrayList<User>();
    for (int i = 0 ; i < rowsNumber ; i++) {
      User user = User.create();
      user.setFirstName(generateRandomString(true));
      user.setLastName(generateRandomString(true));
      user.setEmail(generateRandomEmail());
      user.setPassword(generateRandomString(true));
      generatedUsers.add(user);
    }
    return generatedUsers;
  }
  
  public static List<Note> generateNotes(int rowsNumber) {
    List<Note> notes = new ArrayList<Note>();
    for (int i = 0 ; i < rowsNumber ; i++) {
      Note note = new Note();
      note.setTitle(generateRandomString(generateRandomInt(), false));
      note.setDescription(generateRandomString(generateRandomInt(), false));
      notes.add(note);
    }
    return notes;
  }
  
  /* Set of characters that is valid. Must be printable, memorable,
   * and "won't break HTML" (i.e., not '<', '>', '&', '=', ...).
   * or break shell commands (i.e., not '<', '>', '$', '!', ...).
   * I, L and O are good to leave out, as are numeric zero and one.
   */
  protected static char[] goodAlphaNumChar = {
    // Comment out next two lines to make upper-case-only, then
    // use String toUpper() on the user's input before validating.
    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n',
    'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N',
    'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
    //    '2', '3', '4', '5', '6', '7', '8', '9',
    //      '+', '-', '@',
  };

  protected static char[] goodChar = {
    // Comment out next two lines to make upper-case-only, then
    // use String toUpper() on the user's input before validating.
    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n',
    'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N',
    'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
    '1', '2', '3', '4', '5', '6', '7', '8', '9',
    ' ', '.', ',', ';', '?', ':', '!',  
  };
  
  public static int generateRandomInt() {
    return random.nextInt(120);
  }
  
  public static String generateRandomString(boolean onlyAlphaNum) {
    int length = random.nextInt(12);
    if (length == 0) {
      length = 8;
    }
    return generateRandomString(length, onlyAlphaNum);
  }
  public static String generateRandomString(int length, boolean onlyAlphaNum) {
   char[] availableChar = onlyAlphaNum ? goodAlphaNumChar : goodChar;
    
    StringBuffer sb = new StringBuffer();
    for (int i = 0 ; i < length ; i++) {
      sb.append(availableChar[random.nextInt(availableChar.length)]);
    }
    return sb.toString();
  }

  public static String generateRandomEmail() {
    return generateRandomString(true) + "@agile-spirit.fr";
  }

}
