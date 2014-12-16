/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.generator.filter;

import java.util.HashSet;

import ws.generator.Generator;
import ws.model.CrawlDatum;

/**
 *
 * @author floion z
 */
public class UniqueFilter extends Filter{

    public HashSet<String> hashset=new HashSet<String>();
    
    public void addUrl(String url){
         hashset.add(url);
    }

    public UniqueFilter(Generator generator) {
        super(generator);
    }

    @Override
    public CrawlDatum next() {
        CrawlDatum crawldatum=generator.next();
        if(crawldatum==null){
            return null;
        }
        String url=crawldatum.url;
        if(hashset.contains(url)){
            return next();
        }
        else{
            addUrl(url);
            return crawldatum;
        }
    }
    
}
