#!/usr/bin/env bash
scriptDir=$(dirname $0)
java -jar "$scriptDir/dist/standalone.jar" "$@"
