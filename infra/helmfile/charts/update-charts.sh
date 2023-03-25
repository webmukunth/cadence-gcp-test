#!/usr/bin/env bash

helm repo update
#helm fetch --untar stable/grafana
helm fetch --untar grafana/grafana
#helm fetch --untar stable/prometheus
helm fetch --untar prometheus-community/prometheus
#helm fetch --untar stable/traefik
helm fetch --untar traefik/traefik
helm fetch --untar influxdata/influxdb

helm fetch --untar bitnami/cassandra
helm fetch --untar bitnami/mongodb
helm fetch --untar bitnami/redis

helm fetch --untar incubator/jaeger
helm fetch --untar incubator/raw

helm fetch --untar banzaicloud-stable/cadence
helm fetch --untar confluentinc/cp-helm-charts
cp -vr cp-helm-charts/charts/cp-kafka ./
cp -vr cp-helm-charts/charts/cp-zookeeper ./cp-kafka/charts/
