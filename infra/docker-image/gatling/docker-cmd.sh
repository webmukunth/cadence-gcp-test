#!/usr/bin/env bash

set -exuo pipefail
echo "$(date) Started"
env | sort
gatling.sh --run-description "${RUN_DESC:-Run at $(date)}"
echo "$(date) Done"
