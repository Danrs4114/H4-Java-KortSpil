package dk.zbc.h4.kortspil.servlet;
import dk.zbc.h4.kortspil.KortspilMgr;
import dk.zbc.h4.kortspil.SorteperMgr;
import dk.zbc.h4.kortspil.Spiller;
import dk.zbc.h4.kortspil.xml.XmlMgr;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Niklas on 07-05-2015.
 */
public class Spilservlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doService(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doService(req, resp);
    }

    private void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionId = null;
        if(req.getParameter("uid")!= null) {
            sessionId = req.getParameter("uid");
            Spiller nuvaerendeSpiller = SorteperMgr.getInstance().getCurrentPlayer();
            String content = XmlMgr.getInstance().transformNuvaerendeSpiller(nuvaerendeSpiller);
            resp.setContentType("text/xml");
            resp.getOutputStream().print(content);
        }
    }
}
