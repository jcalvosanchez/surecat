package com.htic.hticarq.core.pool;

import java.util.Enumeration;
import java.util.Hashtable;

public abstract class Pool<T> {

	private long expirationTime;
	private Hashtable<T, Long> locked, unlocked;


	//Constructors are declared as private
	//in order to force children classes to implement a Singleton Pattern
	protected Pool() {
		expirationTime	= 0;
		locked			= new Hashtable<T, Long>();
		unlocked		= new Hashtable<T, Long>();
	}
	protected Pool(long expirationTime) {
		this.expirationTime	= expirationTime;
		locked				= new Hashtable<T, Long>();
		unlocked			= new Hashtable<T, Long>();
	}


	protected abstract T createInstance();

	public abstract boolean validateInstance(T o);

	public abstract void expireInstance(T o);


	public synchronized T checkOutInstance() {
		T t;
		if (unlocked.size() > 0) {
			Enumeration<T> e = unlocked.keys();
			while (e.hasMoreElements()) {
				t = e.nextElement();
				if (isExpired(unlocked.get(t))) {
					// object has expired
					unlocked.remove(t);
					expireInstance(t);
					t = null;
				} else {
					if (validateInstance(t)) {
						unlocked.remove(t);
						lock(t);
						return (t);
					} else {
						// object failed validation
						unlocked.remove(t);
						expireInstance(t);
						t = null;
					}
				}
			}
		}
		// no objects available, create a new one
		t = createInstance();
		lock(t);

		return (t);
	}

	public synchronized void checkInInstance(T t) {
		locked.remove(t);
		unlocked.put(t, System.currentTimeMillis());
	}


	/**
	 * A 0 value for expirationTime means "no expiration".
	 */
	private boolean isExpired (long creationTimeMillis) {
		boolean result = false;
		long now = System.currentTimeMillis();

		if (expirationTime > 0) {
			result = (now - creationTimeMillis) > expirationTime;
		}

		return result;
	}

	private void lock (T t) {
		long now = System.currentTimeMillis();
		locked.put(t, now);
	}
}