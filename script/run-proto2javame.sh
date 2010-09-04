#!/bin/sh
cd ..
java -jar lib/generate/proto2javame-[0-9].*.jar --java_out=src/test/generated src/test/resources/Test.proto
java -jar lib/generate/proto2javame-[0-9].*.jar --java_out=src/test/generated src/test/resources/JUnitTest.proto
java -jar lib/generate/proto2javame-[0-9].*.jar --java_out=src/test/generated src/test/resources/JUnitTest2.proto
java -jar lib/generate/proto2javame-[0-9].*.jar --java_out=src/test/generated src/test/resources/LargeNestedTestFile.proto