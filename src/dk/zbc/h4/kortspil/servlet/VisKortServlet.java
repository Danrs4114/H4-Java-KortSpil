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
 * Created by zbcdaso14 on 07-05-2015.
 */
public class VisKortServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        //TODO denne metode skal ikke kaldes her
        SorteperMgr.getInstance().startSpil();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doService(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doService(req, resp);
    }

    private void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // TODO Get session IDs
        String sessionId = "1";
        Spiller thisSpiller = SorteperMgr.getInstance().getSpiller(sessionId);
        String sessionId1 = "2";
        Spiller nextSpiller = SorteperMgr.getInstance().getSpiller(sessionId1);

        int cardAmount = nextSpiller.getHaand().size();
        String content = XmlMgr.getInstance().transformNumber(cardAmount);
        resp.setContentType("text/xml");
        resp.getOutputStream().print(content);
    }
}
