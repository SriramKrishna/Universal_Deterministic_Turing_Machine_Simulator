/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateMachine;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author usr001
 */
public class StateFactory {
    private final static Map<String,State> stateSet= new HashMap<>();
    
    /**
     * Get an singleton instance of State(id)
     * @param id the id of the state
     * @return 
     */
    public static State getInstance(String id) {
        
        if (stateSet.containsKey(id))
            return stateSet.get(id);
        
        State state = new State(id);
        stateSet.put(id, state);
        return state;
    }
    
}
