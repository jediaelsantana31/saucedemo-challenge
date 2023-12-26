#!/bin/bash

CHROME_VERSION=$(google-chrome --version | awk -F ' ' '{print $3}' | cut -d '.' -f 1)
echo "Detected Chrome Version: $CHROME_VERSION"

CHROMEDRIVER_VERSION=$(curl -s "https://chromedriver.storage.googleapis.com/LATEST_RELEASE_$CHROME_VERSION")
echo "Latest ChromeDriver Version: $CHROMEDRIVER_VERSION"

wget "https://chromedriver.storage.googleapis.com/$CHROMEDRIVER_VERSION/chromedriver_linux64.zip"
unzip chromedriver_linux64.zip -d $HOME
echo "PATH=$HOME:\$PATH" >> $GITHUB_ENV
