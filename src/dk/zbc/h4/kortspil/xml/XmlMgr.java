package dk.zbc.h4.kortspil.xml;

import dk.zbc.h4.kortspil.Deck;
import dk.zbc.h4.kortspil.Spiller;
import dk.zbc.h4.kortspil.Kort;

import java.util.ArrayList;

/**
 * Created by Niklas on 06-05-2015.
 */
public class XmlMgr {
    private static XmlMgr instance = null;
    
    private XmlMgr() {
        
    }
    
    /** Get the current instance of the XmlMgr
     * Get the current running instance of the XmlMgr or create a new instance if an instance doesn't exist.
     * @return Returns the current instance of the XmlMgr.
     */
    public static XmlMgr getInstance() {
        if(instance == null) {
            instance = new XmlMgr();
        }
        return instance;
    }
    
    /** Tranform a number to XML
     * @param number
     * @return Returns the number formatted as an XML String.
     */
    public String transformNumber(int number) {
        StringBuffer sb = new StringBuffer();
        sb.append(getHeader());
        sb.append("<number>" + number + "</number>");
        return sb.toString();
    }
    
    /** Transform a card collection to XML
     * @param dk A deck or hand of cards
     * @return Returns the card collection formatted as an XML String.
     * @deprecated Use transformCard instead
     */
    public String transformKort(Deck dk) {
        StringBuffer sb = new StringBuffer();
        sb.append(getHeader());
        sb.append("<kortListe>");
        for(Kort k : dk) {
            sb.append(transformSingleCard(k));
        }
        sb.append("</kortListe>");
        return sb.toString();
    }
    
    /** Tranform a card collection to XML
     * @param dk A deck or hand of cards
     * @return Returns the card collection formatted as an XML String.
     */
    public String transformCard(Deck dk) {
        StringBuffer sb = new StringBuffer();
        sb.append(getHeader());
        sb.append("<kortListe>");
        for(Kort k : dk) {
            sb.append(transformSingleCard(k));
        }
        sb.append("</kortListe>");
        return sb.toString();
    }
    
    /** Tranform a single card to XML
     * @param card
     * @return Returns the card formatted as an XML String.
     */
    public String transformCard(Kort card) {
        StringBuffer sb = new StringBuffer();
        sb.append(getHeader());
        sb.append(transformSingleCard(card));
        return sb.toString();
    }
    
    /** Tranform an error message string to XML
     * @param errorMessage 
     * @return Returns the error message formatted as an XML String.
     */
    public String transformError(String errorMessage) {
        StringBuffer sb = new StringBuffer();
        sb.append(getHeader());
        sb.append("<error>");
        sb.append(errorMessage);
        sb.append("</error>");
        return sb.toString();
    }
    
    public String transformNuvaerendeSpiller(Spiller spiller) {
        StringBuffer sb = new StringBuffer();
        sb.append(getHeader());
        sb.append("<spiller>");
        sb.append("<person>");
        sb.append("<navn>" + spiller.getNavn() + "</navn>");
        sb.append("<uid>" + spiller.getUserID() + "</uid>");
        sb.append("</person>");
        sb.append("</spiller>");
        
        return sb.toString();
    }
    
    /** Transform a simple response
    * @Param Type
    * @Param Status
    * @returns <response type='@Type'>@Status</response>
    */

    public String transformResponse(String type, String status) {
        StringBuffer sb = new StringBuffer();

        sb.append(getHeader());


        sb.append("<response type='"+type+"'>"+status+"</response>");

        return sb.toString();
    }


    /** Get the XML header 
     * @return Returns the XML header used in all XML documents.
     */
    private String getHeader() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    }
    

     /** Tranform a single card to XML without header
     * @param card
     * @return Returns the card formatted as an XML String. This string does not contain an XML header.
     */
    private String transformSingleCard(Kort card) {
        return "<kort>\n" +
                "        <kuloer>" + card.getKuloer() + "</kuloer>\n" +
                "        <vaerdi>" + card.getVaerdi() + "</vaerdi>\n" +
                "        </kort>";
    }
        
    public String transformListSpillere(ArrayList<Spiller> spillerList) {
        StringBuffer sb = new StringBuffer();
        sb.append(getHeader());
        sb.append("<spillere>");
        
        for (Spiller spiller : spillerList) {
            sb.append("<spiller>\n" +
                "<navn>" + spiller.getNavn() + "</navn>" +
                "<klar>" + spiller.getKlar() + "</klar>" +
                "<antalKort>" + (spiller.getHaand().size()) + "</antalKort>\n" +
            "</spiller>");
        }
        sb.append("</spillere>");

        return sb.toString();

    }
}
