#!/bin/bash

# ============================================
# UI Parallel Execution - Cross-Browser Tests
# ============================================

echo "==============================================="
echo "Starting Parallel UI Tests - Cross-Browser"
echo "==============================================="

# Run tests with 4 parallel threads
# Each thread will execute a different scenario
mvn clean test \
  -Dparallel=methods \
  -DthreadCount=4 \
  -DforkCount=1 \
  -Dcucumber.filter.tags="@UI" \
  -Dmaven.surefire.debug

echo "==============================================="
echo "Parallel UI Tests Completed"
echo "==============================================="

# Optional: Run specific browser tests
# mvn clean test -Dcucumber.filter.tags="@UI and @Chrome"
# mvn clean test -Dcucumber.filter.tags="@UI and @Firefox"
# mvn clean test -Dcucumber.filter.tags="@UI and @Edge"

