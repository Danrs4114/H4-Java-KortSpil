package dk.zbc.h4.kortspil.servlet;
import dk.zbc.h4.kortspil.SorteperMgr;
import dk.zbc.h4.kortspil.Spiller;
import dk.zbc.h4.kortspil.Kort;
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
public class tagKortServlet extends HttpServlet {
    
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
    
    // Outputs amount of cards for next player
    private void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get current player
        String sessionId = req.getSession().getId();
        Spiller thisPlayer = SorteperMgr.getInstance().getSpiller(sessionId);
        
        String content = "";
        int takenCardIdx = -1;
        
        // Cheating check. Check if this player is the current/active player
        if (thisPlayer.equals(SorteperMgr.getInstance().getCurrentPlayer())) {
            // Check if parameter was given
            if(req.getParameter("card")!= null) {
                
                // Get card
                try{
                    takenCardIdx = Integer.parseInt(req.getParameter("card"));
                }
                catch(NumberFormatException e) {
                    content = XmlMgr.getInstance().transformResponse("Error", "ParseInt failed! Card isn't a number!");
                }
                
                // Check if parse was successful
                if (takenCardIdx != -1)
                {
                    // Get previous player
                    int prevPlayerIdx = SorteperMgr.getInstance().getPreviousPlayerIndex(thisPlayer);
                    Spiller prevPlayer = SorteperMgr.getInstance().getPlayerByIndex(prevPlayerIdx);
                    
                    // Check if previous player is the same as the current player (1 player left)
                    if (prevPlayer.equals(thisPlayer))
                    {
                        // Game possibly ended
                        // TODO Check if game ended (?)
                    }
                    
                    // Check takenCardIdx range (0 - prevPlayer card count)
                    if ((takenCardIdx >= 0) && (takenCardIdx <= (prevPlayer.getHaand().size() - 1))) {
                        
                        // Get card from previous player, transfer card, return card
                        Kort prevPlayerCard = prevPlayer.getHaand().get(takenCardIdx);
                        prevPlayer.getHaand().remove(prevPlayerCard);
                        thisPlayer.getHaand().add(prevPlayerCard);
                        content = XmlMgr.getInstance().transformCard(prevPlayerCard);
                    }
                    else {
                        // Return "Out of bounds" error (takenCardIdx)
                        content = XmlMgr.getInstance().transformResponse("Error", "Card index out of bounds!\n"
                                + "Min: 0, "
                                + "Max: " + (prevPlayer.getHaand().size() - 1) + ", "
                                + "Value: " + takenCardIdx + ".");
                    }
                }
            }
            else {
                // Return "Card unspecified" error (parameter)
                content = XmlMgr.getInstance().transformResponse("Error", "Card unspecified!");
            }
        }
        else {
            // Return "This player isn't the current player" error (validation)
            content = XmlMgr.getInstance().transformResponse("Error", "It's not your turn!");
        }
        
        // Set content type & print
        resp.setContentType("text/xml");
        resp.getOutputStream().print(content);
    }
}
