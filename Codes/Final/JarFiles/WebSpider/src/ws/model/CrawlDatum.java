/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.model;

import org.apache.avro.reflect.Nullable;

/**
 *
 * @author floion z
 */
public class CrawlDatum {
    @Nullable public String url;
    @Nullable public int status=-1;
    @Nullable public long fetchtime=-1;
}
