pipeline {
    agent any

    environment {
        // Fixed to match your exact screenshot ID
        DOCKER_CREDS = credentials('docker-cred')
        IMAGE_NAME = "Ishwarya21/password-reset"
    }

    tools {
        // Switched to lowercase to match standard Jenkins configurations
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
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME:latest .'
            }
        }

        stage('Push to Docker Hub') {
            steps {
                sh 'echo $DOCKER_CREDS_PSW | docker login -u $DOCKER_CREDS_USR --password-stdin'
                sh 'docker push $IMAGE_NAME:latest'
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