
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HammingNetworkTest {
	
	HammingNetwork hammingNetwork;
	
  @BeforeClass
  public void init() {
	  
	  this.hammingNetwork = new HammingNetwork(3);
  }
  
  @Test
  public void checkSize() {
	  
	  Assert.assertEquals(this.hammingNetwork.getSize(), 3);
  }
  
  @Test
  public void train() {
	  
	  this.hammingNetwork.train(new double[] {4.0, 4.0, 4.0});
	  
	  double[] vector = this.hammingNetwork.getVectors().get(0);
	  
	  Assert.assertEquals(vector, new double[] {2.0, 2.0, 2.0});
  }
  
  @Test
  public void recognize() {
	  
	  MaxNetwork maxNetworkMock = mock(MaxNetwork.class);
	  
	  HammingNetwork hammingNetwork = new HammingNetwork(3);
	  hammingNetwork.train(new double[] {4.0, 4.0, 4.0});
	  hammingNetwork.finishTraining(maxNetworkMock);
	  
	  hammingNetwork.recognize(new double[] {4.0, 4.0, 4.0});
	  verify(maxNetworkMock).init(new double[] {25.5});
	  verify(maxNetworkMock).start();
  }
  
  
}
