import java.util.ArrayList;

public class MaxNetwork {
	
	final int MAX_COUNT = 100; 
	int count; //if count >= MAX_COUNT, thats bad:( just finish then
	
	private int size;
	
	private ArrayList<Neuron> neurons = new ArrayList<Neuron>();
	private ArrayList<Double> output;

	public MaxNetwork(int size, NeuronCreator neuronCreator) {
		
		this.size = size;
		output = new ArrayList<Double>(size);
		Neuron n;
		
		for (int i = 0; i < size; i++) {
			n = neuronCreator.createNeuron();
			n.process();
			
			neurons.add(n);
			output.add(n.getOutput());
		}
		
		connectNeurons();
	}
	
	public void init(double[] values) {
		
		count = 0;
		
		for (int i = 0; i < size; i++) {
			neurons.get(i).setInner(values[i]);
			neurons.get(i).process();
			output.set(i, neurons.get(i).getOutput());	
		}		
	}
	
	public int start() {
		
		while ( !isFinished() ) {
			process();			
		}

		int index = 0;
		
		for (int i = 0; i < size; i++) {
			if (getOutput().get(i) != 0) {
				index = i;
			}
		}
		
		return index;
	}
	
	public ArrayList<Double> getOutput() {
		return output;
	}
	
	void process() {
		for (Neuron n : neurons) {
			n.calculateInner();
		}		
		
		for (int i = 0; i < size; i++) {
			neurons.get(i).process();
			output.set(i, neurons.get(i).getOutput());
		}
	}
	
	private void connectNeurons() {
		
		for (Neuron n1 : neurons) {
			for (Neuron n2 : neurons) {
				
				if (n1 != n2)
					n1.addInput(n2);
			}
		}
	}
	
	boolean isFinished() {
		
		if (count >= MAX_COUNT) {
			return true;
		}
		
		count++;
		
		int countNonZero = 0;
		
		for (double out : output) {
			if (out > 0)
				countNonZero++;
		}
		
		return countNonZero <= 1;
	}
}
