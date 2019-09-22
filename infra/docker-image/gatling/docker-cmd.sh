#!/usr/bin/env bash

set -exuo pipefail

env | sort

gatling.sh --run-description "${RUN_DESC:-Run at $(date)}"
