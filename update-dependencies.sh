#!/bin/bash

# Generate new lock files
./gradlew dependencies --write-locks

# Add dependency lock files to git so they can be committed
git add ./*.lockfile
