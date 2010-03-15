package jp.co.softem.ikko.core;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.t2framework.commons.meta.BeanDesc;
import org.t2framework.commons.meta.BeanDescFactory;
import org.t2framework.commons.util.CollectionsUtil;
import org.t2framework.t2.adapter.AbstractContainerAdapter;
import org.t2framework.t2.handler.ExceptionHandler;
import org.t2framework.t2.handler.GlobalExceptionHandler;
import org.t2framework.t2.handler.impl.GlobalExceptionHandlerImpl;

@SuppressWarnings("unchecked")
public class CdiAdapter extends AbstractContainerAdapter<BeanManager> {

	BeanManager beanManager;

	public void init() {
		init("");
	}

	public void init(String string) {
		try {
			beanManager = InitialContext.doLookup("java:comp/BeanManager");
		} catch (NamingException ex) {
			Logger.getLogger(CdiAdapter.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	public <T> boolean hasComponent(Class<T> type) {
		Logger logger = Logger.getLogger(CdiAdapter.class.getName());
		for (Bean b : beanManager.getBeans(type)) {
			logger.fine(b.getName());
			return true;
		}
		return false;
	}

	public <T> T getComponent(Class<? super T> type) {
		for (Bean b : beanManager.getBeans(type)) {
			CreationalContext cc = beanManager.createCreationalContext(null);
			Object result = beanManager.getReference(b, type, cc);
			if (result == null) {
				result = b.create(cc);
			} else {
			}
			return (T) result;
		}
		return null;
	}

	public <T> List<T> getComponents(Class<? super T> type) {
		List<Class<?>> result = new ArrayList<Class<?>>();
		for (Bean b : beanManager.getBeans(type)) {
			result.add(b.getBeanClass());
		}
		return (List<T>) result;
	}

	public <T> BeanDesc<T> getBeanDesc(Class<? super T> type) {
		if (hasComponent(type)) {
			return (BeanDesc<T>) BeanDescFactory.getBeanDesc(type);
		}
		return null;
	}

	public <T> void register(Class<? extends T> type) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public <T> void register(T t) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public <T> T injectDependency(T t) {
		Logger logger = Logger.getLogger(CdiAdapter.class.getName());
		for (Bean b : beanManager.getBeans(t.getClass())) {
			logger.fine(b.getName());
			beanManager.createAnnotatedType(Inject.class);
			return t;
		}
		return t;
	}

	public void destroy() {
	}

	public BeanManager getContainer() {
		return beanManager;
	}

	public List<ExceptionHandler<Throwable, Exception>> createExceptionHandlers() {
		return CollectionsUtil.emptyList();
	}

	public GlobalExceptionHandler createGlobalExceptionHandler() {
		return new GlobalExceptionHandlerImpl();
	}

}
