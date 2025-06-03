#!/bin/bash
set -e

CONFIG_FILE="/tmp/configure-datasource-resolved.cli"
MARKER_FILE="/tmp/datasource_configured"

echo "=========================================="
echo "🔧 Entrypoint körs – konfigurerar datasource"
echo "=========================================="

if [ ! -f "$MARKER_FILE" ]; then
  echo "📦 Datasource konfigureras..."
  echo "  🔹 DB_HOST: $DB_HOST"
  echo "  🔹 DB_PORT: $DB_PORT"
  echo "  🔹 DB_NAME: $DB_NAME"
  echo "  🔹 DS_NAME: $DS_NAME"
  echo "  🔹 DS_JNDI: $DS_JNDI"
  echo ""

  # Ersätt variabler i CLI-skriptet
  eval "cat <<EOF
$(cat /tmp/configure-datasource.cli)
EOF" > "$CONFIG_FILE"

  echo "📄 CLI-script som körs:"
  echo "-----------------------------"
  cat "$CONFIG_FILE"
  echo "-----------------------------"

  # Kör CLI-kommandot
  ${JBOSS_HOME}/bin/jboss-cli.sh --file="$CONFIG_FILE"

  # Markera att CLI har körts
  touch "$MARKER_FILE"
else
  echo "✅ Datasource redan konfigurerad – hoppar över."
fi

echo ""
echo "🚀 Startar WildFly..."
exec ${JBOSS_HOME}/bin/standalone.sh -b 0.0.0.0 "$@"
