import java.util.ArrayList;

public class HammingNetwork {
	
	private int inputSize;
	private double T;
	
	private MaxNetwork maxNetwork;

	private ArrayList<double []> vectors = new ArrayList<double []>();
	
	public HammingNetwork(int inputSize) { 
		
		this.inputSize = inputSize; 
		T = inputSize / 2.0;	 
	}
	
	public void train(double[] vector) {
		double[] newCoeffs = new double[inputSize];
		
		for (int i = 0; i < inputSize; i++) {
			newCoeffs[i] = vector[i] / 2;
		}
		
		vectors.add(newCoeffs);
	}
	
	public void finishTraining(MaxNetwork maxNetwork) {
		
		this.maxNetwork = maxNetwork;
	}
//	public void finishTraining(NeuronCreator neuronCreator) {
//		maxNetwork = new MaxNetwork(vectors.size(), neuronCreator);
//	}
	
	public int getSize() {
		
		return this.inputSize;
	}
	
	public ArrayList<double []> getVectors() {
		
		return this.vectors;
	}
	
	public int recognize(double[] input) {
		
		int size = vectors.size();
		
		double[] result = new double[size];
		
		for (int j = 0; j < size; j++) {
			
			result[j] = 0;
			for (int i = 0; i < getSize(); i++) {
				result[j] += input[i] * vectors.get(j)[i];
			}
			result[j] += T;
		}
		
		maxNetwork.init(result);
		return maxNetwork.start();	
	}
	

}
