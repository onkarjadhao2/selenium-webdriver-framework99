pipeline {
    agent any
    tools {
        maven 'maven-3.9.1'
    }
    stages {
        stage('Build') {
            steps {
                checkout scm
                bat 'mvn clean install -DskipTests'
            }
        }
        stage('Test') {
            environment {
                DISPLAY = ":0"
            }
            steps {
                bat 'mvn test'
            }
            post {
                always {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/surefire-reports']],
                    ])
                }
            }
        }
    }
}
