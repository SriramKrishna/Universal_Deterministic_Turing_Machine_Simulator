/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.util.Objects;
import stateMachine.FiniteStateMachine;
import stateMachine.State;
import stateMachine.TransitionResult;

/**
 *
 * @author usr001
 */
public class Machine {

    private final FiniteStateMachine stateMachineController;
    private final ReadWriteHead rwhead;
    private State endingState;

    Machine(FiniteStateMachine stateMachine, ReadWriteHead head) {
        this.stateMachineController = Objects.requireNonNull(stateMachine, "stateMachine");
        this.rwhead = Objects.requireNonNull(head, "head");
    }

    Machine(FiniteStateMachine stm, State endingState, ReadWriteHead rwHead) {
        this.stateMachineController = Objects.requireNonNull(stm, "stateMachine");
        this.rwhead = Objects.requireNonNull(rwHead, "head");
        this.endingState = Objects.requireNonNull(endingState);
    }

    public Tape start() {
        while (!stateMachineController.getCurrentState().isHaltingState()) {
            TransitionResult result = stateMachineController.transit(rwhead.read());
            rwhead.write(result.getOutput());
            rwhead.move(result.getAction());
        }
        return rwhead.getTape();
    }

    @Override
    public String toString() {
        return stateMachineController.toString()
                + "\n"
                + stateMachineController.getQ0().getId()
                + "|"
                + rwhead.getInitialTapePosition()
                + "|"
                + rwhead.getInitialTape()
                + "|"
                + rwhead.getTape();
        //+ rwhead.getInitialTape();
    }

    public boolean isAcepted() {
        return stateMachineController.isAccepted();
    }

}
