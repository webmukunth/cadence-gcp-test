#!/bin/bash

set -euo pipefail

while true; do
  PROJECT_NAME=$(curl -s https://randomword.com/ | grep '<div id="random_word">' | sed 's|.*<div id="random_word">\(.*\)</div>.*|\1|g')
  [[ "$PROJECT_NAME" =~ ^.{6,10}$ ]] && break
  echo "Invalid project name $PROJECT_NAME"
done

RANDOM_SUFFIX=$(hexdump -n 3 -e '"%06X" 1 "\n"' /dev/random | tr '[:upper:]' '[:lower:]')
PROJECT_ID=${PROJECT_NAME}-${RANDOM_SUFFIX}

set -x
gcloud projects create ${PROJECT_ID} --name $PROJECT_NAME

PREV_PROJECT_NAME=$(gcloud config get-value project  2>/dev/null)
gcloud config set project $PROJECT_ID

# Billing
gcloud beta billing projects link $PROJECT_ID \
  --billing-account=$(gcloud beta billing  accounts list   --format="value(name)" --filter open=true)

# Enable the required services
gcloud --project $PROJECT_ID services enable iam.googleapis.com
gcloud --project $PROJECT_ID services enable cloudresourcemanager.googleapis.com
gcloud --project $PROJECT_ID services enable cloudbuild.googleapis.com
gcloud --project $PROJECT_ID services enable compute.googleapis.com
gcloud --project $PROJECT_ID services enable container.googleapis.com
gcloud --project $PROJECT_ID services enable pubsub.googleapis.com
gcloud --project $PROJECT_ID services enable storage-api.googleapis.com
gcloud --project $PROJECT_ID services enable servicenetworking.googleapis.com

# Delete default networks and firewall
#echo Y | gcloud --project $PROJECT_ID compute firewall-rules delete \
#  $(gcloud --project $PROJECT_ID compute firewall-rules list --format="value(name)")

#echo Y | gcloud --project $PROJECT_ID compute networks delete default

# Delete the previous project (only one active project in personal GCP account !)
echo Y | gcloud projects delete $PREV_PROJECT_NAME
