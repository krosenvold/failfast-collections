package org.rosenvold.concurrency.accesscontrollers;

/**
 * @author Kristian Rosenvold
 */
public class AccessController
{
    private final StackTraceElement stackTraceAtConstruction;

    final StackTraceElement stackTraceAtConstruction2;

    public AccessController() {
        try
        {
            throw new Exception();
        }
        catch ( Exception e )
        {
            final StackTraceElement[] stackTraceElements = e.getStackTrace();
            stackTraceAtConstruction = stackTraceElements[2];
            stackTraceAtConstruction2 = stackTraceElements[3];
        }
    }


    protected String getStackTrace(){
        StringBuilder result = new StringBuilder( );
        result.append(stackTraceAtConstruction.toString());
        result.append( "\n");
        result.append(stackTraceAtConstruction2.toString());
        result.append( "\n");
        return result.toString();
    }
}
