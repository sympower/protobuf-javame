#!/bin/sh
cd ..
protoc --java_out=src/test/java src/test/resources/integration-test/IntegrationTestJavaSe.proto
protoc --java_out=src/test/java src/test/resources/integration-test/UpdatedIntegrationTestJavaSe.proto
protoc --java_out=src/test/java src/test/resources/integration-test-enums/EnumTestJavaSe.proto
protoc --java_out=src/test/java src/test/resources/integration-test-nested-types/NestedTestJavaSe.proto
protoc --java_out=src/test/java src/test/resources/integration-test-nested-types/NestedListTestJavaSe.proto
protoc --java_out=src/test/java src/test/resources/integration-test-nested-types/EmployeeGoalJavaSe.proto