# Concurrency Testing tools, fail-fast collections

Detects access behaviour to collections that is inconsistent with the java memory model. Normally blows up
with an exception when the third thread accesses the collection. This is an approximation of the instance
being constructed prior to starting threads, in which case the second thread can still access the data safely.

As long as the code can be run with a liberal amount of threads, this approximation will detect most questionable
access patterns.

Please note that this considers the access to the collections themselves. You probably want to look into
the objects inside the collections too ;)

## How to use

Checkout from source and build this project, mvn install

Make a branch/copy of your code, add this library to pom.xml:

<dependency>
    <groupId>org.jdogma.concurrency</groupId>
    <artifactId>failfast-collections</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>


## Basic operation

You do a search-replace in your code:

<pre>
new HashMap --> new org.jdogma.concurrency.explore.DiagnosticHashMap
new ArrayList --> new org.jdogma.concurrency.explore.DiagnosticArrayList
new java.util.ArrayList --> new org.jdogma.concurrency.explore.DiagnosticArrayList
new HashSet --> new org.jdogma.concurrency.explore.DiagnosticHashSet
new java.util.HashSet --> new org.jdogma.concurrency.explore.DiagnosticHashSet
</pre>

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
 This will allow you to determine if access is happening according to the concurrency contract of ArrayList.

 It is a sad part of the api's that this will only tell you what you current code is doing in the use case youre testing.
 So there's little preventing furture code changes to break this again. So use this tool to chart your concurrency issues
 for further analysis.
 

 

