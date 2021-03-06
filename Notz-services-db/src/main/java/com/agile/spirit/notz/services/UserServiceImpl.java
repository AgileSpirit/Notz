package com.agile.spirit.notz.services;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.services.session.SessionManager;
import com.agile.spirit.notz.util.DataGenerator;
import com.agile.spirit.notz.util.PersistenceUtil;
import com.agile.spirit.notz.util.TransactionnalOperation;

public class UserServiceImpl implements UserService {

  /*
   * NAMED QUERIES
   */
  public User getUserByLogin(final String login) {
    User user = PersistenceUtil.getEntityManager().createNamedQuery(User.FIND_USERS_BY_LOGIN, User.class).setParameter("login", login)
        .getSingleResult();
    return user;
  }

  @Override
  public User getUserById(final String id) {
    return PersistenceUtil.getEntityManager().find(User.class, id);
  }

  @Override
  public User saveOrUpdate(final User user) {
    if (user != null) {
      return (User) new TransactionnalOperation() {
        @Override
        public Object process(final EntityManager entityManager) {
          if (user.getId() == null) {
            user.setCreationDate(new Date());
            entityManager.persist(user);
            return user;
          } else {
            User eUser = getUserById(user.getId());
            if (eUser != null) {
              eUser.merge(user);
            }
            eUser.setModificationDate(new Date());
            return entityManager.merge(eUser);
          }
        }
      }.execute();
    }
    return null;
  }

  @Override
  public void delete(final String id) {
    if (id != null) {
      new TransactionnalOperation() {
        @Override
        public Object process(final EntityManager entityManager) {
          User user = entityManager.find(User.class, id);
          if (user != null) {
            entityManager.remove(user);
            return true;
          }
          return false;
        }
      }.execute();
    }
  }

  @Override
  public User loginUser(final String email, final String password) {
    if (email != null && password != null) {
      User user = getUserByLogin(email);
      if (user != null) {
        if (user.getPassword().equals(password)) {
          SessionManager.getInstance().addUserInSession(user);
          return user;
        }
      }
    }
    return null;
  }

  @Override
  public void generateUsers(final int nb) {
    User admin = DataGenerator.generateAdminUser(0);
    Note sampleNote = new Note();
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
    admin.getNotes().add(sampleNote);
    admin.getNotes().add(Note.create(sampleNote));
    admin.getNotes().add(Note.create(sampleNote));
    admin.getNotes().add(Note.create(sampleNote));
    admin.getNotes().add(Note.create(sampleNote));
    saveOrUpdate(admin);

    List<User> users = DataGenerator.generateUsers(nb);
    for (User user : users) {
      user.setNotes(DataGenerator.generateNotes(5));
      saveOrUpdate(user);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<User> findUser(String expression) {
    EntityManager entityManager = PersistenceUtil.getEntityManager();
    Query query = entityManager.createNamedQuery(User.FIND_USERS_BY_LOGIN, User.class);
    List<User> users = query.getResultList();
    return users;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<User> listUsers() {
    EntityManager entityManager = PersistenceUtil.getEntityManager();
    Query query = entityManager.createNamedQuery(User.LIST_USERS, User.class);
    List<User> users = query.getResultList();
    return users;
  }

}
