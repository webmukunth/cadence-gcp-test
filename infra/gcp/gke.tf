provider "google" {
  version = "~> 2.12"
}

provider "google-beta" {
  version = "~> 2.12"
}

locals {
  project  = "cyanotype-508f8d"
  gke-name = "magneto-gke"
  location = "us-central1-c"
}

data "google_container_engine_versions" "gke-version" {
  project  = "${local.project}"
  location = "${local.location}"
}

resource "google_container_cluster" "gke" {
  provider = "google-beta"

  project                  = "${local.project}"
  name                     = "${local.gke-name}"
  location                 = "${local.location}"
  initial_node_count       = 1
  min_master_version       = "${data.google_container_engine_versions.gke-version.latest_master_version}"
  monitoring_service       = "monitoring.googleapis.com/kubernetes"
  logging_service          = "logging.googleapis.com/kubernetes"
  enable_kubernetes_alpha  = false
  enable_tpu               = false
  remove_default_node_pool = true
  enable_legacy_abac       = false

  master_auth {
    username = ""
    password = ""
    client_certificate_config {
      issue_client_certificate = false
    }
  }

  network_policy {
    # Enable network policy.  A default deny all policy is then configured as a local exec below
    enabled = true
  }

  addons_config {
    # https://cloud.google.com/kubernetes-engine/docs/how-to/hardening-your-cluster#disable_kubernetes_dashboard
    kubernetes_dashboard {
      # Disable dashboard
      disabled = true
    }

    # TODO Tune this
    # https://cloud.google.com/kubernetes-engine/docs/how-to/hardening-your-cluster#restrict_with_network_policy
    network_policy_config {
      # enable network policy
      disabled = false
    }

    http_load_balancing {
      # enable http load balancing
      disabled = false
    }

    horizontal_pod_autoscaling {
      # enable horizontal pod autoscaler
      disabled = false
    }
  }

  master_authorized_networks_config {
    cidr_blocks {
      cidr_block   = "39.109.192.0/18"
      display_name = "starhub"
    }
    cidr_blocks {
      cidr_block   = "156.13.70.0/23"
      display_name = "work wifi"
    }
    cidr_blocks {
      cidr_block   = "35.0.0.0/8"
      display_name = "cloudbuild"
    }
    cidr_blocks {
      cidr_block   = "34.0.0.0/8"
      display_name = "cloudbuild"
    }
  }

  lifecycle {
    ignore_changes = [
      # Ensure cluster is not recreated when pool configuration changes
      "node_pool",
      "initial_node_count",
      "network",
      "subnetwork",
    ]
  }

  timeouts {
    create = "15m"
    update = "15m"
    delete = "15m"
  }
}

resource "google_container_node_pool" "gke-np" {
  name     = "${google_container_cluster.gke.name}-np"
  project  = "${local.project}"
  location = "${local.location}"
  cluster  = "${google_container_cluster.gke.name}"

  management {
    auto_repair  = "true"
    auto_upgrade = "true"
  }

  initial_node_count = "1"

  autoscaling {
    min_node_count = "1"
    max_node_count = "3"
  }

  node_config {
    preemptible  = true
    machine_type = "n1-standard-2"

    metadata = {
      disable-legacy-endpoints = "true"
    }
    oauth_scopes = [
      "logging-write",
      "monitoring",
      "storage-ro"
    ]
  }

  timeouts {
    create = "15m"
    update = "15m"
    delete = "15m"
  }
}