
package com.airhacks.vacs.monitoring.control;

import java.lang.reflect.Method;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author airhacks.com
 */
public class MethodPerformanceSensor {

    @Inject
    MethodStatisticsStore store;

    @AroundInvoke
    public Object measure(InvocationContext ic) throws Exception {
        Method method = ic.getMethod();
        long start = System.currentTimeMillis();
        try {
            return ic.proceed();
        } finally {
            long duration = System.currentTimeMillis() - start;
            this.store.addStatistics(method.getName(), duration);
        }
    }


}
