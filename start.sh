#!/bin/bash

JAR=cgvr-0.0.1-SNAPSHOT.jar
LOG=/home/researcher1/CGVR_Lab/cgvr_lab.log

nohup java -jar $JAR > $LOG 2>&1 &
