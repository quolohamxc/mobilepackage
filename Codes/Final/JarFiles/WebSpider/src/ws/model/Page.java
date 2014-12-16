/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.model;

import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;

/**
 *
 * @author floion z
 */
public class Page{
    
    public String url=null;
    public byte[] content=null;
    public Map<String,List<String>> headers;
    public String html=null;
    public Document doc=null;
    public int status;
    public long fetchtime;
    
    public static final int UNFETCHED=1;
    public static final int FETCHED=2;
    
}
