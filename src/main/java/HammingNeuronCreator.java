
public class HammingNeuronCreator extends NeuronCreator {

	@Override
	public Neuron createNeuron() {
		
		return new NeuronImpl();
	}
}
