/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.parser;

import ws.model.Page;

/**
 *
 * @author floion z
 */
public abstract class Parser {
    public abstract ParseResult getParse(Page page) throws Exception;
}
