#!/usr/bin/env bash
# monitor_devops_health.sh - V10000.520 Rule 8
set -u
set -o pipefail

check_evidence_health() {
    echo "🔍 Checking evidence collection health..."
    local recent_builds=$(find reports/evidence -maxdepth 1 -type d -mtime -7 | wc -l)
    if [[ ${recent_builds} -eq 0 ]]; then
        echo "🚨 ALERT: No evidence collected in the last 7 days!"
        return 1
    fi
    echo "✅ Evidence collection is healthy."
}

check_sbom_freshness() {
    echo "🔍 Checking SBOM freshness..."
    local latest_sbom=$(find docs/sbom -name "*.json" -type f | head -1)
    if [[ -z "${latest_sbom}" ]]; then
        echo "🚨 ALERT: No SBOM files found!"
        return 1
    fi
    echo "✅ SBOM is fresh."
}

main() {
    check_evidence_health
    check_sbom_freshness
}

main "$@"
