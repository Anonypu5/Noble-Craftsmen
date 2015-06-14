package no.grabit.NCLauncher.input;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ole on 30/05/2015.
 */
public class FocusGroup {

	List<Focusable> focusables = new ArrayList<>();

	public void add(Focusable focusable) {
		focusables.add(focusable);
		focusable.setFocusGroup(this);
	}

	public void remove(Focusable focusable) {
		if(focusables.contains(focusable)) {
			focusables.remove(focusable);
			focusable.setFocusGroup(null);
		}
	}

	public void updateFocus(Focusable source, long time) {
		System.out.println(source == null);
		if(source != null && !focusables.contains(source))
			return;

		for(Focusable focusable : focusables) {
			if(focusable == source)
				focusable.onGetFocus();
			else
				focusable.onLoseFocus();
		}
	}

}
