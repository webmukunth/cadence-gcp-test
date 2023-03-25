provider "google" {
  version = "~> 2.12"
}

provider "google-beta" {
  version = "~> 2.12"
}

locals {
  project  = "regolith-0a6bb2"
  gke-name = "temporal-gke"
  location = "us-west1-a"
}

data "google_container_engine_versions" "gke-version" {
  project  = local.project
  location = local.location
}

resource "google_container_cluster" "gke" {
  provider = "google-beta"

  project                  = local.project
  name                     = local.gke-name
  location                 = local.location
  initial_node_count       = 1
  min_master_version       = data.google_container_engine_versions.gke-version.latest_master_version
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
    enabled = false
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
      cidr_block   = "0.0.0.0/0"
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

  maintenance_policy {
    daily_maintenance_window {
      start_time = "19:00"
    }
  }

  lifecycle {
    ignore_changes = all
  }

  timeouts {
    create = "15m"
    update = "15m"
    delete = "15m"
  }
}

resource "google_container_node_pool" "gke-np" {
  provider = "google-beta"
  name     = "${google_container_cluster.gke.name}-np"
  project  = local.project
  location = local.location
  cluster  = google_container_cluster.gke.name

  management {
    auto_repair  = "false"
    auto_upgrade = "false"
  }

  initial_node_count = "3"

  node_config {
    preemptible  = true
    machine_type = "e2-standard-4"
    disk_size_gb = 50
    disk_type    = "pd-standard"

    metadata = {
      disable-legacy-endpoints = "true"
    }

    oauth_scopes = [
      "logging-write",
      "monitoring",
      "storage-ro"
    ]

    labels = {
      tier = "app"
    }
  }

  lifecycle {
    ignore_changes = [
      # Ensure cluster is not recreated when pool configuration changes
      "initial_node_count",
      "instance_group_urls",
      "node_config"
    ]
  }

  timeouts {
    create = "15m"
    update = "15m"
    delete = "15m"
  }
}

resource "google_container_node_pool" "gke-np2" {
  provider = "google-beta"
  name     = "${google_container_cluster.gke.name}-np2"
  project  = local.project
  location = local.location
  cluster  = google_container_cluster.gke.name

  management {
    auto_repair  = "false"
    auto_upgrade = "false"
  }

  initial_node_count = "2"

  node_config {
    preemptible  = true
    machine_type = "e2-standard-2"
    disk_size_gb = 50
    disk_type    = "pd-standard"

    metadata = {
      disable-legacy-endpoints = "true"
    }

    oauth_scopes = [
      "logging-write",
      "monitoring",
      "storage-ro"
    ]

    labels = {
      tier = "app"
    }
  }

  lifecycle {
    ignore_changes = [
      # Ensure cluster is not recreated when pool configuration changes
      "initial_node_count",
      "instance_group_urls",
      "node_config"
    ]
  }

  timeouts {
    create = "15m"
    update = "15m"
    delete = "15m"
  }
}

resource "google_container_node_pool" "gke-db-np" {
  provider = "google-beta"
  name     = "${google_container_cluster.gke.name}-db-np"
  project  = local.project
  location = local.location
  cluster  = google_container_cluster.gke.name

  management {
    auto_repair  = "false"
    auto_upgrade = "false"
  }

  initial_node_count = "1"

  node_config {
    preemptible  = true
    machine_type = "e2-highmem-8"
    disk_size_gb = 50
    disk_type    = "pd-standard"

    metadata = {
      disable-legacy-endpoints = "true"
    }

    oauth_scopes = [
      "logging-write",
      "monitoring",
      "storage-ro"
    ]

    labels = {
      tier = "db"
    }

    taint {
      effect = "NO_SCHEDULE"
      key    = "tier"
      value  = "db"
    }
  }

  lifecycle {
    ignore_changes = [
      # Ensure cluster is not recreated when pool configuration changes
      "initial_node_count",
      "instance_group_urls",
      "node_config"
    ]
  }

  timeouts {
    create = "15m"
    update = "15m"
    delete = "15m"
  }
}

data "google_client_config" "current" {}

provider "kubernetes" {
  # load_config_file       = false
  host                   = "https://${google_container_cluster.gke.endpoint}"
  cluster_ca_certificate = base64decode(google_container_cluster.gke.master_auth[0].cluster_ca_certificate)
  token                  = data.google_client_config.current.access_token
}

resource "kubernetes_cluster_role_binding" "make-me-admin" {
  metadata {
    name = "owner-gke-admin"
  }
  role_ref {
    api_group = "rbac.authorization.k8s.io"
    kind      = "ClusterRole"
    name      = "cluster-admin"
  }
  subject {
    api_group = "rbac.authorization.k8s.io"
    kind      = "User"
    name      = "webmukunth@gmail.com"
  }
}

resource "kubernetes_storage_class" "pd-ssd" {
  metadata {
    name = "pd-ssd"
  }
  storage_provisioner = "kubernetes.io/gce-pd"
  parameters = {
    type = "pd-ssd"
  }
}
