package custom_synth;

import com.jsyn.unitgen.EnvelopeDAHDSR;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.PassThrough;
import com.jsyn.unitgen.SquareOscillator;

public class randComponents {
    static LineOut lineOut = new LineOut();
    static PassThrough Mixer1 = new PassThrough();
    static PassThrough Mixer2 = new PassThrough();
    static EnvelopeDAHDSR Envelope = new EnvelopeDAHDSR();
    static SquareOscillator Step = new SquareOscillator();


}
