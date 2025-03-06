#!/usr/bin/env bash

# Script to upload an application file to BrowserStack App Automate
FILENAME=$1
APP_AUTOMATE_USER=$2
APP_AUTOMATE_KEY=$3

file=$(ls artifacts/app/*"$FILENAME")
echo "Uploading file: $file"
echo "User: $APP_AUTOMATE_USER"
echo "Key: $APP_AUTOMATE_KEY"
response=-$(curl -u "$APP_AUTOMATE_USER:$APP_AUTOMATE_KEY" \
  -X POST "https://api-cloud.browserstack.com/app-automate/upload" \
  -F "file=@$file")
app_url=$(echo "$response" | jq -r '.app_url')
echo "name=$app_url" >> "$GITHUB_OUTPUT"