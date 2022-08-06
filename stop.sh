#!/bin/bash

CGVR_PID=$(ps -ef | grep java | grep cgvr | awk '{print $2}')

if [ -z "$CGVR_PID" ];
then
    echo "CGVR is not running"
else
    kill -9 $CGVR_PID
    echo "CGVR stopped."
fi
