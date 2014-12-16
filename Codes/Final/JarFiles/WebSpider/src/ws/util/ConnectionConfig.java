/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.util;

import java.net.HttpURLConnection;

/**
 *
 * @author floion z
 */
public interface ConnectionConfig {
    public void config(HttpURLConnection con);
    
}
