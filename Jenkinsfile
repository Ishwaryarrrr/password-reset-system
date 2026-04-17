pipeline {
    agent any

    environment {
        DOCKER_CREDS = credentials('docker-cred')
        IMAGE_NAME = "ishwarya21/password-reset"
    }

    tools {
        maven 'maven3' 
        jdk 'jdk17'
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                bat 'mvn clean package'
            }
        }

        // FIXED: We moved the login step here so Jenkins authenticates FIRST
        stage('Build Docker Image') {
            steps {
                bat 'docker login -u %DOCKER_CREDS_USR% -p %DOCKER_CREDS_PSW%'
                bat "docker build -t ${IMAGE_NAME}:latest ."
            }
        }

        stage('Push to Docker Hub') {
            steps {
                bat "docker push ${IMAGE_NAME}:latest"
            }
        }
    }
    
    post {
        always {
            echo 'Pipeline has finished executing.'
        }
        success {
            echo 'Deployment successful! Ready for Kubernetes.'
        }
        failure {
            echo 'Pipeline failed. Check the logs above.'
        }
    }
}