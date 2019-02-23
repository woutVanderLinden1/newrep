package view.viewItems.ItemBox;

import model.Item;

public interface AvailabilityChangeListener {

	void depleted(Item item);

	void added(Item item);

}
