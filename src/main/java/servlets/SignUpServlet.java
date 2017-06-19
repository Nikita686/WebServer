package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import dbService.DBException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nb on 19/04/2017.
 */
public class SignUpServlet extends HttpServlet {

    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");

        if (login == null & pass == null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
         try {
             accountService.addNewUser(login, pass);
         } catch (DBException e){
            e.printStackTrace();
         }

    }
}
