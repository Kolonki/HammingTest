import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class MaxNetworkTest {
	
	MaxNetwork maxNetwork;
	
	
	@Test
	public void checkNeurons() {
		
		MockNeuronCreator neuronCreator = new MockNeuronCreator();
		maxNetwork = new MaxNetwork(5, neuronCreator);
		
		Assert.assertEquals(neuronCreator.count, 5);
	}
	
	@Test
	public void checkOutput() {
		
		MockNeuronCreator neuronCreator = new MockNeuronCreator();
		maxNetwork = new MaxNetwork(3, neuronCreator);
		
		ArrayList<Double> array = new ArrayList<Double>(Arrays.asList(4.0, 4.0, 4.0));
		
		Assert.assertEquals(array, maxNetwork.getOutput());
		
	}
	
	@Test
	public void checkInit() {
		MockNeuronCreator neuronCreator = new MockNeuronCreator();
		maxNetwork = new MaxNetwork(3, neuronCreator);
		maxNetwork.init( new double[] {2.0, 2.0, 2.0} );
		
		ArrayList<Double> array = new ArrayList<Double>(Arrays.asList(4.0, 4.0, 4.0));
		
		Assert.assertEquals(array, maxNetwork.getOutput());
		
	}
	
	@Test
	public void checkStart() {
		
		MockNeuronCreator neuronCreator = new MockNeuronCreator();
		maxNetwork = new MaxNetwork(3, neuronCreator);
		
		MaxNetwork spy = spy(maxNetwork);
		when(spy.isFinished()).thenReturn(true);
		
		spy.start();
		verify(spy, never()).process();
		verify(spy, times(3)).getOutput();
	}
	
	@Test
	public void checkProcess() {
		
		MockNeuronCreator neuronCreator = new MockNeuronCreator();
		maxNetwork = new MaxNetwork(3, neuronCreator);
		
		maxNetwork.process();
		ArrayList<Double> array = new ArrayList<Double>(Arrays.asList(4.0, 4.0, 4.0));
		
		Assert.assertEquals(array, maxNetwork.getOutput());
	
	}
}
