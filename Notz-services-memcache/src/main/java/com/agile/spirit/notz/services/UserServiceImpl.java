package com.agile.spirit.notz.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.util.DataGenerator;
import com.agile.spirit.notz.util.MemoryCache;

public class UserServiceImpl implements UserService {

  MemoryCache memoryCache;

  public UserServiceImpl() {
    this.memoryCache = MemoryCache.getInstance();
  }

  @Override
  public User getUserById(String id) {
    return memoryCache.getData().get(id);
  }

  @Override
  public User saveOrUpdate(User user) {
    if (user.getId() == null || memoryCache.getData().get(user.getId()) == null) {
      user.setId(memoryCache.getNextVal());
    }
    memoryCache.getData().put(user.getId(), user);
    return user;
  }

  @Override
  public void delete(String id) {
    memoryCache.getData().remove(id);
  }

  @Override
  public User loginUser(String login, String password) {
    for (User user : memoryCache.getData().values()) {
      if (user.getEmail().equals(login) || user.getUsername().equals(login)) {
        if (user.getPassword().equals(password)) {
          return user;
        }
      }
    }
    return null;
  }

  @Override
  public void generateUsers(int nb) {
    User admin = DataGenerator.generateAdminUser(0);
    Note sampleNote = new Note();
    sampleNote.setId(DataGenerator.generateRandomString(true));
    sampleNote.setTitle("Candide ~ Voltaire");
    sampleNote
        .setDescription("Candide est un conte philosophique de Voltaire paru à Genève en janvier 1759. Il a été réédité vingt fois du vivant"
            + " de l’auteur (plus de cinquante aujourd’hui), ce qui en fait un des plus grands succès littéraires français.\r\nCandide porte"
            + " le titre complet de Candide ou l'Optimisme, soi-disant traduit des additions du Docteur Ralph qui, en réalité, n'est que le"
            + " pseudonyme utilisé par Voltaire pour éviter la censure. Cette œuvre, ironique dès les premières lignes, ne laisse aucun doute"
            + " sur l’origine de l’auteur, qui ne pouvait qu'être du parti des philosophes : « Les anciens domestiques soupçonnaient que"
            + " [Candide] était fils de la sœur de Monsieur le Baron et d'un bon et honnête gentilhomme du voisinage, que cette demoiselle"
            + " ne voulut jamais épouser parce qu'il n'avait pu prouver que soixante et onze quartiers, et que le reste de son arbre "
            + "généalogique avait été perdu par l'injure du temps1. »\r\nOn perçoit immédiatement, dans la fin de ce premier paragraphe de"
            + " l'œuvre, le sarcasme moquant le conservatisme social de la noblesse arrogante, certes tel que Molière un siècle plus tôt le"
            + " pratiquait aux dépens de la petite aristocratie provinciale2, mais surtout annonçant le Figaro de Beaumarchais : « Si le Ciel"
            + " l'eût voulu, je serais fils d'un prince3. » Candide est également un récit de formation, récit d'un voyage qui transformera"
            + " son héros éponyme en philosophe, un Télémaque d'un genre nouveau.\r\nL'onomastique4, en matière d'interprétation des textes"
            + " voltairiens, se révèle souvent féconde. Le mot « candide » vient du latin candidus qui signifie blanc. Le choix d'un tel nom"
            + " indiquerait l’innocence du héros, voire sa naïveté. Cire vierge sur laquelle on marque en apparence tout, il s'étonnera de"
            + " ce qu'il observera au fil de ses tribulations, à la façon apparemment enfantine de Socrate dans les dialogues platoniciens,"
            + " personnifiant ainsi, selon l'étymologie du mot, l'ironie — e????e?a (eironeia) —, l'ignorance feinte.");
    sampleNote.setCreationDate(new Date());
    sampleNote.setModificationDate(sampleNote.getCreationDate());

    admin.addNote(sampleNote);
    saveOrUpdate(admin);

    List<User> users = DataGenerator.generateUsers(nb);
    for (User user : users) {
      user.setNotes(DataGenerator.generateNotes(5));
      saveOrUpdate(user);
    }
  }

  @Override
  public List<User> findUser(String expression) {
    List<User> users = new ArrayList<User>();
    for (User user : memoryCache.getData().values()) {
      if (user.getId().equals(expression) //
          || user.getCompleteName().contains(expression) //
          || user.getEmail().contains(expression) //
          || user.getUsername().contains(expression)) {
        users.add(user);
      }
    }
    return users;
  }

  @Override
  public List<User> listUsers() {
    return new ArrayList<User>(memoryCache.getData().values());
  }

}
