import MonteCarlo.MonteCarlo;
import MonteCarlo.VecOp;

import java.util.Random;

public class Test {
    static MonteCarlo.VecOp current = new VecOp(new double[]{0, 0.431, 4.0945, 7.327, 10.5595, 13.792, 17.24, 18.7485
            , 20.9035, 21.119, 21.55, 21.981, 21.981});
    static MonteCarlo.VecOp voltage = new VecOp(new double[]{146.227, 179.335, 201.407, 226.238, 251.069, 281.418, 295.213
            , 311.767, 328.321, 331.08, 328.321, 333.839, 333.839});

    public static void main(String[] args) {
        System.out.println("TEST");
        Random random = new Random();
        MonteCarlo.VecOp param = new MonteCarlo.VecOp(new double[]{1000, -999});
        MonteCarlo test = new MonteCarlo(random, new MonteCarlo.VecOp(new double[]{1,15}), function -> error(function));
        param = test.runMethod(10000, 0.01, param);
        System.out.printf("U = I*%f%+f\n",param.vect[0],param.vect[1]);
    }

    public static double error(MonteCarlo.VecOp args) {
        MonteCarlo.VecOp testVolt = current.clone();
        testVolt.mul(args.vect[0]);
        testVolt.add(args.vect[1]);
        testVolt.sub(voltage);
        testVolt.mul(testVolt);
        return Math.sqrt(testVolt.sum() / testVolt.getLen());
    }
}

