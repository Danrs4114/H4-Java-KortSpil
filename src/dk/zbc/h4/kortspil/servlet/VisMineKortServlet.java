package dk.zbc.h4.kortspil.servlet;
import dk.zbc.h4.kortspil.SorteperMgr;
import dk.zbc.h4.kortspil.Spiller;
import dk.zbc.h4.kortspil.xml.XmlMgr;

import javax.ejb.Init;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Niklas on 06-05-2015.
 */
public class VisMineKortServlet extends HttpServlet {
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

    private void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO SessionID &/ user validation
        String sessionId = req.getSession().getId();
         /*
        if(req.getParameter("uid")!= null) {
        sessionId = req.getParameter("uid");
            System.err.println(req.getParameter("uid"));}
         */

        Spiller enSpiller = SorteperMgr.getInstance().getSpiller(sessionId);
        String content = XmlMgr.getInstance().transformCard(enSpiller.getHaand());
        resp.setContentType("text/xml");
        resp.getOutputStream().print(content);
    }
}
