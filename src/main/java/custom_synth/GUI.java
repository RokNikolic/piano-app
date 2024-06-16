package custom_synth;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;

import javax.swing.*;
import java.awt.event.*;

public class GUI extends JFrame {
    public static Synthesizer synth;
    private JSlider Square_Vol;
    private JPanel MainPanel;
    private JSlider Saw_Vol;
    private JSlider Triangle_Vol;
    private JSlider Sine_Vol;
    private JSlider Square_Freq;
    private JSlider Saw_Freq;
    private JSlider Triangle_Freq;
    private JSlider Sine_Freq;
    private JPanel Keyboard;
    private JButton oneOctaveDown;
    private JLabel OctaveLabel;
    private JButton oneOctaveUp;
    private JSlider LowPassCutoff;
    private JSlider HighPassCutoff;
    private JSlider LFO_Freq;
    private JSlider LFO_Connections;
    private JSlider Release;
    private JSlider Sustain;
    private JSlider Decay;
    private JSlider Attack;
    private JSlider BandStopCutoff;
    private JButton C1;
    private JButton C1b;
    private JButton D1;
    private JButton D1b;
    private JButton E1;
    private JButton F1;
    private JButton G1;
    private JButton A1;
    private JButton B1;
    private JButton C2;
    private JButton D2;
    private JButton E2;
    private JButton F2;
    private JButton G2;
    private JButton A2;
    private JButton B2;
    private JButton F1b;
    private JButton G1b;
    private JButton A1b;
    private JButton C2b;
    private JButton D2b;
    private JButton F2b;
    private JButton G2b;
    private JButton A2b;
    private JSlider sustain_note;

    int Octaves = 0;
    double[] Octave_multi = {0.3333, 0.5, 1, 2, 3, 4};

    double[] Keys = {130.81, 138.59, 146.83, 155.56, 164.81, 174.61, 185.00, 196.00, 207.65, 220.00, 233.08, 246.94};

    int sustain_var = 0;

    void play_note(double freq){
        Oscillators.SquareOscillator.frequency.set(freq);
        Oscillators.SawtoothOscillator.frequency.set(freq);
        Oscillators.TriangleOscillator.frequency.set(freq);
        Oscillators.SineOscillator.frequency.set(freq);
        randComponents.Step.amplitude.set(1);
    }

    void stop_note(){
        if (sustain_var == 0){
            randComponents.Step.amplitude.set(0);
        }
    }

    public GUI() {
        setContentPane(MainPanel);
        setTitle("Custom Synth");
        setSize(1280, 920);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        Square_Vol.addChangeListener(e -> {
            double vol = Square_Vol.getValue();
            vol = vol/300;
            Oscillators.SquareOscillator.amplitude.set(vol);
        });
        Square_Freq.addChangeListener(e -> {
            double freq = Square_Freq.getValue();
            Oscillators.SquareOscillator.frequency.set(freq);
        });
        Saw_Vol.addChangeListener(e -> {
            double vol = Saw_Vol.getValue();
            vol = vol/300;
            Oscillators.SawtoothOscillator.amplitude.set(vol);
        });
        Saw_Freq.addChangeListener(e -> {
            double freq = Saw_Freq.getValue();
            Oscillators.SawtoothOscillator.frequency.set(freq);
        });
        Triangle_Vol.addChangeListener(e -> {
            double vol = Triangle_Vol.getValue();
            vol = vol/300;
            Oscillators.TriangleOscillator.amplitude.set(vol);
        });
        Triangle_Freq.addChangeListener(e -> {
            double freq = Triangle_Freq.getValue();
            Oscillators.TriangleOscillator.frequency.set(freq);
        });
        Sine_Vol.addChangeListener(e -> {
            double vol = Sine_Vol.getValue();
            vol = vol/300;
            Oscillators.SineOscillator.amplitude.set(vol);
        });
        Sine_Freq.addChangeListener(e -> {
            double freq = Sine_Freq.getValue();
            Oscillators.SineOscillator.frequency.set(freq);
        });
        LowPassCutoff.addChangeListener(e -> {
            float freq = LowPassCutoff.getValue();
            Filters.LowPassFilter.frequency.set(freq);
        });
        HighPassCutoff.addChangeListener(e -> {
            float freq = HighPassCutoff.getValue();
            Filters.HighPassFilter.frequency.set(freq);
        });
        BandStopCutoff.addChangeListener(e -> {
            float freq = BandStopCutoff.getValue();
            Filters.BandStopFilter.frequency.set(freq);
        });
        oneOctaveDown.addActionListener(e -> {
            if (Octaves>-2){
                Octaves -= 1;
                OctaveLabel.setText(String.valueOf(Octaves));
            }
        });
        oneOctaveUp.addActionListener(e -> {
            if (Octaves<2) {
                Octaves += 1;
                OctaveLabel.setText(String.valueOf(Octaves));
            }
        });
        Attack.addChangeListener(e -> {
            float value = Attack.getValue();
            float converted_value = value/1000;
            randComponents.Envelope.attack.setup(0.002, converted_value, 2.5);
        });
        Decay.addChangeListener(e -> {
            float value = Decay.getValue();
            float converted_value = value/1000;
            randComponents.Envelope.decay.setup(0.002, converted_value, 2.5);
        });
        Sustain.addChangeListener(e -> {
            float value = Sustain.getValue();
            float converted_value = value/100;
            randComponents.Envelope.sustain.setup(0.002, converted_value, 2.5);
        });
        Release.addChangeListener(e -> {
            float value = Release.getValue();
            float converted_value = value/1000;
            randComponents.Envelope.release.setup(0.002, converted_value, 2.5);
        });
        C1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                double freq = Keys[0] * Octave_multi[Octaves+2];
                play_note(freq);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                stop_note();
            }
        });
        C1b.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                double freq = Keys[1] * Octave_multi[Octaves+2];
                play_note(freq);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                stop_note();
            }
        });
        D1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                double freq = Keys[2] * Octave_multi[Octaves+2];
                play_note(freq);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                stop_note();
            }
        });
        D1b.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                double freq = Keys[3] * Octave_multi[Octaves+2];
                play_note(freq);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                stop_note();
            }
        });
        E1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                double freq = Keys[4] * Octave_multi[Octaves+2];
                play_note(freq);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                stop_note();
            }
        });
        F1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                double freq = Keys[5] * Octave_multi[Octaves+2];
                play_note(freq);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                stop_note();
            }
        });
        F1b.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                double freq = Keys[6] * Octave_multi[Octaves+2];
                play_note(freq);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                stop_note();
            }
        });
        G1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                double freq = Keys[7] * Octave_multi[Octaves+2];
                play_note(freq);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                stop_note();
            }
        });
        G1b.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                double freq = Keys[8] * Octave_multi[Octaves+2];
                play_note(freq);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                stop_note();
            }
        });
        A1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                double freq = Keys[9] * Octave_multi[Octaves+2];
                play_note(freq);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                stop_note();
            }
        });
        A1b.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                double freq = Keys[10] * Octave_multi[Octaves+2];
                play_note(freq);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                stop_note();
            }
        });
        B1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                double freq = Keys[11] * Octave_multi[Octaves+2];
                play_note(freq);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                stop_note();
            }
        });
        C2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                double freq = Keys[0] * Octave_multi[Octaves+3];
                play_note(freq);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                stop_note();
            }
        });
        C2b.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                double freq = Keys[1] * Octave_multi[Octaves+3];
                play_note(freq);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                stop_note();
            }
        });
        D2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                double freq = Keys[2] * Octave_multi[Octaves+3];
                play_note(freq);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                stop_note();
            }
        });
        D2b.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                double freq = Keys[3] * Octave_multi[Octaves+3];
                play_note(freq);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                stop_note();
            }
        });
        E2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                double freq = Keys[4] * Octave_multi[Octaves+3];
                play_note(freq);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                stop_note();
            }
        });
        F2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                double freq = Keys[5] * Octave_multi[Octaves+3];
                play_note(freq);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                stop_note();
            }
        });
        F2b.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                double freq = Keys[6] * Octave_multi[Octaves+3];
                play_note(freq);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                stop_note();
            }
        });
        G2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                double freq = Keys[7] * Octave_multi[Octaves+3];
                play_note(freq);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                stop_note();
            }
        });
        G2b.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                double freq = Keys[8] * Octave_multi[Octaves+3];
                play_note(freq);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                stop_note();
            }
        });
        A2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                double freq = Keys[9] * Octave_multi[Octaves+3];
                play_note(freq);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                stop_note();
            }
        });
        A2b.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                double freq = Keys[10] * Octave_multi[Octaves+3];
                play_note(freq);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                stop_note();
            }
        });
        B2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                double freq = Keys[11] * Octave_multi[Octaves+3];
                play_note(freq);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                stop_note();
            }
        });
        sustain_note.addChangeListener(e -> sustain_var = sustain_note.getValue());
        LFO_Freq.addChangeListener(e -> {
            float freq = LFO_Freq.getValue();
            Oscillators.SineLFO.frequency.set(freq/10);
        });
        LFO_Connections.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int value = LFO_Connections.getValue();
                if(value == 1){
                    Oscillators.SineLFO.output.disconnectAll();
                }
                if(value == 2){
                    Oscillators.SineLFO.output.connect(Oscillators.SquareOscillator.frequency);
                    Oscillators.SineLFO.output.connect(Oscillators.SawtoothOscillator.frequency);
                    Oscillators.SineLFO.output.connect(Oscillators.TriangleOscillator.frequency);
                    Oscillators.SineLFO.output.connect(Oscillators.SineOscillator.frequency);
                }
                if(value == 3){
                    Oscillators.SineLFO.output.connect(Filters.LowPassFilter.frequency);
                }
                if(value == 4){
                    Oscillators.SineLFO.output.connect(Filters.HighPassFilter.frequency);
                }
                if(value == 5){
                    Oscillators.SineLFO.output.connect(Filters.BandStopFilter.frequency);
                }
            }
        });
    }

    public void synthesizer() {
        //Make synth
        synth = JSyn.createSynthesizer();

        //Add components
        synth.add(Oscillators.SineOscillator);
        Oscillators.SineOscillator.amplitude.set(0);
        synth.add(Oscillators.SquareOscillator);
        Oscillators.SquareOscillator.amplitude.set(0);
        synth.add(Oscillators.SawtoothOscillator);
        Oscillators.SawtoothOscillator.amplitude.set(0);
        synth.add(Oscillators.TriangleOscillator);
        Oscillators.TriangleOscillator.amplitude.set(0);
        synth.add(Oscillators.SineLFO);
        Oscillators.SineLFO.amplitude.set(2000);
        synth.add(Oscillators.SawtoothLFO);
        synth.add(Oscillators.TriangleLFO);

        synth.add(Filters.LowPassFilter);
        Filters.LowPassFilter.Q.set(1);
        Filters.LowPassFilter.frequency.set(2100);
        synth.add(Filters.HighPassFilter);
        Filters.HighPassFilter.Q.set(1);
        Filters.HighPassFilter.frequency.set(1);
        synth.add(Filters.BandStopFilter);
        Filters.BandStopFilter.Q.set(1);
        Filters.BandStopFilter.frequency.set(1);

        synth.add(randComponents.lineOut);
        synth.add(randComponents.Mixer1);
        synth.add(randComponents.Mixer2);
        synth.add(randComponents.Envelope);
        synth.add(randComponents.Step);
        randComponents.Step.frequency.setup(0, 0, 5.0);
        randComponents.Step.amplitude.set(0);

        //Connections
        //Connect Envelope
        randComponents.Step.output.connect(randComponents.Envelope.input);
        randComponents.Envelope.output.connect(randComponents.Mixer2.input);
        randComponents.Envelope.attack.setup(0.001, 0.1, 2.0);
        randComponents.Envelope.decay.setup(0.001, 0.1, 2.0);
        randComponents.Envelope.sustain.setup(0.001, 0.5, 2.0);
        randComponents.Envelope.release.setup(0.001, 0.5, 2.0);

        randComponents.Mixer2.output.connect(Oscillators.SineOscillator.amplitude);
        randComponents.Mixer2.output.connect(Oscillators.SquareOscillator.amplitude);
        randComponents.Mixer2.output.connect(Oscillators.SawtoothOscillator.amplitude);
        randComponents.Mixer2.output.connect(Oscillators.TriangleOscillator.amplitude);

        //Connect all oscillators to mixer
        Oscillators.SineOscillator.output.connect(randComponents.Mixer1.input);
        Oscillators.SquareOscillator.output.connect(randComponents.Mixer1.input);
        Oscillators.SawtoothOscillator.output.connect(randComponents.Mixer1.input);
        Oscillators.TriangleOscillator.output.connect(randComponents.Mixer1.input);

        //Connect mixer to filters
        randComponents.Mixer1.output.connect(Filters.LowPassFilter.input);
        Filters.LowPassFilter.output.connect(Filters.HighPassFilter.input);
        Filters.HighPassFilter.output.connect(Filters.BandStopFilter.input);

        //Connect to output
        Filters.BandStopFilter.output.connect(0, randComponents.lineOut.input, 0);
        Filters.BandStopFilter.output.connect(0, randComponents.lineOut.input, 1);

        //Start synth
        synth.start();
        randComponents.lineOut.start();

        // Play signal while GUI is open
        while(MainPanel.isValid()){
            try {
                synth.sleepFor(0.000001);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synth.stop();
    }

    public static void main(String[] args){
        new GUI().synthesizer();
    }

}
