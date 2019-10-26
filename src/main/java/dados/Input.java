package dados;

import ia.Function;

/**
 *
 * @author luis
 */
public class Input {

    private double in[] = null;
    private int size = 0;

    public Input() {
    }

    public Input(double in[]) {
        this.in = in.clone();
        size = in.length;
    }

    public void setInput(double in, int pos) {
        if (pos <= size) {
            this.in[pos] = in;
        }
    }

    public double getInput(int pos) {
        if (pos <= size) {
            return in[pos];
        }
        return 0.01;
    }

    public void setInputs(double in[]) {
        if (in.length < size) {
            this.in = in.clone();
        }
    }

    public double[] getInputs() {
        return in;
    }
}
