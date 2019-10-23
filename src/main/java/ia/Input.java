package ia;

/**
 *
 * @author luis
 */
public class Input {

    private double in[];
    private int size;

    public Input(int size) {
        in = new double[size];
        size = 0;
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
        return 0.0001;
    }

    public void setInputs(double in[]) {
        if (in.length < size) {
            new Function().copy(in, in, size);
        }
    }

    public double[] getInputs() {
        return in;
    }
}
