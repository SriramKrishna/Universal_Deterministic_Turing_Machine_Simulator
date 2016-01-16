/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stateMachine;

import components.SquareContent;

/**
 *
 * @author usr001
 */
public interface TransitionResult {

    String getAction();

    SquareContent getOutput();
    
}
