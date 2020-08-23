#!/bin/sh
echo "shell scripts to prevent maven from downloading dependencies every time / Make maven work without internet"
cd ../MavenUsingSample
eval "mvn -o clean install"
