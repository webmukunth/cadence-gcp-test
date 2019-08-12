#!/usr/bin/env bash -ex

kubectl get crd -o json | jq -Mr '.items[] | select(.spec.group == "monitoring.coreos.com") | .metadata.name' | while read CRD; do
    echo "Deleting CRD $CRD"
    kubectl delete crd $CRD
done
