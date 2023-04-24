# Set the base image to the official Maven image
FROM maven:3.8.5-jdk-11-slim

# Copy the entire project to the Docker image
COPY . /usr/src/CucumberTest

# Set the working directory to the project root
WORKDIR /usr/src/CucumberTest

# Install required packages
RUN apt-get update && \
    apt-get install -y wget gnupg2 curl unzip libglib2.0-0 libnss3 libgdk-pixbuf2.0-0 xvfb && \
    rm -rf /var/lib/apt/lists/*

# Install Google Chrome
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list && \
    apt-get update && \
    apt-get install -y google-chrome-stable && \
    rm -rf /var/lib/apt/lists/*

# Install the latest version of ChromeDriver
RUN CHROME_DRIVER_VERSION=`curl -sS chromedriver.storage.googleapis.com/LATEST_RELEASE` && \
    wget -q --continue -P /usr/local/bin/ "http://chromedriver.storage.googleapis.com/${CHROME_DRIVER_VERSION}/chromedriver_linux64.zip" && \
    unzip -q /usr/local/bin/chromedriver_linux64.zip -d /usr/local/bin/ && \
    rm /usr/local/bin/chromedriver_linux64.zip && \
    chmod +x /usr/local/bin/chromedriver

# Set environment variables
ENV DISPLAY=:99
ENV CHROME_BIN=/usr/bin/google-chrome
ENV CHROME_DRIVER=/usr/local/bin/chromedriver

# Install Allure command-line tool
RUN wget -O allure-2.20.1.tgz https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/2.20.1/allure-commandline-2.20.1.tgz && \
    tar -xzf allure-2.20.1.tgz && \
    rm allure-2.20.1.tgz && \
    ln -s /usr/src/CucumberTest/allure-2.20.1/bin/allure /usr/bin/allure
    
ENV PATH="/apache-maven-3.8.5/bin:${PATH}"

# Change the ownership of the project directory to the maven user
RUN chown -R root:root /usr/src/CucumberTest

# Install project dependencies
RUN mvn clean install -DskipTests

# Set the default command to run the test suite
CMD ["mvn", "test"]

