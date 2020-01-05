package intcodecomputer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IntcodeComputer {

    private long[] parts = new long[10000];
    private int relBase = 0;
    private List<Long> outputList = new ArrayList<>();
    private boolean running = true;
    private boolean pauseOnInput;
    private boolean pauseOnOutput;
    private boolean printMode;
    private int inputNumber = 0;
    private int position = 0;
    private long output = 0;

    public IntcodeComputer(String input) {
        this(input, false);
    }

    public IntcodeComputer(String input, boolean pauseOnInput) {
        this(input, pauseOnInput, false);
    }

    public IntcodeComputer(String input, boolean pauseOnInput, boolean pauseOnOutput) {
        this(input, pauseOnInput, pauseOnOutput, false);
    }

    public IntcodeComputer(String input, boolean pauseOnInput, boolean pauseOnOutput, boolean printMode) {
        String[] temp = input.split(",");
        Arrays.fill(parts, 0);
        for (int i = 0; i < temp.length; i++) {
            parts[i] = Long.parseLong(temp[i]);
        }
        this.pauseOnInput = pauseOnInput;
        this.pauseOnOutput = pauseOnOutput;
        this.printMode = printMode;
    }

    public void compute() {
        String opcode = parts[position] + "";
        while (!opcode.equalsIgnoreCase("99")) {
            int mode1 = parseNumber(opcode, opcode.length() - 3);
            int mode2 = parseNumber(opcode, opcode.length() - 4);
            int mode3 = parseNumber(opcode, opcode.length() - 5);
            switch (opcode.toCharArray()[opcode.length() - 1]) {
                case '1': {
                    long num1 = getValue(mode1, position + 1);
                    long num2 = getValue(mode2, position + 2);
                    int output = getAddress(mode3, position + 3);
                    parts[output] = num2 + num1;
                    position += 4;
                    if (printMode) {
                        long ans = num1 + num2;
                        System.out.println("ADDITION [" + mode1 + " " + mode2 + " " + mode3 + "]: " + num1 + " + " + num2 + " = " + ans + " to " + output);
                    }
                    break;
                }
                case '2': {
                    long num1 = getValue(mode1, position + 1);
                    long num2 = getValue(mode2, position + 2);
                    int output = getAddress(mode3, position + 3);
                    parts[output] = num2 * num1;
                    position += 4;
                    if (printMode) {
                        long ans = num1 * num2;
                        System.out.println("MULTIPLICATION [" + mode1 + " " + mode2 + " " + mode3 + "]: " + num1 + " * " + num2 + " = " + ans + " to " + output);
                    }
                    break;
                }
                case '3': {
                    if (pauseOnInput && inputNumber == -100) {
                        return;
                    }
                    int output = getAddress(mode1, position + 1);
                    if (!pauseOnInput) {
                        System.out.println("Enter a number: ");
                        Scanner in = new Scanner(System.in);
                        long value = Long.parseLong(in.next());
                        parts[output] = value;
                    } 
                    else {
                        parts[output] = inputNumber;
                        inputNumber = -100;
                    }
                    position += 2;
                    if (printMode) {
                        System.out.println("Input [" + mode1 + "]: Changed " + output + " to " + parts[output]);
                    }
                    break;
                }
                case '4': {
                    long output = getValue(mode1, position + 1);
                    outputList.add(output);
                    if (printMode) {
                        System.out.println("Output [" + mode1 + "]: " + output);
                    }
                    position += 2;
                    this.output = output;
                    if (pauseOnOutput) {
                        return;
                    }
                    break;
                }
                case '5': {
                    long condition = getValue(mode1, position + 1);
                    int output = (int) getValue(mode2, position + 2);
                    if (condition != 0L) {
                        if (printMode) {
                            System.out.println("JUMP IF TRUE [" + mode1 + " " + mode2 + "]: " + condition + " -> Jumped to " + output);
                        }
                        position = output;
                    }
                    else {
                        if (printMode) {
                            System.out.println("JUMP IF TRUE [" + mode1 + " " + mode2 + "]: " + condition + " -> Nothing happened");
                        }
                        position += 3;
                    }
                    break;
                }
                case '6': {
                    long condition = getValue(mode1, position + 1);
                    int output = (int) getValue(mode2, position + 2);
                    if (condition == 0L) {
                        if (printMode) {
                            System.out.println("JUMP IF FALSE [" + mode1 + " " + mode2 + "]: " + condition + " -> Jumped to " + output);
                        }
                        position = output;
                    } 
                    else {
                        if (printMode) {
                            System.out.println("JUMP IF FALSE [" + mode1 + " " + mode2 + "]: " + condition + " -> Nothing happened");
                        }
                        position += 3;
                    }
                    break;
                }
                case '7': {
                    long num1 = getValue(mode1, position + 1);
                    long num2 = getValue(mode2, position + 2);
                    int output = getAddress(mode3, position + 3);
                    if (num1 < num2) {
                        if (printMode) {
                            System.out.println("LESS THAN [" + mode1 + " " + mode2 + "] " + num1 + " < " + num2 + ": Changed output to 1");
                        }
                        parts[output] = 1;
                    } 
                    else {
                        if (printMode) {
                            System.out.println("LESS THAN [" + mode1 + " " + mode2 + "] " + num1 + " > " + num2 + ": Changed output to 0");
                        }
                        parts[output] = 0;
                    }
                    position += 4;
                    break;
                }
                case '8': {
                    long num1 = getValue(mode1, position + 1);
                    long num2 = getValue(mode2, position + 2);
                    int output = getAddress(mode3, position + 3);
                    if (num1 == num2) {
                        if (printMode) {
                            System.out.println("EQUALS [" + mode1 + " " + mode2 + "] " + num1 + " == " + num2 + ": Changed output to 1");
                        }
                        parts[output] = 1;
                    } 
                    else {
                        if (printMode) {
                            System.out.println("EQUALS [" + mode1 + " " + mode2 + "] " + num1 + " != " + num2 + ": Changed output to 0");
                        }
                        parts[output] = 0;
                    }
                    position += 4;
                    break;
                }
                case '9': {
                    long numb = getValue(mode1, position + 1);
                    relBase += numb;
                    if (printMode) {
                        System.out.println("Move relBase: " + mode1 + ": Changed relBase to " + relBase);
                    }
                    position += 2;
                }
            }
            opcode = parts[position] + "";
        }
        running = false;
    }

    private long getValue(int mode, int position) {
        if (mode == 0) {
            return parts[Integer.parseInt(parts[position] + "")];
        }
        else if (mode == 1) {
            return parts[position];
        }
        else {
            return parts[Integer.parseInt(parts[position] + relBase + "")];
        }
    }

    private int getAddress(int mode, int position) {
        if (mode == 0) {
            return (int) parts[position];
        }
        else {
            return (int) parts[position] + relBase;
        }
    }

    private int parseNumber(String number, int position) {
        if (position < 0) {
            return 0;
        }
        return Integer.parseInt((number.charAt(position) + ""));
    }

    public List<Long> getOutputList() {
        return outputList;
    }

    public void setInputNumber(int inputNumber) {
        this.inputNumber = inputNumber;
    }

    public boolean isRunning() {
        return running;
    }

    public long getOutput() {
        return output;
    }
}