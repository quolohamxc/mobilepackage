/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.generator.filter;

import ws.generator.Generator;

/**
 *
 * @author floion z
 */
public abstract class Filter extends Generator{
    protected Generator generator;
    public Filter(Generator generator){
        this.generator=generator;
    }
}
