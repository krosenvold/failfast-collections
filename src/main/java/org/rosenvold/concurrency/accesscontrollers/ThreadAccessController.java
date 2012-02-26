package org.rosenvold.concurrency.accesscontrollers;

/**
 * @author Kristian Rosenvold
 */
public interface ThreadAccessController
{
    void checkWriteAccess();

    void checkReadAccess();
}
