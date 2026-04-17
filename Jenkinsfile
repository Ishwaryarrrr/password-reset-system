pipeline {
    agent any

    environment {
        // Matches your exact screenshot ID
        DOCKER_CREDS = credentials('docker-cred')
        IMAGE_NAME = "Ishwarya21/password-reset"
    }

    tools {
        // Lowercase to match standard Jenkins configurations
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
                // FIXED: Changed 'sh' to 'bat' for Windows
                bat 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                // FIXED: Changed 'sh' to 'bat' and used double quotes for variables
                bat "docker build -t ${IMAGE_NAME}:latest ."
            }
        }

        stage('Push to Docker Hub') {
            steps {
                // FIXED: Windows-compatible docker login and push
                bat 'docker login -u %DOCKER_CREDS_USR% -p %DOCKER_CREDS_PSW%'
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