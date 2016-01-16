/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.io.IOException;
import components.Machine;
import components.MachineBuilder;
import components.Tape;
import java.util.Scanner;

/**
 *
 * @author usr001
 */
public class TuringMachine {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: java -cp . TuringMachine machine.txt input.txt");
            System.exit(1);
        }

        try {
            try (FileInputStream machineIS = new FileInputStream(args[0])) {
                try (FileInputStream inputString = new FileInputStream(args[1])) {

                        String initialTape = new Scanner(inputString).nextLine();
                        
                        Machine machine = MachineBuilder.build(machineIS, initialTape);
                        
                    try {
                        Tape tape = machine.start();
                        System.out.printf("Machine:\n%s\n", machine);
                        System.out.println(machine.isAcepted() ? "Accepted" : "Rejected");
                    } catch (Exception exception) {
                        System.out.println("Rejected");
                    }
                }
            }
        } catch (Exception ex) {
            //System.out.print(ex.getMessage());
            System.out.println( ex.getMessage());
        }
    }

}
