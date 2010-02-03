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
Collections.emptyList --> org.jdogma.concurrency.explore.DiagnosticArrayList.emptyList 