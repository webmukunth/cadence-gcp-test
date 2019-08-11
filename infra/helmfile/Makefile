.PHONY: repos lint diff apply destroy 

HELMFILE_ENV ?= gke

SHELL:=bash
.ONESHELL:

all: diff

repos:
	helmfile repos

lint: repos
	helmfile --environment ${HELMFILE_ENV} lint --skip-deps

diff: lint
	helmfile --environment ${HELMFILE_ENV} diff --skip-deps

apply: lint
	time helmfile --environment ${HELMFILE_ENV} apply --skip-deps

destroy: 
	helmfile --environment ${HELMFILE_ENV} destroy
