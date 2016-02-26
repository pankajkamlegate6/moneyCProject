package com.moneycontrol.handheld.api;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.util.Log;
import android.widget.Toast;

import com.moneycontrol.handheld.BaseActivity;

public class FragmentBackStackHandler {
	private BaseActivity activity;
	private HashMap<String, Integer> backstack = new HashMap<String, Integer>();
	public static FragmentBackStackHandler instance;

	public static void setInstance(FragmentBackStackHandler instance) {
		FragmentBackStackHandler.instance = instance;
	}

	public FragmentBackStackHandler(BaseActivity activity) {
		this.activity = activity;
		monitorBackStack();
	}

	public static FragmentBackStackHandler getInstance(BaseActivity activity) {
		if (instance == null) {
			instance = new FragmentBackStackHandler(activity);

		}
		return instance;
	}

	public void addFragmentLevel(String name, int level) {
		backstack.put(name, level);

	}

	private void monitorBackStack() {
		activity.getSupportFragmentManager().addOnBackStackChangedListener(
				new OnBackStackChangedListener() {

					@Override
					public void onBackStackChanged() {
						int count = activity.getSupportFragmentManager()
								.getBackStackEntryCount();
						for (int i = 0; i < count; i++) {
							Log.e("state", "added "
									+ activity.getSupportFragmentManager()
											.getBackStackEntryAt(i).getName());
						}

					}
				});
		activity.getSupportFragmentManager().removeOnBackStackChangedListener(
				new OnBackStackChangedListener() {

					@Override
					public void onBackStackChanged() {
						int count = activity.getSupportFragmentManager()
								.getBackStackEntryCount();
						for (int i = 0; i < count; i++) {
							Log.e("state", "remove "
									+ activity.getSupportFragmentManager()
											.getBackStackEntryAt(i).getName());
						}

					}
				});
	}

	public void performRemoveFragment(int index) {
		try {
			@SuppressWarnings("unchecked")
			ArrayList<BackStackEntry> backStack = (ArrayList<BackStackEntry>) getValueFromField(
					null, activity.getSupportFragmentManager(), "mBackStack");

			if (backStack != null && backStack.size() > 1) {
				int backStackSize = backStack.size();

				removeBackStackEntry(backStack, index);
				return;

			} else if (backStack != null && backStack.size() != 0) {
				removeBackStackEntry(backStack, 0);
				return;
			}

		} catch (Exception e) {
			// not handled
			e.printStackTrace();
		}
	}

	/**
	 * Removes the Fragment with index of {@code index} from the back stack
	 * 
	 * @param backStack
	 *            the backstack list
	 * @param index
	 *            the index of the Fragment to be removed in {@code backStack}
	 */
	@SuppressWarnings("unchecked")
	private void removeBackStackEntry(List<BackStackEntry> backStack,
			final int index) {
		FragmentManager fm = activity.getSupportFragmentManager();
		if (backStack.size() - 1 <= 1 || backStack.size() - 1 == index) {
			fm.popBackStack();
			return;
		}
		if (backStack.size() <= index || index < 0) {

			return;
		}

		synchronized (fm) {
			// FragmentManagerImpl#mActive
			List<Fragment> activeFragments = fm.getFragments();

			if (index < 0 || index > activeFragments.size()
					|| activeFragments.get(index) == null) {
				return;
			}

			int backStackSize = backStack.size();
			for (int i = index; i < backStackSize; i++) {
				// fm relies on mIndex for saving the state of the fragment,
				// showing the next/previous, etc.
				// we need to change their index values
				// because we're going to remove one fragment from the backstack
				int newIndex = i - 1;
				Fragment tmpFr = activeFragments.get(i);
				BackStackEntry tmpBse = backStack.get(i);
				if (tmpFr != null) {
					setValueToField(Fragment.class, activeFragments.get(i),
							"mIndex", newIndex);
				} else {
					// should never happen
					continue;
				}
				if (tmpBse != null) {
					setValueToField(null, backStack.get(i), "mIndex", newIndex);
				} else {
					// should never happen
				}
			}

			// remove the fragment from the backstack list
			BackStackEntry removedEntry = backStack.remove(index);
			int removedEntryIndex = index;

			// BackStackRecord#popFromBackStack uses the 'removed' fragment list
			// to go back to the previous fragment.
			// here we set the list of 'removed' fragments from the fragment
			// to-be-removed, to the next one in the backstack
			Object removedEntryOpHead = getValueFromField(null, removedEntry,
					"mHead");
			Object removedOpRemovedFragment = getValueFromField(null,
					removedEntryOpHead, "removed");

			Object nextEntryOpHead = getValueFromField(null,
					backStack.get(removedEntryIndex), "mHead");
			setValueToField(null, nextEntryOpHead, "removed",
					removedOpRemovedFragment);
			Object nextEntryOpTail = getValueFromField(null,
					backStack.get(removedEntryIndex), "mTail");
			setValueToField(null, nextEntryOpTail, "removed",
					removedOpRemovedFragment);

			Fragment removedFragment = activeFragments.get(index);
			try {
				// perform destroy one the fragment before removing it
				Method performDestoryMethod = Fragment.class
						.getDeclaredMethod("performDestroy");
				performDestoryMethod.setAccessible(true);
				performDestoryMethod.invoke(removedFragment);

				// FragmentManagerImpl#mAvailBackStackIndices
				List<Integer> availableIndices = (List<Integer>) getValueFromField(
						fm.getClass(), fm, "mAvailBackStackIndices");

				// FragmentManagerImpl#mBackStackIndices
				List<BackStackEntry> backStackIndices = (List<BackStackEntry>) getValueFromField(
						fm.getClass(), fm, "mBackStackIndices");

				// we need to know where to store the fragment and it's
				// backstackrecord so we can use the manager to make the
				// fragment inactive and mark it's index as free.
				// this will place the objects after the last non-null item in
				// the lists
				int nextAvailableIndex = backStackIndices.size() - 1;
				if (availableIndices != null && availableIndices.size() > 0) {
					nextAvailableIndex = availableIndices.get(availableIndices
							.size() - 1);
					nextAvailableIndex = Math.min(backStackIndices.size() - 1,
							nextAvailableIndex - 1);
				} else if (backStackIndices.size() < activeFragments.size()) {
					// this can happen if the parent activity was restarted for
					// example. now 'activeFragments' holds some null values but
					// 'backStackIndices' holds only non-null values and
					// 'availableIndices' is empty.
					// because 'backStackIndices' does not hold any null values
					// we know that we should place the fragment on the back of
					// that list
					nextAvailableIndex = backStackIndices.size() - 1;
				}

				// move the fragment to be back of the list
				activeFragments.remove(index);
				activeFragments.add(nextAvailableIndex, removedFragment);
				// modify the 'mIndex' field to match the position in the list
				// so we can safely call `makeInactive`
				setValueToField(Fragment.class, removedFragment, "mIndex",
						nextAvailableIndex);

				// notify the fragment that it's detached
				removedFragment.onDetach();

				// move the bsr to be back of the list
				backStackIndices.remove(index);
				backStackIndices.add(nextAvailableIndex, removedEntry);

				// free the index we used for the removed items
				Method freeIndexMethod = fm.getClass().getDeclaredMethod(
						"freeBackStackIndex", int.class);
				freeIndexMethod.setAccessible(true);
				freeIndexMethod.invoke(fm, nextAvailableIndex);

				// the frament needs to be active to be detached by
				// detachFragment
				setValueToField(Fragment.class, removedFragment, "mAdded", true);
				Method detachFragmentMethod = fm.getClass().getDeclaredMethod(
						"detachFragment", Fragment.class, int.class, int.class);
				detachFragmentMethod.invoke(fm, removedFragment, 0, 0);

				// invalidate the fragment.
				// removes it from the 'mActive' list, adds the index to the
				// available indices list, destroys loaders, etc.
				Method makeInactiveMethod = fm.getClass().getDeclaredMethod(
						"makeInactive", Fragment.class);
				makeInactiveMethod.setAccessible(true);
				makeInactiveMethod.invoke(fm, removedFragment);

				// clear references of the activity and manager
				setValueToField(Fragment.class, removedFragment, "mActivity",
						null);
				setValueToField(Fragment.class, removedFragment,
						"mFragmentManager", null);

			} catch (Exception e) {
				// not handled, like every other exception
				e.printStackTrace();

			}
		}
	}

	/**
	 * 
	 * @param clazz
	 *            will be read from {@code fieldObject} if null
	 * @param fieldObject
	 * @param fieldName
	 * @param value
	 */
	private void setValueToField(Class<?> clazz, Object fieldObject,
			String fieldName, Object value) {
		if (clazz == null) {
			clazz = fieldObject.getClass();
		}
		try {
			Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(fieldObject, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param clazz
	 *            will be read from {@code fieldObject} if null
	 * @param fieldObject
	 * @param fieldName
	 * @return
	 */
	private Object getValueFromField(Class<?> clazz, Object fieldObject,
			String fieldName) {
		if (fieldObject == null) {
			return null;
		}

		if (clazz == null) {
			clazz = fieldObject.getClass();
		}

		try {
			Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			return field.get(fieldObject);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
