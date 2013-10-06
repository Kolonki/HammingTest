
public class MockNeuronCreator extends NeuronCreator {
	
	public int count;

	@Override
	public Neuron createNeuron() {
		
		count++;
		return new MockNeuron();
	}

}
