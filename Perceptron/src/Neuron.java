
public class Neuron {

	private int numInputs;
	private Vector weights;
	
	public Neuron(int numInputs) {
		this.numInputs = numInputs;
		this.weights = Vector.getRandomVector(numInputs, 2);
	}
	
	public void train(DataPoint[] dataPoints, int numTimes, double learningRate) {
		for (int i = 0; i < numTimes; i++) {
			Vector deltaW = new Vector(new double[numInputs]);
			for (DataPoint d : dataPoints) {
				double yHat = d.getLabel();
				Vector inputs = d.getVector();
				double y = feed(inputs);
				deltaW = deltaW.add(inputs.scalarMultiply(-(yHat-y)*learningRate));
			}
			weights = weights.add(deltaW);
		}
	}
	
	public double feed(Vector inputs) {
		return sigmoid(inputs.dot(weights));
	}
	
	public double sigmoid(double x) {
		return ((1.0/(1+Math.pow(Math.E, -x)))-0.5)*2;
	}
	
}