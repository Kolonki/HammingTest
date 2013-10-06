
public interface Neuron {

	double getOutput();

	void setInner(double i);

	double process();

	void addInput(Neuron neuron);

	double calculateInner();

}
