# Concurrency Testing tools

## Fail-fast collections.

Make a branch/copy of your code, add this library to pom.xml:

<dependency>
    <groupId>org.jdogma.concurrency</groupId>
    <artifactId>failfast-collections</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>


## Basic operation

You do a search-replace in your code:

new HashMap --> new org.jdogma.concurrency.explore.DiagnosticHashMap
new ArrayList --> new org.jdogma.concurrency.explore.DiagnosticArrayList
new HashSet --> new org.jdogma.concurrency.explore.DiagnosticHashSet

Now things will explode the next time you run with threads. Run with # threads > 3.

## Refining your findings

 Once you stumble upon a concurrency problem you will be informed of where the
 offending collection was allocated. Normally I then just change the package part
 from "explore" to "knownproblem". This uses a fully synchronized implementation of the
 collection, while you can go go charting/finding problems.

 The contract for "Map" and "Set" is very rigid wrt thread-safety; there is basically no
 operation you can do that is thread-safe on these classes.

 ArrayList is a bit different, since the contract allows reads to be done concurrently.

 When you find a problem that involves an "ArrayList" you can choose to replace "explore" with "contract" instead.
 This will allow you to determine if illegal access is happening according to the contract of ArrayList.


 It is a sad part of the api's that this will only tell you what you current code is doing. So there's little
 preventing furture code changes to break this again.

 

