package custom_synth;

import com.jsyn.unitgen.SawtoothOscillator;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.SquareOscillator;
import com.jsyn.unitgen.TriangleOscillator;

public class Oscillators {
    static SineOscillator SineOscillator = new SineOscillator();
    static SquareOscillator SquareOscillator = new SquareOscillator();
    static SawtoothOscillator SawtoothOscillator = new SawtoothOscillator();
    static TriangleOscillator TriangleOscillator = new TriangleOscillator();
    static SineOscillator SineLFO = new SineOscillator();
    static TriangleOscillator TriangleLFO = new TriangleOscillator();
    static SawtoothOscillator SawtoothLFO = new SawtoothOscillator();

}
