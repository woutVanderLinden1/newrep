package misc;

import java.util.ArrayList;

import model.Activation;
import model.CustomActivation;

public interface ActivateAble {

	ArrayList<Activation> getActivations();
	boolean isActivateAble();
	void addActivation(Activation act);
	String getName();
	void removeActivation(Activation activation);

}
