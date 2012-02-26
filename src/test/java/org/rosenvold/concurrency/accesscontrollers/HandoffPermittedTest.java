package org.rosenvold.concurrency.accesscontrollers;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static junit.framework.Assert.assertEquals;

public class HandoffPermittedTest {

    @Test
    public void testSetItem() throws Exception {
            HandoffPermitted<Integer> sut = new HandoffPermitted<Integer>();
        sut.setItem(23);
        assertEquals((Integer)23, sut.getItem());
    }

    @Test
    public void testNotAllowedItem() throws Exception {
        final HandoffPermitted<Integer> sut = new HandoffPermitted<Integer>();
        final AtomicInteger succeed = new AtomicInteger(0);
        sut.setItem(23);
        Thread thread = new Thread(new Runnable() {
            public void run() {
               sut.getItem();
                succeed.incrementAndGet();
            }
        });
        thread.start();
        thread.join();
        assertEquals(0, succeed.intValue());
    }
}
