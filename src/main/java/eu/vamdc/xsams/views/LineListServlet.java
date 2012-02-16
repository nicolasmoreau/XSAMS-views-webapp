package eu.vamdc.xsams.views;

import java.io.InputStream;
import javax.servlet.ServletException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Guy Rixon
 */
public class LineListServlet extends TransformingServlet {
  
  @Override
  protected Transformer getTransformer(String lineListUrl,
                                       String stateListUrl,
                                       String selectedStateUrl,
                                       String broadeningUrl,
                                       String reloadUrl,
                                       String stateId) 
      throws ServletException {
    InputStream q = this.getClass().getResourceAsStream("/line-list.xsl");
    if (q == null) {
      throw new ServletException("Can't find the stylesheet");
    }
    StreamSource transform = new StreamSource(q);
    try {
      Transformer t = TransformerFactory.newInstance().newTransformer(transform);
      t.setParameter("state-location", selectedStateUrl);
      t.setParameter("state-list-location", stateListUrl);
      t.setParameter("broadening-location", broadeningUrl);
      return t;
    } catch (TransformerConfigurationException ex) {
      throw new ServletException(ex);
    }
  }
  
  
  
}
