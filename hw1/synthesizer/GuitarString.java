package synthesizer;

public class GuitarString {
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor
    private int capacity = 0;

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        capacity  = (int)(Math.round(SR/frequency));
        buffer = new ArrayRingBuffer<Double>(capacity);
        for(int i = 0; i < capacity; i++) {
            buffer.enqueue(0.0);
        }
    }

    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        for(int i = 0; i < capacity; i++) {
            buffer.dequeue();
        }

        double[] usedRandomNumbers = new double[capacity];
        for(int i = 0; i < capacity; i++) {
            double r = Math.random() - 0.5;
            for(int j = 0; j < capacity; j++) {
                if(r == usedRandomNumbers[j]) {
                    r = Math.random() - 0.5;
                    j = 0;
                }
            }
            usedRandomNumbers[i] = r;
            buffer.enqueue(r);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double frontSample = buffer.dequeue();
        double nextSample = buffer.peek();
        double sound = (frontSample + nextSample)/2 * DECAY;
        buffer.enqueue(sound);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }

}