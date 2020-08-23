#!/bin/sh
echo "shell scripts to list out the mvn goals available"
cd ../MavenUsingSample
eval "mvn help:describe -Dcmd=install"
