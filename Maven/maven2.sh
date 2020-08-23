#!/bin/sh
echo "shell scripts to demonstrate leveraging local copy of maven within the project"
eval "mvn help:evaluate -Dexpression=settings.localRepository -q -DforceStdout"
