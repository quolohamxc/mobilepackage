/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.generator;


import ws.model.CrawlDatum;
import ws.util.Task;

/**
 *
 * @author floion z
 */
public abstract class Generator extends Task{
           
    public abstract CrawlDatum next();
 
    
}
