#!/usr/bin/env bash
scriptDir=$(dirname $0)
#java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005 -jar "$scriptDir/dist/standalone.jar" "$@"
java -jar "$scriptDir/dist/standalone.jar" "$@"
