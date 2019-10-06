.PHONY: init get validate plan apply install destroy clean  all

SHELL:=bash
.ONESHELL:

all: plan

init:
	terraform init -input=false -reconfigure -no-color 

get: init
	terraform get -update -no-color

validate: get
	terraform fmt
	terraform validate -no-color

plan: validate
	terraform plan -no-color -input=false -lock=true -lock-timeout=5s -refresh=true -out=terraform.plan

apply: 
	terraform apply -no-color -lock=true -lock-timeout=5s terraform.plan
	gcloud container clusters get-credentials cadence-gke --zone us-west1-c

install: plan apply

destroy: init
	terraform destroy -no-color -lock=true -lock-timeout=5s -auto-approve

show: init
	terraform show

upgrade:
	terraform init -input=false -reconfigure -no-color -upgrade=true

