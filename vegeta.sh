#!/bin/bash

RATE=${1:-100}
DUR=${2:-10}
echo "POST http://localhost:8080/transform" \
    | vegeta attack -rate=${RATE} -duration=${DUR}s \
        -header "Content-Type: application/vnd.gpa.v2+xml" \
        -body ~/Documents/work/psp/sample-messages/sample.xml \
    | tee build/results-${RATE}-${DUR}.bin \
    | vegeta report  \
    | tee build/results-${RATE}-${DUR}.report

cat build/results-${RATE}-${DUR}.bin | vegeta plot > build/plot-${RATE}-${DUR}.html
