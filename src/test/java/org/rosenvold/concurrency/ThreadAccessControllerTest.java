package org.rosenvold.concurrency;

import org.rosenvold.concurrency.accesscontrollers.ThreadAccessControllerImpl;
import org.rosenvold.concurrency.accesscontrollers.UnsafeCollectionAccessException;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author <a href="mailto:kristian.rosenvold gmail com">Kristian Rosenvold</a>
 */
public class ThreadAccessControllerTest {

    @Test
    public void testShouldBeSafe() throws Exception {
        ThreadAccessControllerImpl sut = new ThreadAccessControllerImpl();

        sut.checkThreadAccess();
        sut.checkThreadAccess();

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        final Future<Object> stringFuture1 = executorService.submit(new ThreadAccessControllerModifier(sut, "bax2"));
        final Future<Object> stringFuture2 = executorService.submit(new ThreadAccessControllerModifier(sut, "bax3"));

        stringFuture1.get();
        stringFuture2.get();

    }

    @Test(expected = UnsafeCollectionAccessException.class)
    public void testShouldFail() throws Exception {
        ThreadAccessControllerImpl sut = new ThreadAccessControllerImpl();

        sut.checkThreadAccess();
        sut.checkThreadAccess();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        final Future<Object> stringFuture1 = executorService.submit(new ThreadAccessControllerModifier(sut, "bax2"));
        stringFuture1.get();

        // this won't work
        sut.checkThreadAccess();

    }

    class ThreadAccessControllerModifier implements Callable<Object> {
        final ThreadAccessControllerImpl threadAccessController;
        private final String value;

        ThreadAccessControllerModifier( ThreadAccessControllerImpl threadAccessController, String value) {
            this.threadAccessController = threadAccessController;
            this.value = value;
        }

        public Object call() throws Exception {
            threadAccessController.checkThreadAccess();
            return this;
        }
    }


}
