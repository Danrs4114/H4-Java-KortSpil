package dk.zbc.h4.kortspil.servlet;
import dk.zbc.h4.kortspil.Kort;
import dk.zbc.h4.kortspil.SorteperMgr;
import dk.zbc.h4.kortspil.Spiller;
import dk.zbc.h4.kortspil.xml.XmlMgr;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Admin on 12-05-2015.
 */
public class FjernStikServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doService(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doService(req, resp);
    }
    private void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String userId = null;
        int kort1;
        int kort2;

        ArrayList<Kort> ha = new ArrayList<Kort>();

            userId = req.getSession().getId();
            // url:8080/sorteper/fjernstik?uid=1&kort1=8&kort2=7 For demo input data contained in url
            kort1 = Integer.parseInt(req.getParameter("kort1"));
            kort2 = Integer.parseInt(req.getParameter("kort2"));
            Spiller enSpiller = SorteperMgr.getInstance().getSpiller(userId);
            System.err.println(req.getParameter("uid"));
            SorteperMgr.getInstance().fjernStik(kort1,kort2,userId);
            String content = XmlMgr.getInstance().transformCard(enSpiller.getHaand());
            resp.setContentType("text/xml");
            resp.getOutputStream().print(content);

    }

}
