import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class NeuronTest {
	
	private Neuron neuron;
	
	@BeforeClass
	public void init() {
		this.neuron = new NeuronImpl();
	}
	
	@Test
	public void activationTest() {
		neuron.setInner(14);
		neuron.process();
		double output = neuron.getOutput();
		Assert.assertEquals(output, 14.0);
	}
	
	@Test
	public void nonActivationTest() {
		neuron.setInner(-3);
		neuron.process();
		double output = neuron.getOutput();
		Assert.assertEquals(output, 0.0);
	}
	
	@Test
	public void calculationTest() {
		neuron.setInner(0);
		neuron.process();
		
		Neuron mockNeuron = mock(Neuron.class);
		when(mockNeuron.getOutput()).thenReturn(1.0);
		
		neuron.addInput(mockNeuron);
		Assert.assertEquals(neuron.calculateInner(), -0.2);
		
	}
}
