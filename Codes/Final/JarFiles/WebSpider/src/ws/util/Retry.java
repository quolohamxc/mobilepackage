/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.util;

/**
 *
 * @author floion z
 */
public abstract class Retry<T> {
    public abstract Object run() throws Exception;
    
    @SuppressWarnings("unchecked")
	public T getResult(int count){
        while(count>0){
            try{
                return (T)run();
            }catch(Exception e){
                Log.Infos("retry",e.toString());
                count--;
            }
            
        }
        return null;
    }
    public static void main(String[] args){
        
        
    }
}
