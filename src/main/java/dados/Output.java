package dados;

/**
 *
 * @author luis
 */
public class Output {

    public String classificar(double u) {
        if (u < 0.5) {
            return "TUMOR BENIGNO";
        } else {
            return "TUMOR MALIGNO";
        }
    }
}
