package accounts;


import dbService.DBException;
import dbService.DBService;

/**
 * Created by ibm_admin on 19/04/2017.
 */
public class AccountService {

    private final DBService dbService;

    public AccountService() {
        dbService = new DBService();
    }

    public void addNewUser(String name, String password) throws DBException {
        dbService.addUser(name, password);
    }

    public UserProfile getUserByLogin(String login) {
          UserProfile userProfile = null;
          try {
          userProfile = new UserProfile(dbService.getUserByLogin(login).getName(),
                  dbService.getUserByLogin(login).getPass());
          } catch (DBException e){
              e.printStackTrace();
          }
      return userProfile;
    }

}
