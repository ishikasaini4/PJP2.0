#!/bin/sh
echo "shell script to extract & save the build logs to a file"
cd ../MavenUsingSample
eval "mvn clean install > ../Maven/buildfile.txt"

