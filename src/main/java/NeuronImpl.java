import java.util.ArrayList;

public class NeuronImpl implements Neuron {
	
	private static double epsilon = 0.2; // 0 < epsilon < 1/(outputs)
	
	private double innerValue;
	private double outputValue;
	
	private ArrayList<Neuron> inputs = new ArrayList<Neuron>();
	
	public NeuronImpl(double inner) {
		setInner(inner);
	}
	
	public NeuronImpl() {
		this(0);
	}
	
	public void setInner(double innerValue) {
		this.innerValue = innerValue;
	}
	
	public void addInput(Neuron neuron) {
		inputs.add(neuron);
	}
	
	public double calculateInner() {
		
		double inputSumm = 0;
		for (Neuron n : inputs) {
			inputSumm += n.getOutput();
		}	
		
		innerValue = getOutput() - epsilon * inputSumm;
		return innerValue;
	}
	
	public double process() {
		outputValue = activation(innerValue);
		return outputValue;
	}
	
	public double getOutput() {
		return outputValue;
	}
	
	private static double activation(double argument) {
		if (argument <= 0) {
			return 0;
		}
		else {
			return argument;
		}
	}
}
