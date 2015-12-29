package no.grabit.NCLauncher.input;

/**
 * Created by Ole on 30/05/2015.
 */
public interface Focusable {

	void onGetFocus();

	void onLoseFocus();

	void setFocusGroup(FocusGroup group);

}
